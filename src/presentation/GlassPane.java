package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Marc
 */
class GlassPane extends JComponent implements KeyListener{

    public GlassPane() {
        setOpaque( false );
        Color base = UIManager.getColor("inactiveCaptionBorder");
        Color background = new Color(0, 0, 0, 200);
        setBackground( background );
        
        addMouseListener( new MouseAdapter() {} );
        addMouseMotionListener( new MouseMotionAdapter() {} );
        addKeyListener( this );
        setFocusTraversalKeysEnabled(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
            g.setColor( getBackground() );
            g.fillRect(0, 0, getSize().width, getSize().height);
    }
    
    @Override
    public void keyPressed(KeyEvent e){
            e.consume();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
            e.consume();
    }
    
    public void activate() {
        setVisible( true );
        requestFocusInWindow();
    }
    
    public void deactivate() {
        setVisible( false );
    }
}
