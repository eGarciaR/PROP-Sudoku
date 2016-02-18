/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Game.HelpLevel;
import static domain.Game.HelpLevel.*;
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
public class GameTest {
    
    private static Game game = null;
    
    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), GameTest.class.getName());
    }
    
    public GameTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        BoardSudoku board = new BoardSudoku(3);
        board.setDifficulty(BoardSudoku.Difficulty.HARD);
        String helpLevel = "INTERMEDIATE";
        String user = "Eric";
        game = new Game(board,helpLevel,user);
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
     * Test of startGame method, of class PlayGame.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        game.startGame();
        float timeInSeconds = game.getTime();
        System.out.println(timeInSeconds);
    }

    /**
     * Test of pauseGame method, of class PlayGame.
     */
    @Test
    public void testPauseGame() throws InterruptedException {
        System.out.println("pauseGame");
        long timeInSecondsBeforePause = game.getTime();
        game.pauseGame();
        System.out.print("Simulem una pausa del joc...");
        Thread.sleep(4000);
        long timeInSecondsAfterPause = game.getTime();
        assertEquals(timeInSecondsBeforePause,timeInSecondsAfterPause);
    }

    /**
     * Test of resumeGame method, of class PlayGame.
     */
    @Test
    public void testResumeGame() throws InterruptedException {
        System.out.println("resumeGame");
        long timeInSecondsBeforeResume = game.getTime();
        game.resumeGame();
        System.out.print("Simulem que després de un temps el joc continua...");
        Thread.sleep(5000);
        long timeInSecondsAfterResume = game.getTime();
        assertNotSame(timeInSecondsBeforeResume, timeInSecondsAfterResume);
    }

    /**
     * Test of finishGame method, of class PlayGame.
     */
    @Test
    public void testFinishGame() {
        System.out.println("finishGame");
        game.finishGame();
        long time = game.getTime();
        long hardLevelScore = 10000;
        long helpLevelScore = 500;
        long expResult = (hardLevelScore-helpLevelScore)-time;
        long result = game.getScore();
        if (expResult < 0) expResult = 0;
        assertEquals(expResult,result);
    }

    /**
     * Test of getScore method, of class PlayGame.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        long result, expResult;
        if (game.getScore() > 0) { // Si el joc ha acabat, ja haurem calculat el score
            long time = game.getTime();
            expResult = (10000-500)-time;
            result = game.getScore();
        }
        else {              // Sino, l'score ha de ser 0
            expResult = 0;
            result = game.getScore();
        }
        if (expResult < 0) expResult = 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of setScore method, of class PlayGame.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        int previousScore = game.getScore(); // Guardem l'score que teniem per tornar-lo a deixar com estava
        int expScore = 4000;
        boolean expResult = true;
        boolean result = game.setScore(expScore);
        int resultScore = game.getScore();
        assertEquals(expResult, result);
        assertEquals(expScore, resultScore);
        game.setScore(previousScore); // Deixem el score que tenia previament
    }

    /**
     * Test of getTime method, of class PlayGame.
     */
    @Test
    public void testGetTime() throws InterruptedException {
        System.out.println("getTime");
        game.resumeGame(); // Ens assegurem que el temps està corrent
        long actualTime = game.getTime();
        long expResult = 1;
        Thread.sleep(1000); // Esperem un segon
        long result = game.getTime();
        assertEquals(expResult, (result-actualTime));
    }

    /**
     * Test of setTime method, of class PlayGame.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        long previousTime = game.getTime();
        long expTime = 30L;
        boolean expResult = true;
        boolean result = game.setTime(expTime);
        long resultTime = game.getTime();
        assertEquals(expResult, result);
        assertEquals(expTime,resultTime);
        
        game.setTime(previousTime);
    }

    /**
     * Test of getNErrors method, of class PlayGame.
     */
    @Test
    public void testGetNErrors() {
        System.out.println("getNErrors");
        int expResult = 0;
        int result = game.getNErrors();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNErrors method, of class PlayGame.
     */
    @Test
    public void testSetNErrors() {
        System.out.println("setNErrors");
        int errors = 5;
        boolean expResult = true;
        boolean result = game.setNErrors(errors);
        int resultSet = game.getNErrors();
        assertEquals(expResult, result);
        assertEquals(errors, resultSet);
        game.setNErrors(0); // Tornem a posar els errors inicials per seguir calculant bé l'score
    }

    /**
     * Test of incrementNErrors method, of class PlayGame.
     */
    @Test
    public void testIncrementNErrors() {
        System.out.println("incrementNErrors");
        int expResult = game.getNErrors()+1;
        game.incrementNErrors();
        assertEquals(expResult,game.getNErrors());
        game.setNErrors(0); // Tornem a posar els errors inicials per seguir calculant bé l'score
    }

    /**
     * Test of getHelpLevel method, of class PlayGame.
     */
    @Test
    public void testGetHelpLevel() {
        System.out.println("getHelpLevel");
        Game.HelpLevel expResult = INTERMEDIATE;
        Game.HelpLevel result = game.getHelpLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHelpLevel method, of class PlayGame.
     */
    @Test
    public void testSetHelpLevel() {
        System.out.println("setHelpLevel");
        Game.HelpLevel helpLevel = ADVANCED;
        boolean expResult = true;
        boolean result = game.setHelpLevel(helpLevel);
        assertEquals(expResult, result);
        Game.HelpLevel expHelpLevel = ADVANCED;
        Game.HelpLevel resultHelpLevel = game.getHelpLevel();
        assertEquals(expHelpLevel, resultHelpLevel);
        game.setHelpLevel(INTERMEDIATE); // Tornem a posar el valor inicial de helpLevel
    }

    /**
     * Test of updateTime method, of class PlayGame.
     */
    @Test
    public void testUpdateTime() throws InterruptedException {
        System.out.println("updateTime");
        
        long previousTime = game.getTime();
        long time = game.getTime();
        Thread.sleep(1000);
        game.updateTime();
        long timePassed = game.getTime();
        assertEquals(time+1L, timePassed);
        
        game.setTime(previousTime); // Carreguem el temps previ per seguir amb el test
    }

    /**
     * Test of calculateFinalScore method, of class PlayGame.
     */
    @Test
    public void testCalculateFinalScore() {
        System.out.println("calculateFinalScore");
        int previousScore = game.getScore();
        game.setTime(5); // Ens assegurem que és un temps vàlid
        long time = game.getTime();
        System.out.println(time);
        int expResult = (10000-500)-(int)time;
        game.calculateFinalScore();
        int result = game.getScore();
        assertEquals(expResult,result);
        game.setScore(previousScore); // Tornem a posar el score previ
    }

    

 

    /**
     * Test of stopGameAndReturnBoard method, of class PlayGame.
     */
    @Test
    public void testStopGameAndReturnBoard() throws InterruptedException {
        System.out.println("stopGameAndReturnBoard");
        String expResult = "0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|"
                         + "0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|"
                         + "0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0";
        String result = game.stopGameAndReturnBoard();
        System.out.println(result);
        long timeBeforeSave = game.getTime();
        Thread.sleep(1000);
        long timeAfterSave = game.getTime();
        assertEquals(timeBeforeSave, timeAfterSave);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadGame method, of class PlayGame.
     */
    @Test
    public void testLoadGame() {
        System.out.println("loadGame");
        
        // Guardem valors previs per a carregar-los al final i seguir executant el test correctament
        BoardSudoku previousBoard = game.getBoard();
        long previousTime = game.getTime();
        
        BoardSudoku board = new BoardSudoku(3);
        board.fromString("0,2,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|"
                       + "0,0,0,0,0,0,0,0,0|0,0,0,0,2,0,0,0,0|0,0,0,0,0,0,0,0,0|"
                       + "0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,0|0,0,0,0,0,0,0,0,2");
        String user = "EricTest";
        long time = 20L;
        int errors = 3;
        Game.HelpLevel helpLevel = ADVANCED;
        game.loadGame(board, user, time, errors, helpLevel);
        
        BoardSudoku boardResult = game.getBoard();
        String userResult = game.getUsername();
        long timeResult = game.getTime();
        int errorsResult = game.getNErrors();
        Game.HelpLevel helpLevelResult = game.getHelpLevel();
        assertEquals(board, boardResult);
        assertEquals(user, userResult);
        assertEquals(time, timeResult);
        assertEquals(errors, errorsResult);
        assertEquals(helpLevel, helpLevelResult);
        
        // Carregar informació previa per a que el test segueixi funcionant amb els valors anteriors
        game.setBoard(previousBoard);
        game.setTime(previousTime);
        game.setUser("Eric");
        game.setNErrors(0);
        game.setHelpLevel(INTERMEDIATE);
        
    } 
    
    @Test
    public void testsolverHelper() throws Exception {
        
        // Guardem valors previs per a carregar-los al final i seguir executant el test correctament
        BoardSudoku previousBoard = game.getBoard();
        long previousTime = game.getTime();
        
        System.out.println("solverHelper");

        String message;
        BoardSudoku Errors = new BoardSudoku(2);
        Errors.fromString("0,0,0,0|0,1,0,0|0,0,0,4|0,0,3,0");
        game.setBoard(Errors);
        
        System.out.println("Help Level = Beginner");
        game.setHelpLevel(BEGINNER);
        System.out.println(game.solverHelper(0,0,1));
        System.out.println(game.solverHelper(1,2,3));
        System.out.println(game.solverHelper(2,0,4));

        System.out.println("----------");

        System.out.println("Help Level = Intermediate");
        game.setHelpLevel(INTERMEDIATE);
        System.out.println(game.solverHelper(0,0,1));
        System.out.println(game.solverHelper(1,2,3));
        System.out.println(game.solverHelper(2,0,4));

        System.out.println("----------");

        System.out.println("Help Level = Advanced");
        game.setHelpLevel(ADVANCED);
        System.out.println(game.solverHelper(0,0,1));
        System.out.println(game.solverHelper(1,2,3));
        System.out.println(game.solverHelper(2,0,4));
        System.out.println("----------");
        
        
        // Carregar informació previa per a que el test segueixi funcionant amb els valors anteriors
        game.setBoard(previousBoard);
        game.setTime(previousTime);
        game.setUser("Eric");
        game.setNErrors(0);
        game.setHelpLevel(INTERMEDIATE);
    }
}
