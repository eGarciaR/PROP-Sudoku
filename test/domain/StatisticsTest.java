/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import domain.record.Record;
import java.util.ArrayList;
import java.util.HashSet;
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
public class StatisticsTest {

    public static void main(String[] args) {
        JUnitCore.runMainAndExit(new RealSystem(), StatisticsTest.class.getName());
    }

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class Stadistics.
     */
    @Test
    public void testGetInstance() {
        System.out.println("TEST: getInstance");
        Statistics expResult = Statistics.getInstance();
        Statistics result = Statistics.getInstance();
        assertEquals(expResult, result);
        System.out.println("FINISH TEST: getInstance");
    }

    /**
     * Test of updateRanking method, of class Stadistics.
     */
    @Test
    public void testUpdateRanking() {
        System.out.println("TEST: updateRanking");
        Statistics instance = Statistics.getInstance();
        messageRanking(instance.updateRanking("Dani", 2));
        messageRanking(instance.updateRanking("Dani", 4));
        messageRanking(instance.updateRanking("Albert", 2));//Puntuacio repetida
        messageRanking(instance.updateRanking("Dani", 17));
        messageRanking(instance.updateRanking("Dani", 0));//Puntuacio més baixa que s'elimina.
        messageRanking(instance.updateRanking("Pepe", 8));
        instance.printRanking();

        messageRanking(instance.updateRanking("Albert", 100));//Puntuacio més alta.
        messageRanking(instance.updateRanking("Juan", 16));
        messageRanking(instance.updateRanking("Dani", 31));
        messageRanking(instance.updateRanking("Juan", 15));
        messageRanking(instance.updateRanking("Dani", 18));
        instance.printRanking();

        messageRanking(instance.updateRanking("Pepe", 5));
        messageRanking(instance.updateRanking("Juan", 3));
        messageRanking(instance.updateRanking("Dani", 53));
        messageRanking(instance.updateRanking("Dani", 88));//15
        messageRanking(instance.updateRanking("Dani", 5));//Fa eliminar la puntuació més baixa.
        instance.printRanking(); //RANKING PLE
        messageRanking(instance.updateRanking("Dani", 2));//No entra en el Ranking
        System.out.println("FINISH TEST: updateRanking");
    }

    private void messageRanking(int pos) {
        if (pos < 0) {
            System.out.println("Mala sort, no has entrat al Ranking.");
        } else {
            System.out.println("Enhobona, estas en la posició " + (pos) + " del Ranking");
        }
    }

    /**
     * Test of updateRecords method, of class Stadistics.
     */
    @Test
    public void testUpdateRecords() {
        System.out.println("TEST: updateRecords");
        Statistics stadistics = Statistics.getInstance();
        HashSet<Record> recordsActualitzats;
        stadistics.printRecords();

        RegisteredUser user1 = new RegisteredUser("Dani");
        user1.setBestTime(1234);
        user1.setBoardsCreated(2);
        user1.setGamesPlayed(25);
        user1.setGamesWon(22);
        user1.setTotalScore(123456);
        user1.setTotalTime(123456789);

        recordsActualitzats = stadistics.updateRecords(user1);
        printRecordsActualitzats(user1, recordsActualitzats);

        stadistics.printRecords();

        RegisteredUser user2 = new RegisteredUser("Ferran");
        user2.setBestTime(1235);
        user2.setBoardsCreated(1);
        user2.setGamesPlayed(24);
        user2.setGamesWon(23);
        user2.setTotalScore(123455);
        user2.setTotalTime(123456789);
        recordsActualitzats = stadistics.updateRecords(user2);
        printRecordsActualitzats(user2, recordsActualitzats);

        stadistics.printRecords();

        RegisteredUser user3 = new RegisteredUser("Eric");
        user3.setBestTime(0);
        user3.setBoardsCreated(0);
        user3.setGamesPlayed(0);
        user3.setGamesWon(0);
        user3.setTotalScore(0);
        user3.setTotalTime(0);
        recordsActualitzats = stadistics.updateRecords(user3);
        printRecordsActualitzats(user3, recordsActualitzats);
        
        stadistics.printRecords();

        System.out.println("FINISH TEST: updateRecords");
    }
    
    private void printRecordsActualitzats(RegisteredUser user, HashSet<Record> recordsActualitzats) {
        System.out.println(("S'han actualitzat els següents records per " + user.getName()+ ":").toUpperCase());
        if (recordsActualitzats.isEmpty()) {
            System.out.println("No has aconseguit superar cap record");
        }
        for (Record recordActualitzat : recordsActualitzats) {
            recordActualitzat.print();
        }
    }

}
