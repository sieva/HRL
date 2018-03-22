package rw.motardmis.uimodel;

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

@ManagedBean(name = "um")
@SessionScoped
public class UmusanzuUIModel {

    private Motard motar = new Motard();
    private MotardDao mtdao = new MotardDao();
    private Umusanzu umus = new Umusanzu();
    private UmusanzuDao umusDao = new UmusanzuDao();
    private List<Umusanzu> listUmusanzu = umusDao.findAllUmusanzu();
    //private List<Umusanzu> listUmusanzu1 = umusDao.findAllUmusanzu();;
    private String selectedMotard;
    private String skey;
    private double total=0.0;
//    
//    @PostConstruct
//    public void umusanzu(){
//        List<Umusanzu> all = new UmusanzuDao().findAllUmusanzu();
//        for(Umusanzu u: all){
//             total=total+umus.getAmount();
//        }
//    }
    

    public String registerUmusanzu() {
        String msg="";
        try {
            motar = mtdao.findByPermitNo(selectedMotard);
            umus.setMotari(motar);
            umus.setDeposit_Date(new Date());
            umus.setUmusanzuNo(UUID.randomUUID().toString());
            umusDao.Create(umus);
            listUmusanzu = umusDao.findAllUmusanzu();
             msg = "UserRegForm.xhtml?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Umusanzu is registered successfully", ""));
            return "UmusanzuRegForm.xhtml";
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " Registration failed" + ex.getMessage(), ""));
            return "UmusanzuRegForm.xhtml";
        }
    }

    public void serchUmusanzu()throws Exception {
                    total=0;
                  listUmusanzu = new ArrayList<>(); 
         if (skey.length() > 0) {

            
            for (Umusanzu ss : umusDao.findAllUmusanzu()) {
                //  total = total+ss.getAmount();
                if (ss.getUwishyura().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getFname().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getLname().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getDeposit_Date().toString().toLowerCase().contains(skey.toLowerCase())) {
                    if (listUmusanzu.contains(ss) == false) {
                        listUmusanzu.add(ss);
                       // total = total+ss.getAmount();
                       total = total+ss.getAmount();
                    }
                }
            }
        } else {
            listUmusanzu = umusDao.findAllUmusanzu();
            total=total;
           // total = total+new Umusanzu().getAmount();
        }
        
    }
//     if (skey.length() > 0) {
//            listUmusanzu = new ArrayList<>();
//            for (Umusanzu ss : umusDao.findAllUmusanzu()) {
//                if (ss.getUwishyura().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getFname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getLname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getDeposit_Date().toString().toLowerCase().contains(skey.toLowerCase())) {
//                    if (listUmusanzu.contains(ss) == false) {
//                        listUmusanzu.add(ss);
//                    }
//                }
//            }
//        } else {
//            listUmusanzu = umusDao.findAllUmusanzu();
//        }
//    }
//        List<Umusanzu> all = umusDao.findAllUmusanzu();
//            for (Umusanzu ss : all) {
//                if (ss.getUwishyura().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getFname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getLname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
////                        || ss.getAmount().contains(Double.parseDouble(skey))
//                        || ss.getDeposit_Date().toString().toLowerCase().contains(skey.toLowerCase())) {
//                   listUmusanzu1.add(ss);
//            }
        
        
       
//            listUmusanzu = new ArrayList<>();
//            for (Umusanzu ss : umusDao.findAllUmusanzu()) {
//                 if (skey.length() > 0) {
//                if (ss.getUwishyura().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getFname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getLname().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getMotari().getMotoo().getPlate_no().toString().toLowerCase().contains(skey.toLowerCase())
//                     //   || ss.getAmount().toString().toLowerCase().contains(skey.toLowerCase())
//                        || ss.getDeposit_Date().toString().toLowerCase().contains(skey.toLowerCase())) {
//                    if (listUmusanzu.contains(ss) == false) {
//                        listUmusanzu.add(ss);
//                        total = total+ss.getAmount();
//                    } else{
//                  total = total+ss.getAmount();
//                }
//            } else {
//            listUmusanzu = umusDao.findAllUmusanzu();
//                     total = total+ss.getAmount();
//        }
//
//                 }
//    }
//    
//    }
    
    
//    public double getTotal(double amount) {
//        double total=0;
//        return total+3000;
//}
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
//        List<Umusanzu> alls = umusDao.findAllUmusanzu();
//        for(Umusanzu g: alls){
//            total = total + g.getAmount();
//        }
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    public List<Umusanzu> getListUmusanzu1() {
//        if(skey.isEmpty()){
//            for(Umusanzu u: umusDao.findAllUmusanzu()){
//                total = total + u.getAmount();
//            }
//            return new UmusanzuDao().findAllUmusanzu();
//        }else{
//            total--;
//        return listUmusanzu1;
//    }
//    }

//    public void setListUmusanzu1(List<Umusanzu> listUmusanzu1) {
//        this.listUmusanzu1 = listUmusanzu1;
//    }



}
