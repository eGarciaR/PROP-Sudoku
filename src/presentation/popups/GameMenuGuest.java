/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.popups;

import controllers.presentation.GamePresentationCtrl;
import controllers.presentation.PresentationCtrl;
import java.awt.Dimension;

/**
 *
 * @author Marc
 */
public class GameMenuGuest extends javax.swing.JPanel {
    GamePresentationCtrl gameInterfaceCtrl;
    PresentationCtrl presentationCtrl;
    /**
     * Creates new form gameMenu
     * @param presentationCtrl controlador de presentació
     */
    public GameMenuGuest(PresentationCtrl presentationCtrl) {
        this.presentationCtrl = presentationCtrl;
        this.gameInterfaceCtrl = presentationCtrl.getGamePresentationCtrl();
        initComponents();
        Dimension d = new Dimension(400, 250);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pausaLabe = new javax.swing.JLabel();
        continueButton = new javax.swing.JButton();
        saveAndCloseButton = new javax.swing.JButton();

        pausaLabe.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        pausaLabe.setText("PAUSA");

        continueButton.setText("CONTINUAR");
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continueButtonMouseClicked(evt);
            }
        });

        saveAndCloseButton.setText("SORTIR");
        saveAndCloseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveAndCloseButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(saveAndCloseButton)
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pausaLabe)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pausaLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveAndCloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void continueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continueButtonMouseClicked
        gameInterfaceCtrl.resumeGame();
        presentationCtrl.setPopUpVisible(false);
        presentationCtrl.removeGlassPane();
    }//GEN-LAST:event_continueButtonMouseClicked

    private void saveAndCloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveAndCloseButtonMouseClicked
        presentationCtrl.setPopUpPanel("exitGameGuest");
    }//GEN-LAST:event_saveAndCloseButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel pausaLabe;
    private javax.swing.JButton saveAndCloseButton;
    // End of variables declaration//GEN-END:variables
}