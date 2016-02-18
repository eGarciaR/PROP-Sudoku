/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

public class Cell {
    
    /**
     * El valor de la Cel·la.
     */
    private int value;
    
    /**
     * Constructora per defecte.
     */
    public Cell() {
        //Un int sense valor, no pot ser null, implicitament es 0.
        this.value = 0;//Per tant es millor fer explicit que val 0.
    }
    
    /**
     * Constructora amb valor.
     * Pre: value &gt; 0
     * @param value Enter &gt; 0
     */
    public Cell(int value) {
        if (this.filterValue(value)){
            this.value = value;
        }else this.value = 0;
    }

    /**
     * Getter de l'atribut value.
     * @return El valor de la cel·la.
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Setter de l'atribut value.
     * @param value Enter &gt; 0
     * @return True si compleix el filtre, False altrament.
     */
    public boolean setValue(int value) {
        if (this.filterValue(value)) {
            this.value = value;
            return true;
        }
        return false;
    }
    
    /**
     * Filtre de l'atribut value.
     * @param value
     * @return True si value > 0, False altrament.
     */
    private boolean filterValue(int value) {
        return value >= 0;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.value);
    }

}
