package yanmakes.employee_management.Utils;

public enum Gender {

    M("Male"),
    F("Female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
