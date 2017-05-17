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
 * Classe de JPanel sur lesquels on applique une image de fond facilement.
 * M'evite de faire la modification du fond sur le JPanel pour chaque JPanel de la GUI
 * @author Louis Dhanis
 */
class GuiBgPanel extends JPanel{
    private Image background = null;
        /**
         * Constructeur du GuiBgPanel
         * @param path (chemin vers l'image a mettre en fond du JPanel)
         */
        public GuiBgPanel(String path){
        try {                
            background = ImageIO.read(new File(path));
        } 
        catch (IOException ex) {
                System.out.println("Erreur de lecture du fichier image");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background,0,0, this.getWidth(), this.getHeight(), this);
    }
}


