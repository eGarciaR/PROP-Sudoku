/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.record;

import domain.BoardSudokuTest;
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
public class TimerRecordTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), TimerRecordTest.class.getName());
    }
    
    public TimerRecordTest() {
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
     * Test of getInstance method, of class TimerRecord.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        TimerRecord expResult = TimerRecord.getInstance();
        TimerRecord result = TimerRecord.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class TimerRecord.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        
        // Fracàs
        TimerRecord instance = TimerRecord.getInstance();
        boolean expResult = false;
        boolean result = instance.update(user);
        assertEquals(expResult, result);
        
        // Éxit
        user.winGame(3000, 50); // Simulem que el jugador ha guanyat un joc
        expResult = true;
        result = instance.update(user);
        assertEquals(expResult,result);
    }

    /**
     * Test of getValue method, of class TimerRecord.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        TimerRecord instance = TimerRecord.getInstance();
        boolean updated = instance.update(user);
        String expResult = "00 min, 30 sec";
        String result = instance.getValue();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }

    /**
     * Test of toString method, of class TimerRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TimerRecord instance = TimerRecord.getInstance();
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        boolean updated = instance.update(user);
        String expResult = "user=UsuariTest;value=30;";
        String result = instance.toString();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }
    }
    
}
