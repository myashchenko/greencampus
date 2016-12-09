package ua.greencampus.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.greencampus.listener.BaseEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@EntityListeners(BaseEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
