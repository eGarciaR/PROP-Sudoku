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
import javax.swing.JOptionPane;

/**
 *
 * @author dani.criado.casas
 */
public class RankingManager extends PersistenceCtrl {

    /**
     * Única instància de la classe per implementar patró singleton.
     */
    private static RankingManager rankingManager;

    /**
     * Identificador del nom de la taula ranking
     */
    private static final String TABLE_NAME = "ranking";

    /**
     * Identificador de l'atribut position
     */
    private static final String ATTR_POSITION = StatisticsDomainCtrl.RANKING_POSITION;

    /**
     * Identificador de l'atribut user
     */
    private static final String ATTR_USER = StatisticsDomainCtrl.RANKING_USER;

    /**
     * Identificador de l'atribut score
     */
    private static final String ATTR_SCORE = StatisticsDomainCtrl.RANKING_SCORE;

    /**
     * Indica el nombre maxim d'elements que pot tindre el ranking
     */
    private static final int NUM_ELEMENTS_RANKING = 15;

    /**
     * Creador per defecte privat.
     *
     * @param gameName Identificador del nom del joc
     */
    private RankingManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     *
     * @param gameName Identificador del nom del joc
     * @Pre Cert.
     * @Post Crea una nova instancia de RankingManager si no existeix.
     * @return retorna rankingManager.
     */
    public static RankingManager getInstance(String gameName) {
        if (rankingManager == null) {
            rankingManager = new RankingManager(gameName);
        }
        return rankingManager;
    }

    @Override
    /**
     * Crea una taula que conté el ranking a la base de dades
     *
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_POSITION + " INTEGER PRIMARY KEY  NOT NULL  "
                        + "CHECK(1 <= " + ATTR_POSITION + " AND " + ATTR_POSITION + " <= " + NUM_ELEMENTS_RANKING + "),"
                        + ATTR_USER + "        TEXT                 ,"
                        + ATTR_SCORE + "        INTEGER              DEFAULT 0,"
                        + "FOREIGN KEY(" + ATTR_USER + ") REFERENCES USUARIS(NAME)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE NO ACTION);";
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
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
     *
     * @Pre Cert
     * @Post La taula ha quedat inicialitzada
     * @throws SQLException
     */
    private void initTable() throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + ATTR_POSITION + ") values ";
        for (int i = 1; i <= NUM_ELEMENTS_RANKING; i++) {
            sql += "(" + i + "),";
        }
        sql = sql.substring(0, sql.length() - 1);//Eliminem la ultima coma
        sql += ";";
        Statement stmt = this.getStatement();
        stmt.executeUpdate(sql);
    }

    /**
     * Actualitza el ranking
     *
     * @param ranking Nou ranking a actualitzar
     * @pre ranking no és null
     * @post El ranking de la base de dades ha quedat actualitzat
     */
    public void updateAll(ArrayList<HashMap<String, Object>> ranking) {
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");

                String sql = "UPDATE " + TABLE_NAME
                        + " SET " + ATTR_USER + " = ?, " + ATTR_SCORE + " = ? "
                        + "WHERE " + ATTR_POSITION + " = ? "
                        + "AND (" + ATTR_USER + " <> ? OR " + ATTR_USER + " IS NULL "
                        + "OR " + ATTR_SCORE + " <> ?);";
                PreparedStatement ps;
                ps = this.getConnection().prepareStatement(sql);
                for (HashMap<String, Object> rElement : ranking) {
                    int position = (int) rElement.get(ATTR_POSITION);
                    String user = (String) rElement.get(ATTR_USER);
                    int score = (int) rElement.get(ATTR_SCORE);

                    ps.setString(1, user);
                    ps.setInt(2, score);
                    ps.setInt(3, position);
                    ps.setString(4, user);
                    ps.setInt(5, score);
                    ps.executeUpdate();
                }
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                System.out.println("RANKING");
            } finally {
                this.disconnect();
            }
        }
    }

    /**
     * Obté el ranking de la base de dades
     *
     * @pre Cert
     * @return retorna un ArrayList amb la llista del ranking
     */
    public ArrayList<HashMap<String, Object>> getAll() {
        ArrayList<HashMap<String, Object>> ranking = null;
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                ResultSet rs;
                ranking = new ArrayList<>();
                String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ATTR_POSITION + " ASC;";
                rs = stmt.executeQuery(sql);
                HashMap<String, Object> attrs;
                while (rs.next()) {
                    attrs = new HashMap<>();
                    attrs.put(ATTR_POSITION, rs.getInt(ATTR_POSITION));
                    attrs.put(ATTR_USER, rs.getString(ATTR_USER));
                    attrs.put(ATTR_SCORE, rs.getInt(ATTR_SCORE));
                    ranking.add(attrs);
                }
                rs.close();
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            } finally {
                this.disconnect();
            }
        }
        return ranking;
    }
}
