package ua.greencampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.dto.ChatDialogDto;
import ua.greencampus.dto.ChatMessageDto;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.ChatMessage;
import ua.greencampus.entity.User;
import ua.greencampus.service.AuthenticationService;
import ua.greencampus.service.ChatDialogService;
import ua.greencampus.service.ChatMessageService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class ChatController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatDialogService chatDialogService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    @Qualifier("chatMessageDTOValidator")
    private Validator chatMessageDTOValidator;

    @Autowired
    @Qualifier("idValidator")
    private Validator idValidator;

    @Autowired
    @Qualifier("chatDialogDTOValidator")
    private Validator chatDialogDTOValidator;

    private static final int THREAD_COUNT = 10;
    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    @InitBinder("chatMessageDTO")
    public void initChatMessageDTOValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(chatMessageDTOValidator);
    }

    @InitBinder("dialogId")
    public void initIdValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(idValidator);
    }

    @InitBinder("chatDialogDTO")
    public void initChatDialogDTOValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(chatDialogDTOValidator);
    }

    @GetMapping(value = "/chat")
    public String getChat() {
        return "chat";
    }

    @PostMapping(value = "/chat/new/{userId}")
    public String createChat(@PathVariable(value = "userId") Long userToId) {
        ChatDialog chatDialog;
        if ((chatDialog = chatDialogService.getByUserIds(userToId, authenticationService.getLoggedInUserId())) == null) {
            ChatDialogDto chatDialogDTO = new ChatDialogDto();
            chatDialogDTO.setUsersIds(Arrays.asList(userToId, authenticationService.getLoggedInUserId()));
            chatDialog = conversionService.convert(chatDialogDTO, ChatDialog.class);
            chatDialog = chatDialogService.create(chatDialog);
            chatDialog.prepareDialogName(userToId);
            chatDialog.prepareAvatarPath(userToId);
            chatDialogDTO = conversionService.convert(chatDialog, ChatDialogDto.class);

            simpMessagingTemplate.convertAndSend("/dialog/" + userToId + "/new", chatDialogDTO);

        }
        return "redirect:/chat#" + chatDialog.getId();
    }

    @MessageMapping("/ws/chat/dialogs/{userId}")
    public void getDialogs(@Validated @DestinationVariable(value = "userId") Long userId) {
        List<ChatDialog> chatDialogs = chatDialogService.getByUserId(userId);

        List<ChatDialogDto> result = chatDialogs.stream()
                .map(c -> c.prepareDialogName(userId))
                .map(c -> c.prepareAvatarPath(userId))
                .map(d -> conversionService.convert(d, ChatDialogDto.class))
                .collect(Collectors.toList());

        simpMessagingTemplate.convertAndSend("/dialogs/" + userId, result);
    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/{userId}")
    public void getChatMessages(@Validated @DestinationVariable(value = "dialogId") Long dialogId,
                                @Validated @DestinationVariable(value = "userId") Long userId) {
        List<ChatMessage> messages = chatMessageService.getByDialogId(dialogId);
        List<ChatMessageDto> result = messages.stream()
                .map(m -> conversionService.convert(m, ChatMessageDto.class))
                .collect(Collectors.toList());

        simpMessagingTemplate.convertAndSend("/dialog/" + dialogId + "/" + userId, result);
    }

    @MessageMapping("/ws/chat/dialog/new")
    public void createDialog(@Validated ChatDialogDto chatDialogDTO) {
        ChatDialog chatDialog = conversionService.convert(chatDialogDTO, ChatDialog.class);
        chatDialog = chatDialogService.create(chatDialog);

        for (User user : chatDialog.getUsers()) {
            final ChatDialog finalChatDialog = chatDialog;
            executorService.execute(() -> {
                finalChatDialog.prepareDialogName(user.getId());
                finalChatDialog.prepareAvatarPath(user.getId());
                ChatDialogDto chatDialogDTOTemp = conversionService.convert(finalChatDialog, ChatDialogDto.class);

                simpMessagingTemplate.convertAndSend("/dialogs/" + user.getId() + "/new", chatDialogDTOTemp);
            });
        }
    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/new/{userId}")
    public void handleNewMessage(@Validated @DestinationVariable(value = "dialogId") Long dialogId,
                                 @Validated @DestinationVariable(value = "userId") Long userId,
                                 ChatMessageDto chatMessageDTO) {
        chatMessageDTO.setUserFromId(userId);
        ChatMessage chatMessage = conversionService.convert(chatMessageDTO, ChatMessage.class);
        chatMessage = chatMessageService.create(chatMessage);
        ChatDialog dialog = chatMessage.getDialog();
        dialog.getUsers().stream()
                .map(User::getId)
                .filter(id -> !id.equals(userId))
                .forEach(dialog::incrementUnreadCount);
        dialog.setUpdateDate(Calendar.getInstance());
        dialog = chatDialogService.update(dialog);

        chatMessageDTO = conversionService.convert(chatMessage, ChatMessageDto.class);

        for (User user : dialog.getUsers()) {
            final ChatMessageDto finalChatMessageDTO = chatMessageDTO;
            executorService.execute(() -> {
                String url = String.format("/dialog/%s/new/%s", dialogId, user.getId());
                simpMessagingTemplate.convertAndSend(url, finalChatMessageDTO);

                // send to header
                String headerUrl = String.format("/dialog/unreadCount/%s/new", user.getId());
                simpMessagingTemplate.convertAndSend(headerUrl, 1);
            });
        }
    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/read/{userId}")
    public void readDialog(@Validated @DestinationVariable(value = "dialogId") Long dialogId,
                           @Validated @DestinationVariable(value = "userId") Long userId) {
        ChatDialog chatDialog = chatDialogService.read(dialogId);
        chatDialog.decrementUnreadCount(userId);
        chatDialogService.update(chatDialog);
    }

    @MessageMapping("/ws/chat/dialog/unreadCount/{userId}")
    public void getUnreadCount(@Validated @DestinationVariable(value = "userId") Long userId) {
        simpMessagingTemplate.convertAndSend("/dialog/unreadCount/" + userId, chatDialogService.getUnreadCount(userId));
    }
}
