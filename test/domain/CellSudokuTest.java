/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 * Test: passed
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

public class CellSudokuTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), CellSudokuTest.class.getName());
    }
    
    public CellSudokuTest() {
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
     * Test of isFixed method, of class CellSudoku.
     */
    @Test
    public void testIsFixed() throws IllegalArgumentException {
        System.out.println("isFixed");
        CellSudoku instance1 = new CellSudoku(0,false);
        CellSudoku instance2 = new CellSudoku(0,false);
        CellSudoku instance3 = new CellSudoku(0, false);
        boolean expResult = false;
        boolean result1 = instance1.isFixed();
        boolean result2 = instance2.isFixed();
        boolean result3 = instance3.isFixed();
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(expResult, result3);
    }
    
        /**
     * Test of setFixed method, of class CellSudoku.
     */
    @Test
    public void testSetFixed() {
        System.out.println("setFixed");
        boolean fixed = false;
        CellSudoku instance = new CellSudoku(1,true);
        instance.setFixed(fixed);
        boolean expResult = false;
        boolean result;
        result = instance.isFixed();
        assertEquals(expResult,result);
    }

    /**
     * Test of setIsFixed method, of class CellSudoku.
     */
    @Test
    public void testSetIsFixed() {
        System.out.println("setIsFixed");
        boolean isFixed = true;
        CellSudoku instance = new CellSudoku(1,false);
        instance.setFixed(isFixed);
        boolean expResult = true;
        boolean result;
        result = instance.isFixed();
        assertEquals(expResult,result);
    }

//    /**
//     * Test of getRow method, of class CellSudoku.
//     */
//    @Test
//    public void testGetRow() {
//        System.out.println("getRow");
//        CellSudoku instance = new CellSudoku(1, true);
//        CellSudoku instance2 = new CellSudoku(1, 0, true);
//        CellSudoku instance3 = new CellSudoku(1, true, 2, 0);
//        int expResult = 0;
//        int expResult2 = 1;
//        int expResult3 = 2;
//        int result = instance.getRow();
//        int result2 = instance2.getRow();
//        int result3 = instance3.getRow();
//        assertEquals(expResult, result);
//        assertEquals(expResult2, result2);
//        assertEquals(expResult3, result3);
//    }

//    /**
//     * Test of setRow method, of class CellSudoku.
//     */
//    @Test
//    public void testSetRow() {
//        System.out.println("setRow");
//        int row = 2;
//        CellSudoku instance = new CellSudoku(1, true,1,1);
//        instance.setRow(row);
//        int expResult = 2;
//        int result;
//        result = instance.getRow();
//        assertEquals(expResult, result);
//    }

//    /**
//     * Test of getColumn method, of class CellSudoku.
//     */
//    @Test
//    public void testGetColumn() {
//        System.out.println("getColumn");
//        CellSudoku instance = new CellSudoku(1,true);
//        CellSudoku instance2 = new CellSudoku(1, true, 1, 1);
//        CellSudoku instance3 = new CellSudoku(0, 2, true);
//        int expResult = 0;
//        int expResult2 = 1;
//        int expResult3 = 2;
//        int result = instance.getColumn();
//        int result2 = instance2.getColumn();
//        int result3 = instance3.getColumn();
//        assertEquals(expResult, result);
//        assertEquals(expResult2, result2);
//        assertEquals(expResult3, result3);
//    }
//
//    /**
//     * Test of setColumn method, of class CellSudoku.
//     */
//    @Test
//    public void testSetColumn() {
//        System.out.println("setColumn");
//        int column = 3;
//        CellSudoku instance = new CellSudoku(1, true, 1, 1);
//        instance.setColumn(column);
//        int expResult = 3;
//        int result;
//        result = instance.getColumn();
//        assertEquals(expResult,result);
//    }
}
