package ua.greencampus.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.FileEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
@Repository
public class FileDaoImpl implements FileDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FileEntity create(FileEntity fileEntity) {
        entityManager.persist(fileEntity);
        return fileEntity;
    }

    @Override
    public FileEntity read(Long id) {
        return entityManager.find(FileEntity.class,id);
    }

    @Override
    public FileEntity update(FileEntity fileEntity) {
        return entityManager.merge(fileEntity);
    }

    @Override
    public void delete(FileEntity fileEntity) {
        entityManager.remove(fileEntity);
    }

    @Override
    public FileEntity read(String path) {
        return (FileEntity) entityManager.unwrap(Session.class).createCriteria(FileEntity.class)
                .add(Restrictions.eq("path", path))
                .uniqueResult();
    }
}
