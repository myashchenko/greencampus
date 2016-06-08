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

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByParams(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                      @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                      @RequestParam(value = "sort", defaultValue = "", required = false) String sort) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "offset");
        if (offset < 0 || size < 0) {
            bindingResult.rejectValue("bad_param", offset < 0 ? "offset" : "size" + " must be > 0");
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        List<Course> courseList = courseService.getByParams(offset, size, sort);

        List<CourseDTO> courseDTOs = courseList.stream()
                .map(c -> conversionService.convert(c, CourseDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new EntityListResponse<>(courseDTOs));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        courseIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        CourseWithThemesDTO courseDTO = conversionService.convert(courseService.readWithThemes(id), CourseWithThemesDTO.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CourseDTO courseDTO, BindingResult bindingResult) {
        courseDTOValidator.validate(courseDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        Course course = conversionService.convert(courseDTO, Course.class);
        course = courseService.create(course);

        courseDTO = conversionService.convert(course, CourseDTO.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id,
                                               @RequestBody CourseDTO courseDTO, BindingResult bindingResult) {
        courseDTO.setId(id);
        courseDTOValidator.validate(courseDTO, bindingResult);
        courseIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        Course course = conversionService.convert(courseDTO, Course.class);
        course = courseService.update(course);

        courseDTO = conversionService.convert(course, CourseDTO.class);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
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

        CourseDTO courseDTO = conversionService.convert(course, CourseDTO.class);
        courseService.delete(course);

        return ResponseEntity.ok(new EntityResponse<>(courseDTO));
    }
}
