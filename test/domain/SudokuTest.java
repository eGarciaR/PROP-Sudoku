/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.RealSystem;
import org.junit.runner.JUnitCore;

/**
 *
 * @author ericgarciaribera
 */
public class SudokuTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), SudokuTest.class.getName());
    }
    
    public SudokuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setBoard method, of class Sudoku.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        BoardSudoku expResult = new BoardSudoku(3);
        Sudoku instance = new Sudoku();
        instance.setBoard(expResult);
        BoardSudoku result = instance.getBoard();
        assertEquals(expResult,result);
    }

    /**
     * Test of getBoard method, of class Sudoku.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        
        // Fracàs
        Sudoku instance = new Sudoku();
        BoardSudoku expResult = null;
        BoardSudoku result = instance.getBoard();
        assertEquals(expResult, result);
        
        // Éxit
        expResult = new BoardSudoku(3);
        instance.setBoard(expResult);
        result = instance.getBoard();
        assertEquals(expResult,result);
    }

    /**
     * Test of setUser method, of class Sudoku.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String expResult = "UsuariTest";
        Sudoku instance = new Sudoku();
        instance.setUser(expResult);
        String result = instance.getUsername();
        assertEquals(expResult,result);
    }

    /**
     * Test of getUsername method, of class Sudoku.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Sudoku instance = new Sudoku();
        String expResult = "UsuariTest";
        instance.setUser(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDifficulty method, of class Sudoku.
     */
    @Test
    public void testGetDifficulty() {
        System.out.println("getDifficulty");
        Sudoku instance = new Sudoku();
        BoardSudoku board = new BoardSudoku(3);
        board.setDifficulty(BoardSudoku.Difficulty.EASY);
        instance.setBoard(board);
        BoardSudoku.Difficulty expResult = instance.getDifficulty();
        BoardSudoku.Difficulty result = instance.getDifficulty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCellValue method, of class Sudoku.
     */
    @Test
    public void testSetCellValue() {
        System.out.println("setCellValue");
        int row = 0;
        int column = 0;
        int value = 1;
        Sudoku instance = new Sudoku();
        BoardSudoku board = new BoardSudoku(3);
        instance.setBoard(board);
        boolean expResult = true;
        boolean result = instance.setCellValue(row, column, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of correctCell method, of class Sudoku.
     */
    @Test
    public void testCorrectCell() {
        System.out.println("correctCell");
        int row = 0;
        int column = 0;
        int value = 1;
        Sudoku instance = new Sudoku();
        BoardSudoku board = new BoardSudoku(3);
        instance.setBoard(board);
        boolean expResult = true;
        boolean result = instance.correctCell(row, column, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of solverHelper method, of class Sudoku.
     */
    @Test
    public void testSolverHelper() {
        System.out.println("solverHelper");
        int row = 0;
        int column = 0;
        int value = 0;
        Sudoku instance = new Sudoku();
        BoardSudoku board = new BoardSudoku(3);
        instance.setBoard(board);
        String expResult = "";
        String result = instance.solverHelper(row, column, value);
        assertEquals(expResult, result);
    }
    
}
