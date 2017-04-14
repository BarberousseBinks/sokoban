/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Louis Dhanis
 */
public class GuiGame extends JPanel{
    private ArrayList elements;
    
    public GuiGame(Game level){
        
        char[][] board = level.getRepr();
        
    }
}
