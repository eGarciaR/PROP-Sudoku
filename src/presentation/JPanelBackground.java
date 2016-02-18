/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ferran
 */
public class JPanelBackground extends JPanel{
    
    private final Image bimg;
    /**
     * Creadora per defecte del JPanel que farà de fons.  
     * @param s ruta a la imatge que volem posar de fons
     */
    public JPanelBackground(String s){
        this.bimg = new ImageIcon(getClass().getResource(s)).getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bimg,0,0,this);
    }
    
    
    
}
