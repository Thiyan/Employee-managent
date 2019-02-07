package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "leave_request")
public class LeaveRequest implements Serializable {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_by")
    private Employee requestedBy;

    @Column(name = "requested_date")
    private String requestedDate;

//    @ElementCollection
//    @CollectionTable(
//            name="leave_dates",
//            joinColumns=@JoinColumn(name="request_id")
//    )
    @Column(name = "date")
    private String date;

    @Column(name = "reason")
    private String reason;

    @Column(name = "is_checked")
    private boolean checked;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;

    @Column(name = "is_approved")
    private boolean approved;

    @Column(name = "reply")
    private String reply;


    public LeaveRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Employee getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Employee requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "requestId=" + requestId +
                ", requestedBy=" + requestedBy +
                ", requestedDate=" + requestedDate +
                ", dates=" + date +
                ", reason='" + reason + '\'' +
                ", approved=" + approved +
                ", reply='" + reply + '\'' +
                '}';
    }

    public boolean isValid(){
        return this.requestedBy!=null && !this.date.isEmpty() && !this.reason.equals("") && this.checked == false;
    }
}
