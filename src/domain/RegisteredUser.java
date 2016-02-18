/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import controllers.domain.RegisteredUserDomainCtrl;
import java.util.HashMap;

/**
 *
 * @author dani
 */
public class RegisteredUser extends User {

    /**
     * Partides jugades.
     */
    private int gamesPlayed;
    /**
     * Partides guanyades (acabades).
     */
    private int gamesWon;
    /**
     * Acomulació dels temps de partides acabades.
     */
    private long totalTime;
    /**
     * Acomulació de puntuació de partides acabades.
     */
    private int totalScore;
    /**
     * Millor temps de partida.
     */
    private long bestTime;

    /**
     * Numero de sudokus creats.
     */
    private int boardsCreated;

    public RegisteredUser(String name) {
        super(name);
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public boolean setGamesPlayed(int gamesPlayed) {
        if (filterPositive(gamesPlayed)) {
            this.gamesPlayed = gamesPlayed;
            return true;
        }
        return false;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public boolean setGamesWon(int gamesWon) {
        if (filterPositive(gamesWon)) {
            this.gamesWon = gamesWon;
            return true;
        }
        return false;
    }

    public int getGamesUnfinished() {
        return this.gamesPlayed - this.gamesWon; 
    }

    public long getTotalTime() {
        return totalTime;
    }

    public boolean setTotalTime(long totalTime) {
        if (filterPositive(totalTime)) {
            this.totalTime = totalTime;
            return true;
        }
        return false;
    }

    public long getBestTime() {
        return bestTime;
    }

    public boolean setBestTime(long bestTime) {
        if (filterPositive(bestTime)) {
            this.bestTime = bestTime;
            return true;
        }
        return false;
    }

    public long getTotalScore() {
        return this.totalScore;
    }

    public boolean setTotalScore(int totalScore) {
        if (filterPositive(totalScore)) {
            this.totalScore = totalScore;
            return true;
        }
        return false;
    }

    public int getBoardsCreated() {
        return boardsCreated;
    }

    public void setBoardsCreated(int boardsCreated) {
        this.boardsCreated = boardsCreated;
    }
    
    /**
     * Incrementa en 1 els taulers creats.
     */
    public void incBoardsCreated() {
        ++this.boardsCreated;
    }
    
    /**
     * Incrementa en 1 sudokus jugats.
     */
    public void incGamesPlayed() {
        ++this.gamesPlayed;
    }
    
    /**
     * Calcula el temps mitjà en funció del temps total i les partides guanyades.
     * @return Temps mitjà
     */
    public long getAverageTime() {
        if (this.gamesWon == 0) {
            return 0;
        }
        long average = this.totalTime / this.gamesWon;
        return average;
    }
    /**
     * Calcula la puntuació mitjana en funció de la puntuació total i les partides guanyades.
     * @return Temps mitjà
     */
    public int getAverageScore() {
        if (this.gamesWon == 0) {
            return 0;
        }
        int average = (int) (this.totalScore / this.gamesWon);
        return average;
    }
    
    /**
     * Actualitza les estadistiques del jugador quan es guanya una partida.
     * @param score Puntuació
     * @param time Temps
     */
    public void winGame(int score, long time) {
        ++this.gamesWon;
        this.totalScore += score;
        this.totalTime += time;
        if (time != 0 && (time < this.bestTime || this.bestTime == 0)) {
            this.bestTime = time;
        }
    }
    
    /**
     * Empaqueta els atributs de l'objecte amb Map[atribut]=valor.
     * @return Atributs de l'objecte.
     */
    public HashMap<String, Object> getAttrs() {
        HashMap<String, Object> attrs = new HashMap<>();
        attrs.put(RegisteredUserDomainCtrl.ATTR_BEST_TIME, bestTime);
        attrs.put(RegisteredUserDomainCtrl.ATTR_BOARDS_CREATED, boardsCreated);
        attrs.put(RegisteredUserDomainCtrl.ATTR_GAMES_PLAYED, gamesPlayed);
        attrs.put(RegisteredUserDomainCtrl.ATTR_GAMES_WON, gamesWon);
        attrs.put(RegisteredUserDomainCtrl.ATTR_TOTAL_SCORE, totalScore);
        attrs.put(RegisteredUserDomainCtrl.ATTR_TOTAL_TIME, totalTime);
        return attrs;
    }
    
    /**
     * Actualitza el valor dels atributs amb el parametre d'entrada.
     * @param userFromHashMap HashMap del atributs del usuari
     */
    public void load(HashMap<String, Object> userFromHashMap) {
        setBestTime((long) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_BEST_TIME));
        setBoardsCreated((int) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_BOARDS_CREATED));
        setGamesPlayed((int) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_GAMES_PLAYED));
        setGamesWon((int) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_GAMES_WON));
        setTotalScore((int) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_TOTAL_SCORE));
        setTotalTime((long) userFromHashMap.get(RegisteredUserDomainCtrl.ATTR_TOTAL_TIME));
    }

    private boolean filterPositive(long x) {
        return x >= 0;
    }

}
