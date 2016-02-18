package domain;

import exceptions.BadNumberPositionException;
import domain.BoardSudoku.Difficulty;

public class Sudoku {

    /**
     * Tauler sudoku
     */
    private BoardSudoku board;

    /**
     * Usuari del sudoku
     */
    private String user;

    /**
     * Creadora per defecte del sudoku
     */
    public Sudoku() {

    }

    /**
     * Creadora de la classe sudoku
     *
     * @Pre 7 &gt; n &gt; 1
     * @Post s'ha creat un nou sudoku
     * @param n mida del tauler
     * @param user usuari del sudoku
     */
    public Sudoku(int n, String user) {
        board = new BoardSudoku(n);
        this.user = user;
    }

    /**
     * Setter de tauler
     *
     * @Pre tauler correcte
     * @Post tauler de sudoku = board
     * @param board tauler
     */
    public void setBoard(BoardSudoku board) {
        this.board = board;
    }

    /**
     * Getter de tauler
     *
     * @Pre none
     * @Post none
     * @return BoardSudoku tauler de sudoku
     */
    public BoardSudoku getBoard() {
        return this.board;
    }

    /**
     * Setter d'usuari
     *
     * @Pre user valid
     * @Post usuari de sudoku = user
     * @param user usuari
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter d'usuari
     *
     * @Pre none
     * @Post none
     * @return retorna l'usuari del sudoku
     */
    public String getUsername() {
        return user;
    }

    /**
     * Getter de dificultat
     *
     * @Pre none
     * @Post none
     * @return Difficulty dificultat del sudoku
     */
    public Difficulty getDifficulty() {
        return board.getDifficulty();
    }

    /**
     * Afegeix un valor a una cela
     *
     * @Pre n^2 &gt; row, colum &gt; 0 and n^2 &ge; value &gt; 0
     * @Post el valor de la cel·la queda modificat si compleix les normes
     * @param row fila
     * @param column columna
     * @param value valor
     * @return boolean indica si s'ha inserit correctament
     */
    public boolean setCellValue(int row, int column, int value) {
        return this.board.setCellValue(row, column, value);
    }

    /**
     * Indica si la posició i el valor de la cel·la son correctes
     *
     * @throws exceptions.BadNumberPositionException posicio incorrecta
     * @Pre n^2 &gt; row, colum &gt; 0 and n^2 &ge; value &gt; 0
     * @Post none
     * @param row fila
     * @param column columna
     * @param value valor
     * @return boolean true si la posició es correcta false altrament
     */
    public boolean correctCell(int row, int column, int value) throws BadNumberPositionException {
        return board.correctCell(row, column, value);
    }

    /**
     * Retorna el missatge d'error que l'usuari veurà en cas de cometre un
     * error.
     *
     * @Pre:
     * @Post:
     * @param row fila
     * @param column columna
     * @param value valor
     * @return String amb el codi d'error de la cel·la si aquesta te algun error
     * si no String buit
     */
    public String solverHelper(int row, int column, int value) {
        String message = "";
        int errorCode = 0;
        if (value != 0) {
            if (board.rowContains(row, value)) {
                errorCode = 1; //Error a la columna
            } else if (board.colContains(column, value)) {
                errorCode = 2; // Error a la fila
            } else {
                errorCode = 3; //Error a la regio 
            }
        }
        switch (errorCode) {
            case 1:
                message = "Error a la Fila: No pot haver dues cel·les amb el mateix valor en una mateixa fila.";
                break;
            case 2:
                message = "Error a la Columna: No pot haver dues cel·les amb el mateix valor en una mateixa columna.";
                break;
            case 3:
                message = "Error a la Regio: No pot haver dues cel·les amb el mateix valor en una mateixa regió.";
                break;
        }
        return message;
    }
}
