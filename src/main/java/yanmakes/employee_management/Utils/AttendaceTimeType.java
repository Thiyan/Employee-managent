package yanmakes.employee_management.Utils;

public enum AttendaceTimeType {

    AR("Arrival"),
    DP("Departure");

    private String value;

    AttendaceTimeType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
