package ua.greencampus.service;

import ua.greencampus.entity.FileEntity;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
public interface FileService {
    FileEntity create(FileEntity fileEntity);
    FileEntity read(Long id);
    FileEntity update(FileEntity fileEntity);
    void delete(FileEntity fileEntity);
    FileEntity delete(Long id);
}
