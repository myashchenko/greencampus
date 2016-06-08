package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.FileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
public class CourseThemeToCourseThemeDTOConverter implements Converter<CourseTheme, CourseThemeDTO> {

    @Override
    public CourseThemeDTO convert(CourseTheme theme){
        if (theme == null) {
            return null;
        }
        CourseThemeDTO themeDTO = new CourseThemeDTO();
        themeDTO.setId(theme.getId());
        themeDTO.setName(theme.getTitle());
        if (theme.getFiles().size() > 0){
            List<String> filesString = new ArrayList<>();
            for (FileEntity file:theme.getFiles()){
                filesString.add(file.getPath());
            }
            themeDTO.setFiles(filesString);
        }

        return themeDTO;
    }
}
