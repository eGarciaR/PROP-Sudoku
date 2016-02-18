/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.domain;

import domain.BoardSudokuTest;
import domain.Statistics;
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
public class DomainCtrlTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), DomainCtrlTest.class.getName());
    }
    
    GameDomainCtrl gameCtrl;
    
    public DomainCtrlTest() {
        
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
     * Test of getInstance method, of class DomainCtrlSudoku.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DomainCtrl expResult = DomainCtrl.getInstance();
        DomainCtrl result = DomainCtrl.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGameDomainCtrl method, of class DomainCtrlSudoku.
     */
    
    @Test
    public void testGetGameDomainCtrl() {
        System.out.println("getGameDomainCtrl");
        DomainCtrl instance = DomainCtrl.getInstance();
        GameDomainCtrl expResult = GameDomainCtrl.getInstance();
        GameDomainCtrl result = instance.getGameDomainCtrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of startGame method, of class DomainCtrlSudoku.
     */
    
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        int boardId = 0;
        String helpLevel = "";
        String user = "";
        DomainCtrl instance = DomainCtrl.getInstance();
        instance.startGame(boardId, helpLevel, user);
    }

    /**
     * Test of resumeGame method, of class DomainCtrlSudoku.
     */
    
    @Test
    public void testResumeGame() throws InterruptedException {
        System.out.println("resumeGame");
        DomainCtrl instance = DomainCtrl.getInstance();
        gameCtrl = instance.getGameDomainCtrl();
        long timeBeforeResume = gameCtrl.getTime();
        instance.resumeGame();
        Thread.sleep(1000);
        long timeAfterResume = gameCtrl.getTime();
        assertNotSame(timeBeforeResume, timeAfterResume);
    }

    /**
     * Test of pauseGame method, of class DomainCtrlSudoku.
     */
    
    @Test
    public void testPauseGame() throws InterruptedException {
        System.out.println("pauseGame");
        DomainCtrl instance = DomainCtrl.getInstance();
        gameCtrl = instance.getGameDomainCtrl();
        long timeBeforePause = gameCtrl.getTime();
        instance.pauseGame();
        Thread.sleep(1000);
        long timeAfterPause = gameCtrl.getTime();
        assertEquals(timeBeforePause, timeAfterPause);
    }
}
