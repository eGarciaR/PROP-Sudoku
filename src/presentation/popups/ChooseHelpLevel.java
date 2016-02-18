/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.popups;

import controllers.presentation.EditorPresentationCtrl;
import controllers.presentation.GamePresentationCtrl;
import controllers.presentation.PresentationCtrl;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc
 */
public class ChooseHelpLevel extends javax.swing.JPanel {
    EditorPresentationCtrl epCtrl;
    GamePresentationCtrl gpCtrl;
    PresentationCtrl presentationCtrl;
    
    DefaultComboBoxModel<String> dcbmHelpLevel;
    
    /**
     * Creates new form gameMenu
     * @param presentationCtrl controlador de presentació
     */
    public ChooseHelpLevel(PresentationCtrl presentationCtrl) {
        this.presentationCtrl = presentationCtrl;
        this.epCtrl = presentationCtrl.getEditorPresentationCtrl();
        this.gpCtrl = presentationCtrl.getGamePresentationCtrl();
        initHelpLevelModel();
        initComponents();
        Dimension d = new Dimension(300, 250);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
    }
    
    private void initHelpLevelModel() {
        String[] items = this.gpCtrl.getHelpLevelModel();
        for (int i = 0; i < items.length; i++) {
            items[i] = this.gpCtrl.translateHelpLevel(items[i]);
        }
        this.dcbmHelpLevel = new DefaultComboBoxModel<>(items);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitle = new javax.swing.JLabel();
        jbPlay = new javax.swing.JButton();
        jbExit = new javax.swing.JButton();
        jcbHelpLevel = new javax.swing.JComboBox();

        setRequestFocusEnabled(false);

        jlTitle.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        jlTitle.setForeground(new java.awt.Color(1, 160, 4));
        jlTitle.setText("Nivell d'ajuda");

        jbPlay.setText("JUGAR");
        jbPlay.setToolTipText("");
        jbPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPlayActionPerformed(evt);
            }
        });

        jbExit.setText("SORTIR");
        jbExit.setToolTipText("");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });

        jcbHelpLevel.setModel(dcbmHelpLevel);
        jcbHelpLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbHelpLevelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlTitle)
                .addGap(18, 18, 18)
                .addComponent(jcbHelpLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbHelpLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPlayActionPerformed
        playAction();
    }//GEN-LAST:event_jbPlayActionPerformed

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        exitAction();
    }//GEN-LAST:event_jbExitActionPerformed

    private void jcbHelpLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbHelpLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbHelpLevelActionPerformed
    
    private void playAction() {
        String helpLevel = this.gpCtrl.translateHelpLevel((String)this.jcbHelpLevel.getSelectedItem());
        this.gpCtrl.startGameFromEditor(helpLevel);
        presentationCtrl.setPopUpVisible(false);
        presentationCtrl.removeGlassPane();
    }
    private void exitAction() {
        presentationCtrl.setPopUpPanel("editorMenu");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbPlay;
    private javax.swing.JComboBox jcbHelpLevel;
    private javax.swing.JLabel jlTitle;
    // End of variables declaration//GEN-END:variables
}
