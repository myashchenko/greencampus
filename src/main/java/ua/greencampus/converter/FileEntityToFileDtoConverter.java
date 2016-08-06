package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDto;
import ua.greencampus.entity.FileEntity;

/**
 * Created by Arsenii on 18.04.2016.
 */
public class FileEntityToFileDtoConverter implements Converter<FileEntity, FileDto> {

    @Override
    public FileDto convert(FileEntity file) {
        if (file == null) {
            return null;
        }
        return new FileDto(file.getId(), file.getPath(), file.getName());
    }

}
