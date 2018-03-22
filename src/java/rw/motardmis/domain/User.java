package rw.motardmis.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

@Entity
public class User {
@Id
    private String fullname;
    private String username;
    private String password;
    private String gender;
    private String Address;
    private String post;
    @Past 
    private Date dob;
    private Date regDate;
    private String Status;
    @Transient
    private String action;
    

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
     public String formatDob(){
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     return sdf.format(dob);
    }
    public String formatregDate(){
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     return sdf.format(regDate);
    }

    
}
