package ua.greencampus.converter;


import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDTO;
import ua.greencampus.dto.UserDTO;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

/**
 * @author Nikolay Yashchenko
 */
public class UserDTOtoUserConverter implements Converter<UserDTO, User> {

    private UserService userService;
    private ConversionService conversionService;

    public UserDTOtoUserConverter(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @Override
    public User convert(UserDTO userDTO) {
        User user = null;
        Long userId = userDTO.getId();
        if (userId != null) {
            user = userService.read(userId);
        }

        if (user == null) {
            user = new User();
            user.setRole(Role.ROLE_UNACTIVE);
        } else if (userDTO.getRole() != null) {
            user.setRole(Role.stringToEnum(userDTO.getRole()));
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        FileDTO avatar = userDTO.getAvatar();
        if (avatar != null) {
            FileEntity fileEntity = conversionService.convert(avatar, FileEntity.class);
            user.setAvatar(fileEntity);
        }

        return user;
    }
}
