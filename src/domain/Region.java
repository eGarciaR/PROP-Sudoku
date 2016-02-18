/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

public class Region {
    
    /**
    * El numero de xifres de la Regio.
    */
    private int nValues;
    
    /**
    * El tamany de la Regio.
    */
    private int size;
    
    /**
    * La regio
    */
    private CellSudoku[][] region;

    /**
     * Constructora per defecte.
     */
    public Region() {
    }

    /**
     * Constructora amb fila, columna i tamany de la Regio
     * @pre n^2 gt; size, row, column ge; 0
     * @post nova regió creada
     * @param row Fila on pertany la Regio
     * @param column Columna on pertany la Regio
     * @param size Tamany de la Regio
     * @throws IllegalArgumentException Fallada en la Creació de la Regió
     */
    public Region(int row, int column, int size)throws IllegalArgumentException {
        if(!filterSize(size))throw new IllegalArgumentException("El tamany d'una regió ha de ser major que 0");
        this.size = size;
        if(!filterColRow(row))throw new IllegalArgumentException("La posició d'una fila ha d'estar entre 0 i (n*n)-1");
        if(!filterColRow(column))throw new IllegalArgumentException("La posició d'una columna ha d'estar entre 0 i (n*n)-1");
        this.region = new CellSudoku[size][size];
        CellSudoku cell;
        this.region = new CellSudoku[this.size][this.size];
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                cell = new CellSudoku(0, false);
                region[r][c] = cell;
            }
        }
    }

    /**
     * Getter de l'atribut nValues.
     * @pre none
     * @post none
     * @return Numero de xifres de la Regio.
     */
    public int getNValues() {
        return nValues;
    }

    /**
     * Setter de l'atribut nValues.
     * @pre n^2 ge; nNumbers ge; 0
     * @post Numero de xifres de la regio = nNumbers
     * @param nNumbers Numero de xifres de la Regio.
     * @return boolean indica si l'operació s'ha realitzat amb exit
     */
    public boolean setNValues(int nNumbers) {
        if(filterNValues(nNumbers)){
            this.nValues = nNumbers;
            return true;
        }
        return false;
    }

    /**
     * Getter de l'atribut size.
     * @pre none
     * @post none
     * @return Tamany de la Regio.
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter de l'atribut size.
     * @pre size = n
     * @post tamany de la regio = size
     * @param size Tamany de la Regio.
     * @return boolean indica si l'operació s'ha realitzat amb exit
     */
    public boolean setSize(int size) {
        if(filterSize(size)){
            this.size = size;
            return true;
        }
        return false;
    }

    /**
     * Consultor del valor d'una Cela.
     * @pre n^2 ge; row, column ge; 0
     * @post none
     * @param row Fila on esta la Cela.
     * @param column Columna on esta la Cela.
     * @return Integer valor de la Cela identificada per row i column.
     */
    public int getCellValue(int row, int column) {
        return region[row][column].getValue();
    }
    
    /**
     * Modificador del valor d'una Cela.
     * @pre n^2 gt; row, column gte; 0 and n^2 ge; value ge; 0
     * @post valor de la cella a la posicio fila = row columna = column es = value 
     * @param value Valor a modificar.
     * @param row Fila on esta la Cela.
     * @param column Columna on esta la Cela.
     * @return boolean indica si s'ha inserit el valor
     */
    public boolean setCellValue(int row, int column, int value)throws IllegalArgumentException {
        if(!filterCellValue(value)) return false;
        CellSudoku cell = region[row][column];
        int previousValue = cell.getValue();
        boolean ret = cell.setValue(value);
        
        //Si la cel·la encara no tenia valor.
        if (previousValue == 0 && value != 0) {
            ++this.nValues;
        }
        //Si la cel·la passa a no tenir valor.
        if (previousValue != 0 && value == 0) {
            --this.nValues;
        }
        return ret;
    }
    
    /**
     * Consultor del tipus d'una Cela.
     * @pre n^2 gt; row, column ge; 0
     * @post none
     * @param row Fila on esta la Cela.
     * @param column Columna on esta la Cela.
     * @return boolean tipus de la Cela identificada per row i column.
     */
    public boolean isFixedCell(int row, int column) {
        return region[row][column].isFixed();
    }
    
    /**
     * Modificador del tipus d'una Cela.
     * @return boolean boolean indica si l'operació s'ha realitzat amb exit
     * @pre n^2 gt; row, column ge; 0
     * @post tipus de la cella a la posicio fila = row columna = column es = fixed 
     * @param fixed Tipus de Cela a modificar.
     * @param row Fila on esta la Cela.
     * @param column Columna on esta la Cela.
     */
    public boolean setFixedCell(int row, int column, boolean fixed) {
        return region[row][column].setFixed(fixed);
    }
    
    /**
     * Comprova si un valor esta en la regió.
     * @pre none
     * @post none
     * @param value Valor
     * @return boolean true si <em>value</em> està en alguna cel·la de la regió, False altrament.
     */
    public boolean contains(int value) {
        CellSudoku cell;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                cell = region[i][j];
                if (cell.getValue() == value) return true;
            }
        }
        return false;
    }
    
    /**
     * Comprova si un valor esta en la fila d'una regió.
     * @pre n^2 gt; row, column ge; 0
     * @post none
     * @param value Valor
     * @param row Fila
     * @return boolean true si <em>value</em> està en alguna cel·la de la fila <em>row</em>, False altrament.
     */
    public boolean rowContains(int row, int value) {
        CellSudoku cell;
        for (int col = 0; col < this.size; col++) {
            cell = this.region[row][col];
            if (cell.getValue() == value) return true;
        }
        return false;
    }
    
    /**
     * Comprova si un valor esta en la columna d'una regió.
     * @pre n^2 gt; row, column ge; 0
     * @post none
     * @param value Valor de la cel·la
     * @param col Columna on està la cel·la.
     * @return boolean true si <em>value</em> està en alguna cel·la de la columna <em>col</em>, False altrament.
     */
    public boolean colContains(int col, int value) {
        CellSudoku cell;
        for (int row = 0; row < this.size; row++) {
            cell = this.region[row][col];
            if (cell.getValue() == value) return true;
        }
        return false;
    }
    
    @Override
    /**
     * Codifica la regio amb un String
     * @pre none
     * @post none
     */
    public String toString(){
        CellSudoku c;
        String s = "";
        for(int i = 0; i < this.size; ++i){
            for(int j = 0; j < this.size; ++j){
                s += this.region[i][j].toString() + ",";
            }
        }
        return s.substring(0,s.length()-1);
    }
    
    /**
     * Comprova que el valor a inserir a la cela no supera el valor maxim possible
     * (Aquesta comprovació no la pot fer cela ja que no disposa de les dades necesaries)
     * @param value
     * @return 
     */
    private boolean filterCellValue(int value){
        return value <= Math.pow(this.size, 2);
    }
    
    /**
     * Comprova que el valor size sigui correcte
     * @pre none
     * @post none
     * @param size
     * @return boolean true si size es una mesura correcta false en cas contrari
     */
    private boolean filterSize(int size){
        return size >= 0;
    }
    
    /**
     * Comprova que el valor number referent a fila o columna sigui correcte
     * @pre none
     * @post none
     * @param number
     * @return boolean true si number es una fila o columna correcta false en cas contrari
     */
    private boolean filterColRow(int number){
        return number >= 0 && number < Math.pow(this.size, 2);
    }
    
    /**
     * Comprova que nValues sigui un valor correcte
     * @pre none
     * @post none
     * @param nValues
     * @return boolean true si nValues es un valor correcte false en cas contrari
     */
    private boolean filterNValues(int nValues){
        return ((nValues >= 0) && (nValues <= Math.pow(this.size, 2)));
    }
}
