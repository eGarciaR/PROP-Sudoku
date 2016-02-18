/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package domain;

import domain.BoardSudoku.Difficulty;

/**
 *
 * @author eric
 */
public class Game extends Sudoku {

    /**
     * puntuació de la partida
     */
    private int score;

    /**
     * errors de la partida
     */
    private int errors;

    /**
     * temps de la partida
     */
    private long time;

    /**
     * temps auxiliar
     */
    private long auxTime1;

    /**
     * temps auxiliar
     */
    private long auxTime2;

    /**
     * Indica si el joc està pausat
     */
    private boolean gamePaused;

    /**
     * Nivell d'ajuda
     */
    private HelpLevel helpLevel;

    /**
     * Enumeració del nivell d'ajuda
     */
    public enum HelpLevel {

        BEGINNER("BEGINNER"),
        INTERMEDIATE("INTERMEDIATE"),
        ADVANCED("ADVANCED");

        private final String name;

        private HelpLevel(String s) {
            this.name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : this.name.equals(otherName);
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * Constructora amb tauler, nivell d'ajuda i usuari
     *
     * @pre board és un tauler vàlid, helpLevel és un nivell d'ajuda vàlid
     * @post crea una partida amb tauler board, nivell d'ajuda helpLevel i
     * usuari user
     * @param board Tauler
     * @param helpLevel Nivell d'Ajuda
     * @param user Usuari
     */
    public Game(BoardSudoku board, String helpLevel, String user) {
        setBoard(board);
        setUser(user);
        this.helpLevel = HelpLevel.valueOf(helpLevel);
        score = 0;
        errors = 0;
        time = 0;
        auxTime1 = System.currentTimeMillis();
    }

    /**
     * Comença una partida
     *
     * @pre none
     * @post comença una partida i comença a comptar el temps
     */
    public void startGame() {
        time = 0L;
        auxTime1 = System.currentTimeMillis();       // Començem a comptar
        gamePaused = false;
    }

    /**
     * Pausa una partida
     *
     * @pre none
     * @post la partida queda pausada
     */
    public void pauseGame() {
        updateTime();
        gamePaused = true;
    }

    /**
     * Continuar partida
     *
     * @pre none
     * @post la partida continua
     */
    public void resumeGame() {
        updateTime();
        gamePaused = false;
    }

    /**
     * Finalitza una partida
     *
     * @pre none
     * @post finalitza la partida i calcula la puntuació final
     */
    public void finishGame(int n) {
        updateTime();
        gamePaused = true;
        calculateFinalScore(n);
    }

    /**
     * Getter de la puntuació de la partida
     *
     * @pre none
     * @post none
     * @return retorna la puntuació de la partida
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter de la puntuació de la partida
     *
     * @pre none
     * @post puntuació de partida = score
     * @param score puntuació
     * @return true si la operació s'ha realitzad amb éxit, false altrament
     */
    public boolean setScore(int score) {
        if (filterScore(score)) {
            this.score = score;
            return true;
        }
        return false;
    }

    /**
     * Getter del temps de la partida
     *
     * @pre none
     * @post none
     * @return retorna el temps actual de la partida
     */
    public long getTime() {
        updateTime();
        return time;
    }

    /**
     * Setter del temps de la partida
     *
     * @pre none
     * @post temps de la partida = time
     * @param time temps
     * @return true si la operació s'ha realitzat amb éxit, false altrament
     */
    public boolean setTime(long time) {
        if (filterTime(time)) {
            this.time = time;
            return true;
        }
        return false;
    }

    /**
     * Getter del nombre d'errors de la partida
     *
     * @pre none
     * @post none
     * @return retorna el nombre d'errors actuals de la partida
     */
    public int getNErrors() {
        return errors;
    }

    /**
     * Setter del nombre d'errors de la partia
     *
     * @pre none
     * @post nombre d'errors de la partida = errors
     * @param errors nombre d'errors
     * @return true si la operació s'ha realitzat amb éxit, false altrament
     */
    public boolean setNErrors(int errors) {
        if (filterNErrors(errors)) {
            this.errors = errors;
            return true;
        }
        return false;
    }

    /**
     * Incrementa el nombre d'errors en una unitat
     *
     * @pre none
     * @post el nombre d'errors de la partida s'ha incrementat en una unitat
     */
    public void incrementNErrors() {
        ++errors;
    }

    /**
     * Getter del nivell d'ajuda de la partida
     *
     * @pre none
     * @post none
     * @return retorna el nivell d'ajuda de la partida
     */
    public HelpLevel getHelpLevel() {
        return helpLevel;
    }

    /**
     * Setter del nivell d'ajuda de la partida
     *
     * @pre none
     * @post nivell d'ajuda partida = helpLevel
     * @param helpLevel nivell d'ajuda
     * @return true si la operació s'ha realitzat amb éxit, false altrament
     */
    public boolean setHelpLevel(HelpLevel helpLevel) {
        if (filterHelpLevel(helpLevel)) {
            this.helpLevel = helpLevel;
            return true;
        }
        return false;
    }

    /**
     * Actualitza el temps de la partida
     *
     * @pre none
     * @post el temps de la partida s'ha actualitzat
     */
    public void updateTime() {
        if (auxTime2 > 0) {
            auxTime1 = auxTime2;
        }
        auxTime2 = System.currentTimeMillis();
        if (!gamePaused) {
            time += (auxTime2 - auxTime1);
        }
    }

    /**
     * Cálcul de la puntuació final de la partida
     *
     * @pre none
     * @post la puntuació de la partida queda actualitzada segons els paràmetres
     * necessaris per calcular la puntuació
     */
    public void calculateFinalScore(int n) {
        Difficulty difficulty = getDifficulty();
        switch (difficulty) {
            case EASY:
                score = 5000;     // Easy
                break;
            case MEDIUM:
                score = 7500;   // Medium
                break;
            case HARD:
                score = 10000;    // Hard
                break;
        }               // Tamany!
        switch (helpLevel) {
            case BEGINNER:
                score -= 1000;  // Maximum
                break;
            case INTERMEDIATE:
                score -= 500;
                break;
        }
        score += n*1000;
        score -= errors * 1000;
        score -= (int) time/1000;
        if (score < 0) {
            score = 0;
        }
    }

    /**
     * Comprova que el valor s sigui un valor correcte
     *
     * @pre none
     * @post none
     * @param s
     * @return true si s és un valor correcte, false altrament
     */
    private boolean filterScore(int s) {
        return s >= 0;
    }

    /**
     * Comprova que el valor t sigui un valor correcte
     *
     * @pre none
     * @post none
     * @param t
     * @return true si t és un valor correcte, false altrament
     */
    private boolean filterTime(long t) {
        return t >= (long) 0;
    }

    /**
     * Comprova que el valor e sigui un valor correcte
     *
     * @pre none
     * @post none
     * @param e
     * @return true si e és un valor correcte, false altrament
     */
    private boolean filterNErrors(int e) {
        return e >= 0;
    }

    /**
     * Comprova que el valor hL sigui un valor correcte
     *
     * @pre none
     * @post none
     * @param hL
     * @return true si hL és un valor correcte, false altrament
     */
    private boolean filterHelpLevel(HelpLevel hL) {
        return (hL.equalsName("BEGINNER") || (hL.equalsName("INTERMEDIATE")) || (hL.equalsName("ADVANCED")));
    }

    /**
     * Carrega el joc salvat de l'usuari user
     *
     * @pre board és un tauler vàlid, time &ge; 0, errors &ge; 0, helpLevel és
     * un valor vàlid
     * @post tauler partida = board, usuari partida = user, temps partida =
     * time, errors partida = errors nivell ajuda partida = helpLevel i la
     * partida continua
     * @param board Tauler
     * @param user Usuari
     * @param time Temps
     * @param errors Nombre d'errors
     * @param helpLevel Nivell d'ajuda
     */
    public void loadGame(BoardSudoku board, String user, long time, int errors, String helpLevel) {
        setBoard(board);
        setUser(user);
        setTime(time);
        setNErrors(errors);
        setHelpLevel(HelpLevel.valueOf(helpLevel));
        resumeGame();
    }

    /**
     * Retorna el missatge d'error que l'usuari veurà en cas de cometre un
     * error.
     *
     * @pre: En la columna i fila, hi ha una cel·la on no es pot posar el value
     * @post: Retorna el missatge d'error per ajudar al ususari
     *
     * @param row fila
     * @param column columns
     * @param value xifra a assignar
     * @return Missatge d'error
     */
    @Override
    public String solverHelper(int row, int column, int value) {
        String message = "";
        int errorcode = 0;
        if (value != 0) {
            if (getBoard().rowContains(row, value)) {
                errorcode = 1; //Error a la columna
            } else if (getBoard().colContains(column, value)) {
                errorcode = 2; // Error a la fila
            } else {
                errorcode = 3; //Error a la regio 
            }
        }
        //System.out.println("Help Level" + getHelpLevel());
        switch (getHelpLevel()) {
            case BEGINNER:
                switch (errorcode) {
                    case 1:
                        message = "Error a la Fila: No pot haver dues cel·les amb el mateix valor en una mateixa fila.";
                        break;
                    case 2:
                        message = "Error a la Columna: No pot haver dues cel·les amb el mateix valor en una mateixa columna.";
                        break;
                    case 3:
                        message = "Error a la Regio: No pot haver dues cel·les amb el mateix valor en una mateixa regió.";
                        break;
                }
                break;

            case INTERMEDIATE:
                switch (errorcode) {
                    case 1:
                        message = "Error a la Columna";
                        break;
                    case 2:
                        message = "Error a la Fila";
                        break;
                    case 3:
                        message = "Error a la Regió";
                        break;
                }
                break;

            case ADVANCED:
                switch (errorcode) {
                    case 1:
                        message = "Columna";
                        break;
                    case 2:
                        message = "Fila";
                        break;
                    case 3:
                        message = "Regió";
                        break;
                }
                break;
        }
        return message;
    }

    /**
     * Nivells d'ajuda possibles del tauler
     *
     * @return Array de nivells d'ajuda possibles
     */
    public static String[] getHelpLevelModel() {
        HelpLevel[] levels = HelpLevel.values();
        String[] model = new String[levels.length];
        for (int i = 0; i < levels.length; i++) {
            model[i] = levels[i].toString();
        }
        return model;
    }
}
