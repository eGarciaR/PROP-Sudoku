/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package controllers.domain;

import exceptions.ColumnLocationException;
import exceptions.RegionLocationException;
import exceptions.RowLocationException;
import exceptions.BadNumberPositionException;
import domain.BoardSudoku;
import domain.Editor;
import domain.SudokuSolver;
import java.util.HashMap;
import persistence.BoardManager;

/**
 *
 * @author dani
 */
public class EditorDomainCtrl {

    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static EditorDomainCtrl editorDomainCtrl;
    private final BoardDomainCtrl boardDomainCtrl;
    private final BoardManager boardManager;
    private Editor editor;

    /**
     * Constructor per defecte privat.
     */
    private EditorDomainCtrl(String gameName) {
        this.boardDomainCtrl = BoardDomainCtrl.getInstance(gameName);
        this.boardManager = BoardManager.getInstance(gameName);
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @param gameName Nom del Joc
     * @return La única instància de la propia classe.
     */
    public static EditorDomainCtrl getInstance(String gameName) {
        if (editorDomainCtrl == null) {
            editorDomainCtrl = new EditorDomainCtrl(gameName);
        }
        return editorDomainCtrl;
    }

    /**
     * Getter del nom d'usuari.
     *
     * @return retorna el nom de l'usuari
     */
    public String getUsername() {
        return editor.getUsername();
    }

    /**
     * Getter del tauler de l'editor.
     *
     * @return Tauler de l'editor.
     */
    public BoardSudoku getBoard() {
        return this.editor.getBoard();
    }

    /**
     * Inserta un valor a la cela amb columna column i fila row
     *
     * @param row fila a la qual inserir
     * @param column columna a la qual inserir
     * @param value valor que inserir
     * @throws exceptions.BadNumberPositionException Llançada quan no es pot introduïr el valor.
     */
    public void setCellValue(int row, int column, int value) throws BadNumberPositionException {
        try {
            this.editor.correctCell(row, column, value);
        } catch (RowLocationException e) {
            String errorMessage = this.editor.solverHelper(row, column, value);
            throw new RowLocationException(errorMessage);
        } catch (ColumnLocationException e) {
            String errorMessage = this.editor.solverHelper(row, column, value);
            throw new ColumnLocationException(errorMessage);
        } catch (RegionLocationException e) {
            String errorMessage = this.editor.solverHelper(row, column, value);
            throw new RegionLocationException(errorMessage);
        }
        this.editor.setCellValue(row, column, value);
    }

    /**
     * Comença partida en mode editor
     *
     * @pre 7 &gt; n &gt; 1
     * @param n mida
     * @param user usuari
     */
    protected void startEditor(int n, String user) {
        this.editor = new Editor(n, user);
    }

    /**
     * Guarda el tauler a la base de dades.
     *
     * @return True si s'ha desat correctament, False altrament.
     * @pre Tauler del editor es valid
     * @post tauler guardat a la base de dades
     */
    protected boolean save() {
        this.editor.getBoard().setAllCellsFixed();
        HashMap<String, Object> attrsBoard = this.editor.getBoard().getAttrs();
        attrsBoard.put(BoardDomainCtrl.BOARD_CREATOR, this.getUsername());
        int boardId = this.boardManager.add(attrsBoard);
        if (boardId > 0) {
            this.editor.getBoard().setId(boardId);
            return true;
        }
        return false;
    }

    /**
     * Soluciona el tauler de l'editor.
     *
     * @pre board te solucio unica
     * @post board esta solucionat
     */
    public void solve() {
        BoardSudoku bs = this.editor.getBoard();
        bs.setAllCellsFixed();
        SudokuSolver ss = new SudokuSolver(bs);
        if (ss.uniqueSolution()) {
            bs = ss.getBoardSudoku();
        }
        this.editor.setBoard(bs);
    }

    /**
     * Retorna true si el tauler de l'editor té solució unica.
     *
     * @pre El tauler de l'editor té almenys una solució
     * @post game board esta solucionat
     * @return boolean indica si el tauler esta solucionat o no
     */
    public boolean uniqueSolution() {
        SudokuSolver ss = new SudokuSolver(this.editor.getBoard());
        return ss.uniqueSolution();
    }

    /**
     * Operació booleana per saber si un tauler té identificador.
     *
     * @return True si te identificador, False altrament.
     */
    public boolean haveBoardId() {
        return this.editor.getBoard().getId() > 0;
    }

    /**
     * Defineix la dificultat d'un tauler.
     */
    public void setDifficulty() {
        this.editor.getBoard().setDifficulty(this.editor.getBoard().calculateDifficulty());
    }
}
