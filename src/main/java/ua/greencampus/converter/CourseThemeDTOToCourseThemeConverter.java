package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.service.CourseThemeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 12.04.16.
 */
public class CourseThemeDTOToCourseThemeConverter implements Converter<CourseThemeDTO, CourseTheme> {
    private CourseThemeService themeService;

    public CourseThemeDTOToCourseThemeConverter(CourseThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public CourseTheme convert(CourseThemeDTO themeDTO) {
        CourseTheme theme = null;
        Long themeId = themeDTO.getId();
        if (themeId != null) {
            theme = themeService.read(themeId);
        }

        if (theme == null) {
            theme = new CourseTheme();
        }
        if (!themeDTO.getFiles().isEmpty()) {
            List<String> filesString = themeDTO.getFiles();
            List<FileEntity> filesEntity = new ArrayList<>();
            for (String fileString : filesString) {
                FileEntity file = new FileEntity();
                file.setPath(fileString);
                filesEntity.add(file);
            }
            theme.setFiles(filesEntity);
        }

        theme.setTitle(themeDTO.getName());

        return theme;
    }

}
