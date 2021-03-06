/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import controllers.presentation.PresentationCtrl;
import controllers.presentation.UserPresentationCtrl;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Marc
 */
public class HomeView extends JPanel {
    
    private final PresentationCtrl pCtrl;
    private final UserPresentationCtrl uCtrl;
    /**
     * Creates new form Home
     * @param pCtrl control de presentació
     */
    public HomeView(PresentationCtrl pCtrl) {
        this.pCtrl = pCtrl;
        uCtrl = pCtrl.getUserPresentationCtrl();
        initComponents();
        String userName = this.uCtrl.getUsername();
        boolean editor = !userName.equals("");
        this.jBEditor.setEnabled(editor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBEditor = new javax.swing.JButton();
        jBEstadistics = new javax.swing.JButton();
        jbPlay = new javax.swing.JButton();

        setBackground(new Color(0,0,0,0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jBEditor.setText("EDITOR");
        jBEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditorActionPerformed(evt);
            }
        });

        jBEstadistics.setText("ESTADISTIQUES");
        jBEstadistics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBEstadisticsMouseClicked(evt);
            }
        });

        jbPlay.setText("JUGAR");
        jbPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBEstadistics, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jbPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(172, 172, 172))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addComponent(jbPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBEstadistics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditorActionPerformed
        pCtrl.setGlassPane();
        pCtrl.setPopUpPanel("chooseSize");
    }//GEN-LAST:event_jBEditorActionPerformed

    private void jBEstadisticsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBEstadisticsMouseClicked
        pCtrl.setPanel("rankingView");
    }//GEN-LAST:event_jBEstadisticsMouseClicked

    private void jbPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPlayActionPerformed
        pCtrl.setPanel("play");
    }//GEN-LAST:event_jbPlayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBEditor;
    private javax.swing.JButton jBEstadistics;
    private javax.swing.JButton jbPlay;
    // End of variables declaration//GEN-END:variables
}
