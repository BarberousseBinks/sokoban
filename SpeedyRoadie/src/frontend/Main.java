/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.PuzzleDataManager;
import java.io.IOException;

/**
 *
 * @author Louis Dhanis
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        PuzzleDataManager.psBoardUpdate("Ouioujiji");
        GuiFrame game = new GuiFrame();
    }
    
}
