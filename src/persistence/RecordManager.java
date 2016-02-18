/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.StatisticsDomainCtrl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author dani.criado.casas
 */
public class RecordManager extends PersistenceCtrl {

    /**
    * Única instància de la classe per implementar patró singleton.
    */
    private static RecordManager recordManager;
    
    /**
     * Identificador del nom de la taula record
     */
    private static final String TABLE_NAME = "record";
    
    /**
     * Identificador de l'atribut type
     */
    private static final String ATTR_TYPE = StatisticsDomainCtrl.RECORD_TYPE;
    
    /**
     * Identificador de l'atribut user
     */
    private static final String ATTR_USER = StatisticsDomainCtrl.RECORD_USER;
    
    /**
     * Identificador de l'atribut value
     */
    private static final String ATTR_VALUE = StatisticsDomainCtrl.RECORD_VALUE;

    /**
     * Creador per defecte privat.
     * @param gameName Identificador del nom del joc
     */
    private RecordManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     * @param gameName Identificador del nom del joc
     * @pre Cert.
     * @post Crea una nova instancia de RecordManager si no existeix.
     * @return retorna recordManager.
     */
    public static RecordManager getInstance(String gameName) {
        if (recordManager == null) {
            recordManager = new RecordManager(gameName);
        }
        return recordManager;
    }

    @Override
    /**
     * Crea una taula que conté els récords globals a la base de dades
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_TYPE + " TEXT PRIMARY KEY  NOT NULL, "
                        + ATTR_USER + "       TEXT                      , "
                        + ATTR_VALUE + "      TEXT                      , "
                        + "FOREIGN KEY(" + ATTR_USER + ") REFERENCES USUARIS(NAME)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE NO ACTION);";
                Statement stmt = this.getStatement();
                stmt.executeUpdate(sql);
                //Comprovar que es primer cop i encara no hi ha registres.
                sql = "SELECT count(*) FROM " + TABLE_NAME + ";";
                stmt.executeQuery(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next() && rs.getInt(1) == 0) {
                    this.initTable();
                }
                rs.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                JOptionPane.showMessageDialog(null, "No s'ha pogut crear la taula " + TABLE_NAME.toUpperCase(), "Excepció", JOptionPane.ERROR_MESSAGE);
            } finally {
                this.disconnect();
            }
        }

    }

    /**
     * Inicialitza la taula
     * @Pre Cert
     * @Post La taula de récords ha sigut inicialitzada
     * @throws SQLException 
     */
    private void initTable() throws SQLException {
        ArrayList<String> records = new ArrayList<>();
        records.add("CONSTANT");
        records.add("CREATOR");
        records.add("FASTEST");
        records.add("LOSER");
        records.add("PLAYER");
        records.add("SCORER");
        records.add("TIMER");
        records.add("WINNER");
        String sql = "INSERT INTO " + TABLE_NAME + " (" + ATTR_TYPE + ") values ";
        for (String record : records) {
            sql += "('" + record + "'),";
        }
        
        sql = sql.substring(0, sql.length() - 1);//Eliminem la ultima coma
        sql += ";";
        Statement stmt = this.getStatement();
        stmt.executeUpdate(sql);
    }

    /**
     * Actualitza tots els récords de la taula
     * @param records conté els nous récords a actualitzar
     * @pre records no és null
     * @post La taula ha sigut actualitzada
     */
    public void updateAll(HashSet<HashMap<String, Object>> records) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                String sql = "UPDATE " + TABLE_NAME
                        + " SET " + ATTR_USER + " = ?, " + ATTR_VALUE + " = ? "
                        + "WHERE " + ATTR_TYPE + " = ? "
                        + "AND (" + ATTR_USER + " <> ? OR "+ ATTR_USER+ " IS NULL "
                        + "OR " + ATTR_VALUE + " <> ?);";
                PreparedStatement ps;
                ps = this.getConnection().prepareStatement(sql);
                for (HashMap<String, Object> rElement : records) {
                    String type = (String) rElement.get(ATTR_TYPE);
                    String user = (String) rElement.get(ATTR_USER);
                    String value = (String) rElement.get(ATTR_VALUE);
                    
                    ps.setString(1, user);
                    ps.setString(2, value);
                    ps.setString(3, type);
                    ps.setString(4, user);
                    ps.setString(5, value);
                    ps.executeUpdate();
                }
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                System.out.println("RECORD");
            } finally {
                this.disconnect();
            }
        }
    }

    /**
     * Obté tots els récords de la taula
     * @pre Cert
     * @return retorna un HashMap que conté tots els récords de la base de dades
     */
    public HashMap<String, HashMap<String, Object>> getAll() {
        HashMap<String, HashMap<String, Object>> records = null;
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                ResultSet rs;
                records = new HashMap<>();
                String sql = "SELECT * FROM " + TABLE_NAME +";";
                rs = stmt.executeQuery(sql);
                HashMap<String, Object> attrs;
                while (rs.next()) {
                    attrs = new HashMap<>();
                    attrs.put(ATTR_USER, rs.getString(ATTR_USER));
                    attrs.put(ATTR_VALUE, rs.getString(ATTR_VALUE));
                    records.put(rs.getString(ATTR_TYPE), attrs);
                }
                rs.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return records;
    }
}
