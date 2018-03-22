package rw.motardmis.domain;

import java.io.Serializable;
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
public class Moto  implements Serializable {
@Id
    private String plate_no;
    private String owner;
    private String insurance_company;
    private String engine_no;
    @Past 
    private Date manuf_date;
    private Date reg_date;
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assName",nullable = false)
    private Association association;
    @OneToMany(mappedBy = "motoo")
    private List<Motard> motards;

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public String getEngine_no() {
        return engine_no;
    }

    public void setEngine_no(String engine_no) {
        this.engine_no = engine_no;
    }

    public Date getManuf_date() {
        return manuf_date;
    }

    public void setManuf_date(Date manuf_date) {
        this.manuf_date = manuf_date;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public List<Motard> getMotards() {
        return motards;
    }

    public void setMotards(List<Motard> motards) {
        this.motards = motards;
    }
    
    public String formatManufDate(){
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     return sdf.format(manuf_date);
    }
    public String formatRegDate(){
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     return sdf.format(reg_date);
    }

    @Override
    public String toString() {
        return plate_no;
    }

}
