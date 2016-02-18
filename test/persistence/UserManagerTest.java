/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dani
 */
public class UserManagerTest {
    
    public UserManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class UserManagerDani.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        String gameName = "sudoku";
        UserManager expResult = UserManager.getInstance(gameName);
        UserManager result = UserManager.getInstance(gameName);
        assertEquals(expResult, result);
        if (!result.existsUsername("Dani")) {
            assertTrue(result.addUser("Dani", "1234"));
        }
        if (!result.existsUsername("Eric")) {
            assertTrue(result.addUser("Eric", "4321"));
        }
        assertFalse(result.existsUsername("Ferran"));
    }
    
}
