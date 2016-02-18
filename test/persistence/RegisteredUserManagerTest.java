/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controllers.domain.DomainCtrl;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daniel.criado.casas
 */
public class RegisteredUserManagerTest {

    public RegisteredUserManagerTest() {
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
     * Test of getInstance method, of class RegisteredUserManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        String gameName = DomainCtrl.GAME_NAME;
        RegisteredUserManager expResult = RegisteredUserManager.getInstance(gameName);
        RegisteredUserManager result = RegisteredUserManager.getInstance(gameName);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTable method, of class RegisteredUserManager.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        RegisteredUserManager instance = null;
        instance.createTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class RegisteredUserManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String user = "PEPITO";
        RegisteredUserManager instance = RegisteredUserManager.getInstance("sudoku");
        instance.add(user);
    }

    /**
     * Test of update method, of class RegisteredUserManager.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String user = "";
        HashMap<String, Object> attrs = null;
        RegisteredUserManager instance = null;
        instance.update(user, attrs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class RegisteredUserManager.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String user = "";
        RegisteredUserManager instance = null;
        HashMap<String, Object> expResult = null;
        HashMap<String, Object> result = instance.get(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
