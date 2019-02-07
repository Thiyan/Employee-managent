package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "leave_model")
public class LeaveModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lm_id")
    private int lId;

    @Column(name = "lm_name")
    private String mName;

    @Column(name = "days_year")
    private int daysPerYear;

    @Column(name = "days_month")
    private int daysPerMonth;

    @Column(name = "lm_description",length = 500)
    private String description;

    public LeaveModel(){}

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getDaysPerYear() {
        return daysPerYear;
    }

    public void setDaysPerYear(int daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    public int getDaysPerMonth() {
        return daysPerMonth;
    }

    public void setDaysPerMonth(int daysPerMonth) {
        this.daysPerMonth = daysPerMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SalaryModel{" +
                "sId=" + lId +
                ", mName='" + mName + '\'' +
                ", amount='" + daysPerYear + '\'' +
                ", amount='" + daysPerMonth + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean isValid(){

        return !this.mName.equals("") && !String.valueOf(this.daysPerMonth ).equals("") && !String.valueOf(this.daysPerYear ).equals("");
    }
}
