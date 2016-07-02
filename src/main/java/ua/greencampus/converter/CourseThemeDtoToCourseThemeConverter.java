package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.service.CourseThemeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 12.04.16.
 */
public class CourseThemeDtoToCourseThemeConverter implements Converter<CourseThemeDto, CourseTheme> {
    private CourseThemeService themeService;

    public CourseThemeDtoToCourseThemeConverter(CourseThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public CourseTheme convert(CourseThemeDto themeDto) {
        CourseTheme theme = null;
        Long themeId = themeDto.getId();
        if (themeId != null) {
            theme = themeService.read(themeId);
        }

        if (theme == null) {
            theme = new CourseTheme();
        }
        if (!themeDto.getFiles().isEmpty()) {
            List<String> filesString = themeDto.getFiles();
            List<FileEntity> filesEntity = new ArrayList<>();
            for (String fileString : filesString) {
                FileEntity file = new FileEntity();
                file.setPath(fileString);
                filesEntity.add(file);
            }
            theme.setFiles(filesEntity);
        }

        theme.setTitle(themeDto.getName());

        return theme;
    }

}
