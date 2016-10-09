package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

/**
 * Created by Arsenii on 18.04.2016.
 */
@Getter
@Setter
public class FileDto {
    private Long id;
    @NotEmpty(message = Messages.FILE_PATH_EMPTY)
    private String path;
    private String name;

    public FileDto(Long id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    // hibernate need
    public FileDto() {
    }
}
