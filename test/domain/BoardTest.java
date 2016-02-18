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


public class BoardTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), BoardTest.class.getName());
    }
    
    public BoardTest() {
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
     * Test of getId method, of class Board.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Board instance1 = new Board();
        Board instance2 = new Board(1, 1);
        int expResult1 = 0;
        int expResult2 = 1;
        int result1 = instance1.getId();
        int result2 = instance2.getId();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setId method, of class Board.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        Board instance = new Board();
        instance.setId(id);
    }

    /**
     * Test of getN method, of class Board.
     */
    @Test
    public void testGetN() {
        System.out.println("getN");
        Board instance = new Board();
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
    }

    /**
     * Test of setN method, of class Board.
     */
    @Test
    public void testSetN() {
        System.out.println("setN");
        int n = 0;
        Board instance = new Board();
        instance.setN(n);
    }
    
}
