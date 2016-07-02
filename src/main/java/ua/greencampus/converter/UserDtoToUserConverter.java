package ua.greencampus.converter;


import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDto;
import ua.greencampus.dto.UserDto;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

/**
 * @author Nikolay Yashchenko
 */
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    private UserService userService;
    private ConversionService conversionService;

    public UserDtoToUserConverter(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = null;
        Long userId = userDto.getId();
        if (userId != null) {
            user = userService.read(userId);
        }

        if (user == null) {
            user = new User();
            user.setRole(Role.ROLE_UNACTIVE);
        } else if (userDto.getRole() != null) {
            user.setRole(Role.valueOf(userDto.getRole()));
        }
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        FileDto avatar = userDto.getAvatar();
        if (avatar != null) {
            FileEntity fileEntity = conversionService.convert(avatar, FileEntity.class);
            user.setAvatar(fileEntity);
        }

        return user;
    }
}
