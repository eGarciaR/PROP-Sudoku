/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.RealSystem;
import org.junit.runner.JUnitCore;

/**
 *
 * @author dani
 */
public class SudokuSolverTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), SudokuSolverTest.class.getName());
    }

    public SudokuSolverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * Test of uniqueSolution method, of class SudokuSolver.
     */
    @Test
    public void testUniqueSolution() {
        System.out.println("uniqueSolution");
        BoardSudoku test1 = new BoardSudoku(2);
        test1.fromString("3,2,1,4|4,1,2,3|0,0,0,0|0,0,0,0");
        SudokuSolver t1 = new SudokuSolver(test1);
        assertFalse(t1.uniqueSolution());

        BoardSudoku test2 = new BoardSudoku(2);
        test2.fromString("0,2,1,0|4,0,0,3|4,0,0,1|0,2,3,0");
        SudokuSolver t2 = new SudokuSolver(test2);
        assertTrue(t2.uniqueSolution());

        BoardSudoku test3 = new BoardSudoku(2);
        test3.fromString("3,0,0,1|0,1,4,0|0,0,0,2|1,3,0,0");
        SudokuSolver t3 = new SudokuSolver(test3);
        assertFalse(t3.uniqueSolution());

        BoardSudoku test4 = new BoardSudoku(3);
        test4.fromString("0,0,3,2,0,0,0,0,0|0,0,0,0,0,0,5,0,0|0,0,0,0,0,0,0,0,6|0,0,0,8,0,6,3,7,9|0,6,0,0,0,2,1,0,0|0,0,2,0,0,1,0,0,0|0,0,0,0,5,0,0,0,0|0,0,1,0,0,8,4,0,0|0,0,0,0,0,4,0,0,0");
        SudokuSolver t4 = new SudokuSolver(test4);
        assertFalse(t4.uniqueSolution());
    }

}
