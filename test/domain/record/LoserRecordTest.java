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
public class LoserRecordTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), LoserRecordTest.class.getName());
    }
    
    public LoserRecordTest() {
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
     * Test of getInstance method, of class LoserRecord.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LoserRecord expResult = LoserRecord.getInstance();
        LoserRecord result = LoserRecord.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class LoserRecord.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        
        // Fracàs
        LoserRecord instance = LoserRecord.getInstance();
        boolean expResult = false;
        boolean result = instance.update(user);
        assertEquals(expResult, result);
        
        //Éxit
        user.setGamesPlayed(11); // Simulem que el jugador ha jugat 11 cops
        expResult = true;
        result = instance.update(user);
        assertEquals(expResult,result);
    }

    /**
     * Test of getValue method, of class LoserRecord.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.setGamesPlayed(10);
        LoserRecord instance = LoserRecord.getInstance();
        boolean updated = instance.update(user);
        String expResult = "10";
        String result = instance.getValue();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }

    /**
     * Test of toString method, of class LoserRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LoserRecord instance = LoserRecord.getInstance();
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.setGamesPlayed(10);
        boolean updated = instance.update(user);
        String expResult = "user=UsuariTest;value=10;";
        String result = instance.toString();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }
    
}
