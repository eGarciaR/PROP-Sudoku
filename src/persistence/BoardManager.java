/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.BoardDomainCtrl;
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
public class BoardManager extends PersistenceCtrl {

    /**
    * Única instància de la classe per implementar patró singleton.
    */
    private static BoardManager boardManager;
    
    /**
     * Identificador del nom de la taula board
     */
    private static final String TABLE_NAME = "board";
    
    /**
     * Identificador de l'atribut id
     */
    private static final String ATTR_ID = BoardDomainCtrl.BOARD_ID;
    
    /**
     * Identificador de l'atribut difficulty
     */
    private static final String ATTR_DIFFICULTY = BoardDomainCtrl.BOARD_DIFFICULTY;
    
    /**
     * Identificador de l'atribut n
     */
    private static final String ATTR_N = BoardDomainCtrl.BOARD_N;
    
    /**
     * Identificador de l'atribut nNumbers
     */
    private static final String ATTR_N_NUMBERS = BoardDomainCtrl.BOARD_N_NUMBERS;
    
    /**
     * Identificador de l'atribut board
     */
    private static final String ATTR_BOARD = BoardDomainCtrl.BOARD_BOARD;
    
    /**
     * Identificador de l'atribut creator
     */
    private static final String ATTR_CREATOR = "creator";
    
    /**
     * Mida máxima
     */
    private static final String MAX_N = "7";

    /**
     * Creador per defecte privat.
     * @param gameName Identificador del nom del joc
     */
    private BoardManager(String gameName) {
        super(gameName);
        createTable();
    }

    /**
     * Única instància de la classe per implementar patró singleton.
     * @param gameName Identificador del nom del joc
     * @return Instància de BoardManager
     * @Pre Cert.
     * @Post Crea una boardManager.
     */
    public static BoardManager getInstance(String gameName) {
        if (boardManager == null) {
            boardManager = new BoardManager(gameName);
        }
        return boardManager;
    }

    @Override
    /**
     * Crea una taula que conté tots els taulers a la base de dades
     * @Pre Cert.
     * @Post Es crea una taula a la Base de Dades si no existeix.
     */
    public void createTable() {
        if (this.connect(true)) {
            try {
                String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "(" + ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ATTR_DIFFICULTY + " TEXT NOT NULL, "
                        + ATTR_N + " INTEGER NOT NULL "
                        + "CHECK(1 <= " + ATTR_N + " AND " + ATTR_N + " < " + MAX_N + "), "
                        + ATTR_N_NUMBERS + " INTEGER NOT NULL, "
                        + ATTR_BOARD + " TEXT NOT NULL, "
                        + ATTR_CREATOR + " TEXT, "
                        + "FOREIGN KEY(" + ATTR_CREATOR + ") REFERENCES USUARIS(NAME)"
                        + "ON UPDATE CASCADE "
                        + "ON DELETE SET NULL);";
                Statement stmt = this.getStatement();
                stmt.executeUpdate(sql);
                //Comprovar que es primer cop i encara no hi ha registres.
                sql = "SELECT count(*) FROM " + TABLE_NAME + ";";
                stmt.executeQuery(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next() && rs.getInt(1) == 0) {
                    this.initSudokus();
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
     * Inicialitza els taulers de la base de dades
     * @Pre Cert
     * @Post Els taulers han sigut inicialitzats
     * @throws SQLException 
     */
    private void initSudokus() throws SQLException {
        //TODO Crear Sudokus per defecte.
        Statement stmt = this.getStatement();
        stmt.executeUpdate("PRAGMA foreign_keys = ON;");
        String sql = "INSERT INTO " + TABLE_NAME + "(" + ATTR_DIFFICULTY + ", " + ATTR_N + ", " + ATTR_N_NUMBERS + ", " + ATTR_BOARD + " ) VALUES";
        sql += "('EASY', 2, 7,'1F,0V,3F,4F|3F,0V,0V,0V|0V,0V,4F,0V|0V,3F,0V,1F'),";
        sql += "('EASY', 2, 7,'0V,3F,0V,1F|2F,1F,0V,0V|3F,0V,0V,0V|1F,0V,0V,4F'),";
        sql += "('EASY', 2, 7,'0V,0V,0V,0V|3F,4F,0V,0V|2F,1F,4F,0V|4F,0V,2F,0V'),";
        sql += "('EASY', 2, 7,'1F,3F,0V,4F|0V,4F,0V,0V|0V,1F,4F,0V|0V,0V,3F,0V'),";
        sql += "('EASY', 2, 7,'1F,0V,3F,4F|0V,0V,0V,2F|0V,0V,4F,0V|0V,3F,2F,0V'),";
        sql += "('EASY', 2, 7,'0V,0V,3F,4F|0V,0V,0V,2F|2F,1F,4F,0V|4F,0V,0V,0V'),";
        sql += "('EASY', 2, 7,'0V,2F,0V,4F|3F,0V,1F,0V|0V,0V,4F,3F|0V,0V,0V,1F'),";
        sql += "('EASY', 2, 7,'0V,3F,0V,0V|2F,0V,4F,0V|3F,4F,0V,2F|0V,0V,0V,4F'),";
        sql += "('EASY', 2, 7,'1F,0V,0V,0V|0V,0V,0V,3F|3F,0V,4F,2F|0V,2F,3F,0V'),";
        sql += "('EASY', 2, 7,'0V,0V,0V,4F|2F,0V,0V,3F|3F,0V,4F,2F|4F,0V,0V,0V'),";
        sql += "('EASY', 2, 7,'1F,2F,0V,0V|0V,0V,0V,0V|0V,1F,4F,3F|0V,3F,2F,0V'),";
        sql += "('EASY', 2, 7,'1F,0V,3F,0V|3F,0V,1F,2F|2F,0V,0V,0V|0V,3F,0V,0V'),";
        sql += "('EASY', 2, 7,'0V,0V,0V,4F|3F,4F,0V,2F|2F,1F,4F,0V|0V,0V,0V,0V'),";
        sql += "('EASY', 2, 7,'4F,0V,2F,0V|2F,1F,0V,0V|0V,0V,0V,0V|1F,2F,0V,4F'),";
        sql += "('EASY', 2, 7,'0V,0V,3F,4F|0V,4F,1F,0V|2F,1F,0V,0V|0V,0V,0V,1F'),";
        sql += "('EASY', 2, 7,'0V,2F,0V,4F|3F,0V,1F,2F|0V,0V,0V,0V|4F,0V,0V,1F'),";
        sql += "('EASY', 2, 7,'0V,0V,2F,4F|2F,0V,0V,0V|3F,0V,4F,0V|0V,2F,3F,0V'),";
        sql += "('EASY', 2, 7,'1F,0V,0V,4F|3F,0V,1F,0V|2F,1F,0V,0V|0V,0V,0V,1F'),";
        
        
        sql += "('EASY', 3, 36, '0V,0V,3F,0V,5F,6F,7F,8F,0V|0V,0V,0V,0V,8F,9F,1F,0V,0V|0V,0V,9F,1F,0V,3F,0V,0V,0V|0V,1F,0V,0V,0V,5F,8F,9F,7F|0V,0V,5F,0V,9F,7F,0V,0V,4F|8F,0V,0V,2F,1F,0V,3F,6F,5F|0V,3F,0V,0V,4F,0V,0V,0V,0V|6F,0V,2F,0V,7F,8F,5F,0V,1F|0V,0V,0V,0V,0V,0V,6F,0V,2F'),";
        sql += "('EASY', 3, 36, '1F,2F,3F,4F,0V,0V,7F,0V,0V|0V,5F,0V,0V,8F,0V,1F,0V,3F|0V,0V,0V,1F,0V,3F,0V,0V,6F|2F,0V,4F,0V,6F,5F,8F,9F,0V|3F,0V,0V,8F,0V,0V,0V,0V,4F|0V,9F,0V,2F,0V,0V,3F,6F,0V|0V,0V,0V,0V,4F,0V,9F,0V,8F|6F,4F,2F,0V,7F,8F,0V,3F,0V|0V,0V,8F,0V,0V,0V,6F,0V,0V'),";
        sql += "('EASY', 4, 115, '1F,0V,0V,0V,2F,0V,0V,14F,0V,7F,11F,0V,0V,8F,12F,16F|2F,6F,0V,14F,1F,5F,9F,0V,4F,0V,12F,0V,0V,0V,11F,0V|3F,0V,0V,15F,0V,8F,0V,0V,0V,0V,0V,13F,0V,0V,0V,0V|4F,8F,12F,0V,0V,0V,0V,0V,2F,6F,10F,0V,0V,0V,9F,13F|0V,0V,0V,9F,0V,2F,0V,10F,7F,0V,0V,0V,8F,0V,0V,0V|6F,2F,0V,0V,5F,1F,13F,0V,0V,0V,0V,12F,7F,3F,15F,11F|0V,0V,0V,0V,0V,0V,0V,12F,0V,1F,13F,0V,6F,0V,14F,0V|0V,4F,16F,0V,7F,0V,15F,0V,0V,0V,0V,0V,5F,1F,0V,9F|0V,13F,0V,0V,10F,0V,0V,6F,0V,15F,3F,0V,0V,16F,0V,0V|0V,0V,0V,6F,9F,0V,1F,0V,12F,16F,0V,0V,0V,0V,0V,7F|0V,15F,0V,7F,12F,0V,4F,0V,9F,13F,0V,5F,10F,0V,2F,6F|12F,0V,4F,0V,11F,15F,0V,7F,10F,0V,2F,0V,0V,0V,0V,5F|0V,0V,5F,1F,0V,0V,6F,0V,0V,0V,0V,3F,0V,0V,0V,4F|14F,0V,6F,0V,13F,9F,5F,0V,0V,12F,8F,0V,15F,11F,0V,0V|15F,11F,0V,0V,0V,0V,8F,4F,0V,0V,5F,0V,14F,10F,0V,2F|0V,0V,0V,0V,15F,11F,0V,0V,0V,0V,6F,2F,13F,9F,0V,1F'),";
        sql += "('EASY', 4, 115, '0V,5F,0V,13F,2F,0V,0V,0V,3F,7F,11F,0V,0V,8F,12F,0V|0V,6F,0V,0V,0V,0V,9F,13F,0V,8F,0V,16F,3F,0V,11F,0V|0V,7F,0V,15F,4F,8F,0V,16F,1F,0V,0V,0V,0V,0V,0V,14F|4F,0V,12F,0V,0V,7F,11F,0V,0V,6F,0V,14F,0V,0V,9F,0V|5F,0V,0V,9F,0V,0V,14F,10F,0V,0V,0V,0V,8F,4F,0V,12F|6F,2F,0V,10F,5F,0V,0V,9F,8F,0V,0V,0V,7F,0V,15F,0V|0V,0V,0V,11F,8F,0V,0V,0V,0V,1F,13F,9F,0V,2F,0V,0V|8F,4F,16F,0V,0V,0V,0V,0V,6F,2F,0V,0V,0V,1F,13F,0V|0V,13F,1F,0V,0V,14F,0V,0V,0V,0V,3F,7F,0V,0V,0V,8F|10F,0V,0V,0V,9F,0V,1F,5F,0V,0V,4F,0V,11F,15F,3F,0V|11F,0V,3F,7F,0V,16F,4F,0V,0V,13F,0V,0V,10F,0V,0V,0V|12F,0V,0V,8F,11F,15F,0V,0V,0V,14F,2F,0V,0V,0V,1F,5F|0V,0V,0V,0V,0V,10F,0V,2F,15F,11F,0V,0V,16F,0V,8F,0V|0V,10F,0V,2F,13F,9F,5F,1F,16F,0V,8F,4F,0V,11F,0V,0V|15F,0V,0V,0V,0V,0V,0V,4F,0V,0V,5F,1F,0V,10F,6F,0V|0V,0V,0V,0V,0V,11F,7F,3F,14F,0V,0V,2F,0V,9F,0V,1F'),";
        sql += "('EASY', 4, 115, '0V,0V,3F,0V,5F,0V,7F,0V,9F,0V,11F,12F,13F,14F,0V,16F|5F,6F,0V,8F,1F,0V,3F,4F,0V,0V,0V,0V,9F,10F,0V,0V|0V,10F,0V,12F,0V,0V,0V,0V,1F,2F,0V,0V,0V,6F,7F,0V|0V,14F,15F,0V,9F,10F,0V,0V,0V,0V,7F,0V,0V,0V,0V,4F|0V,1F,0V,0V,0V,0V,0V,0V,0V,0V,0V,11F,0V,0V,16F,15F|0V,0V,0V,7F,2F,0V,4F,3F,14F,0V,0V,15F,0V,9F,12F,11F|10F,0V,0V,0V,14F,13F,16F,0V,0V,0V,4F,3F,0V,5F,0V,0V|0V,0V,0V,15F,10F,0V,0V,11F,0V,5F,8F,7F,2F,0V,0V,0V|0V,0V,0V,2F,7F,8F,0V,0V,0V,12F,0V,0V,0V,16F,0V,14F|0V,0V,5F,0V,0V,0V,1F,2F,15F,0V,13F,14F,0V,12F,0V,0V|11F,12F,0V,0V,0V,16F,13F,0V,3F,0V,1F,0V,0V,0V,5F,6F|0V,16F,13F,14F,11F,0V,9F,0V,0V,8F,0V,6F,3F,4F,0V,0V|0V,3F,2F,1F,8F,7F,0V,5F,12F,0V,10F,0V,0V,0V,0V,0V|8F,0V,0V,0V,4F,3F,0V,0V,16F,0V,14F,0V,0V,11F,10F,9F|0V,11F,0V,9F,16F,0V,14F,13F,0V,0V,2F,0V,8F,0V,0V,0V|16F,15F,14F,0V,0V,0V,0V,0V,0V,0V,0V,5F,0V,3F,2F,0V'),";
        sql += "('EASY', 4, 115, '16F,0V,0V,4F,0V,11F,0V,0V,14F,0V,6F,0V,13F,0V,5F,0V|0V,0V,0V,0V,16F,0V,8F,0V,13F,0V,0V,1F,0V,10F,6F,2F|14F,10F,0V,0V,13F,9F,5F,0V,0V,0V,8F,0V,0V,0V,7F,3F|0V,0V,0V,0V,0V,10F,0V,2F,0V,11F,7F,0V,16F,12F,0V,0V|12F,16F,0V,0V,11F,0V,0V,7F,0V,14F,0V,0V,9F,0V,1F,5F|0V,15F,0V,7F,0V,0V,4F,0V,0V,0V,0V,5F,0V,0V,2F,0V|10F,14F,0V,6F,9F,0V,0V,0V,0V,16F,4F,0V,11F,15F,0V,7F|9F,0V,1F,0V,10F,0V,0V,0V,0V,0V,3F,7F,0V,16F,0V,8F|0V,0V,0V,0V,7F,3F,0V,0V,0V,0V,14F,10F,5F,0V,13F,9F|0V,3F,15F,0V,8F,0V,16F,12F,0V,1F,0V,9F,6F,0V,0V,10F|0V,0V,0V,0V,0V,1F,0V,9F,8F,4F,0V,12F,0V,0V,0V,0V|5F,1F,13F,0V,6F,2F,0V,0V,0V,3F,0V,0V,0V,0V,16F,0V|4F,8F,12F,0V,0V,0V,11F,15F,2F,0V,0V,14F,0V,0V,9F,0V|3F,0V,11F,0V,0V,0V,0V,16F,1F,0V,9F,0V,2F,6F,0V,0V|2F,0V,10F,14F,0V,5F,0V,0V,4F,8F,0V,0V,3F,0V,11F,0V|0V,5F,0V,13F,0V,0V,0V,14F,0V,7F,0V,15F,0V,8F,12F,0V'),";
        sql += "('EASY', 4, 115, '0V,0V,9F,13F,2F,6F,10F,0V,0V,0V,11F,15F,4F,0V,12F,0V|2F,6F,0V,0V,0V,0V,0V,13F,0V,8F,12F,0V,0V,0V,0V,0V|0V,7F,0V,15F,4F,0V,12F,16F,1F,5F,0V,0V,0V,0V,10F,0V|0V,8F,12F,0V,3F,0V,0V,0V,0V,6F,10F,14F,0V,5F,9F,0V|5F,0V,13F,9F,6F,0V,0V,10F,0V,3F,0V,0V,0V,0V,16F,0V|6F,2F,0V,10F,5F,0V,0V,9F,0V,0V,16F,0V,7F,0V,0V,11F|7F,0V,15F,0V,0V,4F,0V,0V,0V,1F,13F,9F,6F,0V,0V,0V|0V,4F,0V,12F,0V,3F,15F,0V,0V,0V,0V,10F,0V,0V,0V,0V|9F,0V,0V,5F,0V,14F,0V,0V,0V,0V,0V,7F,12F,16F,4F,0V|0V,14F,0V,6F,9F,0V,1F,0V,0V,0V,0V,8F,0V,0V,3F,7F|11F,0V,3F,7F,0V,16F,0V,8F,0V,13F,0V,0V,0V,14F,0V,0V|12F,0V,0V,8F,11F,15F,3F,7F,0V,0V,2F,0V,9F,0V,1F,5F|0V,0V,0V,0V,0V,0V,6F,2F,15F,0V,0V,0V,0V,12F,0V,0V|14F,10F,6F,0V,0V,9F,0V,0V,0V,12F,8F,4F,0V,11F,0V,3F|0V,0V,0V,3F,0V,0V,8F,0V,13F,9F,5F,0V,14F,10F,0V,2F|16F,0V,0V,0V,0V,11F,0V,0V,14F,0V,0V,0V,13F,0V,5F,1F'),";
        
        sql += "('MEDIUM', 2, 6, '1F,0V,2F,0V|2F,0V,0V,3F|0V,0V,0V,0V|4F,0V,0V,1F'),";
        sql += "('MEDIUM', 2, 6, '0V,3F,0V,1F|0V,0V,4F,0V|3F,0V,0V,2F|1F,0V,0V,0V'),";
        
        sql += "('MEDIUM', 3, 32, '1F,0V,0V,0V,0V,6F,7F,8F,0V|0V,0V,0V,0V,8F,0V,0V,0V,0V|0V,0V,9F,1F,2F,0V,4F,0V,0V|0V,0V,4F,3F,0V,0V,0V,0V,7F|3F,6F,5F,8F,0V,7F,2F,1F,0V|0V,9F,7F,2F,0V,0V,3F,6F,0V|0V,3F,0V,6F,0V,0V,0V,0V,0V|0V,0V,0V,0V,7F,8F,0V,0V,0V|9F,7F,0V,0V,0V,1F,0V,0V,2F'),";
        sql += "('MEDIUM', 3, 32, '0V,0V,0V,6F,4F,2F,0V,0V,1F|0V,0V,0V,0V,7F,8F,0V,0V,0V|6F,4F,0V,0V,0V,0V,9F,0V,0V|0V,9F,0V,3F,6F,5F,0V,0V,0V|2F,1F,0V,0V,0V,7F,0V,6F,5F|0V,0V,5F,0V,0V,0V,0V,9F,7F|7F,0V,9F,0V,5F,0V,0V,2F,3F|1F,0V,0V,0V,0V,0V,4F,0V,0V|0V,0V,6F,1F,0V,3F,0V,8F,0V'),";
        
        sql += "('MEDIUM', 4, 107, '0V,12F,0V,4F,15F,0V,7F,0V,14F,0V,6F,0V,0V,0V,0V,1F|0V,0V,0V,3F,16F,0V,0V,4F,0V,9F,5F,0V,0V,0V,6F,0V|14F,0V,0V,0V,13F,9F,5F,0V,16F,0V,0V,0V,15F,11F,0V,0V|13F,0V,0V,0V,0V,10F,0V,2F,15F,0V,0V,0V,0V,0V,8F,4F|12F,16F,0V,0V,0V,0V,3F,7F,0V,0V,2F,0V,9F,0V,0V,0V|11F,15F,3F,0V,0V,0V,4F,0V,0V,13F,1F,5F,0V,14F,0V,0V|10F,0V,0V,6F,9F,13F,0V,0V,0V,0V,4F,0V,11F,0V,0V,7F|0V,0V,1F,0V,10F,0V,0V,0V,0V,15F,3F,0V,12F,16F,0V,8F|8F,0V,16F,0V,7F,3F,0V,0V,0V,2F,0V,10F,5F,1F,13F,0V|7F,3F,0V,11F,8F,4F,0V,0V,0V,0V,0V,0V,0V,0V,0V,10F|6F,0V,14F,10F,0V,1F,13F,0V,8F,0V,0V,12F,0V,3F,0V,11F|0V,0V,0V,9F,0V,0V,14F,0V,0V,0V,15F,11F,0V,0V,0V,0V|0V,0V,0V,0V,3F,0V,11F,0V,2F,0V,0V,14F,0V,5F,0V,0V|0V,0V,11F,15F,0V,8F,0V,16F,1F,0V,9F,0V,2F,0V,10F,0V|0V,6F,0V,0V,0V,0V,0V,0V,0V,8F,12F,0V,0V,0V,0V,15F|1F,5F,9F,0V,0V,6F,0V,14F,0V,7F,0V,0V,4F,0V,0V,16F'),";
        sql += "('MEDIUM', 4, 104, '0V,12F,0V,0V,15F,0V,7F,3F,0V,0V,0V,2F,0V,0V,5F,0V|15F,0V,0V,0V,0V,0V,0V,4F,0V,9F,5F,1F,14F,10F,6F,2F|0V,0V,6F,2F,0V,0V,0V,1F,16F,0V,0V,4F,0V,11F,0V,0V|13F,9F,0V,0V,0V,0V,6F,0V,0V,11F,0V,0V,0V,0V,8F,4F|0V,0V,4F,8F,11F,15F,0V,0V,0V,14F,0V,6F,0V,13F,0V,5F|11F,0V,3F,0V,12F,0V,0V,8F,9F,0V,1F,0V,0V,0V,2F,0V|0V,14F,2F,0V,0V,13F,1F,0V,12F,0V,0V,0V,0V,0V,0V,7F|0V,13F,0V,5F,10F,0V,0V,0V,0V,0V,3F,0V,12F,16F,0V,0V|8F,0V,0V,12F,7F,0V,0V,0V,0V,0V,14F,0V,0V,1F,0V,0V|0V,3F,0V,0V,8F,0V,16F,0V,5F,0V,0V,0V,0V,0V,0V,10F|6F,0V,14F,0V,5F,0V,0V,9F,0V,4F,0V,0V,7F,3F,15F,0V|0V,0V,0V,0V,0V,2F,0V,10F,0V,0V,15F,11F,0V,4F,16F,0V|4F,0V,0V,0V,3F,0V,11F,0V,2F,0V,10F,0V,1F,5F,0V,13F|3F,7F,0V,15F,4F,0V,12F,0V,0V,0V,0V,13F,0V,6F,0V,0V|0V,0V,0V,0V,0V,5F,0V,0V,0V,8F,0V,16F,3F,0V,11F,15F|1F,0V,9F,0V,0V,0V,0V,14F,0V,7F,0V,15F,0V,0V,0V,0V'),";
        sql += "('MEDIUM', 4, 108, '0V,0V,0V,4F,0V,11F,0V,3F,0V,0V,6F,0V,0V,0V,5F,0V|0V,0V,7F,0V,16F,0V,0V,0V,0V,9F,0V,1F,14F,10F,0V,2F|14F,0V,0V,0V,0V,0V,5F,0V,0V,12F,0V,4F,15F,11F,0V,0V|0V,9F,5F,0V,0V,0V,0V,2F,15F,0V,0V,3F,16F,12F,8F,0V|0V,16F,4F,8F,0V,15F,0V,0V,10F,0V,0V,6F,9F,13F,0V,0V|11F,0V,0V,0V,12F,16F,4F,0V,9F,0V,0V,5F,0V,0V,2F,0V|10F,14F,0V,6F,0V,13F,1F,0V,0V,0V,0V,0V,11F,0V,0V,7F|0V,13F,0V,0V,0V,14F,0V,6F,11F,0V,3F,0V,0V,16F,0V,0V|8F,0V,0V,12F,7F,0V,0V,0V,0V,0V,14F,10F,0V,1F,13F,0V|0V,3F,15F,11F,8F,0V,0V,12F,5F,0V,13F,0V,6F,0V,0V,0V|0V,2F,0V,0V,0V,1F,0V,9F,0V,0V,16F,12F,0V,3F,0V,0V|5F,0V,13F,0V,0V,2F,14F,0V,0V,0V,15F,0V,8F,0V,0V,0V|0V,8F,0V,0V,3F,0V,0V,15F,2F,0V,0V,0V,1F,0V,9F,0V|0V,0V,0V,15F,0V,0V,12F,0V,0V,0V,0V,0V,0V,6F,0V,14F|0V,6F,10F,0V,1F,5F,0V,0V,0V,8F,12F,16F,3F,0V,0V,15F|1F,0V,0V,0V,2F,6F,0V,14F,0V,7F,11F,0V,4F,0V,0V,16F'),";
        sql += "('MEDIUM', 4, 110, '16F,0V,8F,4F,0V,11F,7F,3F,14F,0V,6F,0V,13F,0V,5F,0V|0V,11F,0V,0V,16F,12F,0V,0V,0V,9F,5F,0V,0V,0V,6F,2F|14F,0V,6F,0V,0V,0V,0V,1F,0V,12F,0V,0V,0V,0V,7F,3F|0V,9F,0V,1F,14F,0V,0V,0V,15F,0V,0V,0V,16F,0V,0V,0V|0V,0V,0V,8F,0V,15F,0V,0V,0V,0V,2F,6F,9F,0V,1F,0V|0V,0V,3F,0V,12F,0V,0V,8F,0V,0V,0V,0V,0V,14F,0V,0V|0V,0V,0V,6F,0V,13F,0V,5F,12F,16F,4F,0V,11F,15F,0V,0V|0V,13F,0V,0V,10F,0V,2F,6F,11F,0V,0V,7F,0V,16F,4F,0V|0V,0V,16F,0V,0V,0V,0V,11F,0V,0V,0V,10F,5F,0V,0V,9F|7F,0V,0V,0V,0V,4F,0V,0V,0V,1F,0V,9F,6F,0V,14F,10F|0V,2F,0V,0V,0V,1F,13F,0V,8F,0V,16F,12F,7F,0V,0V,0V|5F,0V,13F,0V,0V,2F,14F,10F,7F,3F,0V,0V,8F,4F,0V,0V|4F,8F,0V,0V,0V,7F,11F,15F,2F,0V,0V,0V,0V,0V,0V,13F|0V,7F,0V,15F,4F,0V,0V,16F,1F,5F,0V,0V,0V,6F,10F,0V|2F,0V,0V,14F,1F,5F,0V,0V,0V,8F,12F,16F,0V,0V,11F,0V|0V,0V,9F,0V,0V,0V,10F,0V,3F,0V,11F,0V,0V,0V,12F,0V'),";
        sql += "('MEDIUM', 4, 106, '1F,0V,3F,0V,0V,6F,0V,8F,0V,10F,0V,0V,13F,0V,15F,16F|0V,0V,0V,0V,0V,0V,3F,4F,0V,0V,0V,16F,9F,0V,0V,12F|0V,10F,0V,0V,13F,14F,0V,0V,0V,0V,0V,4F,0V,6F,7F,0V|13F,14F,0V,0V,9F,0V,11F,12F,0V,0V,7F,0V,1F,2F,0V,0V|0V,1F,0V,0V,0V,0V,0V,7F,10F,0V,12F,0V,0V,0V,0V,15F|6F,5F,8F,0V,0V,1F,0V,0V,0V,13F,0V,0V,0V,0V,0V,0V|0V,9F,12F,11F,14F,0V,16F,15F,0V,1F,0V,0V,0V,5F,8F,0V|0V,0V,16F,0V,0V,9F,0V,11F,6F,0V,0V,7F,2F,0V,4F,3F|0V,0V,0V,2F,0V,0V,5F,0V,11F,0V,0V,0V,0V,16F,0V,0V|7F,8F,0V,0V,3F,0V,0V,0V,15F,0V,13F,0V,0V,12F,0V,10F|11F,0V,0V,0V,0V,16F,0V,14F,0V,4F,1F,2F,7F,0V,5F,0V|15F,0V,0V,0V,0V,0V,9F,0V,0V,8F,0V,6F,0V,0V,1F,0V|4F,0V,0V,0V,0V,7F,6F,0V,0V,11F,0V,9F,16F,0V,0V,13F|0V,0V,6F,5F,0V,0V,2F,1F,0V,0V,14F,0V,0V,0V,10F,9F|12F,11F,10F,0V,16F,15F,0V,0V,0V,0V,2F,0V,0V,0V,0V,5F|0V,0V,0V,13F,12F,11F,0V,0V,8F,7F,0V,0V,0V,3F,2F,0V'),";
        
        sql += "('HARD', 4, 107, '0V,5F,0V,13F,0V,0V,10F,0V,3F,0V,11F,0V,0V,0V,0V,16F|0V,6F,0V,0V,1F,0V,9F,0V,4F,0V,12F,16F,0V,7F,11F,0V|0V,0V,0V,0V,4F,0V,0V,16F,0V,5F,9F,13F,2F,6F,0V,14F|4F,0V,0V,0V,3F,7F,11F,0V,0V,0V,0V,14F,0V,0V,9F,13F|5F,1F,0V,0V,6F,0V,14F,10F,0V,3F,0V,0V,8F,0V,0V,0V|0V,0V,0V,10F,5F,0V,0V,0V,0V,0V,16F,12F,0V,0V,0V,11F|7F,0V,15F,11F,0V,4F,0V,0V,0V,0V,13F,0V,0V,2F,0V,0V|8F,4F,0V,12F,7F,0V,0V,0V,6F,0V,0V,0V,0V,1F,13F,9F|0V,0V,1F,0V,0V,0V,0V,6F,11F,15F,0V,0V,0V,0V,4F,8F|10F,14F,0V,6F,0V,13F,0V,0V,12F,0V,4F,0V,0V,15F,0V,0V|11F,0V,0V,7F,0V,0V,0V,8F,0V,0V,0V,5F,10F,14F,2F,0V|0V,16F,4F,0V,0V,15F,0V,7F,0V,14F,2F,0V,9F,0V,1F,0V|0V,9F,0V,0V,14F,0V,0V,0V,15F,0V,0V,3F,16F,0V,8F,4F|14F,10F,0V,0V,0V,0V,5F,1F,0V,12F,0V,0V,15F,0V,7F,0V|0V,0V,7F,3F,16F,12F,0V,0V,0V,9F,0V,0V,14F,0V,0V,0V|0V,12F,0V,0V,0V,11F,0V,0V,0V,0V,6F,2F,0V,0V,0V,0V'),";
        sql += "('HARD', 4, 105, '0V,0V,0V,13F,0V,6F,0V,0V,0V,0V,11F,0V,4F,0V,12F,16F|0V,0V,10F,0V,1F,5F,0V,0V,0V,0V,0V,0V,3F,7F,0V,0V|3F,7F,0V,0V,0V,8F,0V,16F,0V,0V,9F,0V,0V,0V,0V,14F|4F,0V,0V,16F,0V,0V,11F,0V,2F,6F,0V,14F,0V,5F,0V,13F|5F,0V,0V,9F,0V,0V,0V,10F,7F,0V,15F,11F,0V,0V,16F,0V|6F,2F,14F,0V,5F,1F,13F,0V,0V,4F,0V,12F,0V,0V,0V,11F|7F,0V,0V,11F,0V,4F,0V,0V,0V,1F,0V,9F,6F,0V,0V,0V|0V,0V,0V,0V,0V,3F,15F,0V,0V,0V,0V,0V,0V,1F,13F,0V|0V,0V,1F,0V,0V,0V,2F,0V,0V,15F,0V,0V,12F,0V,0V,0V|0V,14F,0V,0V,9F,13F,0V,5F,0V,0V,4F,8F,0V,0V,3F,0V|11F,0V,3F,0V,12F,0V,0V,0V,0V,0V,0V,5F,10F,14F,0V,6F|12F,16F,0V,0V,0V,0V,0V,7F,10F,0V,2F,0V,9F,0V,0V,5F|13F,9F,0V,1F,0V,10F,6F,2F,0V,0V,0V,3F,0V,0V,8F,4F|0V,0V,0V,2F,0V,0V,0V,0V,16F,12F,8F,0V,0V,11F,7F,0V|0V,0V,7F,0V,16F,0V,0V,4F,13F,0V,5F,0V,14F,10F,0V,0V|16F,12F,0V,4F,15F,0V,0V,3F,14F,0V,0V,2F,0V,0V,0V,0V'),";
        sql += "('HARD', 4, 107, '0V,5F,9F,0V,0V,6F,0V,14F,3F,7F,11F,0V,0V,0V,12F,0V|0V,0V,10F,14F,1F,5F,0V,0V,0V,0V,12F,0V,0V,7F,0V,15F|3F,7F,0V,0V,0V,0V,0V,16F,0V,5F,0V,13F,2F,0V,0V,14F|4F,0V,12F,0V,3F,0V,0V,0V,2F,6F,0V,0V,0V,0V,9F,13F|5F,0V,13F,0V,0V,0V,0V,0V,7F,0V,0V,11F,8F,4F,0V,0V|0V,2F,0V,0V,0V,0V,0V,9F,8F,0V,16F,0V,0V,0V,0V,11F|7F,0V,15F,0V,0V,4F,16F,12F,0V,1F,13F,0V,0V,2F,0V,0V|0V,4F,0V,0V,0V,3F,15F,0V,0V,0V,14F,10F,5F,0V,13F,9F|0V,0V,0V,0V,10F,0V,2F,0V,11F,0V,3F,0V,0V,16F,0V,0V|0V,14F,0V,6F,9F,13F,0V,0V,0V,0V,0V,8F,0V,0V,3F,0V|0V,0V,0V,7F,0V,0V,4F,0V,9F,13F,0V,0V,10F,0V,0V,6F|12F,0V,0V,8F,0V,15F,0V,7F,0V,14F,2F,0V,0V,0V,1F,0V|0V,9F,0V,1F,14F,0V,0V,2F,0V,0V,7F,0V,16F,0V,0V,4F|0V,0V,6F,2F,13F,0V,0V,1F,16F,12F,0V,0V,0V,11F,7F,0V|15F,11F,0V,0V,0V,12F,8F,4F,0V,0V,0V,1F,14F,10F,6F,0V|0V,0V,8F,0V,0V,11F,0V,0V,0V,0V,0V,0V,0V,9F,0V,0V'),";
        sql += "('HARD', 4, 107, '0V,2F,0V,0V,5F,0V,7F,0V,0V,0V,0V,12F,13F,14F,0V,0V|0V,6F,0V,8F,0V,0V,0V,0V,0V,14F,15F,0V,0V,0V,11F,12F|9F,0V,11F,0V,13F,0V,0V,16F,0V,2F,0V,0V,5F,0V,0V,0V|13F,14F,15F,0V,9F,10F,0V,0V,0V,0V,7F,8F,1F,0V,0V,4F|0V,0V,4F,0V,0V,5F,8F,0V,10F,0V,0V,0V,14F,0V,0V,15F|6F,0V,0V,0V,2F,0V,0V,3F,0V,0V,16F,0V,10F,9F,0V,0V|10F,0V,12F,0V,0V,0V,0V,0V,0V,1F,0V,3F,0V,0V,8F,7F|0V,13F,16F,15F,0V,0V,12F,11F,0V,5F,0V,7F,2F,0V,0V,0V|3F,4F,0V,0V,0V,8F,0V,6F,11F,0V,9F,0V,0V,16F,0V,0V|0V,8F,5F,0V,3F,4F,0V,0V,15F,0V,13F,14F,11F,0V,9F,0V|0V,0V,0V,10F,15F,0V,13F,0V,3F,0V,0V,0V,0V,0V,5F,6F|0V,0V,13F,0V,0V,12F,0V,0V,0V,0V,0V,0V,0V,4F,0V,2F|0V,3F,0V,1F,0V,0V,0V,5F,0V,11F,0V,0V,16F,0V,0V,13F|0V,0V,0V,5F,4F,0V,2F,1F,0V,0V,14F,13F,0V,0V,0V,0V|0V,11F,10F,0V,0V,15F,14F,0V,4F,0V,0V,1F,8F,7F,0V,0V|16F,15F,14F,13F,0V,0V,10F,0V,8F,0V,6F,0V,0V,3F,0V,0V'),";
        
        sql += "('HARD', 2, 5, '0V,2F,0V,0V|3F,0V,1F,0V|0V,0V,0V,3F|0V,0V,2F,0V'),";
        sql += "('HARD', 3, 28, '2F,0V,0V,0V,0V,7F,6F,0V,0V|5F,4F,0V,0V,1F,0V,0V,2F,0V|6F,0V,9F,0V,0V,0V,0V,0V,7F|0V,8F,0V,3F,0V,0V,5F,9F,0V|4F,0V,0V,1F,0V,0V,0V,0V,0V|3F,9F,0V,0V,0V,5F,0V,0V,0V|0V,2F,0V,0V,0V,0V,0V,0V,5F|0V,0V,0V,9F,6F,0V,0V,3F,2F|9F,0V,0V,8F,0V,0V,0V,4F,0V');";
        stmt.executeUpdate(sql);
    }

    /**
     * Afegeix un tauler a la base de dades
     * @param board Tauler a afegir
     * @Pre board no és null
     * @Post S'afegeix un tauler a la base de dades
     * @return retorna l'identificador del tauler
     */
    public int add(HashMap<String, Object> board) {
        int id = -1;
        String sql = "";
        if (this.connect(true)) {
            try {
                Statement stmt = this.getStatement();
                stmt.executeUpdate("PRAGMA foreign_keys = ON;");
                sql = "INSERT INTO " + TABLE_NAME + "(" + ATTR_DIFFICULTY + ", " + ATTR_N + ", " + ATTR_N_NUMBERS + ", " + ATTR_BOARD + ", " + ATTR_CREATOR + ") VALUES (?, ?, ?, ?, ?);";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setString(1, (String) board.get(ATTR_DIFFICULTY));
                ps.setInt(2, (int) board.get(ATTR_N));
                ps.setInt(3, (int) board.get(ATTR_N_NUMBERS));
                ps.setString(4, (String) board.get(ATTR_BOARD));
                ps.setString(5, (String) board.get(ATTR_CREATOR));
                int result = ps.executeUpdate();
                ps.close();
                if (result == 1) {
                    sql = "SELECT last_insert_rowid() FROM " + TABLE_NAME + ";";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage() + " SQL: "+sql);
            } finally {
                this.disconnect();
            }
        }
        return id;
    }

    /**
     * Obté tots els taulers de la base de dades segons mida i dificultat
     * @param n Mida del tauler
     * @param difficulty Dificultat del tauler
     * @Pre n és una mida valida, difficulty és una dificultat valida
     * @return retorna un ArrayList que conté els taulers amb mida n i dificultat difficulty
     */
    public ArrayList<HashMap<String, Object>> getAll(int n, String difficulty) {
        ArrayList<HashMap<String, Object>> boards = new ArrayList<>();
        if (this.connect(true)) {
            try {
                ResultSet rs;
                String sql = "SELECT " + ATTR_ID + ", " + ATTR_CREATOR + " FROM " + TABLE_NAME + " WHERE " + ATTR_N + "=? AND " + ATTR_DIFFICULTY + "=? ORDER BY " + ATTR_ID + " ASC;";
                PreparedStatement ps = this.getConnection().prepareStatement(sql);
                ps.setInt(1, n);
                ps.setString(2, difficulty);
                rs = ps.executeQuery();
                HashMap<String, Object> attrs;
                while (rs.next()) {
                    attrs = new HashMap<>();
                    attrs.put(ATTR_ID, rs.getInt(ATTR_ID));
                    attrs.put(ATTR_CREATOR, rs.getString(ATTR_CREATOR));
                    boards.add(attrs);
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
    
    /**
     * Obté el tauler de la base de dades identificat per id.
     * @param id identificador del tauler
     * @Pre id &gt; 0 
     * @return retorna un HashMap amb el tauler d'identificador id
     */
    public HashMap<String, Object> getById(int id) {
        HashMap<String, Object> attrs = null;
        if (this.connect(true)) {
            PreparedStatement ps;
            ResultSet rs;
            try {
                attrs = new HashMap<>();
                attrs.put(ATTR_ID, id);
                String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ATTR_ID + " = ?;";
                ps = this.getConnection().prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String difficulty = rs.getString(ATTR_DIFFICULTY);
                    int n = rs.getInt(ATTR_N);
                    int nNumbers = rs.getInt(ATTR_N_NUMBERS);
                    String board = rs.getString(ATTR_BOARD);
                    String creator = rs.getString(ATTR_CREATOR);
                    attrs.put(ATTR_DIFFICULTY, difficulty);
                    attrs.put(ATTR_N, n);
                    attrs.put(ATTR_N_NUMBERS, nNumbers);
                    attrs.put(ATTR_BOARD, board);
                    attrs.put(ATTR_CREATOR, creator);
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
}
