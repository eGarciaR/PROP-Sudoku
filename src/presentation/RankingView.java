/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import controllers.presentation.PresentationCtrl;
import controllers.presentation.StatisticsPresentationCtrl;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ferran
 */
public class RankingView extends javax.swing.JPanel {

    private final PresentationCtrl pCtrl;
    private final StatisticsPresentationCtrl siCtrl;
    /**
     * Creates new form RankingView
     * @param pCtrl control de presentació
     */
    public RankingView(PresentationCtrl pCtrl) {
        this.pCtrl = pCtrl;
        this.siCtrl = pCtrl.getStatistiscsPresentationCtrl();
        initComponents();
        fillRanking();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTitolLabel = new javax.swing.JLabel();
        jBackButton = new javax.swing.JButton();
        jSeeRecordsButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jRankTable = new javax.swing.JTable();

        setBackground(new Color (0,0,0,0));

        jTitolLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTitolLabel.setForeground(new java.awt.Color(255, 255, 255));
        jTitolLabel.setText("RÀNQUING");

        jBackButton.setText("ENRERE");
        jBackButton.setMinimumSize(new java.awt.Dimension(103, 23));
        jBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackButtonActionPerformed(evt);
            }
        });

        jSeeRecordsButton.setText("RÈCORDS");
        jSeeRecordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSeeRecordsButtonActionPerformed(evt);
            }
        });

        jRankTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jRankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Usuari", "Puntuació"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jRankTable.getTableHeader().setResizingAllowed(false);
        jRankTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jRankTable);
        if (jRankTable.getColumnModel().getColumnCount() > 0) {
            jRankTable.getColumnModel().getColumn(0).setResizable(false);
            jRankTable.getColumnModel().getColumn(1).setResizable(false);
            jRankTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addComponent(jTitolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185)
                .addComponent(jSeeRecordsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(123, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jTitolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeeRecordsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGap(100, 100, 100)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSeeRecordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSeeRecordsButtonActionPerformed
        // TODO Go to Records
        pCtrl.setPanel("recordsView");
    }//GEN-LAST:event_jSeeRecordsButtonActionPerformed

    private void jBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackButtonActionPerformed
        // TODO go to Main Menu
        pCtrl.setPanel("home");
    }//GEN-LAST:event_jBackButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBackButton;
    private javax.swing.JTable jRankTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSeeRecordsButton;
    private javax.swing.JLabel jTitolLabel;
    // End of variables declaration//GEN-END:variables

/**
  * Funció que agafa el Ranquing actual de la base de dades, mitjançant el controlador,
  * i el posa a la taula de la vista.
  */
    private void fillRanking(){
        DefaultTableModel model = (DefaultTableModel) jRankTable.getModel();
        ArrayList<HashMap<String, Object>> ranking = siCtrl.getRanking();
        for (int i = 0; i < 15; ++i) {
            Object [] fila = new Object[3];
            fila[0] = ranking.get(i).get("position");
            fila [1] = ranking.get(i).get("user");
            fila [2] = ranking.get(i).get("score");
            model.addRow(fila);
        }
        jRankTable.setModel(model);
    };

}
