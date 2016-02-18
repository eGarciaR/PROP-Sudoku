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
public class ScorerRecordTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), ScorerRecordTest.class.getName());
    }
    
    public ScorerRecordTest() {
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
     * Test of getInstance method, of class ScorerRecord.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ScorerRecord expResult = ScorerRecord.getInstance();
        ScorerRecord result = ScorerRecord.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class ScorerRecord.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        
        // Fracàs
        ScorerRecord instance = ScorerRecord.getInstance();
        boolean expResult = false;
        boolean result = instance.update(user);
        assertEquals(expResult, result);
        
        // Éxit
        user.winGame(4000, 30); // Simulem que el jugador ha guanyat un joc
        expResult = true;
        result = instance.update(user);
        assertEquals(expResult,result);
    }

    /**
     * Test of getValue method, of class ScorerRecord.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        ScorerRecord instance = ScorerRecord.getInstance();
        boolean updated = instance.update(user);
        String expResult = "3000";
        String result = instance.getValue();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }

    /**
     * Test of toString method, of class ScorerRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ScorerRecord instance = ScorerRecord.getInstance();
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        boolean updated = instance.update(user);
        String expResult = "user=UsuariTest;value=3000;";
        String result = instance.toString();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }
    
}
