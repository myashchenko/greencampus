package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDto;
import ua.greencampus.dto.UserDto;
import ua.greencampus.entity.User;

/**
 * @author Nikolay Yashchenko
 */
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    private ConversionService conversionService;

    public UserToUserDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UserDto convert(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole().name());
        if (user.getAvatar() != null) {
            FileDto avatarDTO = conversionService.convert(user.getAvatar(), FileDto.class);
            userDto.setAvatar(avatarDTO);
        }
        return userDto;
    }
}
