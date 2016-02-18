/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.popups;

import controllers.presentation.EditorPresentationCtrl;
import controllers.presentation.PresentationCtrl;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author Marc
 */
public class EditorMenu extends javax.swing.JPanel {
    EditorPresentationCtrl epCtrl;
    PresentationCtrl presentationCtrl;
    /**
     * Creates new form gameMenu
     * @param presentationCtrl controlador de presentació
     */
    public EditorMenu(PresentationCtrl presentationCtrl) {
        this.presentationCtrl = presentationCtrl;
        this.epCtrl = presentationCtrl.getEditorPresentationCtrl();
        initComponents();
        this.jbContinue.setToolTipText("Introduïr més xifres.");
        Dimension d = new Dimension(250, 300);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        
    }
    
    
    public void resetButtons() {
        jbContinue.setEnabled(true);
        jbSave.setEnabled(true);
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
        jbContinue = new javax.swing.JButton();
        jbSolve = new javax.swing.JButton();
        jbSave = new javax.swing.JButton();
        jbPlay = new javax.swing.JButton();
        jbExit = new javax.swing.JButton();

        setRequestFocusEnabled(false);

        jlTitle.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jlTitle.setText("OPCIONS");

        jbContinue.setText("CONTINUAR");
        jbContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbContinueActionPerformed(evt);
            }
        });

        jbSolve.setText("RESOLDRE");
        jbSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSolveActionPerformed(evt);
            }
        });

        jbSave.setText("DESAR");
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jlTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSolve, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbSolve, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbContinueActionPerformed
        continueAction();
    }//GEN-LAST:event_jbContinueActionPerformed

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        saveAction();
    }//GEN-LAST:event_jbSaveActionPerformed

    private void jbPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPlayActionPerformed
        playAction();
    }//GEN-LAST:event_jbPlayActionPerformed

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        exitAction();
    }//GEN-LAST:event_jbExitActionPerformed

    private void jbSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSolveActionPerformed
        solveAction();
    }//GEN-LAST:event_jbSolveActionPerformed
    
    private void continueAction(){
        presentationCtrl.setPopUpVisible(false);
        presentationCtrl.removeGlassPane();
    }
    
    private void solveAction() {
        this.epCtrl.solve();
        presentationCtrl.setPopUpVisible(false);
        presentationCtrl.removeGlassPane();
    }
    
    private void saveAction() {
        if (!epCtrl.haveBoardId()){
            if (this.epCtrl.saveBoard()) {
                JOptionPane.showMessageDialog(this, "Desat correctament.");
                this.jbContinue.setEnabled(false);
                this.jbSave.setEnabled(false);
            }else {
                JOptionPane.showMessageDialog(this, "S'ha produït un error. No s'ha pogut desar.");
            }
        }
    }
    private void playAction() {
        presentationCtrl.setPopUpPanel("chooseHelpLevel");      
    }
    private void exitAction() {
        presentationCtrl.setPopUpVisible(false);
        presentationCtrl.removeGlassPane();
        presentationCtrl.setPanel("home");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbContinue;
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbPlay;
    private javax.swing.JButton jbSave;
    private javax.swing.JButton jbSolve;
    private javax.swing.JLabel jlTitle;
    // End of variables declaration//GEN-END:variables
}
