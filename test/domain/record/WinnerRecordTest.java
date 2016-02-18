/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.record;

import domain.RegisteredUser;
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
public class WinnerRecordTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), WinnerRecordTest.class.getName());
    }
    
    public WinnerRecordTest() {
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
     * Test of getInstance method, of class WinnerRecord.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        WinnerRecord expResult = WinnerRecord.getInstance();
        WinnerRecord result = WinnerRecord.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class WinnerRecord.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        
        // Fracàs
        WinnerRecord instance = WinnerRecord.getInstance();
        boolean expResult = false;
        boolean result = instance.update(user);
        assertEquals(expResult, result);
        
        // Éxit
        user.winGame(3000, 40); // Simulem que el jugador ha superat el record
        user.winGame(3000, 40);
        user.winGame(3000, 40);
        user.winGame(3000, 40);
        expResult = true;
        result = instance.update(user);
        assertEquals(expResult,result);
    }

    /**
     * Test of getValue method, of class WinnerRecord.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        WinnerRecord instance = WinnerRecord.getInstance();
        boolean updated = instance.update(user);
        String expResult = "1";
        String result = instance.getValue();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }

    /**
     * Test of toString method, of class WinnerRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        WinnerRecord instance = WinnerRecord.getInstance();
        boolean updated = instance.update(user);
        String expResult = "user=UsuariTest;value=1;";
        String result = instance.toString();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }
    
}
