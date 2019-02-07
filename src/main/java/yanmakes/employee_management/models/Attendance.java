package yanmakes.employee_management.models;

import yanmakes.employee_management.Utils.AttendaceType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

    @Id
    @Column(name = "aid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "date")
    private String date;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "departure")
    private String departure;

    @Column(name = "attendance")
    private boolean attendance;

    @Column(name = "att_type")
    private AttendaceType attType;

    public Attendance(){}

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public AttendaceType getAttType() {
        return attType;
    }

    public void setAttType(AttendaceType attType) {
        this.attType = attType;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "aId=" + aId +
                ", employee=" + employee +
                ", dateTime=" + date +
                ", attendance=" + attendance +
                ", arrival=" + arrival +
                ", attendance=" + attendance +
                '}';
    }

        public boolean isValid(){
        return this.employee!=null;
    }
}
