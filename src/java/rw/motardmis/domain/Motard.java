package rw.motardmis.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

@Entity
public class Motard {

    @Id
    private String permit_no;
    private String fname;
    private String lname;
    private String gender;
    private String phone;
    private String email;
    @Past
    private Date dob;
    private String address;
    private Date regDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_PlateNo", nullable = false)
    private Moto motoo;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "motari", orphanRemoval = true)
    private List<Umusanzu> umusanzu;

    public String getPermit_no() {
        return permit_no;
    }

    public void setPermit_no(String permit_no) {
        this.permit_no = permit_no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Moto getMotoo() {
        return motoo;
    }

    public void setMotoo(Moto motoo) {
        this.motoo = motoo;
    }

    public String formatDob() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dob);
    }

    public String formatRegDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(regDate);
    }

    @Override
    public String toString() {
        return permit_no;
    }

}
