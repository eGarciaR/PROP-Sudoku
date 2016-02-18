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
public class RowLocationException extends BadNumberPositionException {
    public RowLocationException() {
        super();
    }
    public RowLocationException(String message) {
        super(message);
    }
}

