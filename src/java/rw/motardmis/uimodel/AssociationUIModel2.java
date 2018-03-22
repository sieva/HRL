package rw.motardmis.uimodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.AssociationDao;
import rw.motardmis.domain.Association;

@ManagedBean(name = "ass2")
@SessionScoped
public class AssociationUIModel2 {

    private Association assoc = new Association();
    private AssociationDao assDao = new AssociationDao();
    private List<Association> listAssociation = assDao.findAllAssociation();
    private String searchkey;

    public String createAssociation() {
          String msg="";
        try {
           assoc.setCreat_Date(new Date());
            assDao.Create(assoc);
            listAssociation = assDao.findAllAssociation(); 
             msg = "AssociationRegForm2.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Association is registered successfully", ""));
            return "AssociationRegForm2.xhtml";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Registration failed " + ex.getMessage(), ""));
            return "AssociationRegForm2.xhtml";
        }
         
    }

    public String assregForm() {
        return "AssociationRegForm2";
    }

    public String deleteAssociation(Association a) {

        try {
            assDao.delete(a);
            listAssociation = assDao.findAllAssociation();
            FacesMessage msg = new FacesMessage(assoc.getAss_name() + " is Well deleted.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "AssociationRegForm2";

        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail to delete." + ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "AssociationRegForm2";
        }

//        String msg = "";
//        try {
//            assDao.delete(a);
//            listAssociation = assDao.findAllAssociation();
//            assDel();
//            msg = "AssociationRegForm.xhtml?faces-redirect=true";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Association is well deleted", ""));
//            return msg;
//        } catch (Exception ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Failed to delete", ""));
//            return msg;
//        }
    }

    public String assDel() {
        return "AssociationRegForm2";
    }

    public String editAssociation(Association n) {
        assoc = n;
        return "AssociationUpdateForm2";
    }

    public String updateAssociation(Association a) {
         String msg = "";
        try {
            assDao.Update(assoc);
            listAssociation = assDao.findAllAssociation();
           // msg = "AssociationRegForm.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Association is well updated", ""));
            return "AssociationRegForm2.xhtml";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Failed to update Association", ""));
            return "AssociationRegForm2.xhtml";
        }
    }

    public void serchAssociation() {
        if (searchkey.length() > 0) {
            listAssociation = new ArrayList<>();
            for (Association ss : assDao.findAllAssociation()) {
                if (ss.getAss_name().toLowerCase().contains(searchkey.toLowerCase())
                        || ss.getAddress().toString().toLowerCase().contains(searchkey.toLowerCase())
                        || ss.getPhone().toString().toLowerCase().contains(searchkey.toLowerCase())) {
                    if (listAssociation.contains(ss) == false) {
                        listAssociation.add(ss);
                    }
                }
            }
        } else {
            listAssociation = assDao.findAllAssociation();
        }
    }

    public Association getAssoc() {
        return assoc;
    }

    public void setAssoc(Association assoc) {
        this.assoc = assoc;
    }

    public AssociationDao getAssDao() {
        return assDao;
    }

    public void setAssDao(AssociationDao assDao) {
        this.assDao = assDao;
    }

    public List<Association> getListAssociation() {
        return listAssociation;
    }

    public void setListAssociation(List<Association> listAssociation) {
        this.listAssociation = listAssociation;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

}
