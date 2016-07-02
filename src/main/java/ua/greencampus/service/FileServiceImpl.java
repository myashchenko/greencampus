package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.FileDao;
import ua.greencampus.entity.FileEntity;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDao fileDao;

    @Transactional
    @Override
    public FileEntity create(FileEntity fileEntity) {
        return fileDao.save(fileEntity);
    }

    @Transactional
    @Override
    public FileEntity read(Long id) {
        return fileDao.findOne(id);
    }

    @Transactional
    @Override
    public FileEntity update(FileEntity fileEntity) {
        return fileDao.save(fileEntity);
    }

    @Transactional
    @Override
    public void delete(FileEntity fileEntity) {
        fileDao.delete(fileEntity);
    }

    @Transactional
    @Override
    public FileEntity delete(Long id) {
        FileEntity fileEntity = fileDao.findOne(id);
        delete(fileEntity);
        return fileEntity;
    }
}
