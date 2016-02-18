/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.RealSystem;
import org.junit.runner.JUnitCore;

/**
 *
 * @author dani
 */
public class RegisteredUserTest {
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), RegisteredUserTest.class.getName());
    }
    
    public RegisteredUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * Test of setGamesPlayed method, of class RegisteredUser.
     */
    @Test
    public void testSetGamesPlayed() {
        System.out.println("TEST: setGamesPlayed");
        int expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 5;
        assertTrue(instance.setGamesPlayed(expResult));
        assertEquals(expResult, instance.getGamesPlayed());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setGamesPlayed(expResult));
        assertNotSame(expResult, instance.getGamesPlayed());
        System.out.println("FINISH TEST: setGamesPlayed");
    }

    /**
     * Test of setGamesWon method, of class RegisteredUser.
     */
    @Test
    public void testSetGamesWon() {
        System.out.println("TEST: setGamesWon");
        int expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 5;
        assertTrue(instance.setGamesWon(expResult));
        assertEquals(expResult, instance.getGamesWon());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setGamesWon(expResult));
        assertNotSame(expResult, instance.getGamesWon());
        System.out.println("FINISH TEST: setGamesWon");
    }

    /**
     * Test of getGamesUnfinished method, of class RegisteredUser.
     */
    @Test
    public void testGetGamesUnfinished() {
        System.out.println("TEST: getGamesUnfinished");
        int expResult, gamesPlayed, gamesWon;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        gamesPlayed = 7;
        assertTrue(instance.setGamesPlayed(gamesPlayed));
        gamesWon = 6;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 1;
        assertEquals(expResult, instance.getGamesUnfinished());
        
        //Escenari de fracás
        gamesPlayed = 6;
        assertTrue(instance.setGamesPlayed(gamesPlayed));
        gamesWon = 7;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 0;
        assertNotSame(expResult, instance.getGamesUnfinished());
        System.out.println("FINISH TEST: getGamesUnfinished");
    }

    /**
     * Test of setTotalTime method, of class RegisteredUser.
     */
    @Test
    public void testSetTotalTime() {
        System.out.println("TEST: setTotalTime");
        long expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 12345;
        assertTrue(instance.setTotalTime(expResult));
        assertEquals(expResult, instance.getTotalTime());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setTotalTime(expResult));
        assertNotSame(expResult, instance.getTotalTime());
        System.out.println("FINISH TEST: setTotalTime");
    }

    /**
     * Test of setBestTime method, of class RegisteredUser.
     */
    @Test
    public void testSetBestTime() {
        System.out.println("TEST: setBestTime");
        long expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 12345;
        assertTrue(instance.setBestTime(expResult));
        assertEquals(expResult, instance.getBestTime());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setBestTime(expResult));
        assertNotSame(expResult, instance.getBestTime());
        System.out.println("FINISH TEST: setBestTime");
    }

    /**
     * Test of setTotalScore method, of class RegisteredUser.
     */
    @Test
    public void testSetTotalScore() {
        System.out.println("TEST: setTotalScore");
        long expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 50000;
        assertTrue(instance.setTotalScore(expResult));
        assertEquals(expResult, instance.getTotalScore());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setTotalScore(expResult));
        assertNotSame(expResult, instance.getTotalScore());
        System.out.println("FINISH TEST: setTotalScore");
    }

    /**
     * Test of setBoardsCreated method, of class RegisteredUser.
     */
    @Test
    public void testSetBoardsCreated() {
        System.out.println("TEST: setBoardsCreated");
        int expResult;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        expResult = 2;
        assertTrue(instance.setGamesWon(expResult));
        assertEquals(expResult, instance.getGamesWon());
        
        //Escenari de fracás
        expResult = -1;
        assertFalse(instance.setGamesWon(expResult));
        assertNotSame(expResult, instance.getGamesWon());
        System.out.println("FINISH TEST: setBoardsCreated");
    }

    /**
     * Test of getAverageTime method, of class RegisteredUser.
     */
    @Test
    public void testGetAverageTime() {
        System.out.println("TEST: getAverageTime");
        int gamesWon;
        long expResult, totalTime;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        totalTime = 3000;
        assertTrue(instance.setTotalTime(totalTime));
        gamesWon = 3;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 1000;
        assertEquals(expResult, instance.getAverageTime());
        
        //Escenari de fracás
        totalTime = 0;
        assertTrue(instance.setTotalTime(totalTime));
        gamesWon = 0;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 0;
        assertEquals(expResult, instance.getAverageTime());
        System.out.println("FINISH TEST: getAverageTime");
    }

    /**
     * Test of getAverageScore method, of class RegisteredUser.
     */
    @Test
    public void testGetAverageScore() {
        System.out.println("TEST: getAverageScore");
        int gamesWon;
        long expResult, totalScore;
        RegisteredUser instance = new RegisteredUser("Player");
        
        //Escenari d'èxit
        totalScore = 3000;
        assertTrue(instance.setTotalScore(totalScore));
        gamesWon = 3;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 1000;
        assertEquals(expResult, instance.getAverageScore());
        
        //Escenari de fracás
        totalScore = 0;
        assertTrue(instance.setTotalTime(totalScore));
        gamesWon = 0;
        assertTrue(instance.setGamesWon(gamesWon));
        expResult = 0;
        assertEquals(expResult, instance.getAverageScore());
        System.out.println("FINISH TEST: getAverageScore");
    }

    /**
     * Test of updateGameWon method, of class RegisteredUser.
     */
    @Test
    public void testWinGame() {
        System.out.println("TEST: winGame");
        int score1, score2;
        long time1, time2;
        RegisteredUser instance = new RegisteredUser("Player");
        
        instance.setGamesPlayed(instance.getGamesPlayed()+1);
        score1 = 10000;
        time1 = 12345;
        instance.winGame(score1, time1);
        assertEquals(instance.getGamesPlayed(), instance.getGamesWon());
        assertEquals(instance.getTotalScore(), score1);
        assertEquals(instance.getTotalTime(), time1);
        assertEquals(instance.getBestTime(), time1);
        
        
        instance.setGamesPlayed(instance.getGamesPlayed()+1);
        score2 = 15000;
        time2 = 1234;
        instance.winGame(score2, time2);
        assertEquals(instance.getGamesPlayed(), instance.getGamesWon());
        assertEquals(instance.getTotalScore(), score1+score2);
        assertEquals(instance.getTotalTime(), time1+time2);
        assertEquals(instance.getBestTime(), time2);
        
        System.out.println("FINISH TEST: winGame");
    }
  
}
