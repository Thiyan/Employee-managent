package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "tid")
    private int tId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "description",length = 3000)
    private String description;

    @Column(name = "dead_line")
    private String deadLine;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @Column(name = "active")
    private boolean active;


    public Task(){}

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tId=" + tId +
                ", employees=" + employee +
                ", description='" + description + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy=" + createdBy +
                ", active=" + active +
                '}';
    }

    public boolean isValid(){
        return this.employee!=null && !this.description.equals("") && !this.deadLine.equals("") /*&& !this.createdBy.equals("")*/;
    }
}
