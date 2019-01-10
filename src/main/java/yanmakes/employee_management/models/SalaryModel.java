package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "salary_model")
public class SalaryModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sm_id")
    private int sId;

    @Column(name = "sm_name")
    private String mName;

    @Column(name = "full_amount")
    private String fullAmount;

    @Column(name = "half_amount")
    private String halfAmount;

    @Column(name = "sm_description",length = 500)
    private String description;

    public SalaryModel(){}

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(String fullAmount) {
        this.fullAmount = fullAmount;
    }

    public String getHalfAmount() {
        return halfAmount;
    }

    public void setHalfAmount(String halfAmount) {
        this.halfAmount = halfAmount;
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
                "sId=" + sId +
                ", mName='" + mName + '\'' +
                ", fullAmount='" + fullAmount + '\'' +
                ", halfAmount='" + halfAmount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean isValid(){
        return !this.mName.equals("") && !this.halfAmount.equals("") && !this.fullAmount.equals("");
    }
}
