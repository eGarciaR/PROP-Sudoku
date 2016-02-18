package controllers.presentation;

import controllers.domain.RegisteredUserDomainCtrl;
import controllers.domain.UserDomainCtrl;

/**
 *
 * @author Marc
 */
public class UserPresentationCtrl {

    private static UserPresentationCtrl userPresentationCtrl;
    private final PresentationCtrl presentationCtrl;
    private final UserDomainCtrl userDomainCtrl;
    private final RegisteredUserDomainCtrl registeredUserDomainCtrl;

    private UserPresentationCtrl(PresentationCtrl pCtrl) {
        this.presentationCtrl = pCtrl;
        this.userDomainCtrl = this.presentationCtrl.getUserDomainCtrl();
        this.registeredUserDomainCtrl = this.presentationCtrl.getRegisteredUserDomainCtrl();

    }

    public static UserPresentationCtrl getInstance(PresentationCtrl pCtrl) {
        if (userPresentationCtrl == null) {
            userPresentationCtrl = new UserPresentationCtrl(pCtrl);
        }
        return userPresentationCtrl;
    }

    public boolean login(String user, String password) {
        if (userDomainCtrl.correctLoginUser(user, password)) {
            userDomainCtrl.setUser(user);
            presentationCtrl.setPanel("home");
            return true;
        } else {
            return false;
        }
    }

    public boolean existsUsername(String user) {
        return userDomainCtrl.existsUser(user);
    }

    public boolean addUser(String user, String pass) {
        if (userDomainCtrl.addUser(user, pass) && registeredUserDomainCtrl.addUser(user)) {
            userDomainCtrl.setUser(user);
            presentationCtrl.setPanel("home");
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(String user, String pass) {
        if (userDomainCtrl.correctLoginUser(user, pass)) {
            if (userDomainCtrl.deleteUser(user) && registeredUserDomainCtrl.deleteUser(user)) {
                presentationCtrl.setPanel("login");
                return true;
            }
        }
        return false;
    }

    public void logout() {
        userDomainCtrl.logOut();
        presentationCtrl.setPanel("login");
    }

    public boolean changePassword(String user, String oldPass, String newPass) {
        if (userDomainCtrl.correctLoginUser(user, oldPass)) {
            if (userDomainCtrl.changePass(newPass)) {
                presentationCtrl.setPanel("home");
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return userDomainCtrl.getUser();
    }

}
