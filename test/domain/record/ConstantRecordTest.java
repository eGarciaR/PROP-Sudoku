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
 * @author eric
 */
public class ConstantRecordTest {

    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), ConstantRecordTest.class.getName());
    }

    public ConstantRecordTest() {
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
     * Test of getInstance method, of class ConstantRecord.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ConstantRecord expResult = ConstantRecord.getInstance();
        ConstantRecord result = ConstantRecord.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class ConstantRecord.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RegisteredUser user = new RegisteredUser("UsuariTest");

        // Fracàs
        ConstantRecord instance = ConstantRecord.getInstance();
        boolean expResult = false;
        boolean result = instance.update(user);
        assertEquals(expResult, result);

        // Éxit
        user.winGame(3000, 20); // Simulem que el jugador ha superat el record.
        expResult = true;
        result = instance.update(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class ConstantRecord.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        ConstantRecord instance = ConstantRecord.getInstance();
        String expResult = "00 min, 30 sec";
        boolean updated = instance.update(user);
        String result = instance.getValue();
        if (updated) {//SI LO ACTUALIZA TENDRA VALUE, 
            assertEquals(expResult, result);
        } else {//SINO TAMBIEN LO TENDRA el que tenia
            assertNotNull(result);
        }

    }

    /**
     * Test of toString method, of class ConstantRecord.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ConstantRecord instance = ConstantRecord.getInstance();
        RegisteredUser user = new RegisteredUser("UsuariTest");
        user.winGame(3000, 30);
        String expResult = "user=UsuariTest;value=30;";
        boolean updated = instance.update(user);
        String result = instance.toString();
        if (updated) {
            assertEquals(expResult, result);
        } else {
            assertNotNull(result);
        }

    }

}
