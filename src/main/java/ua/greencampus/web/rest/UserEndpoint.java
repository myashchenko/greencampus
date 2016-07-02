package ua.greencampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
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
    @Qualifier("userDTOValidator")
    private Validator userDTOValidator;

    @Autowired
    @Qualifier("emailDTOValidator")
    private Validator emailDTOValidator;

    @Autowired
    @Qualifier("passwordDTOValidator")
    private Validator passwordDTOValidator;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByParams(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                      @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                      @RequestParam(value = "sort", defaultValue = "", required = false) String sort) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "offset");
        if (offset < 0 || size < 0) {
            bindingResult.rejectValue("bad_params", offset < 0 ? "offset" : "size" + "should be > 0");
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        List<User> userList = userService.getByParams(offset, size, sort);
        List<UserDTO> userDTOs = userList.stream()
                .map(c -> conversionService.convert(c, UserDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new EntityListResponse<>(userDTOs));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        UserDTO userDTO = conversionService.convert(userService.read(id), UserDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(userDTO));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        userDTOValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = conversionService.convert(userDTO, User.class);

        if (userService.getIdByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body(new BaseResponse());
        }
        user.setPassword(userDTO.getPassword());
        user = userService.create(user);
        userDTO = conversionService.convert(user, UserDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(userDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO,
                                               BindingResult bindingResult) {
        userDTO.setId(id);
        emailDTOValidator.validate(userDTO, bindingResult);
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        User user = conversionService.convert(userDTO, User.class);
        user = userService.update(user);
        userDTO = conversionService.convert(user, UserDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(userDTO));
    }


    @PutMapping(value = "/pass/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updatePassword(@PathVariable("id") Long id,
                                                       @RequestBody PasswordDTO passwordDTO,
                                                       BindingResult bindingResult) {
        passwordDTO.setId(id);
        passwordDTOValidator.validate(passwordDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = userService.read(id);
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        user = userService.update(user);
        UserDTO userDTO = conversionService.convert(user, UserDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(userDTO));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        userIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        User user = userService.delete(id);
        UserDTO userDTO = conversionService.convert(user, UserDTO.class);

        return ResponseEntity.ok(new EntityResponse<>(userDTO));
    }
}

