package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "salary")
public class Salry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "salary_id")
    private int salaryId;

    @Column(name = "month")
    private String month;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @Column(name = "salary_amount")
    public String salaryAmount;

    public Salry(){}

    public Salry(String month, Employee employee, String salaryAmount) {
        this.month=month;
        this.employee=employee;
        this.salaryAmount=salaryAmount;
    }


    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
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

    public String getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(String salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    @Override
    public String toString() {
        return "Salry{" +
                "salaryId=" + salaryId +
                ", month='" + month + '\'' +
                ", employee=" + employee +
                '}';
    }
}

