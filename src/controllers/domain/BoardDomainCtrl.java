/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package controllers.domain;

import domain.BoardSudoku;
import domain.BoardSudoku.Difficulty;
import domain.SudokuSolver;
import java.util.ArrayList;
import java.util.HashMap;
import persistence.BoardManager;

/**
 *
 * @author dani
 */
public class BoardDomainCtrl {

    /**
     * Atributs de BoardSudoku
     */
    public static final String BOARD_ID = "id";
    public static final String BOARD_DIFFICULTY = "difficulty";
    public static final String BOARD_N = "n";
    public static final String BOARD_N_NUMBERS = "n_numbers";
    public static final String BOARD_BOARD = "board";
    public static final String BOARD_CREATOR = "creator";

    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static BoardDomainCtrl boardDomainCtrl;
    private final BoardManager boardManager;

    /**
     * Constructor per defecte privat.
     */
    private BoardDomainCtrl(String gameName) {
        this.boardManager = BoardManager.getInstance(gameName);
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @param gameName Nom del Joc
     * @return La única instància de la propia classe.
     */
    public static BoardDomainCtrl getInstance(String gameName) {
        if (boardDomainCtrl == null) {
            boardDomainCtrl = new BoardDomainCtrl(gameName);
        }
        return boardDomainCtrl;
    }

    /**
     * Genera un tauler aleatori.
     *
     * @param n Mida
     * @param diff Nivell de dificultat
     * @return retorna un tauler aleatori
     */
    public BoardSudoku generateRandom(int n, String diff) throws IllegalArgumentException {
        Difficulty difficulty = Difficulty.valueOf(diff);
        BoardSudoku board = new BoardSudoku(n);
        board.setDifficulty(difficulty);
        int initialCells = this.calculateInitialCells(n, difficulty);
        SudokuSolver sudokuAux = new SudokuSolver(board);
        board = sudokuAux.generateRandom(initialCells);
        return board;
    }

    /**
     * Calcula les xifres inicials en funció del tamany i la dificultat. TODO:
     * millorar-la?
     *
     * @param n Tamany
     * @param difficulty Nivell de dificultat // * @return Retorna el numero de
     * cel·les inicials que com a mínim han de tenir valor.
     */
    private int calculateInitialCells(int n, Difficulty difficulty) {
        int exp = 4;
        int n4 = (int) Math.pow(n, exp);
        int cells;
        int percentatge;
        if (difficulty == Difficulty.EASY) {
            percentatge = 45;
        } else if (difficulty == Difficulty.MEDIUM) {
            percentatge = 40;
        } else {//HARD
            percentatge = 35;
        }
        cells = n4 * percentatge / 100;
        return cells;
    }

    /**
     * Carrega un Sudoku de la base de dades.
     *
     * @param boardId Identificador del Sudoku.
     * @return Sudoku.
     */
    public BoardSudoku load(int boardId) {
        HashMap<String, Object> attrs = this.boardManager.getById(boardId);
        BoardSudoku bs = new BoardSudoku(boardId, (int) attrs.get(BOARD_N));
        BoardSudoku.Difficulty difficulty = BoardSudoku.Difficulty.valueOf((String) attrs.get(BOARD_DIFFICULTY));
        bs.setDifficulty(difficulty);
        bs.setNNumbers((int) attrs.get(BOARD_N_NUMBERS));
        bs.fromString((String) attrs.get(BOARD_BOARD));
        return bs;
    }

    /**
     * Afegeix un Sudoku a la base de dades.
     *
     * @param bs Sudoku
     * @param user Usuari creador
     * @return Identificador assignat per la base de dades.
     */
    public int add(BoardSudoku bs, String user) {
        HashMap<String, Object> attrs = bs.getAttrs();
        attrs.put(BOARD_CREATOR, user);
        return this.boardManager.add(attrs);
    }

    /**
     * Llistat de Sudokus en funció de la mida i la dificultat.
     *
     * @param n Mida
     * @param difficulty Dificultat
     * @return Llistat de Sudokus
     */
    public ArrayList<HashMap<String, Object>> getAll(int n, String difficulty) {
        return this.boardManager.getAll(n, difficulty);
    }

    /**
     * Array de enters possibles de la mida d'un Sudoku.
     * @return Array de enters
     */
    public Integer[] getSizeModel() {
        return BoardSudoku.getSizeModel();
    }

    /**
     * Array de strings possibles de la dificultat d'un Sudoku.
     *
     * @return Array de strings
     */
    public String[] getDifficultyModel() {
        return BoardSudoku.getDifficultyModel();
    }
}
