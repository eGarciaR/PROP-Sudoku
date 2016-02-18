/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package controllers.domain;

import domain.BoardSudoku;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author dani
 */
public class DomainCtrl {

    /**
     * Unica instància de la classe per implementar patró singleton.
     */
    private static DomainCtrl domainCtrl;
    private final BoardDomainCtrl boardDomainCtrl;
    private final GameDomainCtrl gameDomainCtrl;
    private final EditorDomainCtrl editorDomainCtrl;
    private final StatisticsDomainCtrl statisticsDomainCtrl;
    private final UserDomainCtrl userDomainCtrl;
    private final RegisteredUserDomainCtrl registeredUserDomainCtrl;
    public static final String GAME_NAME = "sudoku";

    /**
     * Constructor per defecte privat.
     */
    private DomainCtrl() {
        this.userDomainCtrl = UserDomainCtrl.getInstance(GAME_NAME);
        this.registeredUserDomainCtrl = RegisteredUserDomainCtrl.getInstance(GAME_NAME);
        this.boardDomainCtrl = BoardDomainCtrl.getInstance(GAME_NAME);
        this.gameDomainCtrl = GameDomainCtrl.getInstance(GAME_NAME);
        this.editorDomainCtrl = EditorDomainCtrl.getInstance(GAME_NAME);
        this.statisticsDomainCtrl = StatisticsDomainCtrl.getInstance(GAME_NAME);
    }

    /**
     * Actua com a constructor de la classe.
     *
     * @return La única instància de la propia classe.
     */
    public static DomainCtrl getInstance() {
        if (domainCtrl == null) {
            domainCtrl = new DomainCtrl();
        }
        return domainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de GameDomainCtrl
     *
     * @return Instancia de GameDomainCtrl
     */
    public GameDomainCtrl getGameDomainCtrl() {
        return this.gameDomainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de EditorDomainCtrl
     *
     * @return Instancia de EditorDomainCtrl
     */
    public EditorDomainCtrl getEditorDomainCtrl() {
        return this.editorDomainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de StadisticsDomainCtrl
     *
     * @return Instancia de StadisticsDomainCtrl
     */
    public StatisticsDomainCtrl getStadisticsDomainCtrl() {
        return this.statisticsDomainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de UserDomainCtrl
     *
     * @return Instancia de UserDomainCtrl
     */
    public UserDomainCtrl getUserDomainCtrl() {
        return this.userDomainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de RegisteredUserDomainCtrl
     *
     * @return Instancia de registeredUserDomainCtrl
     */
    public RegisteredUserDomainCtrl getRegisteredUserDomainCtrl() {
        return this.registeredUserDomainCtrl;
    }

    /**
     * Per otorgar la funcionalitat de BoardDomainCtrl
     *
     * @return Instancia de BoardDomainCtrl
     */
    public BoardDomainCtrl getBoardDomainCtrl() {
        return this.boardDomainCtrl;
    }

    public void initJocsDeProva() {
        String user = "";
        for (int i = 1; i < 6; ++i) {
            user = "User" + Integer.toString(i);
            if (!userDomainCtrl.existsUser(user)) {
                userDomainCtrl.addUser(user, "Pass" + Integer.toString(i));
                registeredUserDomainCtrl.addUser(user);
                registeredUserDomainCtrl.incGamesPlayed(user);
                registeredUserDomainCtrl.winGame(user, i * 1000, 60000 + i * 2000);
                statisticsDomainCtrl.updateRanking(user, i * 1000);
                statisticsDomainCtrl.updateRecords(registeredUserDomainCtrl.getRegisteredUser(user));
            }
        }
    }

    public void startGame(int boardId, String helpLevel) {
        String user = userDomainCtrl.getUser();
        BoardSudoku bs = this.boardDomainCtrl.load(boardId);
        if (!user.equals("")) {
            if (userDomainCtrl.existsUser(user)) {
                registeredUserDomainCtrl.incGamesPlayed(user);
            }
        }
        gameDomainCtrl.startPlayGame(bs, helpLevel, user);
    }

    public void startRandomGame(int n, String difficulty, String helpLevel) {
        String user = this.userDomainCtrl.getUser();
        if (!user.equals("")) {
            if (userDomainCtrl.existsUser(user)) {
                registeredUserDomainCtrl.incGamesPlayed(user);
            }
        }
        BoardSudoku bs = boardDomainCtrl.generateRandom(n, difficulty);
        gameDomainCtrl.startPlayGame(bs, helpLevel, user);
    }
    
    public void startGameFromEditor(String helpLevel) {
        String user = this.userDomainCtrl.getUser();
        BoardSudoku bs = editorDomainCtrl.getBoard();
        bs.setAllCellsFixed();
        registeredUserDomainCtrl.incGamesPlayed(user);
        gameDomainCtrl.startPlayGame(bs, helpLevel, user);
    }
    public void startEditor(int n) {
        String user = this.userDomainCtrl.getUser();
        this.editorDomainCtrl.startEditor(n, user);
    }

    public void finishGame() {
        gameDomainCtrl.finishGame();
        String userName = gameDomainCtrl.getUsername();
        int score = gameDomainCtrl.getScore();
        long time = gameDomainCtrl.getTime();
        if (userDomainCtrl.existsUser(userName)) {
            registeredUserDomainCtrl.winGame(userName, score, time);
            this.statisticsDomainCtrl.updateRanking(userName, score);
            this.statisticsDomainCtrl.updateRecords(registeredUserDomainCtrl.getRegisteredUser(userName));
        }
    }

    public boolean saveEditor() {
        boolean ok = this.editorDomainCtrl.save();
        if (ok) {
            String user = this.userDomainCtrl.getUser();
            registeredUserDomainCtrl.incBoardsCreated(user);
            HashSet<HashMap<String, Object>> updatedRecords = this.statisticsDomainCtrl.updateRecords(registeredUserDomainCtrl.getRegisteredUser(user));
        }
        return ok;
    }

    public void loadGame() {
        String user = this.userDomainCtrl.getUser();
        this.gameDomainCtrl.loadGame(user);
    }
}
