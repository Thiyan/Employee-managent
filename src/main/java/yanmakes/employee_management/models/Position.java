package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "position")
public class Position implements Serializable {

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;

    @Column(name = "position")
    private String position;


    public Position() {
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Position{" +
                "pId=" + pId +
                ", position='" + position + '\'' +
                '}';
    }

    public boolean isValid(){
        return !this.position.equals("") ;
    }
}
