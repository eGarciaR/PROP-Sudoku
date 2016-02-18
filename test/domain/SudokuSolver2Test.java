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

/**
 *
 * @author dani
 */
public class SudokuSolver2Test {
    
    public SudokuSolver2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of copyOf3Dim method, of class SudokuSolver2.
     */
    @Test
    public void testCopyOf3Dim() {
        System.out.println("copyOf3Dim");
        boolean[][][] array = null;
        SudokuSolver2 instance = null;
        boolean[][][] expResult = null;
        boolean[][][] result = instance.copyOf3Dim(array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minOneSolution method, of class SudokuSolver2.
     */
    @Test
    public void testMinOneSolution() {
        System.out.println("minOneSolution");
        SudokuSolver2 instance = null;
        boolean expResult = false;
        boolean result = instance.minOneSolution();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minOneSolutionCopy method, of class SudokuSolver2.
     */
    @Test
    public void testMinOneSolutionCopy() {
        System.out.println("minOneSolutionCopy");
        SudokuSolver2 instance = null;
        boolean expResult = false;
        boolean result = instance.minOneSolutionCopy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uniqueSolution method, of class SudokuSolver2.
     */
    @Test
    public void testUniqueSolution() {
        System.out.println("uniqueSolution");
        SudokuSolver2 instance = null;
        boolean expResult = false;
        boolean result = instance.uniqueSolution();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of uniqueSolutionCopy method, of class SudokuSolver2.
     */
    @Test
    public void testUniqueSolutionCopy() {
        System.out.println("uniqueSolutionCopy");
        SudokuSolver2 instance = null;
        boolean expResult = false;
        boolean result = instance.uniqueSolutionCopy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoardSudoku method, of class SudokuSolver2.
     */
    @Test
    public void testGetBoardSudoku() {
        System.out.println("getBoardSudoku");
        SudokuSolver2 instance = null;
        BoardSudoku expResult = null;
        BoardSudoku result = instance.getBoardSudoku();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandom method, of class SudokuSolver2.
     */
    @Test
    public void testGenerateRandom() {
        System.out.println("generateRandom");
        int initialCells = 0;
        SudokuSolver2 instance = null;
        BoardSudoku expResult = null;
        BoardSudoku result = instance.generateRandom(initialCells);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printBoard method, of class SudokuSolver2.
     */
    @Test
    public void testPrintBoard() {
        System.out.println("printBoard");
        SudokuSolver2 instance = null;
        instance.printBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
