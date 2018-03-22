package rw.motardmis.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

@Entity
public class Association implements Serializable {

    @Id
    @Column(name = "ass_name", unique = true, nullable = false)
    private String ass_name;
    private String address;
    private String phone;
    private String email;
    private String pres_name;
    private String secret_name;
    private String treas_name;
    @Past
    private Date creat_Date;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "association", orphanRemoval = true)
    // @OneToMany(mappedBy = "association", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Moto> motos;

    public String getAss_name() {
        return ass_name;
    }

    public void setAss_name(String ass_name) {
        this.ass_name = ass_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPres_name() {
        return pres_name;
    }

    public void setPres_name(String pres_name) {
        this.pres_name = pres_name;
    }

    public String getSecret_name() {
        return secret_name;
    }

    public void setSecret_name(String secret_name) {
        this.secret_name = secret_name;
    }

    public String getTreas_name() {
        return treas_name;
    }

    public void setTreas_name(String treas_name) {
        this.treas_name = treas_name;
    }

    public Date getCreat_Date() {
        return creat_Date;
    }

    public void setCreat_Date(Date creat_Date) {
        this.creat_Date = creat_Date;
    }

    public String formatCreateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(creat_Date);
    }

    @Override
    public String toString() {
        return ass_name;
    }

}
