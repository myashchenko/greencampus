package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.FileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
public class CourseThemeToCourseThemeDtoConverter implements Converter<CourseTheme, CourseThemeDto> {

    @Override
    public CourseThemeDto convert(CourseTheme theme) {
        if (theme == null) {
            return null;
        }
        CourseThemeDto themeDto = new CourseThemeDto();
        themeDto.setId(theme.getId());
        themeDto.setName(theme.getTitle());
        if (theme.getFiles().size() > 0) {
            List<String> filesString = new ArrayList<>();
            for (FileEntity file : theme.getFiles()) {
                filesString.add(file.getPath());
            }
            themeDto.setFiles(filesString);
        }

        return themeDto;
    }
}
