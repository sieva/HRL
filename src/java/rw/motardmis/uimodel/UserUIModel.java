package rw.motardmis.uimodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javassist.bytecode.stackmap.BasicBlock;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.UserDao;
import rw.motardmis.domain.User;

@ManagedBean(name = "user")
@SessionScoped
public class UserUIModel {

    private User us = new User();
    private UserDao usDao = new UserDao();
    private List<User> listUsers = usDao.updAll();
    private String skey;

    public String registerUser() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -16);
        Date checkDt = calendar.getTime();
        String msg = "";

        User s = usDao.getOne(us.getUsername());
        if (s == null) {
            if (us.getDob().before(checkDt)) {

                us.setRegDate(new Date());
                us.setStatus("ACTIVE");
                usDao.Create(us);
                listUsers = usDao.updAll();
                msg = "UserRegForm.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " User is registered successfully", ""));
                return "UserRegForm.xhtml";

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This User " + us.getUsername() + " is under 16 years old.", ""));
           return msg;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This User " + us.getUsername() + " already exists", ""));
      return msg;
        }
    }

    public void serchUser() {
        if (skey.length() > 0) {
            listUsers = new ArrayList<>();
            for (User usr : usDao.updAll()) {
                if (usr.getFullname().toLowerCase().contains(skey.toLowerCase())
                        || usr.getUsername().toString().toLowerCase().contains(skey.toLowerCase())
                        || usr.getGender().toString().toLowerCase().contains(skey.toLowerCase())
                        || usr.getStatus().toString() .toLowerCase().contains(skey.toLowerCase())
                        || usr.getPost().toString().toLowerCase().contains(skey.toLowerCase())) {
                    if (listUsers.contains(usr) == false) {
                        listUsers.add(usr);
                    }
                }
            }
        } else {
            listUsers = usDao.updAll();
        }

    }
    //------------------------------------------------------------- 

    public String update(User km) {
        us = km;
        try {
            if (us.getStatus().equalsIgnoreCase("ACTIVE")) {
                us.setStatus("BLOCKED");
                usDao.Update(us);
                listUsers = usDao.updAll();

            } else {
                us.setStatus("ACTIVE");
                usDao.Update(us);
                listUsers = usDao.updAll();

            }
            usDao.Update(us);
            us = new User();
            listUsers = usDao.updAll();
        } catch (Exception e) {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage fm = new FacesMessage("Fail to change the User's status");
            fc.addMessage(null, fm);
        }
        return "UserRegForm";
    }
    //=================================================================   

    public User getUs() {
        return us;
    }

    public void setUs(User us) {
        this.us = us;
    }

    public UserDao getUsDao() {
        return usDao;
    }

    public void setUsDao(UserDao usDao) {
        this.usDao = usDao;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

}
