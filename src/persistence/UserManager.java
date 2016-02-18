
package persistence;

import java.sql.ResultSet;

/**
 * Classe encarregada de gestionar la informació dels usuaris a la base de dades
 * @author Joan Monge Tomàs
 */
public class UserManager extends PersistenceCtrl{
    
    /**
     * Única instància de la classe per implementar patró singleton.
     */
    private static UserManager userManager;
    
    /**
     * Creador per defecte privat.
     * @Pre Cert.
     * @param gameName Identificador del nom del Joc.
     * @Post Cert.
     */
    private UserManager(String gameName){
        super(gameName);
        createTable();
    }
    
    /**
     * Única instància de la classe per implementar patró singleton.
     * @param gameName String que identifica el nom del Joc.
     * @return Retorna el userManager.
     * @Pre Cert.
     * @Post Crea una nova instancia de userManager si no existeix.
     */
    public static UserManager getInstance(String gameName){
        if(userManager == null) userManager = new UserManager(gameName);
        return userManager;
    }
    
    @Override
    
    /**
     * Crea una taula d'usuaris a la Base de Dades.
     * @Pre Cert.
     * @Post Es crea una taula usuaris a la Base de Dades si no existeix.
     */
    public void createTable() {
        if(connect(true)){
            try {
                String sql = "CREATE TABLE IF NOT EXISTS USUARIS " +
                    "(NAME             TEXT PRIMARY KEY     NOT NULL," +
                    "PASSWORD          TEXT                 NOT NULL);";      
                getStatement().executeUpdate(sql);
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
    }
     
    /**
     * Comproba si un usuari existeix a la Base de Dades.
     * @param user String que indica el nom de l'usuari a comprobar.
     * @return Retorna True si l'usuari existeix a la base de dades, False altrament.
     * @Pre Cert.
     * @Post Cert.
     */
    public boolean existsUsername(String user){
        boolean ret = false;
        if(connect(true)){
            try {
                String sql = "SELECT count(*) FROM USUARIS WHERE NAME = '"+user+"';";
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    int num = rs.getInt(1);
                    if(num > 0) ret = true;
                }
                rs.close();
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
        return ret;
    }
    
    /**
     * Permet saber la contrasenya d'un usuari.
     * @param user String que indica el nom de l'usuari.
     * @return Retorna un String amb la contrasenya de l'usuari indicat.
     * @Pre user no pot ser nul.
     * Post Cert.
     */
    public String getPassword(String user) {
        String ret = "";
        if(connect(true)){
            try {
                String sql = "SELECT PASSWORD FROM USUARIS WHERE NAME = '"+user+"';";
                ResultSet rs = getStatement().executeQuery(sql);
                while(rs.next())ret = rs.getString(1);
                rs.close();
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
        return ret;
    }
   
    /**
     * Afegeix un usuari a la Base de Dades.
     * @param user String que indica el nom de l'usuari.
     * @param pass String que indica la contrasenya de l'usuari.
     * @return Retorna True si s'afegeix l'usuari, altrament False.
     * @Pre user no pot ser nul i pass no és nul.
     * @Post S'afegeix un nou usuari a la taula Usuaris de la Base de Dades.
     */
    public boolean addUser(String user, String pass){
        boolean ret = false;
        if(connect(true)){
            try {
                String sql = "INSERT INTO USUARIS VALUES ('"+user+"','"+pass+"');";
                int m = getStatement().executeUpdate(sql);
                if (m > 0) ret = true;
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
        return ret;
    }
    
    /**
     * Esborra un usuari de la Base de Dades.
     * @param user String que indica el nom de l'usuari.
     * @return Retorna True si s'ha esborrat, False altrament.
     * @Pre Cert.
     * @Post Si el usuari pertany a la Base de Dades la tupla es borrada.
     */
    public boolean deleteUser(String user) {
        boolean ret = false;
        if(connect(true)) {
            try{
                String sql = "DELETE FROM USUARIS WHERE NAME = '"+user+"';";
                int m = getStatement().executeUpdate(sql);
                if (m>0) ret = true;                
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            disconnect();
        }
        return ret;
    }
    
    /**
     * Canviar contrasenya.
     * @param user String que indica el nom de l'usuari.
     * @param newPass String codificada en MD5 que indica la nova contrasenya .
     * @return Retorna True si s'ha canviat la contrasenya, False altrament.
     * @Pre Cert.
     * @Post Si el usuari pertany a la Base de Dades la tupla es borrada.
     */
    public boolean changePass(String user, String newPass) {
        boolean do_it = false;
        if(connect(true)){
            try {
                String sql = "UPDATE USUARIS SET PASSWORD = '"+newPass+"' WHERE NAME = '"+user+"';";
                int m = getStatement().executeUpdate(sql);
                if (m > 0) do_it = true;
            } 
            catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage()); 
            }
            disconnect();
        }
        return do_it;
    }
    
    /**
     * Escriu tots els usuaris existents de la Base de Dades.
     * @Pre Cert.
     * @Post Escriu tots els usuaris existents a la Base de Dades.
     */
    public void writeDB() {
        if(connect(true)){
            try {
                String sql = "SELECT * FROM USUARIS;";
                ResultSet rs = getStatement().executeQuery(sql);
                System.out.println();
                System.out.println("Contingut de la BD:");
                while (rs.next()) {
                    String name = rs.getString(1);
                    String pass = rs.getString(2);
                    System.out.println("Nom: " + name);
                    System.out.println("Password: " + pass);
                }
                rs.close();
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
    }
    
    /**
     * Esborra tots els usuaris de la Base de Dades.
     * @Pre Cert.
     * @Post La taula usuaris conté 0 tuples.
     */
    public void cleanDB() {
        if(connect(true)){
            try {
                String sql = "DELETE from USUARIS;";
                getStatement().executeUpdate(sql);
            } catch (Exception e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
            }
            disconnect();
        }
    }
}
