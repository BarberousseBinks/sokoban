package frontend;

import backend.Board;
import backend.PuzzleDataReader;
import static backend.PuzzleDataReader.getMovesSaved;
import backend.PuzzleGenerator;
import java.io.IOException;
import java.util.ArrayList;

/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */

/**
 * Classe principale du jeu. 
 * C'est ici que la partie interface graphique et la partie algorithmique vont s'assembler pour donner un programme fonctionnel
 * @author Louis Dhanis
 */
public class Main {
        
    
    /**
     * Point d'entrée du programme.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
      // Gui test = new Gui();
      /*
      Board newBoard = PuzzleGenerator.generateBoard(2,3,4);
      newBoard.printBoard();
      */
      
      //PuzzleDataReader.updateClassicSave(2, 1);
      //System.out.println(PuzzleDataReader.hasSave(3));
      ArrayList<Integer> bob = getMovesSaved(1);
    }   
    

}
