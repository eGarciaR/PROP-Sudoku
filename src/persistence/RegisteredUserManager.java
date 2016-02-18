/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.RegisteredUserDomainCtrl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author eric
 */
public class RegisteredUserManager extends PersistenceCtrl {

    /**
    * Única instància de la classe per implementar patró singleton.
    */
    private static RegisteredUserManager userManager;
    
    /**
     * Identificador del nom de la taula registered_user
     */
    private static final String TABLE_NAME = "registered_user";
    
    /**
     * Identificador de l'atribut user
     */
    private static final String ATTR_USER = RegisteredUserDomainCtrl.ATTR_USER;
    
    /**
     * Identificador de l'atribut gamesPlayer
     */
    private static final String ATTR_GAMES_PLAYED = RegisteredUserDomainCtrl.ATTR_GAMES_PLAYED;
    
    /**
     * Identificador de l'atribut gamesWon
     */
    private static final String ATTR_GAMES_WON = RegisteredUserDomainCtrl.ATTR_GAMES_WON;
    
    /**
     * Identificador de l'atribut totalTime
     */
    private static final String ATTR_TOTAL_TIME = RegisteredUserDomainCtrl.ATTR_TOTAL_TIME;
    
    /**
     * Identificador de l'atribut totalScore
     */
    private static final String ATTR_TOTAL_SCORE = RegisteredUserDomainCtrl.ATTR_TOTAL_SCORE;
    
    /**
     * Identificador de l'atribut bestTime
     */
    private static final String ATTR_BEST_TIME = RegisteredUserDomainCtrl.ATTR_BEST_TIME;
    
    /**
     * Identificador de l'atribut boardsCreated
     */
    private static final String ATTR_BOARDS_CREATED = RegisteredUserDomainCtrl.ATTR_BOARDS_CREATED;

    /**
     * Creador per defecte privat.
     * @param gameName Identificador del nom del joc
     */
    private RegisteredUserManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     * @param gameName Identificador del nom del joc
     * @pre Cert.
     * @post Crea una nova instancia de RegisteredUserManager si no existeix.
     * @return retorna userManager.
     */
    public static RegisteredUserManager getInstance(String gameName) {
        if (userManager == null) {
            userManager = new RegisteredUserManager(gameName);
        }
        return userManager;
    }

    @Override
    /**
     * Crea una taula que conté els usuaris registrats a la base de dades
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_USER + "       TEXT PRIMARY KEY     NOT NULL,"
                        + ATTR_GAMES_PLAYED + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_GAMES_WON + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_TOTAL_TIME + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_TOTAL_SCORE + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_BEST_TIME + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + ATTR_BOARDS_CREATED + "        INTEGER              NOT NULL  DEFAULT 0,"
                        + "FOREIGN KEY(" + ATTR_USER + ") REFERENCES USUARIS(NAME)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE CASCADE);";
                Statement stmt = this.getStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "No s'ha pogut crear la taula " + TABLE_NAME.toUpperCase(), "Excepció", JOptionPane.ERROR_MESSAGE);
            } finally {
                this.disconnect();
            }
        }

    }

    /**
     * Afegeix un usuari registrat a la base de dades
     * @param user Identifica l'usuari a guardar
     * @pre user no és null
     * @post S'afegeix un usuari registrat a la base de dades
     * @return retorna true si s'ha afegit correctament
     */
    public boolean add(String user) {
        boolean added = false;
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "INSERT INTO " + TABLE_NAME + " (" + ATTR_USER + ") VALUES (?);";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                int a = ps.executeUpdate();
                if (a > 0) added = true;
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return added;

    }

    /**
     * Actualitza la base de dades amb els atributs d'un usuari
     * @param user Identifica l'usuari a actualitzar
     * @param attrs Conté els atributs a actualitzar
     * @pre user no és null, attrs no és null
     * @post s'actualitzen els atributs de l'usuari user
     */
    public void update(String user, HashMap<String, Object> attrs) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "UPDATE " + TABLE_NAME
                        + " SET " + ATTR_BEST_TIME + " = ?, "
                        + ATTR_BOARDS_CREATED + " = ?, "
                        + ATTR_GAMES_PLAYED + " = ?, "
                        + ATTR_GAMES_WON + " = ?, "
                        + ATTR_TOTAL_SCORE + " = ?, "
                        + ATTR_TOTAL_TIME + " = ? "
                        + " WHERE " + ATTR_USER + " = ?; ";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setLong(1, (long) attrs.get(ATTR_BEST_TIME));
                ps.setInt(2, (int) attrs.get(ATTR_BOARDS_CREATED));
                ps.setInt(3, (int) attrs.get(ATTR_GAMES_PLAYED));
                ps.setInt(4, (int) attrs.get(ATTR_GAMES_WON));
                ps.setInt(5, (int) attrs.get(ATTR_TOTAL_SCORE));
                ps.setLong(6, (long) attrs.get(ATTR_TOTAL_TIME));
                ps.setString(7, user);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                System.out.println("rum Update");
            } finally {
                this.disconnect();
            }
        }
    }
    
    public void incGamesPlayed(String user) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "UPDATE " + TABLE_NAME
                        + " SET " + ATTR_GAMES_PLAYED + " = " + ATTR_GAMES_PLAYED + " + 1 "
                        + " WHERE " + ATTR_USER + " = ?; ";
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
    
    public void incBoardsCreated(String user) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "UPDATE " + TABLE_NAME
                        + " SET " + ATTR_BOARDS_CREATED + " = " + ATTR_BOARDS_CREATED + " + 1 "
                        + " WHERE " + ATTR_USER + " = ?; ";
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

    /**
     * S'obté l'usuari registrat amb els seus atributs
     * @param user Identifica l'usuari
     * @Pre user no és null
     * @return retorna un HashMap amb tots els atributs de l'usuari
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
                    attrs.put(ATTR_GAMES_PLAYED, rs.getInt(ATTR_GAMES_PLAYED));
                    attrs.put(ATTR_GAMES_WON, rs.getInt(ATTR_GAMES_WON));
                    attrs.put(ATTR_TOTAL_TIME, rs.getLong(ATTR_TOTAL_TIME));
                    attrs.put(ATTR_TOTAL_SCORE, rs.getInt(ATTR_TOTAL_SCORE));
                    attrs.put(ATTR_BEST_TIME, rs.getLong(ATTR_BEST_TIME));
                    attrs.put(ATTR_BOARDS_CREATED, rs.getInt(ATTR_BOARDS_CREATED));
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
     * Elimina un usuari de la base de dades
     * @param user Identificador de l'usuari 
     * @pre user no és null
     * @post L'usuari user ha sigut eliminat de la base de dades
     * @return retorna true si s'ha eliminat, false altrament
     */
    public boolean delete(String user) {
        boolean res = false;
        if (this.connect(true)) {
            try {
                String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ATTR_USER + " = ?;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                int d = ps.executeUpdate();
                if (d > 0) res = true;
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return res;
    }
}
