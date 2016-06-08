package ua.greencampus.entity;

/**
 * Created by Arsenii on 25.03.2016.
 */
public enum Role {
    ROLE_USER, ROLE_UNACTIVE, ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMIN;

    public static Role stringToEnum(String role) {
        for (Role r : Role.values()) {
            if (role.equals(r.name())) {
                return r;
            }
        }
        return null;
    }

}
