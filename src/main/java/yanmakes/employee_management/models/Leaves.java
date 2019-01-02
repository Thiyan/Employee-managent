package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "leaves")
public class Leaves implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leave_id")
    private int leaveId;

    @Column(name = "month")
    private String month;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "taken")
    private int taken;

    public Leaves(){}

    public Leaves(String month, Employee employee, int taken) {
        this.month=month;
        this.employee=employee;
        this.taken=taken;
    }


    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Leaves{" +
                "leaveId=" + leaveId +
                ", month='" + month + '\'' +
                ", employee=" + employee +
                ", taken=" + taken +
                '}';
    }
}

