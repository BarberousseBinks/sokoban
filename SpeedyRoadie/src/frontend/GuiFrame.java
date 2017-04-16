/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import backend.PuzzleGenerator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Louis Dhanis
 */
public class GuiFrame extends JFrame{
    
    private final GuiGamePanel mainFrame;
    
    public GuiFrame() throws IOException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame = new GuiGamePanel(new Game(PuzzleGenerator.generateBoard(3,3,4)));
        this.mainFrame.setFocusable(true);
        this.setFocusable(false);
        this.setContentPane(this.mainFrame);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }


    
}
