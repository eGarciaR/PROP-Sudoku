/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.GameDomainCtrl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.ResultSet;

/**
 *
 * @author eric
 */
public class GameManager extends PersistenceCtrl {
    
    /**
    * Única instància de la classe per implementar patró singleton.
    */
    private static GameManager gameManager;
    
    /**
     * Identificador del nom de la taula saved_games
     */
    private static final String TABLE_NAME = "saved_games";
    
    /**
     * Identificador de l'atribut user
     */
    private static final String ATTR_USER = GameDomainCtrl.ATTR_USER;
    
    /**
     * Identificador de l'atribut errors
     */
    private static final String ATTR_ERRORS = GameDomainCtrl.ATTR_ERRORS;
    
    /**
     * Identificador de l'atribut time
     */
    private static final String ATTR_TIME = GameDomainCtrl.ATTR_TIME;
    
    /**
     * Identificador de l'atribut helpLevel
     */
    private static final String ATTR_HELP_LEVEL = GameDomainCtrl.ATTR_HELP_LEVEL;
    
    /**
     * Identificador de l'atribut boardId
     */
    private static final String ATTR_BOARD_ID= GameDomainCtrl.ATTR_BOARD_ID;
    
    /**
     * Identificador de l'atribut board
     */
    private static final String ATTR_BOARD = GameDomainCtrl.ATTR_BOARD;
    
    /**
     * Creador per defecte privat.
     * @param gameName Identificador del nom del joc
     */
    GameManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     * @param gameName Identificador del nom del joc
     * @Pre Cert.
     * @Post Crea una nova instancia de GameManager si no existeix.
     * @return retorna gameManager.
     */
    public static GameManager getInstance(String gameName) {
        if (gameManager == null) {
            gameManager = new GameManager(gameName);
        }
        return gameManager;
    }
    
    @Override
    /**
     * Crea una taula que conté la partida salvada d'un usuari a la base de dades
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_USER + "       TEXT PRIMARY KEY     NOT NULL,"
                        + ATTR_ERRORS + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_TIME + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_HELP_LEVEL + "        TEXT              NOT NULL  DEFAULT 0,"
                        + ATTR_BOARD_ID + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_BOARD + "        TEXT                 NOT NULL,"
                        + "FOREIGN KEY(" + ATTR_USER + ") REFERENCES USUARIS(NAME)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE CASCADE, "
                        + "FOREIGN KEY(" + ATTR_BOARD_ID + ") REFERENCES board(id)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE CASCADE);";
                Statement stmt = this.getStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
    }
       
    /**
     * Afegeix una partida salvada a la base de dades
     * @param attrs Elements de la partida salvada
     * @Pre attrs no és null
     * @Post S'afegeix a la base de dades una partida salvada
     */
    public void add(HashMap<String, Object> attrs) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "INSERT INTO " + TABLE_NAME 
                        + "(" + ATTR_BOARD + ", " + ATTR_BOARD_ID + ", "
                        + ATTR_ERRORS + ", " 
                        + ATTR_HELP_LEVEL + ", " + ATTR_TIME + ", "
                        + ATTR_USER + ") " + "VALUES (?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, (String) attrs.get(ATTR_BOARD));
                ps.setInt(2, (int) attrs.get(ATTR_BOARD_ID));
                ps.setInt(3, (int) attrs.get(ATTR_ERRORS));
                ps.setString(4, (String) attrs.get(ATTR_HELP_LEVEL));
                ps.setLong(5, (long) attrs.get(ATTR_TIME));
                ps.setString(6, (String) attrs.get(ATTR_USER));
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
    }
    
    /**
     * Indica si l'usuari ja té una partida salvada
     * @param user Identifica l'usuari
     * @Pre user no és null
     * @return retorna true si l'usuari té una partida salvada, false altrament
     */
    public boolean savedGame(String user) {
        boolean ret = false;
        if (this.connect(true)) {
            try {
                String sql = "SELECT count(*) FROM " + TABLE_NAME + " WHERE " + ATTR_USER + " = ?;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int n = rs.getInt(1);
                    if(n > 0) ret = true;
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return ret;
    }
    
    /**
     * Obté la partida salvada d'un usuari
     * @param user Identifica l'usuari
     * @Pre user no és null
     * @return retorna un HashMap amb la informació de la partida salvada
     */
    public HashMap<String, Object> get(String user) {
        HashMap<String, Object> attrs = null;
        if (this.connect(true)) {
            try {
                PreparedStatement ps;
                ResultSet rs;
                attrs = new HashMap<>();
                attrs.put(ATTR_USER, user);
                String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ATTR_USER + " = ?;";
                ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                rs = ps.executeQuery();
                if (rs.next()) {
                    attrs.put(ATTR_BOARD, rs.getString(ATTR_BOARD));
                    attrs.put(ATTR_BOARD_ID, rs.getInt(ATTR_BOARD_ID));
                    attrs.put(ATTR_ERRORS, rs.getInt(ATTR_ERRORS));
                    attrs.put(ATTR_HELP_LEVEL, rs.getString(ATTR_HELP_LEVEL));
                    attrs.put(ATTR_TIME, rs.getLong(ATTR_TIME));
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return attrs;
    }
    
    /**
     * Elimina una partida salvada de l'usuari
     * @param user Identifica l'usuari
     * @Pre user no es null
     * @Post La partida salvada de l'usuari user ha sigut eliminada
     */
    public void delete(String user) {
        if (this.connect(true)) {
            try {
                String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ATTR_USER + " = ?;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
    }
    
}
