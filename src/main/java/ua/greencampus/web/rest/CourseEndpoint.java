package ua.greencampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.dto.*;
import ua.greencampus.entity.Course;
import ua.greencampus.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
@RestController
@RequestMapping(value = "/api/course")
public class CourseEndpoint {

    @Autowired
    @Qualifier("idValidator")
    private Validator courseIdValidator;

    @Autowired
    @Qualifier("courseDTOValidator")
    private Validator courseDTOValidator;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByParams(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                      @RequestParam(value = "sort", defaultValue = "", required = false) String sort) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "page");
        if (page < 0 || size < 0) {
            bindingResult.rejectValue("bad_param", page < 0 ? "page" : "size" + " must be > 0");
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        List<Course> courseList = courseService.getByParams(page, size, sort);

        List<CourseDto> courseDTOs = courseList.stream()
                .map(c -> conversionService.convert(c, CourseDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new EntityListResponse<>(courseDTOs));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        courseIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        CourseWithThemesDto courseDTO = conversionService.convert(courseService.readWithThemes(id), CourseWithThemesDto.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CourseDto courseDTO, BindingResult bindingResult) {
        courseDTOValidator.validate(courseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        Course course = conversionService.convert(courseDTO, Course.class);
        course = courseService.create(course);

        courseDTO = conversionService.convert(course, CourseDto.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody CourseDto courseDTO,
                                               BindingResult bindingResult) {
        courseDTO.setId(id);
        courseDTOValidator.validate(courseDTO, bindingResult);
        courseIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        Course course = conversionService.convert(courseDTO, Course.class);
        course = courseService.update(course);

        courseDTO = conversionService.convert(course, CourseDto.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        courseIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        Course course = courseService.read(id);
        if (course == null) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        CourseDto courseDTO = conversionService.convert(course, CourseDto.class);
        courseService.delete(course);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }
}
