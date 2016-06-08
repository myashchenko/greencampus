package ua.greencampus.converter;


import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.FileDTO;
import ua.greencampus.entity.FileEntity;


/**
 * Created by Arsenii on 18.04.2016.
 */
public class FileEntityToFileDTOConverter implements Converter<FileEntity, FileDTO> {

    @Override
    public FileDTO convert(FileEntity file) {
        if (file == null){
            return null;
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setName(file.getName());
        fileDTO.setPath(file.getPath());

        return fileDTO;
    }

}
