package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime requestedDate;

    @ElementCollection
    @CollectionTable(
            name="leave_dates",
            joinColumns=@JoinColumn(name="request_id")
    )
    @Column(name = "dates")
    private List<LocalDate> dates;

    @Column(name = "reason")
    private String reason;

    @Column(name = "is_checked")
    private boolean checked;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;

    @Column(name = "is_approved")
    private boolean approved;

    @Column(name = "days")
    private int days;

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

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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
                ", dates=" + dates +
                ", reason='" + reason + '\'' +
                ", approved=" + approved +
                ", days=" + days +
                ", reply='" + reply + '\'' +
                '}';
    }

    public boolean isValid(){
        return !this.requestedBy.equals("") && !this.dates.isEmpty() && !this.equals("") && this.checked == false;
    }
}
