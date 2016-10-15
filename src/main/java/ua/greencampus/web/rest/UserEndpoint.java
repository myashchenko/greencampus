package ua.greencampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.common.CheckAccess;
import ua.greencampus.common.Messages;
import ua.greencampus.common.CheckType;
import ua.greencampus.dto.*;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arsenii on 31.03.2016.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserEndpoint extends AbstractEndpoint {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserEndpoint(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByParams(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                      @RequestParam(value = "sort", defaultValue = "", required = false) String sort) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "page");
        if (page < 0 || size < 0) {
            bindingResult.rejectValue("bad_params", page < 0 ? "page" : "size" + "should be > 0");
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        List<User> userList = userService.getByParams(page, size, sort);
        List<UserDto> userDtos = userList.stream()
                .map(c -> map(c, UserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new EntityListResponse<>(userDtos));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        UserDto userDto = map(userService.read(id), UserDto.class);
        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserDto userDto) {
        User user = map(userDto, User.class);

        if (userService.getIdByEmail(user.getEmail()) != null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.putMessage(Messages.USER_ALREADY_EXISTS);
            return ResponseEntity.badRequest().body(baseResponse);
        }

        user.setPassword(userDto.getPassword());
        user.setRole(Role.ROLE_UNACTIVE);
        user = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EntityResponse<>(map(user, UserDto.class)));
    }

    @CheckAccess(type = CheckType.USER)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);

        User user = map(userDto, User.class);
        user = userService.update(user);
        userDto = map(user, UserDto.class);
        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }


    @PutMapping(value = "/pass/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updatePassword(@PathVariable("id") Long id,
                                                       @RequestBody PasswordDto passwordDto) {
        passwordDto.setId(id);
        User user = userService.read(id);
        if (passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(Messages.PASSWORD_INCORRECT));
        }
        userService.update(user);
        return ResponseEntity.ok(new BaseResponse());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new BaseResponse(Messages.USER_DELETED));
    }
}

