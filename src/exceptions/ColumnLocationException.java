/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package exceptions;

/**
 *
 * @author dani
 */
public class ColumnLocationException extends BadNumberPositionException {
    public ColumnLocationException() {
        super();
    }
    public ColumnLocationException(String message) {
        super(message);
    }
}

