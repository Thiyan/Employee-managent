package yanmakes.employee_management.Exceptions;

public class EMException extends Exception {

    private static final long serialVersionUID = 1L;

    private EMStatus status;

    public EMException(EMStatus status){

        super(status.getStatusDescription());
        this.status=status;
    }

    public EMStatus getStatus() {
        return status;
    }

    public void setStatus(EMStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EMException{" +
                "status=" + status +
                '}';
    }
}
