package ua.greencampus.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.greencampus.common.Messages;
import ua.greencampus.dto.BaseResponse;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.dto.EntityResponse;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.service.CourseService;
import ua.greencampus.service.CourseThemeService;

/**
 * Created by Ivan Mikho on 10.04.16.
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(value = "/api/theme")
public class ThemeEndpoint extends AbstractEndpoint {

    private CourseThemeService themeService;
    private CourseService courseService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> read(@PathVariable("id") Long id) {
        CourseThemeDto themeDto = map(themeService.read(id), CourseThemeDto.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDto));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CourseThemeDto themeDto,
                                               @RequestParam(name = "courseId") Long courseId) {
        CourseTheme theme = map(themeDto, CourseTheme.class);
        Course course = courseService.read(courseId);
        course.getThemes().add(theme);
        courseService.update(course);
        themeDto = map(theme, CourseThemeDto.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@PathVariable("id") Long id, @RequestBody CourseThemeDto themeDto) {
        themeDto.setId(id);
        CourseTheme theme = map(themeDto, CourseTheme.class);
        theme = themeService.update(theme);
        themeDto = map(theme, CourseThemeDto.class);
        return ResponseEntity.ok(new EntityResponse<>(themeDto));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
        CourseTheme theme = themeService.read(id);
        if (theme == null) {
            return ResponseEntity.badRequest().body(new BaseResponse(Messages.THEME_NOT_FOUND));
        }
        CourseThemeDto themeDto = map(theme, CourseThemeDto.class);
        themeService.delete(theme);
        return ResponseEntity.ok(new EntityResponse<>(themeDto));
    }
}
