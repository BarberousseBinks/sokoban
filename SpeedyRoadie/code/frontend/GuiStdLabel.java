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
 * Classe de Label standardisée avec la charte graphique de la GUI
 * pour m'éviter à redéfinir le design de chaque JButton
 * @author Louis Dhanis
 */
public class GuiStdLabel extends JLabel{

    /**
     * Constructeur du GuiStdLabel
     * Prend en paramètre le texte à mettre sur le JLabel
     * @param text
     */
    public GuiStdLabel(String text){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        
        try {
            Font pressStart2P = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("misc/font/PressStart2P.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(pressStart2P);
            pressStart2P = pressStart2P.deriveFont(20f);
            this.setFont(pressStart2P);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(GuiStdButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setText(text);
        //Code inspiré de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
        this.setForeground(Color.red);
    }
}
