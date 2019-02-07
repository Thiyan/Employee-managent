package yanmakes.employee_management.Exceptions;

public enum EMStatus {

    SUCCESS("S1000", "Success"),

    ERROR("E1000", "Unknown error occurred in operation"),
    DB_ERROR("E1002", "Unknown error occurred in database operation"),
    NO_ENTRY_FOUND("E1003", "Empty results from database"),
    MISSING_REQUIRED_PARAMS("E1004", "One or more required parameters are missing"),
    EMPTY_FILE("E1005","Uploaded file is empty"),
    PARTIALLY_STORED1("E1006","Images are parlially uploaded.Unknown error occurred in database operation"),
    PARTIALLY_STORED2("E1007","Images are parlially uploaded.One or more required parameters are missing"),
    NO_ARRIVAL("E1008","Departure time can't create without arrival time and date" ),
    EXCEEDED_REQUESTE("E1009","Request days are greater than remaining leave days" ),
    ID_IS_REQUIRED("E1010","New objects cannot be allowed for PUT method" ),
    NO_REQUEST("E1011","Reply can't create without request" ),
    WRONG_PASSWORD("E1012","Current password is incorrect" ),
    WRONG_USER("E1013","Incorrect user id" );

    private final String statusCode;
    private final String statusDescription;


    EMStatus(String statusCode, String successDescription) {
        this.statusCode = statusCode;
        this.statusDescription = successDescription;
    }

    public static EMStatus getCCStatus(String statusCode) {
        for (EMStatus ccStatus : EMStatus.values()) {
            if (ccStatus.statusCode.equals(statusCode)) {
                return ccStatus;
            }
        }
        return SUCCESS;
    }


    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public boolean isSuccess() {
        return this.statusCode.equals(EMStatus.SUCCESS.statusCode);
    }
}
