package ua.greencampus.common;

/**
 * @author Mykola Yashchenko
 */
public interface Messages {
    String EMAIL_EMPTY = "email may not be empty";
    String PASSWORD_EMPTY = "password may not be empty";
    String PASSWORD_TOO_SHORT = "password size should be 5 or greater";
    String USER_ALREADY_EXISTS = "user with this email already exists";
    String PASSWORD_INCORRECT = "password incorrect";
    String COURSE_NOT_FOUND = "course not found";
    String THEME_NOT_FOUND = "theme not found";
    String FILE_PATH_EMPTY = "file path may not be empty";
    String THEME_NAME_EMPTY = "theme name may not be empty";
    String COURSE_TITLE_EMPTY = "course title may not be empty";
    String ID_INCORRECT = "id may not be empty or smaller than 0";
    String USER_DELETED = "user deleted successfully";
    String ACCESS_DENIED = "you do not have permissions to do this operation";
}
