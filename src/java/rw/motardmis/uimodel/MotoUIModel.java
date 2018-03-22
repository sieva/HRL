package rw.motardmis.uimodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.AssociationDao;
import rw.motardmis.dao.MotoDao;
import rw.motardmis.domain.Association;
import rw.motardmis.domain.Moto;

@ManagedBean(name = "m")
@SessionScoped
public class MotoUIModel {

    private Association ass = new Association();
    private AssociationDao assDao = new AssociationDao();
    private Moto mt = new Moto();
    private MotoDao mtDao = new MotoDao();
    private List<Moto> listMoto = mtDao.findAllMotos();
    private String selectedAssociation;
    private String skey;

    public String registerMoto() {
        String msg="";
        try {
            ass = assDao.findByName(selectedAssociation);
            mt.setAssociation(ass);
            mt.setReg_date(new Date());
            mtDao.Create(mt);
            listMoto = mtDao.findAllMotos();
             msg = "MotoRegForm.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Moto is registered successfully", ""));
            return "MotoRegForm.xhtml";
             
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Registration failed " + ex.getMessage(), ""));
            return "MotoRegForm.xhtml";
        }
        
    }

    public void serchMoto() {
        if (skey.length() > 0) {
            listMoto = new ArrayList<>();
            for (Moto ss : mtDao.findAllMotos()) {
                if (ss.getPlate_no().toLowerCase().contains(skey.toLowerCase())
                        || ss.getOwner().toString().toLowerCase().contains(skey.toLowerCase())
                         || ss.getAssociation().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getInsurance_company().toString().toLowerCase().contains(skey.toLowerCase())) {
                    if (listMoto.contains(ss) == false) {
                        listMoto.add(ss);
                    }
                }
            }
        } else {
            listMoto = mtDao.findAllMotos();
        }
    }

    public List<Association> getAssociation() {
        return assDao.findAllAssociation();
    }

    public Moto getMt() {
        return mt;
    }

    public void setMt(Moto mt) {
        this.mt = mt;
    }

    public MotoDao getMtDao() {
        return mtDao;
    }

    public void setMtDao(MotoDao mtDao) {
        this.mtDao = mtDao;
    }

    public List<Moto> getListMoto() {
        return listMoto;
    }

    public void setListMoto(List<Moto> listMoto) {
        this.listMoto = listMoto;
    }

    public String getSelectedAssociation() {
        return selectedAssociation;
    }

    public void setSelectedAssociation(String selectedAssociation) {
        this.selectedAssociation = selectedAssociation;
    }

    public Association getAss() {
        return ass;
    }

    public void setAss(Association ass) {
        this.ass = ass;
    }

    public AssociationDao getAssDao() {
        return assDao;
    }

    public void setAssDao(AssociationDao assDao) {
        this.assDao = assDao;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

  

}
