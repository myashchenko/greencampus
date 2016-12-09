package ua.greencampus.common;

import ua.greencampus.entity.User;

/**
 * @author Mykola Yashchenko
 */
public enum CheckType {
    USER(User.class, "getId"),

    UNDEFINED(null, null);

    private Class<?> tClass;
    private String fieldToCheck;

    CheckType(Class<?> tClass, String idField) {
        this.tClass = tClass;
        this.fieldToCheck = idField;
    }

    public Class<?> getEntityClass() {
        return tClass;
    }

    public String getMethod() {
        return fieldToCheck;
    }
}
