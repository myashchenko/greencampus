package ua.greencampus.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ua.greencampus.entity.User;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface UserDao extends PagingAndSortingRepository<User, String> {
    User findByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    List<Object> findIdByEmail(@Param("email") String email);

    @Query("SELECT u.role FROM User u WHERE u.email = :email")
    List<Object> findRoleByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePassword(@Param("id") String userId, @Param("password") String newPassword);
}
