package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDTO;
import ua.greencampus.dto.UserDTO;
import ua.greencampus.entity.User;

/**
 * @author Nikolay Yashchenko
 */
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    private ConversionService conversionService;

    public UserToUserDTOConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UserDTO convert(User user) {
        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole().name());
        if (user.getAvatar() != null) {
            FileDTO avatarDTO = conversionService.convert(user.getAvatar(), FileDTO.class);
            userDTO.setAvatar(avatarDTO);
        }
        return userDTO;
    }
}
