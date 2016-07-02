package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.FileEntity;

/**
 * Created by Ivan Mikho on 13.04.16.
 */
public interface FileDao extends JpaRepository<FileEntity, Long> {
}
