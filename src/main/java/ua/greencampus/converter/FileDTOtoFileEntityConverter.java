package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDTO;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.service.FileService;


/**
 * Created by Arsenii on 18.04.2016.
 */
public class FileDTOtoFileEntityConverter implements Converter<FileDTO, FileEntity> {

    private FileService fileService;

    public FileDTOtoFileEntityConverter(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public FileEntity convert(FileDTO fileDTO) {
        if (fileDTO.getPath() == null) return null;
        FileEntity file = null;
        Long fileId = fileDTO.getId();
        if (fileId != null) {
            file = fileService.read(fileId);
        }

        if (file == null) {
            file = new FileEntity();
        }

        file.setName(fileDTO.getName());
        file.setPath(fileDTO.getPath());

        return file;
    }
}
