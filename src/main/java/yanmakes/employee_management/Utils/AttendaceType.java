package yanmakes.employee_management.Utils;

public enum AttendaceType {

    HF("Half_day"),
    FL("Full_day"),
    AB("Absent");

    private String value;

    AttendaceType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
