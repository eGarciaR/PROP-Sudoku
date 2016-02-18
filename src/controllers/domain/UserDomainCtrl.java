
package controllers.domain;

import domain.User;
import persistence.UserManager;


/**
 * Classe encarregada de gestionar la informació dels Usuaris dins del Domini 
 * @author Carlos Lerida Mezcua
 * @author Arnau Fornells Piera
 */
public class UserDomainCtrl {
    
    /**
     * Única instància de la classe per implementar patró singleton.
     */
    private static UserDomainCtrl userDomainCtrl;
    
    /**
     * Gestor de la persistència d'usuari.
     */
    private UserManager userManager;
    
    /**
     * Usuari actual.
     */
    private User user;
    
    /**
     * Constructor per defecte privat.
     * @Pre Cert.
     * @param gameName Identificador del nom del Joc.
     * @Post Es crea una instancia del parametre imlicit userManager.
     */
    private UserDomainCtrl(String gameName) {
        this.userManager = UserManager.getInstance(gameName);
    }
    
    /**
     * Actua com a constructor de la classe.
     * @param gameName String que identifica el nom del Joc.
     * @return L'única instància de la propia classe.
     * @Pre Cert.
     * @Post Es crea una instància nova si no existeix i es retorna la instància.
     */
    public static UserDomainCtrl getInstance(String gameName){
        if(userDomainCtrl == null) userDomainCtrl = new UserDomainCtrl(gameName);
        return userDomainCtrl;
    }
    
    /**
     * Permet saber si existeix un usuari a la Base de Dades.
     * @param user Nom de l'usuari.
     * @return True si l'usuari existeix, False altrament.
     * @Pre String user es un paràmetre vàlid i no es nul.
     * @Post Indica si l'usuari existeix.
     */
    public boolean existsUser(String user) {
        return this.userManager.existsUsername(user);
    }

    /**
     * Inicia sessió d'un usuari.
     * @param user Nom de l'usuari no pot ser nul.
     * @param pass Password de l'usuari no pot ser nul.
     * @return True si les dades són correctes, False altrament.
     * @Pre String user i string pass son paràmetres vàlids i no nuls.
     * @Post Indica si l'usuari ha iniciat sessió correctament.
     */
    public boolean correctLoginUser(String user, String pass) {
        String passf = this.userManager.getPassword(user);
        if (passf.equals(pass)) return true;
        return false;
    }
    
    /**
     * Tanca la sessió de l'usuari actual.
     * @Pre Cert.
     * @Post Tanca la sessió de l'usuari del paràmetre implicit user.
     */
    public void logOut() {
        this.user = null;
    }
    
    /**
     * Permet inserir un usuari a la Base de Dades.
     * @param user Nom de l'usuari no pot ser nul.
     * @param pass Password de l'usuari no pot ser nul.
     * @return True si s'ha afegit l'usuari a la BD, False altrament.
     * @Pre String user i string pass son paràmetres vàlids i no nuls.
     * @Post Indica si l'usuari i la seva contrasenya han sigut inserits a 
     * la Base de Dades.
     */
    public boolean addUser(String user, String pass) {
        return this.userManager.addUser(user,pass);
        //Inserta un usuari a la BD
    }
    
    /**
     * Permet esborrar un usuari de la Base de Dades.
     * @param user Nom de l'usuari no pot ser nul.
     * @return True si s'ha esborrat correctament, False altrament.
     * @Pre String user es un paràmetre valid i no nul.
     * @Post Indica si l'usuari amb nom user passat per paràmetrets ha sigut
     * esborrat.
     */
    public boolean deleteUser(String user) {
        boolean ret = false;
        if (existsUser(user)) ret = this.userManager.deleteUser(user);
        return ret;
    }
    
    /**
     * Crea un nou usuari amb nom usuari igual a user.
     * @param user Nom de l'usuari no pot ser nul.
     * @Pre Sring user es un paràmetre vàlid i no pot ser nul.
     * @Post S'ha creat un usuari amb nom user.
     */
    public void setUser(String user) {
        this.user = new User(user);
    }
    
    /**
     * Permet saber l'usuari actual.
     * @return Retorna l'usuari actual.
     * @Pre Cert.
     * @Post Retorna l'usuari del paràmetre implicit user.
     */
    public String getUser() {
        if(user != null)return this.user.getName();
        else return "";
    }
    
    /**
     * Escriu els usuaris existents a la Base de Dades.
     * @Pre Cert.
     * @Post S'escriuen tots els usuaris de la Base de Dades.
     */
    public void writeDBUsers() {
        this.userManager.writeDB();
    }
    
    /**
     * Esborra tots els usuaris de la Base de Dades.
     * @Pre Cert.
     * @Post Elimina tots els usuaris existents a la Base de Dades.
     */
    public void cleanDBUsers() {
        this.userManager.cleanDB();
    }
    
    /**
     * Canvia la contrasenya del usuari
     * @param newPass String codificat en MD5 que conté la nova contrasenya
     * @return true si s'ha canviat correctament,false altrament
     * @Pre L'atribut user del paràmetre implícit no és nul.
     * @Post La contrasenya del usuari passa a ser newPass
     */
    public boolean changePass(String newPass) {
        if(userManager.changePass(user.getName(), newPass)) return true;
        else return false;  
    }
}
