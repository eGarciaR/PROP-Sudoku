/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author ericgarciaribera
 */
public class Editor extends Sudoku {
    
    /**
     * Contructora d'Editor
     * @pre 7 gt; n gt; 1
     * @post s'ha creat un nou editor
     * @param n tamany del tauler
     * @param user nom usuari
     */
    public Editor(int n, String user) {
        BoardSudoku board = new BoardSudoku(n);
        setBoard(board);
        setUser(user);
    }
    
}
