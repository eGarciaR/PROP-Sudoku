/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.domain;

import domain.BoardSudoku;
import domain.BoardSudokuTest;
import domain.Game;
import static domain.Game.HelpLevel.*;
import domain.SudokuSolver;
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
 * @author Marc
 */
public class GameDomainCtrlTest {

    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), GameDomainCtrlTest.class.getName());
    }

    public GameDomainCtrlTest() {
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

//    /**
//     * Test of getInstance method, of class GameDomainCtrlSudoku.
//     */
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        GameDomainCtrl expResult = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        GameDomainCtrl result = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        assertEquals("Singleton getInstance", expResult, result);
//    }
//
//    /**
//     * Test of getScore method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testGetScore() {
//        System.out.println("getScore");
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startPlayGame(null, "BEGINNER", null);
//        int expResult = 0;
//        int result = instance.getScore();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTime method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testGetTime() throws InterruptedException {
//        System.out.println("getTime");
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startPlayGame(null, "BEGINNER", null);
//        long expResult = 3L;
//        Thread.sleep(3000);
//        long result = instance.getTime();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getErrors method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testGetErrors() {
//        System.out.println("getErrors");
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startPlayGame(null, "BEGINNER", null);
//        int expResult = 0;
//        int result = instance.getErrors();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getUsername method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testGetUsername() {
//        System.out.println("getUsername");
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startPlayGame(null, "BEGINNER", "pepito");
//        String expResult = "pepito";
//        String result = instance.getUsername();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of pauseGame method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testPauseGame() throws InterruptedException {
//        System.out.println("pauseGame");
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startPlayGame(null, "BEGINNER", null);
//        long result = instance.getTime();
//        instance.pauseGame();
//        Thread.sleep(3000);
//        long expResult = instance.getTime();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of setGameCellValue method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testSetGameCellValue() {
//        System.out.println("setGameCellValue");
//        int row = 0;
//        int column = 0;
//        int value = 2;
//        GameDomainCtrl instance = GameDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        BoardSudoku b = new BoardSudoku(2);
//        instance.startPlayGame(b, "BEGINNER", null);
//        instance.setCellValue(row, column, value);
//        int expValue = b.getCellValue(row, column);
//        assertEquals(expValue, value);
//    }
//
//    /**
//     * Test of setEditorCellValue method, of class GameDomainCtrl.
//     */
//    @Test
//    public void testSetEditorCellValue() {
//        System.out.println("setEditorCellValue");
//        int row = 0;
//        int column = 0;
//        int value = 1;
//        EditorDomainCtrl instance = EditorDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
//        instance.startEditor(2, null);
//        boolean expResult = true;
//        boolean result = instance.setCellValue(row, column, value);
//        assertEquals(expResult, result);
//        int resultValue = instance.getBoard().getCellValue(row, column);
//        assertEquals(value, resultValue);
//    }
    /**
     * Test of generateRandomSudoku method, of class GameDomainCtrl.
     */
    @Test
    public void testGenerateRandomSudoku() {
        System.out.println("generateRandomSudoku");
        String difficulty = "EASY";
        BoardDomainCtrl instance = BoardDomainCtrl.getInstance(DomainCtrl.GAME_NAME);
        BoardSudoku result;
        for (int n = 0; n < 4; n++) {
            System.out.println("Random Sudoku: N=" + 4 + " Difficulty=" + difficulty);
            result = instance.generateRandom(4, difficulty);
            result.printBoard();
            System.out.println("N numbers:" + result.getNNumbers());
            System.out.println(result.toString());
            assertNotNull(result);
        }

        difficulty = "MEDIUM";
        for (int n = 0; n < 4; n++) {
            System.out.println("Random Sudoku: N=" + 4 + " Difficulty=" + difficulty);
            result = instance.generateRandom(4, difficulty);
            result.printBoard();
            System.out.println("N numbers:" + result.getNNumbers());
            System.out.println(result.toString());
            assertNotNull(result);
        }

        difficulty = "HARD";
        for (int n = 0; n < 4; n++) {
            System.out.println("Random Sudoku: N=" + 4 + " Difficulty=" + difficulty.toString());
            result = instance.generateRandom(4, difficulty);
            result.printBoard();
            System.out.println("N numbers:" + result.getNNumbers());
            System.out.println(result.toString());
            assertNotNull(result);
        }
    }

}
