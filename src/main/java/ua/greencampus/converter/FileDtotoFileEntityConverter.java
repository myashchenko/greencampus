package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDto;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.service.FileService;


/**
 * Created by Arsenii on 18.04.2016.
 */
public class FileDtotoFileEntityConverter implements Converter<FileDto, FileEntity> {

    private FileService fileService;

    public FileDtotoFileEntityConverter(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public FileEntity convert(FileDto fileDto) {
        if (fileDto.getPath() == null) return null;
        FileEntity file = null;
        Long fileId = fileDto.getId();
        if (fileId != null) {
            file = fileService.read(fileId);
        }

        if (file == null) {
            file = new FileEntity();
        }

        file.setName(fileDto.getName());
        file.setPath(fileDto.getPath());

        return file;
    }
}
