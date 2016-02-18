package controllers.presentation;

import presentation.popups.DeleteUser;
import presentation.popups.EditorMenu;
import presentation.popups.ExitGame;
import presentation.popups.PopUpView;
import presentation.popups.ExitGameGuest;
import presentation.popups.GameMenuGuest;
import presentation.popups.GameMenu;
import controllers.domain.BoardDomainCtrl;
import presentation.MainView;
import controllers.domain.DomainCtrl;
import controllers.domain.EditorDomainCtrl;
import controllers.domain.GameDomainCtrl;
import controllers.domain.RegisteredUserDomainCtrl;
import controllers.domain.StatisticsDomainCtrl;
import controllers.domain.UserDomainCtrl;
import java.awt.Dimension;
import java.util.HashSet;
import javax.swing.JPanel;
import presentation.*;
import presentation.popups.ChooseHelpLevel;
import presentation.popups.FinishGame;
import presentation.popups.ChooseRandom;
import presentation.popups.ChooseSize;

/**
 *
 * @author Marc
 */
public class PresentationCtrl {

    private static PresentationCtrl presentationCtrl;
    private final UserPresentationCtrl userPresentationCtrl;
    private final DomainCtrl domainCtrl;
    private final GamePresentationCtrl gamePresentationCtrl;
    private final EditorPresentationCtrl editorPresentationCtrl;
    private final StatisticsPresentationCtrl statisticsPresentationCtrl;

    private final MainView mainView;
    private LoginView loginView;
    private RegisterView registerView;
    private HomeView homeView;
    private final PlayView playView;
    private EditorView editorView;
    private GameView gameView;
    private RankingView rankingView;
    private RecordsView recordsView;
    private ChooseBoardView chooseBoardView;
    private PassChangeView passChangeView;
    private final GameMenu gameMenu;
    private final GameMenuGuest gameMenuGuest;
    private final ExitGame exitGame;
    private final ExitGameGuest exitGameGuest;
    private final EditorMenu editorMenu;
    private final ChooseHelpLevel chooseHelpLevel;
    private final FinishGame finishGame;
    private final ChooseSize chooseSize;
    private final ChooseRandom chooseRandom;

    private final PopUpView popUpView;
    private static DeleteUser deleteUserView;

    private PresentationCtrl() {
        domainCtrl = DomainCtrl.getInstance();
        userPresentationCtrl = UserPresentationCtrl.getInstance(this);
        gamePresentationCtrl = GamePresentationCtrl.getInstance(this);
        editorPresentationCtrl = EditorPresentationCtrl.getInstance(this);
        statisticsPresentationCtrl = StatisticsPresentationCtrl.getInstance(this);

        mainView = MainView.getInstance(this);
        loginView = new LoginView(this);
        registerView = new RegisterView(this);
        homeView = new HomeView(this);
        popUpView = PopUpView.getInstance();
        rankingView = new RankingView(this);
        recordsView = new RecordsView(this);
        playView = new PlayView(this);
        gameMenu = new GameMenu(this);
        gameMenuGuest = new GameMenuGuest(this);
        passChangeView = new PassChangeView(this);
        exitGame = new ExitGame(this);
        exitGameGuest = new ExitGameGuest(this);
        deleteUserView = new DeleteUser(this);
        editorMenu = new EditorMenu(this);
        chooseHelpLevel = new ChooseHelpLevel(this);
        finishGame = new FinishGame(this);
        chooseSize = new ChooseSize(this);
        chooseRandom = new ChooseRandom(this);
    }

    public static PresentationCtrl getInstance() {
        if (presentationCtrl == null) {
            presentationCtrl = new PresentationCtrl();
        }
        return presentationCtrl;
    }

    public UserDomainCtrl getUserDomainCtrl() {
        return this.domainCtrl.getUserDomainCtrl();
    }

    public GameDomainCtrl getGameDomainCtrl() {
        return this.domainCtrl.getGameDomainCtrl();
    }

    public EditorDomainCtrl getEditorDomainCtrl() {
        return this.domainCtrl.getEditorDomainCtrl();
    }

    public DomainCtrl getDomainCtrl() {
        return this.domainCtrl;
    }

    public void initPresentation() {
        mainView.setVisible();
        mainView.setActivePanel(loginView);
        domainCtrl.initJocsDeProva();
    }

    public RegisteredUserDomainCtrl getRegisteredUserDomainCtrl() {
        return this.domainCtrl.getRegisteredUserDomainCtrl();
    }

    public StatisticsDomainCtrl getStatisticsDomainCtrl() {
        return this.domainCtrl.getStadisticsDomainCtrl();
    }

    public UserPresentationCtrl getUserPresentationCtrl() {
        return this.userPresentationCtrl;
    }

    public BoardDomainCtrl getBoardDomainCtrl() {
        return this.domainCtrl.getBoardDomainCtrl();
    }

    public GamePresentationCtrl getGamePresentationCtrl() {
        return this.gamePresentationCtrl;
    }

    public EditorPresentationCtrl getEditorPresentationCtrl() {
        return this.editorPresentationCtrl;
    }

    public StatisticsPresentationCtrl getStatistiscsPresentationCtrl() {
        return this.statisticsPresentationCtrl;
    }

    public void setPanel(String s) {
        switch (s) {
            case "login":
                loginView = new LoginView(this);
                mainView.setActivePanel(loginView);
                changeUserBar(true);
                break;
            case "register":
                registerView = new RegisterView(this);
                mainView.setActivePanel(registerView);
                break;
            case "home":
                homeView = new HomeView(this);
                mainView.setActivePanel(homeView);
                changeUserBar(false);
                break;
            case "play":
                playView.enableLoadGame();
                mainView.setActivePanel(playView);
                break;
            case "recordsView":
                recordsView = new RecordsView(this);
                mainView.setActivePanel(recordsView);
                break; 
            case "rankingView":
                rankingView = new RankingView(this);
                mainView.setActivePanel(rankingView);
                break;
            case "chooseBoard":
                chooseBoardView = new ChooseBoardView(this);
                mainView.setActivePanel(chooseBoardView);
                break;
            case "passChangeView":
                passChangeView = new PassChangeView(this);
                mainView.setActivePanel(passChangeView);
                break;
        }
    }

    public void setGlassPane() {
        mainView.setGlassPane();
    }

    public void removeGlassPane() {
        mainView.removeGlassPane();
    }

    public void setPopUpPanel(String s) {
        Dimension d=null;
        JPanel jp=null;
        switch (s) {
            case "gameMenu":
                jp = gameMenu;
                break;
            case "exitGame":
                jp=exitGame;
                break;
            case "gameMenuGuest":
                jp = gameMenuGuest;
                break;
            case "exitGameGuest":
                jp = exitGameGuest;
                break;
            case "deleteUserView":
                jp = deleteUserView;  
                break;
            case "editorMenu":
                jp = editorMenu;
                editorMenu.resetButtons();
                break;
            case "chooseHelpLevel":
                jp = chooseHelpLevel;
                break;
            case "finishGame":
                jp = finishGame;
                finishGame.getScore();
                break;
            case "chooseSize":
                jp = chooseSize;
                break;
            case "chooseRandom":
                jp = chooseRandom;
                break;
        }
        if (jp != null) {
            d = jp.getPreferredSize();
        }
        popUpView.resizeView(d);
        popUpView.setActivePanel(jp);
        popUpView.setVisible(true);
    }

    public void saveGame() {
        domainCtrl.getGameDomainCtrl().saveGame();
    }

    public void setPopUpVisible(boolean b) {
        popUpView.setVisible(b);
    }

    public HashSet<Integer> getBoardsDone() {
        String user = this.domainCtrl.getUserDomainCtrl().getUser();
        return this.domainCtrl.getGameDomainCtrl().getBoardsCreated(user);
    }

    public void changeUserBar(boolean logout) {
        String userName = userPresentationCtrl.getUsername();
        boolean registered;
        registered = (!"".equals(userName));
        if (logout) {
            userName = "default";
        }
        mainView.changeUserBar(userName, registered);
    }
    
    public void toPlayUserBar (boolean toPlay){
        mainView.playingUserBar(toPlay, userPresentationCtrl.getUsername());
    }

    public void startGame(int boardId, String helpLevel) {
        this.domainCtrl.startGame(boardId, helpLevel);
        int n = this.domainCtrl.getGameDomainCtrl().getN();
        gameView = new GameView(n, this);
        this.mainView.setActivePanel(gameView.getGamePanel());
        toPlayUserBar(true);
    }

    public void startRandomGame(int n, String difficulty, String helpLevel) {
        this.domainCtrl.startRandomGame(n, difficulty, helpLevel);
        gameView = new GameView(n, this);
        this.mainView.setActivePanel(gameView.getGamePanel());
        toPlayUserBar(true);
    }
    
    public void startGameFromEditor(String helpLevel) {
        this.domainCtrl.startGameFromEditor(helpLevel);
        int n = this.domainCtrl.getGameDomainCtrl().getN();
        gameView = new GameView(n, this);
        this.mainView.setActivePanel(gameView.getGamePanel());
        toPlayUserBar(true);
    }

    public void loadGame() {
        this.domainCtrl.loadGame();
        int n = this.domainCtrl.getGameDomainCtrl().getN();
        gameView = new GameView(n, this);
        this.mainView.setActivePanel(gameView.getGamePanel());
        toPlayUserBar(true);
    }

    public void startEditor(int n) {
        this.domainCtrl.startEditor(n);
        editorView = new EditorView(n, this);
        this.mainView.setActivePanel(editorView.getEditorPanel());
    }
    
    public void solveEditor() {
        this.domainCtrl.getEditorDomainCtrl().solve();
        this.editorPresentationCtrl.setBoard(editorView);
        editorView.disableValidate();
        this.mainView.setActivePanel(editorView.getEditorPanel());
    }
    
    public void repaint(){
        mainView.repintar();
    }
    
    public boolean isGameEnded(){
        return this.domainCtrl.getGameDomainCtrl().gameEnded();
    }
    
    public void finishGame(){
        this.domainCtrl.finishGame();
    }
}
