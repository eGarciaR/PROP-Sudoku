
package persistence;

import java.sql.*;

/**
 * Classe de control de gestionar la persistensia a trabes de Bases de Dades.
 * @author Joan Monge Tomàs
 * @author Aitor Romero Reviriego
 */
public abstract class PersistenceCtrl {
    /**
     * Indica el nom de la Base de Dades.
     */
    private final String dbName;
    
    /**
     * Ruta on es guardarà la Base de Dades.
     */
    private final String userHome;
    
    /**
     * Indica si existeix una connexió.
     */
    private Connection connection = null;
    
    /**
     * En stmt es crean els Statement.
     */
    private Statement stmt = null;
    
    /**
     * Constructora per defecte.
     * @param gameName String que identifica el nom del Joc.
     * @Pre Cert.
     * @Post Cert.
     */
    public PersistenceCtrl(String gameName){
        this.dbName = gameName+".db";
        this.userHome = System.getProperty("user.home");
    }
    
    /**
     * Permet utilitzar el Connection.
     * @return El Connection de la base de dades.
     * @Pre connection esta inicialitzat.
     * @Post Cert.
     */
    protected Connection getConnection() {
        return connection;
    }
    
    /**
     * Permet utilitzar el Statement.
     * @return El Statement de la conexió amb la base de dades.
     * @Pre stmt esta inicialitzat amb una conexió.
     * @Post Cert.
     */
    protected Statement getStatement() {
        return stmt;
    }
    
    /**
     * @Pre Cert.
     * @Post Es crea la taula segons la subclasse que la especifiqui.
     */
    public abstract void createTable();
    
    /**
     * Mètode per connectar-se a la base de dades.
     * @param createStatment Boolea que informa si es vol crear un Statment o s'utilitzara un PreparedStatment
     * @return Retorna True si es connecta a la base de dades, altrement False.
     * @Pre Cert.
     * @Post Cert.
     */
    public boolean connect(boolean createStatment){
        String db = userHome+"/"+dbName;
        try {
          Class.forName("org.sqlite.JDBC");
          connection = DriverManager.getConnection("jdbc:sqlite:"+db);
          if(createStatment) stmt = connection.createStatement();
        } 
        catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }
        return true;
    }
    
    /**
     * Mètode per desconnectr-se de la base de dades.
     * @Pre Està connectat a la Base de Dades.
     * @Post Es desconnecta de la Base de Dades.
     */
    public void disconnect(){
        try {
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
        }
    }
}

