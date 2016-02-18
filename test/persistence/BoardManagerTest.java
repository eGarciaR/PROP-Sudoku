/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dani
 */
public class BoardManagerTest {
    
    public BoardManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class BoardManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        String gameName = "";
        BoardManager expResult = null;
        BoardManager result = BoardManager.getInstance(gameName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTable method, of class BoardManager.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        BoardManager instance = null;
        instance.createTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class BoardManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        HashMap<String, Object> board = null;
        BoardManager instance = null;
        int expResult = 0;
        int result = instance.add(board);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class BoardManager.
     */
    @Test
    public void testGetAll_0args() {
        System.out.println("getAll");
        BoardManager instance = null;
        ArrayList<HashMap<String, Object>> expResult = null;
        ArrayList<HashMap<String, Object>> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class BoardManager.
     */
    @Test
    public void testGetAll_int_String() {
        System.out.println("getAll");
        int n = 2;
        String difficulty = "EASY";
        BoardManager instance = BoardManager.getInstance("sudoku");
        ArrayList<HashMap<String, Object>> expResult = null;
        ArrayList<HashMap<String, Object>> result = instance.getAll(n, difficulty);
        System.out.println(result.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of getById method, of class BoardManager.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        BoardManager instance = null;
        HashMap<String, Object> expResult = null;
        HashMap<String, Object> result = instance.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
