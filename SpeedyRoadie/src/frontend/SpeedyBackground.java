/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Louis Dhanis
 */
class SpeedyBackground extends JPanel{
    private Image background = null;
    public SpeedyBackground(String path){
    try {                
        background = ImageIO.read(new File(path));
    } catch (IOException ex) {
        // handle exception...
    }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background,0,0, this.getWidth(), this.getHeight(), this);
    }
}


