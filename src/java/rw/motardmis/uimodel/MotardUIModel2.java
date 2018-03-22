package rw.motardmis.uimodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.MotardDao;
import rw.motardmis.dao.MotoDao;
import rw.motardmis.domain.Motard;
import rw.motardmis.domain.Moto;

@ManagedBean(name = "mo2")
@SessionScoped
public class MotardUIModel2 {

    private Moto mot = new Moto();
    private MotoDao motDao = new MotoDao();
    private Motard mta = new Motard();
    private MotardDao mtrdDao = new MotardDao();
    private List<Motard> listMotard = mtrdDao.findAllMotard();
    private String selectedMoto;
    private String skey;

    public String registerMotard() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -16);
        Date checkDt = calendar.getTime();
        calendar.add(Calendar.YEAR, -80);
        Date checkDt2 = calendar.getTime();

        String msg = "";
        if (mta.getPermit_no().matches("[0-9]+") && mta.getPermit_no().length() == 16) {
              if (Integer.parseInt(mta.getPermit_no().substring(1, 5)) == mta.getDob().getYear()+1900) {
                   
                if (mta.getPhone().length() == 10 && mta.getPhone().matches("[0-9]+")) {
                    if (mta.getDob().before(checkDt)) {
                        if (mta.getDob().after(checkDt2)) {
                            mot = motDao.findByPlateNo(selectedMoto);
                            mta.setMotoo(mot);
                            mta.setRegDate(new Date());
                            mtrdDao.Create(mta);
                            listMotard = mtrdDao.findAllMotard();
                            msg = "UserRegForm.xhtml?faces-redirect=true";
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Motard is registered successfully", ""));
                            return "MotardRegForm2.xhtml";

                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Motard must be less than 80 years old.", ""));
                            return msg;
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Motard must be more than 16 years old.", ""));
                        return msg;
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Phone Number. [ex: 07xxxxxxxx] --> Ten integers.", ""));
                    return msg;
                }
            }   
                        else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please! Dob must much with Identity year of birth", ""));
                return msg;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Permit No. must be only 16 integers.", ""));
            return msg;
        }

    }


public void serchMotard() {
        if (skey.length() > 0) {
            listMotard = new ArrayList<>();
            for (Motard ss : mtrdDao.findAllMotard()) {
                if (ss.getFname().toLowerCase().contains(skey.toLowerCase())
                        || ss.getLname().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getGender().toString().toLowerCase().contains(skey.toLowerCase())
                        || ss.getPermit_no().toString().toLowerCase().contains(skey.toLowerCase())) {
                    if (listMotard.contains(ss) == false) {
                        listMotard.add(ss);
                    }
                }
            }
        } else {
            listMotard = mtrdDao.findAllMotard();
        }
    }

    public List<Moto> getMoto() {
        return motDao.findAllMotos();
    }

    public Motard getMta() {
        return mta;
    }

    public void setMta(Motard mta) {
        this.mta = mta;
    }

    public MotardDao getMtrdDao() {
        return mtrdDao;
    }

    public void setMtrdDao(MotardDao mtrdDao) {
        this.mtrdDao = mtrdDao;
    }

    public List<Motard> getListMotard() {
        return listMotard;
    }

    public void setListMotard(List<Motard> listMotard) {
        this.listMotard = listMotard;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public Moto getMot() {
        return mot;
    }

    public void setMot(Moto mot) {
        this.mot = mot;
    }

    public MotoDao getMotDao() {
        return motDao;
    }

    public void setMotDao(MotoDao motDao) {
        this.motDao = motDao;
    }

    public String getSelectedMoto() {
        return selectedMoto;
    }

    public void setSelectedMoto(String selectedMoto) {
        this.selectedMoto = selectedMoto;
    }

}
