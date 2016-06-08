package ua.greencampus.dto;

/**
 * @author Nikolay Yashchenko
 */
public class ErrorDTO {
    private String key;
    private String value;

    public ErrorDTO() {}

    public ErrorDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
