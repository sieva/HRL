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

@ManagedBean(name = "logout")
@SessionScoped
public class LogoutUIModel {

    private User us = new User();
    private UserDao usDao = new UserDao();
    private List<User> listUsers = usDao.findAllUsers();

    public String login() {
        String msg1 = "";
        logout();
                msg1 = "Login.xhtml?faces-redirect=true";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "For Login, Enter your Username and password bellow...", ""));
                return msg1;    
    }

    public String log() {
         new User();
        return "home.xhtml";
    }
    public String logout() {
         new User();
        return "Login.xhtml";
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

}
