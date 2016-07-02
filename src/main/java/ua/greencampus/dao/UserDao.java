package ua.greencampus.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ua.greencampus.entity.User;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    List<Object> findIdByEmail(@Param("email") String email);
}
