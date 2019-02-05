package yanmakes.employee_management.models;

import yanmakes.employee_management.Utils.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "eid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eId;

    @Column(name = "fname",nullable = false,length = 100)
    private String fName;

    @Column(name = "lname",nullable = false,length = 100)
    private String lName;

    @Column(name = "dob",nullable = false)
    private String dob;

    @Column(name = "gender",nullable = false)
    private Gender gender;

    @Column(name = "doe",nullable = false)
    private String doe;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "mobile",nullable = false)
    private String mobile;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "caddress",length = 500)
    private String curAddress;

    @Column(name = "paddress",length = 500)
    private String perAddress;

    @Column(name = "blood",nullable = false)
    private String blood;

    @Column(name = "position",nullable = false)
    private String position;

    @Column(name = "userId",nullable = false)
    private String userId;

    @Column(name = "password",nullable = false,length = 100)
    private String password;

    @Column(name = "image",length = 500)
    private String image;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "salary")
    private SalaryModel salaryModel;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "leaves")
    private LeaveModel leaveModel;

    @Column(name = "active")
    private boolean active;


    public Employee(){}

    public Employee(Employee employee) {

        this.eId=employee.geteId();
        this.fName=employee.getfName();
        this.lName=employee.getlName();
        this.dob=employee.getDob();
        this.gender=employee.getGender();
        this.doe=employee.getDoe();
        this.email=employee.getEmail();
        this.mobile=employee.getMobile();
        this.phone=employee.getPhone();
        this.curAddress=employee.getCurAddress();
        this.perAddress=employee.getPerAddress();
        this.blood=employee.getBlood();
        this.position=employee.getPosition();
        this.password=employee.getPassword();
        this.userId=employee.getUserId();
        this.image=employee.getImage();
        this.roles=employee.getRoles();
        this.salaryModel=employee.getSalaryModel();
        this.leaveModel=employee.getLeaveModel();
        this.active=employee.isActive();

    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurAddress() {
        return curAddress;
    }

    public void setCurAddress(String curAddress) {
        this.curAddress = curAddress;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public SalaryModel getSalaryModel() {
        return salaryModel;
    }

    public void setSalaryModel(SalaryModel salaryModel) {
        this.salaryModel = salaryModel;
    }

    public LeaveModel getLeaveModel() {
        return leaveModel;
    }

    public void setLeaveModel(LeaveModel leaveModel) {
        this.leaveModel = leaveModel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                ", doe='" + doe + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", curAddress='" + curAddress + '\'' +
                ", perAddress='" + perAddress + '\'' +
                ", blood='" + blood + '\'' +
                ", position='" + position + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
//                ", roles='" + roles + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

//    public boolean isValid(){
//        return (this.fName!=null & this.lName!=null & this.email != null && this.dob && this.doe != null &&
//                this.blood != null && this.gender && this.mobile && this.phone != null && this.image != null);
//    }
}
