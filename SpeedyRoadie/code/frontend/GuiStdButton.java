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
import javax.swing.JButton;

/**
 * Classe de bouton standardisee.
 * Un GuiStdButton est un bouton esthetiquement different d'un JButton normal de par son apparence.
 * @author Louis Dhanis
 */
public class GuiStdButton extends JButton{
    
    /**
     * Constructeur du GuiStdButton
     */
    public GuiStdButton(){
        this.setFocusable(false);
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
        //Code inspire de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
        this.setBackground(Color.black);
        this.setForeground(Color.red);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    /**
     * Permet de redefinir le texte du GuiStdButton apres son instanciation
     * @param text le texte a afficher sur le GuiStdButton
     */
    public GuiStdButton(String text){
        this();
        this.setText(text);
    }
}
