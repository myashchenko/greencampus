package ua.greencampus.web;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
import ua.greencampus.service.UserService;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class ChatController {

    private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatDialogService chatDialogService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    @Qualifier("chatMessageDtoValidator")
    private Validator chatMessageDtoValidator;

    @Autowired
    @Qualifier("idValidator")
    private Validator idValidator;

    @Autowired
    @Qualifier("chatDialogDtoValidator")
    private Validator chatDialogDtoValidator;

    private static final int THREAD_COUNT = 10;
    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    @InitBinder("chatMessageDto")
    public void initChatMessageDtoValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(chatMessageDtoValidator);
    }

    @InitBinder("dialogId")
    public void initIdValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(idValidator);
    }

    @InitBinder("chatDialogDto")
    public void initChatDialogDtoValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(chatDialogDtoValidator);
    }

    @GetMapping(value = "/chat")
    public String getChat() {
        return "chat";
    }

    @PostMapping(value = "/chat/new/{userId}")
    public String createChat(@PathVariable(value = "userId") String userToId) {
        ChatDialog chatDialog;
        if ((chatDialog = chatDialogService.getByUserIds(userToId, authenticationService.getLoggedInUserId())) == null) {
            chatDialog = new ChatDialog();
            chatDialog.setUsers(new LinkedHashSet<>(Arrays.asList(userService.read(userToId),
                    userService.read(authenticationService.getLoggedInUserId()))));
            chatDialog = chatDialogService.create(chatDialog);
            chatDialog.prepareDialogName(userToId);
            chatDialog.prepareAvatarPath(userToId);

            simpMessagingTemplate.convertAndSend("/dialog/" + userToId + "/new", mapperFacade.map(chatDialog, ChatDialogDto.class));

        }
        return "redirect:/chat#" + chatDialog.getId();
    }

    @MessageMapping("/ws/chat/dialogs/{userId}")
    public void getDialogs(@Validated @DestinationVariable(value = "userId") String userId) {
        List<ChatDialog> chatDialogs = chatDialogService.getByUserId(userId);

        List<ChatDialogDto> result = chatDialogs.stream()
                .map(c -> c.prepareDialogName(userId))
                .map(c -> c.prepareAvatarPath(userId))
                .map(d -> mapperFacade.map(d, ChatDialogDto.class))
                .collect(Collectors.toList());

        simpMessagingTemplate.convertAndSend("/dialogs/" + userId, result);
    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/{userId}")
    public void getChatMessages(@Validated @DestinationVariable(value = "dialogId") String dialogId,
                                @Validated @DestinationVariable(value = "userId") String userId) {
        List<ChatMessage> messages = chatMessageService.getByDialogId(dialogId);
        List<ChatMessageDto> result = messages.stream()
                .map(m -> mapperFacade.map(m, ChatMessageDto.class))
                .collect(Collectors.toList());

        simpMessagingTemplate.convertAndSend("/dialog/" + dialogId + "/" + userId, result);
    }

//    @MessageMapping("/ws/chat/dialog/new")
//    public void createDialog(@Validated ChatDialogDto chatDialogDto) {
//        ChatDialog chatDialog = conversionService.convert(chatDialogDto, ChatDialog.class);
//        chatDialog = chatDialogService.create(chatDialog);
//
//        for (User user : chatDialog.getUsers()) {
//            final ChatDialog finalChatDialog = chatDialog;
//            executorService.execute(() -> {
//                finalChatDialog.prepareDialogName(user.getId());
//                finalChatDialog.prepareAvatarPath(user.getId());
//                ChatDialogDto chatDialogDtoTemp = conversionService.convert(finalChatDialog, ChatDialogDto.class);
//
//                simpMessagingTemplate.convertAndSend("/dialogs/" + user.getId() + "/new", chatDialogDtoTemp);
//            });
//        }
//    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/new/{userId}")
    public void handleNewMessage(@Validated @DestinationVariable(value = "dialogId") String dialogId,
                                 @Validated @DestinationVariable(value = "userId") String userId,
                                 ChatMessageDto chatMessageDto) {
        chatMessageDto.setUserFromId(userId);
        ChatMessage chatMessage = mapperFacade.map(chatMessageDto, ChatMessage.class);
        chatMessage = chatMessageService.create(chatMessage);
        ChatDialog dialog = chatMessage.getDialog();
        dialog.getUsers().stream()
                .map(User::getId)
                .filter(id -> !id.equals(userId))
                .forEach(dialog::incrementUnreadCount);
        dialog = chatDialogService.update(dialog);

        chatMessageDto = mapperFacade.map(chatMessage, ChatMessageDto.class);

        for (User user : dialog.getUsers()) {
            final ChatMessageDto finalChatMessageDto = chatMessageDto;
            executorService.execute(() -> {
                String url = String.format("/dialog/%s/new/%s", dialogId, user.getId());
                simpMessagingTemplate.convertAndSend(url, finalChatMessageDto);

                // send to header
                String headerUrl = String.format("/dialog/unreadCount/%s/new", user.getId());
                simpMessagingTemplate.convertAndSend(headerUrl, 1);
            });
        }
    }

    @MessageMapping("/ws/chat/dialog/{dialogId}/read/{userId}")
    public void readDialog(@Validated @DestinationVariable(value = "dialogId") String dialogId,
                           @Validated @DestinationVariable(value = "userId") String userId) {
        ChatDialog chatDialog = chatDialogService.read(dialogId);
        chatDialog.decrementUnreadCount(userId);
        chatDialogService.update(chatDialog);
    }

    @MessageMapping("/ws/chat/dialog/unreadCount/{userId}")
    public void getUnreadCount(@Validated @DestinationVariable(value = "userId") String userId) {
        simpMessagingTemplate.convertAndSend("/dialog/unreadCount/" + userId, chatDialogService.getUnreadCount(userId));
    }
}
