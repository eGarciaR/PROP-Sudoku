package presentation;

import exceptions.BadNumberPositionException;
import controllers.presentation.GamePresentationCtrl;
import controllers.presentation.PresentationCtrl;
import controllers.presentation.UserPresentationCtrl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Marc
 */
public class GameView extends SudokuView {

    protected final GamePresentationCtrl gICtrl;
    private final UserPresentationCtrl upCtrl;

    private JButton jbPause;
    private JButton jbSaveAndClose;
    private JLabel timeDisplay;

    private final boolean isGuest;
    private boolean gameEnd;

    //------------ Constructores i metodes publics -------------
    /**
     * Constructor de la classe Editor
     *
     * @param n mida
     * @param pCtrl control de presentació
     */
    public GameView(int n, PresentationCtrl pCtrl) {
        super(n, pCtrl);
        gICtrl = pCtrl.getGamePresentationCtrl();
        upCtrl = pCtrl.getUserPresentationCtrl();
        isGuest = upCtrl.getUsername().equals("");
        gameEnd = false;
        initializeComponents();
    }

    public JPanel getGamePanel() {
        return this.getSudokuPanel();
    }

    public void setTime(String s) {
        timeDisplay.setText(" " + s + " ");
    }

    //-------------- listeners -------------------
    private void saveAndCloseAction() {
        gICtrl.pauseGame();
        if (isGuest) {
            this.getPCtrl().setPopUpPanel("exitGameGuest");
        } else {
            this.getPCtrl().setPopUpPanel("exitGame");
        }
        this.getPCtrl().setPopUpVisible(true);
        this.getPCtrl().setGlassPane();
    }

    private void pauseAction() {
        gICtrl.pauseGame();
        if (isGuest) {
            this.getPCtrl().setPopUpPanel("gameMenuGuest");
        } else {
            this.getPCtrl().setPopUpPanel("gameMenu");
        }
        this.getPCtrl().setPopUpVisible(true);
        this.getPCtrl().setGlassPane();
    }

    @Override
    public void setInput(JTextField cell) throws BadNumberPositionException {
        int value;
        if (cell.isEditable()) {
            String[] location = cell.getAccessibleContext().getAccessibleName().split(",");
            if (cell.getText().equals("")) {
                value = 0;
            } else {
                value = Integer.parseInt(cell.getText());
            }
            gICtrl.setCellValue(Integer.parseInt(location[0]), Integer.parseInt(location[1]), value);
            if (gICtrl.isGameEnded()) {
                if (!gameEnd) {
                    gameEnd = true;
                    gICtrl.finishGame();
                    this.getPCtrl().setPopUpPanel("finishGame");
                    this.getPCtrl().setGlassPane();
                    
                }

            }
        }
    }
    //-------------- metodes privats ----------------

    private void initializeComponents() {
        initializeGame();
        initializePauseButton();
        initializeSaveAndCloseButton();
        initializeTimeDisplay();

    }

    private void initializePauseButton() {
        jbPause = new JButton("PAUSAR");
        jbPause.setPreferredSize(new Dimension(100, 40));
        this.getButtonsPanel().add(jbPause);
        jbPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pauseAction();
            }
        });
    }

    private void initializeSaveAndCloseButton() {
        if (isGuest) {
            jbSaveAndClose = new JButton("SORTIR");
        } else {
            jbSaveAndClose = new JButton("SALVAR I SORTIR");
        }
        jbSaveAndClose.setPreferredSize(new Dimension(200, 40));
        this.getButtonsPanel().add(jbSaveAndClose);
        jbSaveAndClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndCloseAction();
            }
        });
    }

    private void initializeTimeDisplay() {
        timeDisplay = new JLabel();
        timeDisplay.setSize(new Dimension(45, 150));
        timeDisplay.setHorizontalAlignment(JTextField.CENTER);
        timeDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeDisplay.setFont(new java.awt.Font("Lucida Grande", 0, 30));
        timeDisplay.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102), 3));
        timeDisplay.setOpaque(true);
        timeDisplay.setBackground(new java.awt.Color(255, 255, 255));
        getInfoPanel().add(timeDisplay);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TaskExampleOnce(), 0, 1000);
    }

    private void initializeGame() {
        gICtrl.setInitialBoard(this);
    }

    class TaskExampleOnce extends TimerTask {

        @Override
        public void run() {
            setTime(gICtrl.getTime());
        }
    }
}
