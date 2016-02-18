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

public class RegionTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), RegionTest.class.getName());
    }
    
    public RegionTest() {
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
    
    @Rule public ExpectedException thrown= ExpectedException.none();

    /**
     * Test of Exceptions, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testExceptions()throws Exception{
        thrown.expect( Exception.class );
        thrown.expectMessage("El tamany d'una regió ha de ser major que 0");
        Region instance2 = new Region(2,0,-1);
    }
    
    
//    /**
//     * Test of getRow method, of class Region.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetRow() throws Exception {
//        System.out.println("getRow");
//        Region instance = new Region();
//        Region instance2 = new Region(2,0,3);
//        int expResult = 0;
//        int expResult2 = 2;
//        int result = instance.getRow();
//        int result2 = instance2.getRow();
//        assertEquals(expResult, result);
//        assertEquals(expResult2, result2);
//    }

//    /**
//     * Test of setRow method, of class Region.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testSetRow() throws Exception {
//        System.out.println("setRow");
//        int row = 0;
//        Region instance = new Region(2,0,3);
//        instance.setRow(row);
//        int expResult = 0;
//        int result = instance.getRow();
//        assertEquals(expResult,result);
//    }
//
//    /**
//     * Test of getColumn method, of class Region.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testGetColumn() throws Exception {
//        System.out.println("getColumn");
//        Region instance = new Region();
//        Region instance2 = new Region(0,2,3);
//        int expResult = 0;
//        int expResult2 = 2;
//        int result = instance.getColumn();
//        int result2 = instance2.getColumn();
//        assertEquals(expResult, result);
//        assertEquals(expResult2, result2);
//    }

//    /**
//     * Test of setColumn method, of class Region.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testSetColumn() throws Exception {
//        System.out.println("setColumn");
//        int column = 3;
//        Region instance = new Region(0,0,3);
//        instance.setColumn(column);
//        int expResult = 3;
//        int result = instance.getColumn();
//        assertEquals(expResult,result);
//    }

    /**
     * Test of getNValues method, of class Region.
     */
    @Test
    public void testGetNValues() {
        System.out.println("getNValues");
        Region instance = new Region();
        int expResult = 0;
        int result = instance.getNValues();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNValues method, of class Region.
     */
    @Test
    public void testSetNValues() {
        System.out.println("setNValues");
        int nNumbers = 3;
        Region instance = new Region();
        instance.setSize(9);
        instance.setNValues(nNumbers);
        int expResult = 3;
        int result = instance.getNValues();
        assertEquals(expResult,result);
    }

    /**
     * Test of getSize method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSize() throws Exception {
        System.out.println("getSize");
        Region instance = new Region();
        Region instance2 = new Region(0, 0, 3);
        int expResult = 0;
        int expResult2 = 3;
        int result = instance.getSize();
        int result2 = instance2.getSize();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setSize method, of class Region.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        int size = 3;
        Region instance = new Region();
        instance.setSize(size);
        int expResult = 3;
        int result = instance.getSize();
        assertEquals(expResult,result);
    }

    /**
     * Test of getCellValue method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCellValue() throws Exception {
        System.out.println("getCellValue");
        int row = 1;
        int column = 1;
        Region instance = new Region(0,0,3);
        instance.setCellValue(1,1,1);
        int expResult = 1;
        int result = instance.getCellValue(row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of setCellValue method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetCellValue() throws Exception {
        System.out.println("setCellValue");
        int value = 1;
        int row = 0;
        int column = 0;
        Region instance = new Region(0,0,3);
        instance.setCellValue(row, column, value);
        int expResult = 1;
        int result = instance.getCellValue(row, column);
        assertEquals(expResult,result);
    }

    /**
     * Test of isFixedCell method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsFixedCell() throws Exception {
        System.out.println("isFixedCell");
        int row = 0;
        int column = 0;
        Region instance = new Region(0,0,3);
        boolean expResult = false;
        boolean result = instance.isFixedCell(row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of setFixedCell method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetFixedCell() throws Exception {
        System.out.println("setFixedCell");
        boolean fixed = true;
        int row = 0;
        int column = 0;
        Region instance = new Region(0,0,3);
        instance.setFixedCell(row, column, fixed);
        boolean expResult = fixed;
        boolean result = instance.isFixedCell(row, column);
        assertEquals(expResult,result);
    }

    /**
     * Test of contains method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testContains() throws Exception {
        System.out.println("contains");
        int value = 2;
        Region instance = new Region(0,0,3);
        boolean expResult = false;
        boolean result = instance.contains(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of rowContains method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testRowContains() throws Exception {
        System.out.println("rowContains");
        int value = 0;
        int row = 0;
        Region instance = new Region(0,0,3);
        boolean expResult = true;
        boolean result = instance.rowContains(row, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of colContains method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testColContains() throws Exception {
        System.out.println("colContains");
        int value = 0;
        int col = 0;
        Region instance = new Region(0,0,3);
        boolean expResult = true;
        boolean result = instance.colContains(col, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Region.
     * @throws java.lang.Exception
     */
    @Test
    public void testToString() throws Exception {
        System.out.println("toString");
        Region instance = new Region(0,0,3);
        String expResult = "0,0,0,0,0,0,0,0,0";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
