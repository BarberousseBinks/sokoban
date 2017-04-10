/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Louis Dhanis
 */
public class GuiBgButton extends JButton{
    
    public GuiBgButton(String text,String path){
        this.setText(text);
//Code inspiré de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
        try{
            Image img = ImageIO.read(getClass().getResource(path));
            img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(img));
        } 
        catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du Sprite");
        }
    }
}
