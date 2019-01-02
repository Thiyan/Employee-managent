package yanmakes.employee_management.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private int rId;

    @Column(name = "role")
    private String role;

    public Role(){}

    public Role(int i, String role) {
        this.rId=i;
        this.role=role;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", role='" + role + '\'' +
                '}';
    }
}

