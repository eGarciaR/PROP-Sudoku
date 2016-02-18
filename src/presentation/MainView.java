package presentation;

import controllers.presentation.PresentationCtrl;
import controllers.presentation.UserPresentationCtrl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marc
 */
public class MainView {

    // ---------components de la interficie----------
    private static MainView mv;
    private static JFrame JFrameVista;
    private static JPanel activePanel;
    private static GlassPane glassPane;
    private static JPanelBackground backgroundPanel; // pel fondo de moment no rula

    private final PresentationCtrl pCtrl;
    private final UserPresentationCtrl uCtrl;

    // ------------components del menu-----------
    private JMenuBar menuBar;
    private JMenu user;
    private JMenuItem logoutU;
    private JMenuItem passworCh;
    private JMenuItem deleteU;

    // ---------constructor----------
    /**
     * Constructor de MainView
     */
    private MainView(PresentationCtrl pCtrl) {
        JFrameVista = new JFrame("Sudoku");
        ImageIcon i = new ImageIcon(getClass().getResource("/resources/img/Slogo.png"));
        JFrameVista.setIconImage(i.getImage());
        activePanel = new JPanel();
        backgroundPanel = new JPanelBackground("/resources/img/background.png"); // pel fondo de moment no rula
        glassPane = new GlassPane();
        this.pCtrl = pCtrl;
        uCtrl = this.pCtrl.getUserPresentationCtrl();
        initComponents();
    }

    /**
     * Actua com a constructor
     *
     * @param pCtrl control de presentació
     * @return retorna la unica instancia de la classe
     */
    public static MainView getInstance(PresentationCtrl pCtrl) {
        if (mv == null) {
            mv = new MainView(pCtrl);
        }
        return mv;
    }

    // --------metodes publics---------
    /**
     * Fa visible la vista
     */
    public void setVisible() {
        JFrameVista.pack();
        JFrameVista.setVisible(true);
    }

    /**
     * Setter d'activePanel
     *
     * @param panel panell a activar
     */
    public void setActivePanel(JPanel panel) {
        activePanel.removeAll();       
        activePanel.add(panel);
        JFrameVista.pack();
        JFrameVista.repaint();
    }
/**
 * Activa el glassPane
 */
    public void setGlassPane() {
        glassPane.activate();
    }
/**
 * Desactiva el glassPane
 */
    public void removeGlassPane() {
        glassPane.deactivate();
    }

    /**
     * Escala la vista a la dimensió d
     *
     * @param d dimensió
     */
    public void resizeView(Dimension d) {
        JFrameVista.setMinimumSize(d);
        JFrameVista.setPreferredSize(d);
        JFrameVista.setSize(d);
        JFrameVista.setLocationRelativeTo(null);
    }

    // --------metodes privats--------
    /**
     * Inicialitza tots els componenets de la vista principal
     */
    private void initComponents() {
        initActivePanel();
        initMenuBar();
        initMainView();
    }

    /**
     * Inicialitza la vista principal
     */
    private void initMainView() {
        // tamany
        JFrameVista.setMinimumSize(new Dimension(800, 800));
        JFrameVista.setPreferredSize(JFrameVista.getMinimumSize());
        JFrameVista.setResizable(false);
        JFrameVista.setLocationRelativeTo(null);
        JFrameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        activePanel.setBounds(0, 0, 750,750);
        JFrameVista.getContentPane().add(activePanel);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800,800);
        JFrameVista.getContentPane().add(backgroundPanel);
        JFrameVista.setGlassPane(glassPane);
    }

    /**
     * Inicialitza el panel principal
     */
    private void initActivePanel() {
        activePanel.setLayout(new FlowLayout());
        activePanel.setPreferredSize(new Dimension(800, 800));
        activePanel.setBackground(new Color(0,0,0,0));
    }
/**
 * Inicialitza els menus de la barra de menus
 */
    private void initUserMenu() {
        user = new JMenu("Usuari");
        logoutU = new JMenuItem("Logout");
        passworCh = new JMenuItem("Canviar Contrasenya");
        deleteU = new JMenuItem("Eliminar Usuari");

        logoutU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutUClicked(e);
            }
        });
        passworCh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordChClicked(e);
            }
        });

        deleteU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUClicked(e);
            }
        });
        ImageIcon i = new ImageIcon(getClass().getResource("/resources/img/userIcon20x20.png"));
        user.setIcon(i);
        user.add(logoutU);
        user.add(passworCh);
        user.add(deleteU);
        user.setEnabled(false);

    }

    /**
     * Inicialitza la barra de menu
     */
    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalGlue());
        initUserMenu();
        menuBar.add(user);
        JFrameVista.setJMenuBar(this.menuBar);
    }
/**
 * Depenent de l'usuari i el bolea log, activa per als diferents usuaris,
 * les diferens opcions que tindra l'usuari.
 * @param usuari usuari 
 * @param log indica si el user es registrat o no
 */
    public void changeUserBar(String usuari, boolean log) {
        if ("default".equals(usuari)) {
            user.setEnabled(false);
            user.setText("Usuari");
        } else {
            user.setEnabled(true);
            if (log) {
                user.setText(usuari);
                passworCh.setEnabled(true);
                deleteU.setEnabled(true);
            } else {
                user.setText("Convidat");
                passworCh.setEnabled(false);
                deleteU.setEnabled(false);
            }
        }
    }
/**
 * Desactiva o activa el menu per a un usuari registrat que es disposa a jugar una partida.
 * @param toPlay indica si va a jugar o no
 * @param usuari user
 */   
    public void playingUserBar(boolean toPlay, String usuari){
        if (! usuari.equals("")) {
            user.setEnabled(!toPlay);
        }
    
    }

    /**
     * Accio de premer el boto de logout
     * @param e event
     */
    private void logoutUClicked(java.awt.event.ActionEvent e) {
        uCtrl.logout();
    }

    /**
     * Accio de clicar el camp password
     * @param e event
     */
    private void passwordChClicked(java.awt.event.ActionEvent e) {
        pCtrl.setPanel("passChangeView");
    }

    /**
     * Acccio de clicar el eliminar usuari
     * @param e event
     */
    private void deleteUClicked(java.awt.event.ActionEvent e) {
        pCtrl.setPopUpPanel("deleteUserView");
    }
    
    /**
     * Repintar el frame
     */
    public void repintar(){
        JFrameVista.pack();
        JFrameVista.repaint();
    }
}
