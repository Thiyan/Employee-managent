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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "arrival")
    private LocalTime arrival;

    @Column(name = "departure")
    private LocalTime departure;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
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
        return !this.employee.equals("");
    }
}
