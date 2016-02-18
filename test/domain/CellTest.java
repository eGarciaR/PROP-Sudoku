/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 * Test: passed
 */
package domain;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.internal.RealSystem;
import org.junit.runner.JUnitCore;


public class CellTest{
    
    public static void main(String[] args) {      
        JUnitCore.runMainAndExit(new RealSystem(), CellTest.class.getName());
    }
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getValue method, of class Cell.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Cell instance = new Cell();
        int expResult = 0;
        int result = instance.getValue();
        assertEquals(expResult, result); //El missatge de prova no surt.
    }

    /**
     * Test of setValue method, of class Cell.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        
        Cell instance = new Cell();
        int value;
        boolean expResult;
        boolean result;
//        Scanner scanIn = new Scanner(System.in);
//        String s = scanIn.nextLine();
//        scanIn.close();
        //Escenari de fracas
        value = -1;
        expResult = false;
        result = instance.setValue(value);
        assertEquals(expResult, result);
        
        //Escenari d'éxit
        value = 1;
        expResult = true;//Hauria de ser true
        result = instance.setValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Cell.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cell instance = new Cell(1);
        String expResult = "1";
        String result = instance.toString();
        assertEquals(expResult, result);
//        System.out.println(expResult);
    }
    
}
