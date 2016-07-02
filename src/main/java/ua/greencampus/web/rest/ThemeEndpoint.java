package ua.greencampus.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.dto.BaseResponse;
import ua.greencampus.dto.CourseDTO;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.dto.EntityResponse;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.service.CourseService;
import ua.greencampus.service.CourseThemeService;
import ua.greencampus.validator.CourseIdValidator;
import ua.greencampus.validator.ThemeDTOValidator;
import ua.greencampus.validator.ThemeIdValidator;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivan Mikho on 10.04.16.
 */
@RestController
@RequestMapping(value = "/api/theme")
public class ThemeEndpoint {

    @Autowired
    @Qualifier("themeIdValidator")
    ThemeIdValidator themeIdValidator;

    @Autowired
    @Qualifier("themeDTOValidator")
    ThemeDTOValidator themeDTOValidator;

    @Autowired
    @Qualifier("courseIdValidator")
    CourseIdValidator courseIdValidator;

    @Autowired
    ConversionService conversionService;

    @Autowired
    CourseThemeService themeService;

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "id");
        themeIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }

        CourseThemeDTO themeDTO = conversionService.convert(themeService.read(id), CourseThemeDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDTO));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CourseThemeDTO themeDTO,
                                               @RequestParam(name = "courseId", required = true) Long courseId,
                                               BindingResult bindingResult){
        themeDTOValidator.validate(themeDTO, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        CourseTheme theme = conversionService.convert(themeDTO, CourseTheme.class);
        Course course = courseService.read(courseId);
        List<CourseTheme> themes = course.getThemes();
        themes.add(theme);
        course = courseService.update(course);
        themeDTO = conversionService.convert(theme, CourseThemeDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody CourseThemeDTO themeDTO,
                                               BindingResult bindingResult){
        themeDTO.setId(id);
        themeDTOValidator.validate(themeDTO, bindingResult);
        themeIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        CourseTheme theme = conversionService.convert(themeDTO, CourseTheme.class);
        theme = themeService.update(theme);
        themeDTO = conversionService.convert(theme, CourseThemeDTO.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDTO));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id, BindingResult bindingResult) {
        themeIdValidator.validate(id, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        CourseTheme theme = themeService.read(id);
        if (theme == null){
            return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
        }
        CourseThemeDTO themeDTO = conversionService.convert(theme, CourseThemeDTO.class);
        themeService.delete(theme);
        return ResponseEntity.ok(new EntityResponse<>(themeDTO));
    }
}