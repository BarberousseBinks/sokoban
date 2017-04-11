/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Louis Dhanis
 */
public class GuiLabel extends JLabel{
    public GuiLabel(String text){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        
        try {
            Font pressStart2P = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("sprites/font/PressStart2P.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(pressStart2P);
            pressStart2P = pressStart2P.deriveFont(10f);
            this.setFont(pressStart2P);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(GuiBgButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setText(text);
        //Code inspiré de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
        this.setForeground(new Color(0xED008C));
    }
}
