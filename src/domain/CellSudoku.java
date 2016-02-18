/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

public class CellSudoku extends Cell{
        
        /**
        * El tipus de Cela.
        */
	private boolean fixed;

    /**
     * Constructora amb valor i tipus de Cela.
     * @pre 0 le; value
     * @post 
     * @param value Enter &gt; 0
     * @param fixed Tipus de Cela
     * @throws IllegalArgumentException Error en el valor de la Cel·la
     */
    public CellSudoku(int value, boolean fixed)throws IllegalArgumentException {
        if(!filterValue(value)) throw new IllegalArgumentException("El valor d'una cel·la ha de ser major o igual a 0");
        this.setValue(value);
        this.fixed = fixed;
    }

    /**
     * Comprovador del tipus de Cela
     * @pre none
     * @post none
     * @return boolean tipus cela.
     */
    public boolean isFixed() {
    	return fixed;
    }

    /**
     * Setter de l'atribut fixed.
     * @return boolean indica si l'operació s'ha realitzat amb exit
     * @pre none
     * @post tipus cel·la = fixed
     * @param fixed Tipus de Cela.
     */
    public boolean setFixed(boolean fixed) {
    	if(this.fixed != fixed){
            this.fixed = fixed;
            return true;
        }
        return false;
    }
    @Override
    public boolean setValue(int value){
        if(!this.fixed){
            super.setValue(value);
            return true;
        }
        return false;
    }
    /**
     * Filtre del valor value
     * @pre none
     * @post none
     * @param value valor que es vol filtrar
     * @return boolean que indica si el valor es correcte
     */
    private boolean filterValue(int value) {
        return value >= 0;
    }
    
    @Override
    public String toString(){
        if (fixed) {
            return Integer.toString(getValue()) + "F";
        }
        else {
            return Integer.toString(getValue()) + "V";
        }
    }
    
}