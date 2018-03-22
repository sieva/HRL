package rw.motardmis.domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Umusanzu {
@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="umusanzuNo", unique = true, nullable = false)
    private String umusanzuNo;
    private double Amount;
    private String Uwishyura;
    private String plateNo;
    private Date deposit_Date;
    private String Receiver;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motarPermitNo",nullable = false)
    private Motard motari;

    public String getUmusanzuNo() {
        return umusanzuNo;
    }

    public void setUmusanzuNo(String umusanzuNo) {
        this.umusanzuNo = umusanzuNo;
    }
    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
     public String decAmount() {
        DecimalFormat df = new DecimalFormat("###,###"+" Frw");
        return df.format(Amount);
    }
    



    public String getUwishyura() {
        return Uwishyura;
    }

    public void setUwishyura(String Uwishyura) {
        this.Uwishyura = Uwishyura;
    }

    public Date getDeposit_Date() {
        return deposit_Date;
    }

    public void setDeposit_Date(Date deposit_Date) {
        this.deposit_Date = deposit_Date;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public Motard getMotari() {
        return motari;
    }

    public void setMotari(Motard motari) {
        this.motari = motari;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
     public String formatDeposDate(){
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
     return sdf.format(deposit_Date);
    }
 
}
