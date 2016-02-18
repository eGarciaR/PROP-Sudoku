package presentation.popups;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author Marc
 */
public class PopUpView {

    private static PopUpView puv;
    private static JFrame JFrameVista;
    private static JPanel activePanel;

    //------------- Constructor ---------------
    private PopUpView() {
        activePanel = new JPanel();
        JFrameVista = new JFrame("Sudoku");
        ImageIcon i = new ImageIcon(getClass().getResource("/resources/img/Slogo.png"));
        JFrameVista.setIconImage(i.getImage());
        initComponents();
        removeCloseButton(JFrameVista);
    }

    public void removeCloseButton(Component comp) {
    if (comp instanceof JMenu) {
      Component[] children = ((JMenu) comp).getMenuComponents();
      for (int i = 0; i < children.length; ++i)
        removeCloseButton(children[i]);
    }
    else if (comp instanceof AbstractButton) {
      Action action = ((AbstractButton) comp).getAction();
      String cmd = (action == null) ? "" : action.toString();
      if (cmd.contains("CloseAction")) {
        comp.getParent().remove(comp);
      }
    }
    else if (comp instanceof Container) {
      Component[] children = ((Container) comp).getComponents();
      for (int i = 0; i < children.length; ++i)
        removeCloseButton(children[i]);
    }
  }

    /**
     * Actua com a constructor
     *
     * @return retorna la unica instancia de la classe
     */
    public static PopUpView getInstance() {
        if (puv == null) {
            puv = new PopUpView();
        }
        return puv;
    }

    //------------ Metodes publics -----------
    /**
     * Fa visible la vista
     *
     * @param b indica si es vol fer visible
     */
    public void setVisible(boolean b) {
        JFrameVista.pack();
        JFrameVista.setVisible(b);
    }

    /**
     * Setter d'activePanel
     *
     * @param panel panell a activar
     */
    public void setActivePanel(JPanel panel) {
        PopUpView.activePanel.removeAll();
        PopUpView.activePanel.add(panel);
        JFrameVista.pack();
        JFrameVista.repaint();
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

    // -------------- Metodes privats ----------------
    /**
     * Inicialitza tots els components
     */
    private void initComponents() {
        initPopUpView();
        initActivePanel();
    }

    /**
     * Inicialitza el JFrame
     */
    private void initPopUpView() {
        JFrameVista.setResizable(false); //Per l'usuari
        JFrameVista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //No deixa tancar
        JFrameVista.setLocationRelativeTo(null);
        JFrameVista.setContentPane(activePanel);
        JFrameVista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                JFrameVista.requestFocus();
            }
        });
        
        removeCloseButton(JFrameVista);
    }
    

    /**
     * Inicialitza el panel principal
     */
    private void initActivePanel() {
        activePanel.setLayout(new FlowLayout());
        activePanel.setBackground(Color.blue);
    }
}
