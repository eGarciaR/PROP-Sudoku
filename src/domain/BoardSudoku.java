package domain;

import controllers.domain.BoardDomainCtrl;
import exceptions.ColumnLocationException;
import exceptions.RegionLocationException;
import exceptions.RowLocationException;
import java.util.HashMap;

public class BoardSudoku extends Board {

    /**
     * Xifres actuals del tauler
     */
    private int nNumbers;

    /**
     * Dificultat del tauler
     */
    private Difficulty difficulty;

    /**
     * Matriu de regions(Tauler)
     */
    private Region[][] board;

    /**
     * Atribut derivat. Indica quants values hi ha en cada fila del tauler.
     */
    private int[] valuesInRows;

    /**
     * Atribut derivat. Indica quants values hi ha en cada columna del tauler.
     */
    private int[] valuesInColumns;

    /**
     * Mida mímima del tauler.
     */
    private final static int MIN_N = 2;

    /**
     * Mida màxima del tauler.
     */
    private final static int MAX_N = 4;

    /**
     * Enumeració de les dificultats del tauler
     */
    public enum Difficulty {

        EASY("EASY"),
        MEDIUM("MEDIUM"),
        HARD("HARD");

        private final String name;

        private Difficulty(String s) {
            this.name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : this.name.equals(otherName);
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * Constructora amb mida d'un tauler
     *
     * @Pre 7 &gt; n &gt; 0
     * @Post crea un nou tauler de dimensió n
     * @param n mida del tauler
     * @throws IllegalArgumentException Fallo en la mida del tauler
     */
    public BoardSudoku(int n) throws IllegalArgumentException {
        if (!filterN(n)) {
            throw new IllegalArgumentException("La mida d'un tauler és " + MIN_N + " <= mida <= " + MAX_N + ".");
        }
        this.setN(n);
        this.initBoard(n);
    }

    /**
     * Constructora amb mida i id d'un tauler
     *
     * @Pre 7 &gt; n &gt; 0 and id valid
     * @Post crea un nou tauler de dimenció n i id = id
     * @param id id del tauler
     * @param n mida del tauler
     * @throws IllegalArgumentException Fallo en la mida del tauler
     */
    public BoardSudoku(int id, int n) throws IllegalArgumentException {
        if (!filterN(n)) {
            throw new IllegalArgumentException("La mida d'un tauler és " + MIN_N + " <= mida <= " + MAX_N + ".");
        }
        this.setId(id);
        this.setN(n);
        this.nNumbers = 0;
        this.initBoard(n);
    }

    /**
     * Inicialitza el tauler
     *
     * @Pre 7 &gt; n &gt; 0
     * @Post el tauler queda inicialitzat
     * @param n mida del tuler
     * @throws Exception
     */
    private void initBoard(int n) throws IllegalArgumentException {
        this.board = new Region[n][n];
        this.valuesInColumns = new int[n * n];
        this.valuesInRows = new int[n * n];
        this.nNumbers = 0;
        Region regio;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                regio = new Region(i, j, n);
                board[i][j] = regio;
            }
        }
    }

    @Override
    /**
     * Setter de l'atribut n.
     *
     * @Pre 7 &gt; n &gt; 0
     * @Post mida del tauler = n
     * @param n mida del tauler.
     * @return boolean indica si l'operació s'ha realitzat amb éxit
     */
    public boolean setN(int n) {
        if (filterN(n)) {
            super.setN(n);
            return true;
        }
        return false;
    }

    /**
     * Getter de l'atribut n
     *
     * @return Integer mida del tauler
     */
    public int getNNumbers() {
        return nNumbers;
    }

    /**
     * Setter de l'atribut nNumbers.
     *
     * @Pre n² &ge; nNumbers &ge; 0
     * @Post nombres en el tauler = nNumbers
     * @param nNumbers nombre de numeros en el tauler.
     * @return boolean indica si l'operació s'ha realitzat amb exit
     */
    public boolean setNNumbers(int nNumbers) {
        if (filterNNumbers(nNumbers)) {
            this.nNumbers = nNumbers;
            return true;
        }
        return false;
    }

    /**
     * Retorna el nivell de dificultat del tauler.
     *
     * @return Retorna el nivell de dificultat del tauler.
     */
    public Difficulty getDifficulty() {
        if (this.difficulty == null) {
            this.calculateDifficulty();
        }
        return this.difficulty;
    }

    /**
     * Setter de la dificultat del tauler.
     *
     * @Pre none
     * @Post la dificultat del tauler es = a difficulty
     * @param difficulty Nivell de dificultat del tauler
     * @return True si s'ha canviat, False altrament.
     */
    public boolean setDifficulty(Difficulty difficulty) {
        if (this.difficulty != difficulty) {
            this.difficulty = difficulty;
            return true;
        }
        return false;
    }

    /**
     * Comptabilitza el numero de cel·les amb valor d'una fila del tauler.
     *
     * @Pre n^2 &gt; row &ge; 0
     * @Post none
     * @param row Fila
     * @return Retorna el numero de cel·les amb valor d'una fila del tauler.
     * @throws IllegalArgumentException Fallo en el valor row.
     */
    public int countValuesInRow(int row) throws IllegalArgumentException {
        if (!filterColRow(row)) {
            throw new IllegalArgumentException("El valor d'una fila es 0 <= fila <= n^2");
        }
        return valuesInRows[row];
    }

    /**
     * Comptabilitza el numero de cel·les amb valor d'una columna del tauler.
     *
     * @Pre n^2 &gt; col &ge; 0
     * @Post none
     * @param col Columna
     * @return Retorna el numero de cel·les amb valor d'una columna del tauler.
     */
    public int countValuesInColumn(int col) throws IllegalArgumentException {
        if (!filterColRow(col)) {
            throw new IllegalArgumentException("El valor d'una columna es 0 <= columna <= n^2");
        }
        return valuesInColumns[col];
    }

    /**
     * Comptabilitza el numero de cel·les amb valor d'una Regió.
     *
     * @param row Fila
     * @param col Columna
     * @return Retorna el numero de cel·les amb valor d'una Regió.
     */
    public int countValuesInRegion(int row, int col) {
        int nValues;
        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        Region region = this.board[regionRow][regionCol];
        nValues = region.getNValues();
        return nValues;
    }

    /**
     * Donats una fila i una columna retorna el valor de la cela corresponent.
     *
     * @Pre n^2 &gt; row, col &ge; 0
     * @param row Fila
     * @param col Columna
     * @return Integer valor de la cel·la amb fila = row columna = col
     */
    public int getCellValue(int row, int col) {
        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        int cellRow = row % n;
        int cellCol = col % n;
        Region region = this.board[regionRow][regionCol];
        int value = region.getCellValue(cellRow, cellCol);
        return value;
    }

    /**
     * Donats una fila i una columna modifica la cela corresponent amb el valor
     * de xifra.
     *
     * @Pre n^2 &gt; row, col &ge; 0 n^2 &gt; value &gt; 0
     * @Post la cel·la pren per valor value
     * @param row Fila
     * @param col Columna
     * @param value Valor
     * @return boolean indica si l'operació s'ha realitzat amb exit
     */
    public boolean setCellValue(int row, int col, int value) {
        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        int cellRow = row % n;
        int cellCol = col % n;
        Region region = this.board[regionRow][regionCol];
        int previousValue = region.getCellValue(cellRow, cellCol);
        boolean ret = region.setCellValue(cellRow, cellCol, value);

        //Si la cel·la encara no tenia valor.
        if (previousValue == 0 && value != 0) {
            ++this.valuesInColumns[col];
            ++this.valuesInRows[row];
            ++this.nNumbers;
        }
        //Si la cel·la passa a no tenir valor.
        if (previousValue != 0 && value == 0) {
            --this.valuesInColumns[col];
            --this.valuesInRows[row];
            --this.nNumbers;
        }
        return ret;
    }

    /**
     * Donats una fila i una columna ens indica si la cela es fixa o no.
     *
     * @Pre n^2 &gt; row, col &ge; 0 n^2
     * @Post none
     * @param row Fila
     * @param col Columna
     * @return True si es una cela fixa, False altrament.
     */
    public boolean isFixedCell(int row, int col) {
        boolean fixed;

        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        int cellRow = row % n;
        int cellCol = col % n;
        Region region = this.board[regionRow][regionCol];
        fixed = region.isFixedCell(cellRow, cellCol);
        return fixed;
    }

    /**
     * Donats una fila i una columna modifica l'estat de la cela amb el valor de
     * fixa.
     *
     * @Pre n^2 &gt; row, col &ge; 0 n^2 &gt; value &gt; 0
     * @Post el Tipus de la cel·la pren per valor fixed
     * @param row Fila
     * @param col Columna
     * @param fixed Tipus
     * @return boolean indica si l'operació s'ha realitzat amb exit
     */
    public boolean setFixedCell(int row, int col, boolean fixed) {
        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        int cellRow = row % n;
        int cellCol = col % n;
        Region region = this.board[regionRow][regionCol];
        return region.setFixedCell(cellRow, cellCol, fixed);
    }

    /**
     * Indica si un valor està en una regió.
     *
     * @Pre n^2 &gt; row, col ge; 0 n^2 &gt; value &gt; 0
     * @Post none
     * @param value Valor a buscar
     * @param row Fila
     * @param col Columna
     * @return True si la regió conté <em>value</em>, False altrament.
     */
    public boolean regionContains(int row, int col, int value) {
        int n = this.getN();
        int regionRow = row / n;
        int regionCol = col / n;
        Region region = this.board[regionRow][regionCol];
        return region.contains(value);
    }

    /**
     * Indica si un valor està en una fila.
     *
     * @Pre n^2 &gt; row, col ge; 0
     * @Post none
     * @param value Valor a buscar
     * @param row Fila
     * @return True si la <em>row</em> conté <em>value</em>, False altrament.
     */
    public boolean rowContains(int row, int value) {
        Region region;
        int n = this.getN();
        int regionRow = row / n;
        int cellRow = row % n;
        for (int col = 0; col < n; col++) {
            region = this.board[regionRow][col];
            if (region.rowContains(cellRow, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si un valor està en una columna.
     *
     * @Pre n^2 &gt; row, col &ge; 0
     * @Post none
     * @param value Valor a buscar.
     * @param col Columna.
     * @return True si la <em>col</em> conté <em>value</em>, False altrament.
     */
    public boolean colContains(int col, int value) {
        Region region;
        int n = this.getN();
        int regionCol = col / n;
        int cellCol = col % n;
        for (int row = 0; row < n; row++) {
            region = this.board[row][regionCol];
            if (region.colContains(cellCol, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Donat un Sudoku i una posició, comprova que el valor satisfà les regles
     * del joc.
     * @Pre n^2 &gt; row, col &ge; 0 n^2 &gt; value &gt; 0
     * @Post none
     * @param value Xifra que es vol posar a la Cel·la
     * @param row Fila a la que es troba la Cel·la
     * @param col Columna a la que es troba la Cel·la
     * @return False si el valor incompleix alguna norma del joc, altrament
     * retorna true
     * @throws exceptions.RowLocationException Llançada quan no es pot introduïr el valor a la fila.
     * @throws exceptions.ColumnLocationException Llançada quan no es pot introduïr el valor a la columna.
     * @throws exceptions.RegionLocationException Llançada quan no es pot introduïr el valor a la regió.
     */
    public boolean correctCell(int row, int col, int value) throws RowLocationException, ColumnLocationException, RegionLocationException{
        int pValue = this.getCellValue(row, col);
        if (pValue == value) return true;
        if (value != 0) {
            if (rowContains(row, value)) {
                throw new RowLocationException();
            }
            if (colContains(col, value)) {
                throw new ColumnLocationException();
            }
            if (regionContains(row, col, value)) {
                throw new RegionLocationException();
            }
        }
        return true;
    }

    /**
     * Retorna el numero total de cel·les del tauler
     *
     * @Pre none
     * @Post none
     * @return Integer numero total de cel·les del tauler
     */
    public int numTotalCels() {
        int n = getN();
        int exp = 4;
        return (int) Math.pow(n, exp);
    }

    // Funcio provisional.
    public void printBoard() {
        int n = this.getN();
        int n2 = (int) Math.pow(n, 2);
        for (int i = 0; i < n2; ++i) {
            if (i != 0 && i % n == 0) {
                for (int z = 0; z < n2 * 3 - 1; ++z) {
                    System.out.print("-");
                }
                System.out.println("");
            }
            for (int j = 0; j < n2; ++j) {
                if (j != 0 && j % n == 0) {
                    System.out.print("|");
                } else if (j != 0) {
                    System.out.print(",");
                }
                int k = this.getCellValue(i, j);
                if (k < 10) {
                    System.out.print(" " + k);
                } else {
                    System.out.print(k);
                }
            }
            System.out.println("");
        }
    }

    // Format board: 1,2,3,4|5,6,7,8|9,0,1,2|3,4,5,6
    /**
     * Codifica el tauler a un String
     *
     * @Pre none
     * @Post none
     * @return String tauler codificat en forma de String
     */
    @Override
    public String toString() {
        int n = this.getN();
        String s = "";
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                s += board[r][c].toString() + "|";
            }
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    /**
     * Donat un String amb un format determinat omple un tauler.
     * @param board Tauler
     */
    public void fromString(String board) {
        String[] regions = board.split("\\|");
        int n = this.getN();
        this.initBoard(n);
        int rowRegion = 0, colRegion = 0;
        int rowCell, colCell, value, row, col;
        for (String sRegion : regions) {
            String[] cells = sRegion.split(",");
            rowCell = 0;
            colCell = 0;
            for (String cell : cells) {
                String fixed = cell.substring(cell.length() - 1, cell.length());
                cell = cell.substring(0, cell.length() - 1);
                value = Integer.valueOf(cell);
                row = rowRegion*n + rowCell;
                col = colRegion*n + colCell;
                this.setCellValue(row, col, value);
                if (value != 0 && fixed.equals("F")) {
                    this.setFixedCell(row, col, true);
                }
                ++colCell;
                if (colCell >= n) {
                    ++rowCell;
                    colCell = 0;
                }
            }
            ++colRegion;
            if (colRegion >= n) {
                ++rowRegion;
                colRegion = 0;
            }
        }
    }

    /**
     * Fa una copia dels valors de les cel·les del tauler
     *
     * @Pre none
     * @Post original es una shadow copy del tauler
     * @param original tauler
     * @throws IllegalArgumentException No s'ha pogut copiar el sudoku
     */
    public void copySudoku(BoardSudoku original) throws IllegalArgumentException {
        int n = original.getN();
        int N = n * n;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                this.setCellValue(i, j, original.getCellValue(i, j));
                this.setFixedCell(i, j, original.isFixedCell(i, j));
            }
        }
    }

    /**
     * Fa una deep copy del tauler
     *
     * @Pre none
     * @Post none
     * @return BoardSudoku copia del tauler original
     * @throws IllegalArgumentException No s'ha pogut copiar el Board
     */
    public BoardSudoku copyBoard() throws IllegalArgumentException {
        BoardSudoku copy = new BoardSudoku(this.getId(), this.getN());
        copy.setDifficulty(this.difficulty);
        copy.setNNumbers(this.nNumbers);
        int n = copy.getN();
        int n2 = (int) Math.pow(n, 2);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n2; ++j) {
                copy.setCellValue(i, j, this.getCellValue(i, j));
                copy.setFixedCell(i, j, this.isFixedCell(i, j));
            }
        }
        return copy;
    }

    /**
     * Calcula la dificultat del tauler en funció del nombre de cel·les que té.
     *
     * @Pre totes les xifres del tauler son de tipus Fixed
     * @Post el tauler te dificultat
     * @return Dificultat del tauler
     */
    public Difficulty calculateDifficulty() {
        int exp = 4;
        int n4 = (int) Math.pow(getN(), exp);
        double cells = (double) this.nNumbers;
        double percentatge = cells * 100 / n4;
        Difficulty diff;
        if (percentatge <= 35) {
            diff = Difficulty.HARD;
        } else if (percentatge <= 40) {
            diff = Difficulty.MEDIUM;
        } else {
            diff = Difficulty.EASY;
        }
        return diff;
    }

    /**
     * Comprova que nNumbers sigui un valor correcte
     *
     * @Pre none
     * @Post none
     * @param nNumbers
     * @return boolean true si nNumbers es un valor correcte false en cas
     * contrari
     */
    private boolean filterNNumbers(int nNumbers) {
        return nNumbers >= 0 && nNumbers <= Math.pow(this.getN(), 4);
    }

    /**
     * Comprova que el valor n sigui correcte
     *
     * @Pre none
     * @Post none
     * @param n tamany del tauler
     * @return boolean true si n es un tmany correcte false en cas contrari
     */
    private boolean filterN(int n) {
        return n >= MIN_N && n <= MAX_N;
    }

    /**
     * Comprova que el valor number referent a fila o columna sigui correcte
     *
     * @Pre none
     * @Post none
     * @param number
     * @return boolean true si number es una fila o columna correcta false en
     * cas contrari
     */
    private boolean filterColRow(int number) {
        return number >= 0 && number < Math.pow(this.getN(), 2);
    }

    /**
     *
     * @return Clau,Valor dels atributs de l'objecte.
     */
    public HashMap<String, Object> getAttrs() {
        HashMap<String, Object> attrs = new HashMap<>();
        attrs.put(BoardDomainCtrl.BOARD_ID, this.getId());
        attrs.put(BoardDomainCtrl.BOARD_DIFFICULTY, this.difficulty.toString());
        attrs.put(BoardDomainCtrl.BOARD_N, this.getN());
        attrs.put(BoardDomainCtrl.BOARD_N_NUMBERS, this.nNumbers);
        attrs.put(BoardDomainCtrl.BOARD_BOARD, this.toString());
        return attrs;
    }

    /**
     * Rang de mides possibles del tauler
     *
     * @return Array de mides possibles
     */
    public static Integer[] getSizeModel() {
        Integer[] model = new Integer[MAX_N - MIN_N + 1];
        int i = 0;
        for (int size = MIN_N; size <= MAX_N; size++) {
            model[i] = size;
            ++i;
        }
        return model;
    }

    /**
     * Difficultats possibles del tauler
     *
     * @return Array de dificultats possibles
     */
    public static String[] getDifficultyModel() {
        Difficulty[] diffs = Difficulty.values();
        String[] model = new String[diffs.length];
        for (int i = 0; i < diffs.length; i++) {
            model[i] = diffs[i].toString();
        }
        return model;
    }
    
    public void setAllCellsFixed() {
        int n = this.getN();
        int n2 = (int) Math.pow(n,2);
        int v;
        for (int r = 0; r < n2; ++r) {
            for (int c = 0; c < n2; ++c) {
                v = getCellValue(r, c);
                if (v != 0) {
                    setFixedCell(r, c, true);
                }
            }
        }
    }

}
