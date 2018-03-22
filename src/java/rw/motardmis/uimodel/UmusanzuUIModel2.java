package rw.motardmis.uimodel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.MotardDao;
import rw.motardmis.dao.UmusanzuDao;
import rw.motardmis.domain.Motard;
import rw.motardmis.domain.Umusanzu;

@ManagedBean(name = "um2")
@SessionScoped
public class UmusanzuUIModel2 {

    private Motard motar = new Motard();
    private MotardDao mtdao = new MotardDao();
    private Umusanzu umus = new Umusanzu();
    private UmusanzuDao umusDao = new UmusanzuDao();
    private List<Umusanzu> listUmusanzu = umusDao.findAllUmusanzu();
    private String selectedMotard;
    private String skey;
    private double total = 0.0;

    public String registerUmusanzu() {
        String msg = "";
        try {
            motar = mtdao.findByPermitNo(selectedMotard);
            umus.setMotari(motar);
            umus.setDeposit_Date(new Date());
            umus.setUmusanzuNo(UUID.randomUUID().toString());
            umusDao.Create(umus);
            listUmusanzu = umusDao.findAllUmusanzu();
            total = total + umus.getAmount();
            msg = "UserRegForm.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Umusanzu is registered successfully", ""));
            return "UmusanzuRegForm2.xhtml";
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Registration failed" + ex.getMessage(), ""));
            return "UmusanzuRegForm2.xhtml";
        }
    }

    public void serchUmusanzu() throws Exception {
        total = 0;
        listUmusanzu = new ArrayList<>();

        if ((skey.length() == 0) || (skey.isEmpty())) {
            //  listUmusanzu = umusDao.findAllUmusanzu();
            for (Umusanzu kk : umusDao.findAllUmusanzu()) {
                if (listUmusanzu.contains(kk) == false) {
                    listUmusanzu.add(kk);
                    total = total + kk.getAmount();
                }
            }
        } else if (skey.length() > 0) {
            for (Umusanzu ss : umusDao.findAllUmusanzu()) {

                if (ss.getUwishyura().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getFname().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getLname().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getMotoo().getAssociation().getAss_name().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.formatDeposDate().contains(skey)
                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getDeposit_Date().toString().toLowerCase().contains(skey.toLowerCase())) {
                    if (listUmusanzu.contains(ss) == false) {
                        listUmusanzu.add(ss);
                        total = total + ss.getAmount();
                    }
                }
            }
        }

    }

    public List<Motard> getMotard() {
        return mtdao.findAllMotard();
    }

    public Umusanzu getUmus() {
        return umus;
    }

    public void setUmus(Umusanzu umus) {
        this.umus = umus;
    }

    public UmusanzuDao getUmusDao() {
        return umusDao;
    }

    public void setUmusDao(UmusanzuDao umusDao) {
        this.umusDao = umusDao;
    }

    public List<Umusanzu> getListUmusanzu() {
        return listUmusanzu;
    }

    public void setListUmusanzu(List<Umusanzu> listUmusanzu) {
        this.listUmusanzu = listUmusanzu;
    }

    public String getSelectedMotard() {

        return selectedMotard;
    }

    public void setSelectedMotard(String selectedMotard) {
        this.selectedMotard = selectedMotard;
    }

    public Motard getMotar() {
        return motar;
    }

    public void setMotar(Motard motar) {
        this.motar = motar;
    }

    public MotardDao getMtdao() {
        return mtdao;
    }

    public void setMtdao(MotardDao mtdao) {
        this.mtdao = mtdao;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String decTotalFormat() {
        DecimalFormat ds = new DecimalFormat("###,###"+" Frw");
        return ds.format(total);
    }

    public double getTotal() {
        return total;

    }

    public void setTotal(double total) {
        this.total = total;
    }

}
