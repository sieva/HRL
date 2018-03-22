package rw.motardmis.uimodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javassist.bytecode.stackmap.BasicBlock;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rw.motardmis.dao.UserDao;
import rw.motardmis.domain.User;

@ManagedBean(name = "login")
@SessionScoped
public class LoginUIModel {

    private User us = new User();
    private UserDao usDao = new UserDao();
    private List<User> listUsers = usDao.updAll();

    private String userName1;
    private String password1;

    public String login() {
       // new User();
        String msg1 = "";
        try {
            User s = usDao.loginUser(userName1, password1);
            if (s.getUsername().equalsIgnoreCase(userName1) && s.getPassword().equalsIgnoreCase(password1)) {
                if (s.getStatus().equalsIgnoreCase("active")) {
                    if (s.getPost().equalsIgnoreCase("Director")) {
                        log();
                        new User();
                        // msg1 = "Login.xhtml?faces-redirect=true";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Welcome " + s.getPost() + " " + s.getFullname() + " !!!", ""));
                        return "home2.xhtml";
                    } else {
                        log2();
                        new User();
                        //msg1 = "Login.xhtml?faces-redirect=true";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Welcome " + s.getPost() + " " + s.getFullname() + " !!!", ""));
                        return "home.xhtml";
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " User " + s.getFullname() + " is BLOCKED.  Contact the Administration. THANKS.", ""));
                    return "Login.xhtml";
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username and password. Contact The Admin.", ""));
                return msg1;
            }
        }
    catch(Exception ex){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail to login... ", ""));
        return msg1;

    }
}

public String log() {
        return "home2.xhtml";
    }

    public String log2() {
        return "home.xhtml";
    }

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

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

}
