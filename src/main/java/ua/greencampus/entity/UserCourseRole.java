package ua.greencampus.entity;

/**
 * @author Ivan Mikho, created on 18.05.16.
 */
public enum UserCourseRole {
    CREATOR, PARTICIPANT;

    public static Role stringToEnum(String role) {
        for (Role r : Role.values()) {
            if (role.equals(r.name())) {
                return r;
            }
        }
        return null;
    }
}
