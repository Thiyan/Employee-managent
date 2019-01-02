package yanmakes.employee_management.Utils;

import org.springframework.stereotype.Component;
import yanmakes.employee_management.Exceptions.EMStatus;

@Component
public class EMResponse<T> {

    private String statusCode;
    private String statusDescription;
    private T content;

    public EMResponse(){
        this.statusCode= EMStatus.ERROR.getStatusCode();
        this.statusDescription=EMStatus.ERROR.getStatusDescription();

    }

    public EMResponse(String statusCode, String statusDescription){
        this.statusCode=statusCode;
        this.statusDescription=statusDescription;

    }

    public EMResponse(EMStatus agStatus) {
        this.statusCode = agStatus.getStatusCode();
        this.statusDescription = agStatus.getStatusDescription();
    }

    public EMResponse(T content) {
        this.statusCode = EMStatus.SUCCESS.getStatusCode();
        this.statusDescription = EMStatus.SUCCESS.getStatusDescription();
        this.content=content;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean hasContent(){
        return this.content!=null;
    }
}
