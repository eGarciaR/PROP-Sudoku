package presentation;

import exceptions.BadNumberPositionException;
import controllers.presentation.EditorPresentationCtrl;
import controllers.presentation.PresentationCtrl;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class EditorView extends SudokuView {

    private final EditorPresentationCtrl epCtrl;

    private JButton jbValidate;
    private JButton jbBack;

    //------------ Constructores i metodes publics -------------
    /**
     * Constructor de la classe Editor
     *
     * @param n mida
     * @param pCtrl control de presentació
     */
    public EditorView(int n, PresentationCtrl pCtrl) {
        super(n, pCtrl);
        epCtrl = pCtrl.getEditorPresentationCtrl();
        initializeComponents();
    }

    /**
     * Getter del panel editor
     *
     * @return pnaell del editor
     */
    public JPanel getEditorPanel() {
        return this.getSudokuPanel();
    }
    
    public void disableValidate() {
        this.jbValidate.setEnabled(false);
    }
    // -------------Event listeners----------------

    private void exitAction() {
        this.getPCtrl().setPanel("home");
    }

    private void validateAction() {
        boolean unique = this.epCtrl.uniqueSolution();
        if (unique) {
            //Mostrar PopUp amb Opcions
            this.epCtrl.setDifficulty();
            this.getPCtrl().setPopUpPanel("editorMenu");
            this.getPCtrl().setGlassPane();
        } else {
            setErrorMessage("La solució no és única. Introdueix més números.");
        }
    }
    //-------------- metodes privats ----------------
    private void initializeComponents() {
        initializeButtons();
    }

    private void initializeButtons() {
        initializeBackButton();
        initializeValideButton();
    }

    private void initializeValideButton() {
        jbValidate = new JButton("VALIDAR");
        jbValidate.setPreferredSize(new Dimension(100, 40));
        this.getButtonsPanel().add(jbValidate);
        jbValidate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                validateAction();
            }
        });
    }

    private void initializeBackButton() {
        jbBack = new JButton("SORTIR");
        jbBack.setPreferredSize(new Dimension(100, 40));
        this.getButtonsPanel().add(jbBack);
        jbBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitAction();
            }
        });

    }

    @Override
    protected void setInput(JTextField cell) throws BadNumberPositionException {
        int value;
        if (cell.isEditable()) {
            String[] location = cell.getAccessibleContext().getAccessibleName().split(",");
            if (cell.getText().equals("")) {
                value = 0;
            } else {
                value = Integer.parseInt(cell.getText());
            }
            epCtrl.setCellValue(Integer.parseInt(location[0]), Integer.parseInt(location[1]), value);   
        }
    }

}
