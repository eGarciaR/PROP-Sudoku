/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package controllers.domain;

import exceptions.ColumnLocationException;
import exceptions.RegionLocationException;
import exceptions.RowLocationException;
import exceptions.BadNumberPositionException;
import domain.BoardSudoku;
import domain.Game;
import java.util.HashMap;
import java.util.HashSet;
import persistence.GameManager;
import persistence.UserBoardManager;

/**
 *
 * @author dani
 */
public class GameDomainCtrl {

    /**
     * Atributs de Game
     */
    public static final String ATTR_USER = "user";
    public static final String ATTR_ERRORS = "errors";
    public static final String ATTR_TIME = "time";
    public static final String ATTR_HELP_LEVEL = "help_level";
    public static final String ATTR_BOARD_ID = "board_id";
    public static final String ATTR_BOARD = "board";

    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static GameDomainCtrl gameDomainCtrl;
    private final BoardDomainCtrl boardDomainCtrl;
    private final GameManager gameManager;
    private final UserBoardManager userBoardManager;
    private Game game;

    /**
     * Constructor per defecte privat.
     */
    private GameDomainCtrl(String gameName) {
        boardDomainCtrl = BoardDomainCtrl.getInstance(gameName);
        userBoardManager = UserBoardManager.getInstance(gameName);
        gameManager = GameManager.getInstance(gameName);
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @param gameName Nom del Joc
     * @return La única instància de la propia classe.
     */
    public static GameDomainCtrl getInstance(String gameName) {
        if (gameDomainCtrl == null) {
            gameDomainCtrl = new GameDomainCtrl(gameName);
        }
        return gameDomainCtrl;
    }

    /**
     * Getter de la puntuació
     *
     * @return retorna la puntuació
     */
    public int getScore() {
        return game.getScore();
    }

    /**
     * Getter del temps
     *
     * @return retorna el temps
     */
    public long getTime() {
        return game.getTime();
    }

    /**
     * Getter del nombre d'errors
     *
     * @return retorna el nombre d'errors
     */
    public int getErrors() {
        return game.getNErrors();
    }

    /**
     * Getter del nom d'usuari
     *
     * @return retorna el nom de l'usuari
     */
    public String getUsername() {
        return game.getUsername();
    }

    /**
     * Comença la partida
     *
     * @pre board és un tauler vàlid i helpLevel és un nivell d'ajuda vàlid
     * @post crea un nou joc, comença la partida i esborra les partides salvades
     * de l'usuari
     * @param board tauler del sudoku
     * @param helpLevel nivell d'ajuda
     * @param user nom de l'usuari
     */
    protected void startPlayGame(BoardSudoku board, String helpLevel, String user) {
        game = new Game(board, helpLevel, user);
        game.startGame();
        gameManager.delete(user);
    }

    /**
     * Pausa la partida
     *
     * @pre cert
     * @post la partida queda pausada
     */
    public void pauseGame() {
        game.pauseGame();
    }

    /**
     * Continua la execució de la partida
     *
     * @pre cert
     * @post continua la partida
     */
    public void resumeGame() {
        game.resumeGame();
    }

    /**
     * Finalitza la partida
     *
     * @pre cert
     * @post el joc finalitza
     */
    public void finishGame() {
        game.finishGame(this.game.getBoard().getN());
        int boardId = this.game.getBoard().getId();
        if (boardId == 0) { //Aleatori o Editor
            boardId = this.boardDomainCtrl.add(this.game.getBoard(), null);
        }
        if (!this.game.getUsername().equals("")) {
            HashMap<String, Object> userBoard = new HashMap<>();
            userBoard.put(ATTR_USER, this.game.getUsername());
            userBoard.put(ATTR_BOARD_ID, boardId);
            userBoardManager.add(userBoard);
        }
    }

    /**
     * Salva la partida
     *
     * @pre cert
     * @post para el joc i guarda les dades necessàries per jugar amb
     * posterioritat
     */
    public void saveGame() {
        game.pauseGame();
        int boardId = game.getBoard().getId();
        if (boardId == 0) { //sudokuRandom o editor
            boardId = boardDomainCtrl.add(game.getBoard(), null);
        }
        HashMap<String, Object> attrsToSave = new HashMap<>();
        attrsToSave.put(ATTR_BOARD, game.getBoard().toString());
        attrsToSave.put(ATTR_BOARD_ID, boardId);
        attrsToSave.put(ATTR_ERRORS, game.getNErrors());
        attrsToSave.put(ATTR_HELP_LEVEL, game.getHelpLevel().toString());
        attrsToSave.put(ATTR_TIME, game.getTime());
        attrsToSave.put(ATTR_USER, game.getUsername());
        gameManager.add(attrsToSave);
    }

    /**
     * Carrega la partida d'un usuari.
     *
     * @param user Usuari
     */
    protected void loadGame(String user) {
        HashMap<String, Object> attrsToLoad = gameManager.get(user);
        int boardId = (int) attrsToLoad.get(ATTR_BOARD_ID);
        BoardSudoku board = boardDomainCtrl.load(boardId);
        String boardToLoad = (String) attrsToLoad.get(ATTR_BOARD);
        board.fromString(boardToLoad);
        long time = (long) attrsToLoad.get(ATTR_TIME);
        int errors = (int) attrsToLoad.get(ATTR_ERRORS);
        String helpLevel = (String) attrsToLoad.get(ATTR_HELP_LEVEL);
        if (game == null) {
            game = new Game(board, helpLevel, user);
        }
        game.loadGame(board, user, time, errors, helpLevel);
        gameManager.delete(user); // Borrem la partida salvada de la BD
    }

    public boolean savedGame(String user) {
        return this.gameManager.savedGame(user);
    }

    /**
     * Canvia el valor de la cel·la d'un joc.
     *
     * @pre row és una fila vàlida, column és una columna vàlida i value és un
     * valor vàlid
     * @post si el valor de la cela es pot canviar, el canvia
     * @param row fila del tauler
     * @param column columna del tauler
     * @param value valor del tauler
     * @throws exceptions.BadNumberPositionException Llançada quan no es pot introduïr el valor.
     */
    public void setCellValue(int row, int column, int value) throws BadNumberPositionException {
        boolean b = false;
        try {
            b = game.correctCell(row, column, value);
        } catch (RowLocationException e) {
            game.incrementNErrors();
            String errorMessage = game.solverHelper(row, column, value);
            throw new RowLocationException(errorMessage);
        } catch (ColumnLocationException e) {
            game.incrementNErrors();
            String errorMessage = game.solverHelper(row, column, value);
            throw new ColumnLocationException(errorMessage);
        } catch (RegionLocationException e) {
            game.incrementNErrors();
            String errorMessage = game.solverHelper(row, column, value);
            throw new RegionLocationException(errorMessage);
        }
        game.setCellValue(row, column, value);
    }

    /**
     * Indica si el joc ha acabat
     *
     * @pre none
     * @post none
     * @return boolean indica si el joc ha acabat
     */
    public boolean gameEnded() {
        return this.game.getBoard().getNNumbers() == Math.pow(this.game.getBoard().getN(), 4);
    }

    public int getN() {
        return game.getBoard().getN();
    }

    public String getBoard() {
        return game.getBoard().toString();
    }

    public String[] getHelpLevelModel() {
        return Game.getHelpLevelModel();
    }

    public HashSet<Integer> getBoardsCreated(String user) {
        return this.userBoardManager.getAllByUser(user);
    }

}
