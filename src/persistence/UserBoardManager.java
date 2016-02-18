/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author dani.criado.casas
 */
public class UserBoardManager extends PersistenceCtrl {

    /**
     * Única instància de la classe per implementar patró singleton.
     */
    private static UserBoardManager boardManager;

    /**
     * Identificador del nom de la taula user_board
     */
    private static final String TABLE_NAME = "user_board";

    /**
     * Identificador del nom de la taula board
     */
    private static final String TABLE_BOARD = "board";

    /**
     * Identificador de l'atribut id
     */
    private static final String ATTR_ID = "id";

    /**
     * Identificador de l'atribut user
     */
    private static final String ATTR_USER = "user";

    /**
     * Identificador de l'atribut board_id
     */
    public static final String ATTR_BOARD_ID = "board_id";

    /**
     * Creador per defecte privat.
     *
     * @param gameName Identificador del nom del joc
     */
    private UserBoardManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     *
     * @param gameName Identificador del nom del joc
     * @pre Cert.
     * @post Crea una nova instancia de userBoardManager si no existeix.
     * @return retorna boardManager.
     */
    public static UserBoardManager getInstance(String gameName) {
        if (boardManager == null) {
            boardManager = new UserBoardManager(gameName);
        }
        return boardManager;
    }

    @Override
    /**
     * Crea una taula que conté els taulers jugats per un usuari a la Base de
     * Dades.
     *
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_USER + "             TEXT NOT NULL, "
                        + ATTR_BOARD_ID + "           TEXT NOT NULL, "
                        + "PRIMARY KEY (" + ATTR_USER + ", " + ATTR_BOARD_ID + "), "
                        + "FOREIGN KEY(" + ATTR_USER + ") REFERENCES USUARIS(NAME) "
                        + "ON UPDATE CASCADE "
                        + "ON DELETE CASCADE, "
                        + "FOREIGN KEY(" + ATTR_BOARD_ID + ") REFERENCES " + TABLE_BOARD + " (" + ATTR_ID + ") "
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
     * Afegeix l'identificador del tauler jugat per un usuari a la base de dades
     *
     * @param board Conté l'usuari que ha jugat el tauler a guardar.
     * @pre board no és null
     * @post S'afegeix una nova parella de usuari-boardId a la base de dades.
     */
    public void add(HashMap<String, Object> board) {
        if (this.connect(true)) {
            try {
                String user = (String) board.get(ATTR_USER);
                int boardId = (int) board.get(ATTR_BOARD_ID);
                Statement stmt = this.getStatement();
                String sql = "SELECT count(*) FROM " + TABLE_NAME + " WHERE " + ATTR_USER + " = ? AND " + ATTR_BOARD_ID + "=?;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                ps.setInt(2, boardId);
                ResultSet rs = ps.executeQuery();
                int exists = 0;
                while (rs.next()) {
                    exists = rs.getInt(1);
                }
                rs.close();
                if (exists == 0) {
                    stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                    sql = "INSERT INTO " + TABLE_NAME + "(" + ATTR_USER + ", " + ATTR_BOARD_ID + ") VALUES (?, ?);";
                    ps = this.getConnection().prepareStatement(sql);
                    ps.setString(1, (String) board.get(ATTR_USER));
                    ps.setInt(2, (int) board.get(ATTR_BOARD_ID));
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
    }

    /**
     * S'obté els identificadors dels taulers jugats per user
     *
     * @param user Indica el nom de l'usuari.
     * @pre user no es null
     * @return retorna un HashSet amb els identificadors de tauler jugats per
     * user
     */
    public HashSet<Integer> getAllByUser(String user) {
        HashSet<Integer> boards = null;
        if (this.connect(true)) {
            try {
                ResultSet rs;
                boards = new HashSet<>();
                String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ATTR_USER + "= ?;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, user);
                rs = ps.executeQuery();
                while (rs.next()) {
                    boards.add(rs.getInt(ATTR_BOARD_ID));
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return boards;
    }
}
