/*
 * Copyright 2015
 * Projecte de PROP - Sudoku
 * Versió 1.0
 * Autors: Dani Criado, Eric Garcia, Marc Cubiró, Ferran Perelló
 */
package presentation;

import controllers.presentation.GamePresentationCtrl;
import controllers.presentation.PresentationCtrl;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dani
 */
public class ChooseBoardView extends javax.swing.JPanel {
    
    /**
     * Controllers
     */
    PresentationCtrl pCtrl;
    GamePresentationCtrl gpCtrl;
    
    /**
     * Models
     */
    DefaultComboBoxModel<Integer> dcbmSize;
    DefaultComboBoxModel<String> dcbmDifficulty;
    DefaultComboBoxModel<String> dcbmHelpLevel;
    DefaultTableModel dtmBoards;
    
    /**
     * Icons
     */
    TableCellRenderer buttonRenderer;
    IconRenderer ir;
    ImageIcon iiPlay;
    ImageIcon iiTick;
    ImageIcon iiCross;
    private final String[] columnNames = {"ID", "Creador", "Fet", "Jugar"};

    /**
     * Creates new form ChooseBoardView
     *
     * @param presentationCtrl control de presentació
     */
    public ChooseBoardView(PresentationCtrl presentationCtrl) {
        this.pCtrl = presentationCtrl;
        this.gpCtrl = this.pCtrl.getGamePresentationCtrl();
        initRenderers();
        initIcons();
        initModels();
        initComponents();
        initTable();
    }
/**
 * Funció que inicialitza els icones
 */
    private void initIcons() {
        this.iiPlay = new ImageIcon(getClass().getResource("/resources/img/play.png"));
        this.iiTick = new ImageIcon(getClass().getResource("/resources/img/tick.png"));
        this.iiCross = new ImageIcon(getClass().getResource("/resources/img/cross.png"));
    }
/**
 * Funcio que inicialitza els renders
 */
    private void initRenderers() {
        this.buttonRenderer = new JTableButtonRenderer();
        this.ir = new IconRenderer();
    }
/**
 * Funcio que inicialitza el tamany dels models per a la taula 
 */
    private void initSizeModel() {
        Integer[] items = this.gpCtrl.getSizeModel();
        this.dcbmSize = new DefaultComboBoxModel<>(items);
    }
/**
 * Funcio que inicialitza el desplegable de Dificultat
 */
    private void initDifficultyModel() {
        String[] items = this.gpCtrl.getDifficultyModel();
        for (int i = 0; i < items.length; i++) {
            items[i] = this.gpCtrl.translateDifficulty(items[i]);
        }
        this.dcbmDifficulty = new DefaultComboBoxModel<>(items);
    }
/**
 * Funcio que inicialitza el desplegable de Nivell d'Ajuda
 */
    private void initHelpLevelModel() {
        String[] items = this.gpCtrl.getHelpLevelModel();
        for (int i = 0; i < items.length; i++) {
            items[i] = this.gpCtrl.translateHelpLevel(items[i]);
        }
        this.dcbmHelpLevel = new DefaultComboBoxModel<>(items);
    }
/**
 * Funcio que inicialitza el model de la taula
 */
    private void initBoardsModel() {
        Object[][] data = null;
        this.dtmBoards = new DefaultTableModel(data, columnNames);
    }
/**
 * Funcio que modifica la taula segons els valors escollits als desplegables superiors
 */
    private void initTable() {
        this.jtBoards.addMouseListener(new JTableButtonMouseListener(jtBoards));
        this.jtBoards.setAutoCreateRowSorter(true);
        int tickHeight = this.iiTick.getIconHeight();
        int playHeight = this.iiPlay.getIconHeight();
        this.jtBoards.setRowHeight(Integer.max(tickHeight, playHeight) + 10);
        this.jtBoards.getTableHeader().setReorderingAllowed(false);
        queryTable();
        
        this.jtBoards.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = jtBoards.rowAtPoint(e.getPoint());
                if (row > -1) {
                    jtBoards.clearSelection();
                    jtBoards.setRowSelectionInterval(row, row);
                } else {
                    jtBoards.setSelectionBackground(Color.blue);
                }
            }
        });
    }
/**
 * Funcio que inicialitza la vista
 */
    private void initModels() {
        initSizeModel();
        initDifficultyModel();
        initHelpLevelModel();
        initBoardsModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlSize = new javax.swing.JLabel();
        jcbSize = new javax.swing.JComboBox();
        jlDifficulty = new javax.swing.JLabel();
        jcbDifficulty = new javax.swing.JComboBox();
        jlHelpLevel = new javax.swing.JLabel();
        jcbHelpLevel = new javax.swing.JComboBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel2 = new javax.swing.JPanel();
        jbBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtBoards = new javax.swing.JTable();

        setBackground(new Color(0,0,0,0));
        setPreferredSize(new java.awt.Dimension(752, 591));

        jPanel1.setBackground(new Color(0,0,0,0));

        jlSize.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        jlSize.setForeground(new java.awt.Color(16, 35, 235));
        jlSize.setText("Mida");

        jcbSize.setModel(dcbmSize);
        jcbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSizeActionPerformed(evt);
            }
        });

        jlDifficulty.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        jlDifficulty.setForeground(new java.awt.Color(244, 26, 4));
        jlDifficulty.setText("Dificultat");

        jcbDifficulty.setModel(dcbmDifficulty);
        jcbDifficulty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDifficultyActionPerformed(evt);
            }
        });

        jlHelpLevel.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        jlHelpLevel.setForeground(new java.awt.Color(1, 160, 4));
        jlHelpLevel.setText("Nivell d'ajuda");

        jcbHelpLevel.setModel(dcbmHelpLevel);
        jcbHelpLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbHelpLevelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jlSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(jlDifficulty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlHelpLevel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbHelpLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlSize)
                            .addComponent(jcbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlDifficulty)
                            .addComponent(jcbDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlHelpLevel)
                            .addComponent(jcbHelpLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new Color(0,0,0,0));

        jbBack.setText("ENRERE");
        jbBack.setToolTipText("");
        jbBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jbBack, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jtBoards.setModel(dtmBoards);
        jScrollPane1.setViewportView(jtBoards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSizeActionPerformed
        queryTable();
    }//GEN-LAST:event_jcbSizeActionPerformed

    private void jcbDifficultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDifficultyActionPerformed
        queryTable();
    }//GEN-LAST:event_jcbDifficultyActionPerformed

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        pCtrl.setPanel("play");
    }//GEN-LAST:event_jbBackActionPerformed

    private void jcbHelpLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbHelpLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbHelpLevelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbBack;
    private javax.swing.JComboBox jcbDifficulty;
    private javax.swing.JComboBox jcbHelpLevel;
    private javax.swing.JComboBox jcbSize;
    private javax.swing.JLabel jlDifficulty;
    private javax.swing.JLabel jlHelpLevel;
    private javax.swing.JLabel jlSize;
    private javax.swing.JTable jtBoards;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Demana al domini la informació a mostrar en la taula en funció de la mida i la dificultat.
     * @return Informació
     */
    private Object[][] getData() {
        int n = (int) this.jcbSize.getSelectedItem();
        String difficulty = this.gpCtrl.translateDifficulty((String) this.jcbDifficulty.getSelectedItem());
        ArrayList<HashMap<String, Object>> boards = gpCtrl.getBoards(n, difficulty);
        HashSet<Integer> boardsDone = pCtrl.getBoardsDone();
        Object[][] data = new Object[boards.size()][4];
        int i = 0;
        JButton jbPlay;
        for (HashMap<String, Object> board : boards) {
            int boardId = (int) board.get("id");
            String creador = (String) board.get("creator");
            if (board.get("creator") == null) {
                creador = "SISTEMA";
            }

            ImageIcon tick;
            if (!boardsDone.isEmpty() && boardsDone.contains(boardId)) {
                tick = this.iiTick;
            }else {
                tick = this.iiCross;
            }
            jbPlay = createPlayButton(boardId, this.iiPlay);

            data[i][0] = boardId;
            data[i][1] = creador;
            data[i][2] = tick;
            data[i][3] = jbPlay;
            ++i;
        }
        return data;
    }
    /**
     * Omple la taula.
     */
    private void queryTable() {
        Object[][] data = getData();
        this.dtmBoards = new DefaultTableModel(data, columnNames);
        this.jtBoards.setModel(dtmBoards);
        this.jtNoEditable();
        this.jtBoards.getColumn("Jugar").setCellRenderer(this.buttonRenderer);
        this.jtBoards.getColumn("Fet").setCellRenderer(this.ir);
    }
    
    /**
     * Creació d'un botó posant-li un event per jugar una partida amb el id del tauler.
     * @param boardId Identificador de tauler.
     * @param i Imatge del botó
     * @return JButton preparat per ser clicat i començar una partida.
     */
    private JButton createPlayButton(final int boardId, ImageIcon i) {
        JButton button = new JButton("PLAY");
        button.setIcon(i);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String helpLevel = gpCtrl.translateHelpLevel((String) jcbHelpLevel.getSelectedItem());
                gpCtrl.startGame(boardId, helpLevel);
            }
        });
        return button;
    }
    
    /**
     * Fa que la taula no sigui editable.
     */
    private void jtNoEditable() {
        for (int c = 0; c < this.jtBoards.getColumnCount(); c++) {
            Class<?> cClass = this.jtBoards.getColumnClass(c);
            this.jtBoards.setDefaultEditor(cClass, null);// remove editor
        }
    }
    
    /**
     * Classe renderitzadora dels buttons en la taula.
     */
    private static class JTableButtonRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }
    }
    
    /**
     * Classe escoltadora de l'event del botó de la taula.
     */
    private static class JTableButtonMouseListener extends MouseAdapter {

        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    ((JButton) value).doClick();
                }
            }
        }
    }
    
    /**
     * Classe per renderitzar icones en la taula.
     */
    private static class IconRenderer extends DefaultTableCellRenderer {

        public IconRenderer() {
            super();
        }

        @Override
        public void setValue(Object value) {
            this.setText("");
            if (value != null) {
                this.setIcon((Icon) value);
            }
        }
    }
}
