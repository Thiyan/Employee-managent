package yanmakes.employee_management.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notice")
public class Notice implements Serializable {

    @Id
    @Column(name = "nid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nId;

    @Column(name = "title")
    private String title;

    @Column(name = "message",length = 4000)
    private String message;

    public Notice() {
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "aId=" + nId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isValid(){
        return !this.title.equals("") && !this.message.equals("");
    }
}
