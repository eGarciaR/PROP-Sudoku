/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.domain;

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
public class UserDomainCtrlSudokuTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), UserDomainCtrlSudokuTest.class.getName());
    }
    
    public UserDomainCtrlSudokuTest() {
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
     * Test of getInstance method, of class UserDomainCtrlSudoku.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        RegisteredUserDomainCtrl expResult = RegisteredUserDomainCtrl.getInstance();
        RegisteredUserDomainCtrl result = RegisteredUserDomainCtrl.getInstance();
        assertEquals(expResult, result);
    }
    
}
