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
import org.junit.Rule;
import org.junit.internal.RealSystem;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;

/**
 *
 * @author Marc
 */
public class BoardSudokuTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), BoardSudokuTest.class.getName());
    }
    
    public BoardSudokuTest() {
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test of Exceptions, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testExceptions() throws IllegalArgumentException {
        thrown.expect(Exception.class);
        thrown.expectMessage("La mida d'un tauler és 2 <= mida <= 4.");
        BoardSudoku instance = new BoardSudoku(-1);
    }

    /**
     * Test of getNNumbers method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNNumbers() throws IllegalArgumentException {
        System.out.println("getNNumbers");
        BoardSudoku instance = new BoardSudoku(3);
        instance.setNNumbers(3);
        int expResult = 3;
        int result = instance.getNNumbers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNNumbers method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetNNumbers() throws IllegalArgumentException {
        System.out.println("setNNumbers");
        int NNumbers = 0;
        BoardSudoku instance = new BoardSudoku(2);
        instance.setNNumbers(NNumbers);
    }

    /**
     * Test of getValuesInRow method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetValuesInRow() throws IllegalArgumentException {
        System.out.println("getValuesInRow");
        int row = 2;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(2, 2, 3);
        instance.setCellValue(2, 6, 5);
        instance.setCellValue(2, 7, 5);
        int expResult = 3;
        int result = instance.countValuesInRow(row);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValuesInColumn method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCountValuesInColumn() throws IllegalArgumentException {
        System.out.println("getValuesInColumn");
        int col = 2;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(2, 2, 3);
        instance.setCellValue(6, 2, 5);
        int expResult = 2;
        int result = instance.countValuesInColumn(col);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellValue method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCellValue() throws Exception {
        System.out.println("getCellValue");
        int row = 3;
        int col = 3;
        BoardSudoku instance = new BoardSudoku(2);
        instance.setCellValue(3, 3, 3);
        int expResult = 3;
        int result = instance.getCellValue(row, col);
        assertEquals(expResult, result);
    }

    /**
     * Test of setCellValue method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetCellValue() throws Exception {
        System.out.println("setCellValue");
        int value = 0;
        int row = 8;
        int col = 8;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(row, col, value);
    }

    /**
     * Test of isFixedCell method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testIsFixedCell() throws Exception {
        System.out.println("isFixedCell");
        int row = 3;
        int column = 3;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setFixedCell(3, 3, true);
        boolean expResult = true;
        boolean result = instance.isFixedCell(row, column);
        assertEquals(expResult, result);
        result = instance.isFixedCell(1, 1);
        assertEquals(false, result);
    }

    /**
     * Test of setFixedCell method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetFixedCell() throws Exception {
        System.out.println("setFixedCell");
        boolean fixed = false;
        int row = 3;
        int column = 3;
        BoardSudoku instance = new BoardSudoku(2);
        instance.setFixedCell(row, column, fixed);
    }

    /**
     * Test of regionContains method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRegionContains() throws Exception {
        System.out.println("regionHaveValue");
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(2, 2, 3);
        instance.setCellValue(6, 6, 5);
        boolean expResult = true;
        boolean result = instance.regionContains(0, 0, 0);
        assertEquals(expResult, result);
        result = instance.regionContains(7, 8, 5);
        assertEquals(expResult, result);
    }

    /**
     * Test of rowContains method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRowContains() throws Exception {
        System.out.println("rowHaveValue");
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(7, 6, 3);
        instance.setCellValue(3, 8, 5);
        boolean result = instance.rowContains(7, 3);
        assertEquals(true, result);
        result = instance.rowContains(4, 5);
        assertEquals(false, result);
    }

    /**
     * Test of colContains method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testColContains() throws Exception {
        System.out.println("colHaveValue");
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(7, 6, 3);
        instance.setCellValue(3, 8, 5);
        boolean result = instance.colContains(6, 3);
        assertEquals(true, result);
        result = instance.colContains(6, 5);
        assertEquals(false, result);
    }

    /**
     * Test of correctCell method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCorrectCell() throws Exception {
        System.out.println("correctCell");
        int value = 3;
        int row = 0;
        int col = 0;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(1, 1, 3);
        instance.setCellValue(4, 2, 4);
        instance.setCellValue(3, 3, 2);
        boolean expResult = false;
        boolean result = instance.correctCell(row, col, value);
        assertEquals(expResult, result);
    }


    /**
     * Test of numTotalCels method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNumTotalCels() throws Exception {
        System.out.println("numTotalCels");
        BoardSudoku instance = new BoardSudoku(2);
        int expResult = 16;
        int result = instance.numTotalCels();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToString() throws Exception {
        System.out.println("toString");
        BoardSudoku instance = new BoardSudoku(2);
        instance.setCellValue(0, 0, 2);
        System.out.println(instance.getCellValue(0, 0));
        String expResult = "2,0,0,0|0,0,0,0|0,0,0,0|0,0,0,0";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of fromString method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFromString() throws Exception {
        System.out.println("fromString");
        BoardSudoku instance = new BoardSudoku(2);
        String expResult = "2,0,0,0|0,0,0,0|0,0,0,0|0,0,0,0";
        instance.fromString(expResult);
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
        assertTrue(instance.isFixedCell(0, 0));
    }

    /**
     * Test of countValuesInRow method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCountValuesInRow() throws Exception {
        System.out.println("countValuesInRow");
        int row = 3;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(3, 1, 3);
        instance.setCellValue(3, 2, 4);
        instance.setCellValue(3, 3, 2);
        int expResult = 3;
        int result = instance.countValuesInRow(row);
        assertEquals(expResult, result);
    }

    /**
     * Test of countValuesInRegion method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCountValuesInRegion() throws Exception {
        System.out.println("countValuesInRegion");
        int row = 0;
        int col = 0;
        BoardSudoku instance = new BoardSudoku(3);
        instance.setCellValue(0, 1, 3);
        instance.setCellValue(1, 2, 4);
        instance.setCellValue(3, 3, 2);
        int expResult = 2;
        int result = instance.countValuesInRegion(row, col);
        assertEquals(expResult, result);
    }

    /**
     * Test of printBoard method, of class BoardSudoku.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPrintBoard() throws Exception {
        System.out.println("printBoard");
        BoardSudoku instance = new BoardSudoku(2);
        instance.printBoard();

    }

}
