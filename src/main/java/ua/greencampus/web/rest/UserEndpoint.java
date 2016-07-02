package ua.greencampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.dto.*;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arsenii on 31.03.2016.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserEndpoint {

    @Autowired
    @Qualifier("userIdValidator")
    private Validator userIdValidator;

    @Autowired
    @Qualifier("userDtoValidator")
    private Validator userDtoValidator;

    @Autowired
    @Qualifier("emailDtoValidator")
    private Validator emailDtoValidator;

    @Autowired
    @Qualifier("passwordDtoValidator")
    private Validator passwordDtoValidator;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                .map(c -> conversionService.convert(c, UserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new EntityListResponse<>(userDtos));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        UserDto userDto = conversionService.convert(userService.read(id), UserDto.class);
        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody UserDto userDto, BindingResult bindingResult) {
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = conversionService.convert(userDto, User.class);

        if (userService.getIdByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body(new BaseResponse());
        }
        user.setPassword(userDto.getPassword());
        user = userService.create(user);
        userDto = conversionService.convert(user, UserDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EntityResponse<>(userDto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody UserDto userDto,
                                               BindingResult bindingResult) {
        userDto.setId(id);
        emailDtoValidator.validate(userDto, bindingResult);
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        User user = conversionService.convert(userDto, User.class);
        user = userService.update(user);
        userDto = conversionService.convert(user, UserDto.class);
        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }


    @PutMapping(value = "/pass/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updatePassword(@PathVariable("id") Long id,
                                                       @RequestBody PasswordDto passwordDto,
                                                       BindingResult bindingResult) {
        passwordDto.setId(id);
        passwordDtoValidator.validate(passwordDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = userService.read(id);
        if (passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        user = userService.update(user);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = userService.delete(id);
        UserDto userDto = conversionService.convert(user, UserDto.class);

        return ResponseEntity.ok(new EntityResponse<>(userDto));
    }
}

