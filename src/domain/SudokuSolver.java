/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dani
 */
public class SudokuSolver {

    /**
     * Mida del tauler.
     */
    private final int n;
    /**
     * Mida del tauler al quadrat.
     */
    private final int n2;
    /**
     * Numero total de cel·les.
     */
    private final int N;
    /**
     * Tauler de cel·les.
     */
    private int[][] board;
    /**
     * Variable per saber si el sudoku té solució unica o no.
     */
    private boolean unique;
    /**
     * Identificador del BoardSudoku que li pasem.
     */
    private final int idBoardSudoku;
    /**
     * Dificultat del BoardSudoku que li pasem.
     */
    private final BoardSudoku.Difficulty difficulty;
    /**
     * Tauler de cel·les auxiliar per copiar la solució unica.
     */
    private int[][] boardAux;

    /**
     * Creadora de Solve Sudoku
     *
     * @pre board és un sudoku vàlid
     * @post es crea un Sudoku Solver amb els atributs del paràmetre board
     *
     * @param board Tauler de cel·les
     */
    public SudokuSolver(BoardSudoku board) {
        this.idBoardSudoku = board.getId();
        this.difficulty = board.getDifficulty();
        this.n = board.getN();
        this.n2 = (int) Math.pow(n, 2);
        this.N = (int) Math.pow(n2, 2);
        this.board = new int[this.n2][this.n2];
        for (int r = 0; r < this.n2; ++r) {
            for (int c = 0; c < this.n2; ++c) {
                this.board[r][c] = board.getCellValue(r, c);
            }
        }
    }

    private void initDefaultBoard() {
        for (int r = 0; r < this.n2; ++r) {
            for (int c = 0; c < this.n2; ++c) {
                this.board[r][c] = 0;
            }
        }
    }

    /**
     * Comprova les regles del Sudoku.
     *
     * @pre 0 &le; row, col &lt; n2, 1 &le; value &le; n2
     * @post None
     *
     * @param board Tauler de cel·les
     * @param row Fila
     * @param col Columna
     * @param value Valor de la cel·la
     * @return False si incompleix alguna regla, True altrament.
     */
    private boolean correctValue(int[][] board, int row, int col, int value) {
        boolean inRow = valueinRow(board, row, value);
        boolean inCol = valueinCol(board, col, value);
        boolean inRegion = valueinRegion(board, row, col, value);
        return (!inRow && !inCol && !inRegion);
    }

    /**
     * Comprova si un valor esta en una fila.
     *
     * @pre 0 &le; row &lt; n2, 1 &le; value &le; n2
     * @post None
     *
     * @param board Tauler de cel·les
     * @param row Fila
     * @param value Valor de la cel·la
     * @return True si el valor ja esta en la fila, False altrament.
     */
    private boolean valueinRow(int[][] board, int row, int value) {
        for (int col = 0; col < this.n2; ++col) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprova si un valor esta en una columna.
     *
     * @pre 0 &le; col &lt; n2, 1 &le; value &le; n2
     * @post None
     *
     * @param board Tauler de cel·les
     * @param col Columna
     * @param value Valor de la cel·la
     * @return True si el valor ja esta en la columna, False altrament.
     */
    private boolean valueinCol(int[][] board, int col, int value) {
        for (int row = 0; row < this.n2; ++row) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprova si un valor esta en una regió.
     *
     * @pre 0 &le; row,col &lt; n2, 1 &le; value &le; n2
     * @post None
     *
     * @param board Tauler de cel·les
     * @param row Fila
     * @param col Columna
     * @param value Valor de la cel·la
     * @return True si el valor ja esta en la regió, False altrament.
     */
    private boolean valueinRegion(int[][] board, int row, int col, int value) {
        int initialrow = row - (row % this.n);
        int finalrow = initialrow + this.n;
        int initialcol = col - (col % this.n);
        int finalcol = initialcol + this.n;
        for (int r = initialrow; r < finalrow; ++r) {
            for (int c = initialcol; c < finalcol; ++c) {
                if (board[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Fa una copia d'una matriu d'enters.
     *
     * @param board Matriu d'enters.
     * @return Retorna una copia de la matriu d'enters pasada per parametre.
     */
    private int[][] copyBoard(int[][] board) {
        int[][] myInt = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            int[] aMatrix = board[i];
            int aLength = aMatrix.length;
            myInt[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
        }
        return myInt;
    }

    /**
     * Donat un tauler original, retorna true si com a mínim té una solució,
     * altrament retorna false.
     *
     * @pre: Board és un tauler vàlid.
     * @post: l'atribut board té la solució en cas de tenir-ne.
     *
     * @return True si board té com a mínim una solució, False si no en té cap.
     */
    public boolean minOneSolution() {
        return minOneSolutionRec(this.board, 0);
    }

    /**
     * Donat un tauler original, retorna true si com a mínim té una solució,
     * altrament retorna false.
     *
     * @pre: Board és un tauler vàlid.
     * @post: None.
     *
     * @return True si board té com a mínim una solució, False si no en té cap.
     */
    public boolean minOneSolutionCopy() {
        int[][] board = copyBoard(this.board);
        return minOneSolutionRec(board, 0);
    }

    /**
     * Donat un tauler original, retorna true si té una única solució, altrament
     * retorna false.
     *
     * @pre: Board és un tauler vàlid.
     * @post: l'atribut board conté la solució única, en cas que la tingui.
     * @return True si board té solució única, False si en té més d'una o cap.
     */
    public boolean uniqueSolution() {
        this.unique = false;
        boolean solution;
        solution = uniqueSolutionRec(this.board, 0);
        if (this.unique) {
            board = boardAux;
            return true;
        }
        return false;
    }

    /**
     * Donat un tauler original, retorna true si té una única solució, altrament
     * retorna false.
     *
     * @pre: Board és un tauler vàlid.
     * @post: None.
     * @return True si board té solució única, False si en té més d'una o cap.
     */
    public boolean uniqueSolutionCopy() {
        int[][] board = copyBoard(this.board);
        unique = false;
        boolean solution;
        solution = uniqueSolutionRec(board, 0);

        if (unique) {
            return true;
        }
        return false;
    }

    /**
     * Donat un Tauler i un nombre de cel·la, retorna True si troba almenys una
     * solució pel Sududoku. Si no en té cap, retorna false.
     *
     * @pre: Board és un tauler parcialment complert. x és el nº de Cel·la que
     * està comprovant.
     * @post: Board conté la solució parcial.
     *
     *
     * @param board Matriu d'enters.
     * @param x Enter
     * @return True sí té una solució com a mínim, altrament False.
     */
    private boolean minOneSolutionRec(int[][] board, int x) {
        int row = x / this.n2;
        int col = x % this.n2;
        if (x == this.N) {
            return true;
        }
        if (board[row][col] != 0) {
            if (minOneSolutionRec(board, x + 1)) {
                return true;
            }
        } else {
            for (int v = 1; v <= this.n2; v++) {
                if (this.correctValue(board, row, col, v)) {
                    board[row][col] = v;
                    if (minOneSolutionRec(board, x + 1)) {
                        return true;
                    }
                }
            }
            board[row][col] = 0;
        }
        return false;
    }

    /**
     * Donat un Tauler i un nombre de cel·la, retorna True si troba una solució
     * única pel Sududoku. Si no en té cap, retorna false.
     *
     * @pre: Board és un tauler parcialment complert. x és el nº de Cel·la que
     * està comprovant.
     * @post: Board conté la solució parcial.
     *
     * @param board Matriu d'enters
     * @param x Enter
     * @return True si té una solució única, altrament False.
     */
    private boolean uniqueSolutionRec(int[][] board, int x) {
        int row = x / this.n2;
        int col = x % this.n2;
        if (x == this.N) {
            if (unique) {
                unique = false;
                return true;
            } else {
                boardAux = copyBoard(board);
                unique = true;
                return false;
            }
        }
        if (board[row][col] != 0) {
            if (uniqueSolutionRec(board, x + 1)) {
                return true;
            }
        } else {
            for (int v = 1; v <= this.n2; v++) {
                if (this.correctValue(board, row, col, v)) {
                    board[row][col] = v;
                    if (uniqueSolutionRec(board, x + 1)) {
                        return true;
                    }
                }
            }
            board[row][col] = 0;
        }
        return false;
    }

    /**
     * Crea una instància de BoardSudoku amb els atributs definits en el
     * parametre implicit.
     *
     * @return Retorna una instància de BoardSudoku amb els valors definits en
     * el parametre implicit.
     */
    public BoardSudoku getBoardSudoku() {
        BoardSudoku bs = new BoardSudoku(this.idBoardSudoku, n);
        bs.setDifficulty(this.difficulty);
        for (int r = 0; r < this.n2; r++) {
            for (int c = 0; c < this.n2; c++) {
                int v = this.board[r][c];
                if (v != 0) {
                    bs.setCellValue(r, c, v);
                    bs.setFixedCell(r, c, true);
                }
            }
        }
        return bs;
    }

    /**
     * Intercanvia les columnes simètriques.
     */
    private void swapCols() {
        int limit = this.n2 / 2;
        int r, c, tmpValue, tmpR, tmpC;
        int tmpCell, divN;

        for (int cell = 0; cell < this.N; cell++) {
            r = cell / this.n2;
            c = cell % this.n2;
            if (cell % this.n2 < limit) {
                tmpValue = this.board[r][c];
                divN = (int) Math.floor((double) cell / this.n2);
                tmpCell = (this.n2 * divN + (this.n2 - 1)) - (cell - (this.n2 * divN));
                tmpR = tmpCell / this.n2;
                tmpC = tmpCell % this.n2;
                this.board[r][c] = this.board[tmpR][tmpC];
                this.board[tmpR][tmpC] = tmpValue;
            }
        }
    }

    /**
     * Intercanvia les files simètriques.
     */
    private void swapRows() {
        int limit = this.n2 / 2;
        int r, c, tmpValue, tmpR, tmpC;
        int tmpCell, divN, modN;

        for (int cell = 0; cell < this.N; cell++) {
            r = cell / this.n2;
            c = cell % this.n2;
            if (Math.floor(cell / this.n2) < limit) {
                modN = (int) Math.floor(cell % this.n2);
                divN = (int) Math.floor(cell / this.n2);
                tmpValue = this.board[r][c];
                tmpCell = modN + ((this.n2 - 1 - divN) * this.n2);
                tmpR = tmpCell / this.n2;
                tmpC = tmpCell % this.n2;
                this.board[r][c] = this.board[tmpR][tmpC];
                this.board[tmpR][tmpC] = tmpValue;
            }
        }
    }

    /**
     * Intercanvia les diagonals simètriques amb la diagonal principal.
     */
    private void swapMainDiagonal() {
        int limit = this.n2 - 1;
        int r, c, tmpValue, tmpR, tmpC;
        int tmpCell, divN, modN;

        for (int cell = 0; cell < this.N; cell++) {
            r = cell / this.n2;
            c = cell % this.n2;
            if ((Math.floor(cell / this.n2) + cell % this.n2) < limit) {
                modN = (int) Math.floor(cell % this.n2);
                divN = (int) Math.floor(cell / this.n2);
                tmpValue = this.board[r][c];
                tmpCell = (this.n2 - 1 - modN) * this.n2 + (this.n2 - 1 - divN);
                tmpR = tmpCell / this.n2;
                tmpC = tmpCell % this.n2;
                this.board[r][c] = this.board[tmpR][tmpC];
                this.board[tmpR][tmpC] = tmpValue;
            }
        }
    }

    /**
     * Intercanvia les diagonals simètriques amb la diagonal secundaria.
     */
    private void swapMinorDiagonal() {
        int r, c, tmpValue, tmpR, tmpC;
        int tmpCell, divN, modN;

        for (int cell = 0; cell < this.N; cell++) {
            r = cell / this.n2;
            c = cell % this.n2;
            if ((Math.floor(cell / this.n2)) > cell % this.n2) {
                modN = (int) Math.floor(cell % this.n2);
                divN = (int) Math.floor(cell / this.n2);
                tmpValue = this.board[r][c];
                tmpCell = divN + modN * this.n2;
                tmpR = tmpCell / this.n2;
                tmpC = tmpCell % this.n2;
                this.board[r][c] = this.board[tmpR][tmpC];
                this.board[tmpR][tmpC] = tmpValue;
            }
        }
    }

    /**
     * Genera un board random amb un cert nombre de cel·les inicials.
     *
     * @param initialCells Nombre de Cel·les inicials
     * @return Retorna una instància de BoardSudoku amb un sudoku vàlid
     * aleatori.
     */
    public BoardSudoku generateRandom(int initialCells) {
        BoardSudoku sudoku;
        Random random = new Random(System.currentTimeMillis());
        int pickCells = this.N - initialCells;
        int permutations = random.nextInt(this.n2 + 1) * initialCells;
        this.minOneSolution();//Crea el sudoku basic.
        this.permutations(permutations);//Barrejal
        this.pickCells(pickCells);
        sudoku = this.getBoardSudoku();
        return sudoku;
    }

    /**
     * Aplicar un cert numero de permutacions al board.
     *
     * @param permutations Numero de permutacions a fer.
     */
    private void permutations(int permutations) {
        Random random = new Random(System.currentTimeMillis());
        int swap;
        for (int i = 0; i < permutations; i++) {
            swap = random.nextInt(4);
            switch (swap) {
                case 0:
                    this.swapRows();
                    break;
                case 1:
                    this.swapCols();
                    break;
                case 2:
                    this.swapMainDiagonal();
                    break;
                case 3:
                    this.swapMinorDiagonal();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Extreure un cert número de cel·les al board, comprovant la seva unicitat.
     *
     * @param cells Numero de cel·les a extreure.
     */
    private void pickCells(int cells) {
        Random random = new Random(System.currentTimeMillis());
        boolean[][] bs = new boolean[this.n2][this.n2];
        int r, c, tmpValue;
        while (cells != 0) {
            r = random.nextInt(this.n2);
            c = random.nextInt(this.n2);
            boolean rows[] = new boolean[this.n2];
            boolean columns[] = new boolean[this.n2];
            boolean outR = false;
            boolean outC = false;
            while (bs[r][c] && (!outR && !outC)) {
                r = random.nextInt(this.n2);
                c = random.nextInt(this.n2);
                rows[r] = true;
                columns[c] = true;
                outR = true;
                outC = true;
                for (int i = 0; i < rows.length && outR; ++i) {
                    if (!rows[i]) {
                        outR = false;
                        outC = false;
                    }
                }
                for (int i = 0; i < columns.length && outC; ++i) {
                    if (!columns[i]) {
                        outC = false;
                    }
                }
            }
            tmpValue = this.board[r][c];
            this.board[r][c] = 0;
            if (!uniqueSolutionCopy()) {//No té solució unica.
                this.board[r][c] = tmpValue;
            } else {
                --cells;//Queda una menys per treure
            }
            bs[r][c] = true;
        }
    }

    public void printBoard() {
        String s = null;
        for (int r = 0; r < this.n2; r++) {
            for (int c = 0; c < this.n2; c++) {
                s += Integer.toString(this.board[r][c]) + ",";
            }
            s += "\n";
        }
        System.out.println(s);
    }

    private ArrayList<Location> initLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        Location l;
        for (int r = 0; r < n2; r++) {
            for (int c = 0; c < n2; c++) {
                l = new Location(r, c);
                locations.add(l);
            }
        }
        return locations;
    }

    private class Location {

        private final int r, c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getRow() {
            return r;
        }

        public int getCol() {
            return c;
        }
    }
}
