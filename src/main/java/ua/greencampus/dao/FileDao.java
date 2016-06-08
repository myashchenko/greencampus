package ua.greencampus.dao;

import ua.greencampus.entity.FileEntity;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
public interface FileDao {
    FileEntity create(FileEntity fileEntity);
    FileEntity read(Long id);
    FileEntity update(FileEntity fileEntity);
    void delete(FileEntity fileEntity);
    FileEntity read(String path);
}
