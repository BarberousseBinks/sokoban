/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.PuzzleDataManager;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Louis Dhanis
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
		
		if(args.length == 0){ //S'il n'y a pas d'arguments
			GuiFrame game = new GuiFrame();
		}
		else if(args.length == 3){
			
		}	
		else{
			String argChain = "";
			for(int i = 0; i < args.length; i++){
				argChain = argChain+ " " + args[i];
			}
			System.out.println("Argument manquant: \n votre requête > java frontend/Main "+ argChain +" \n requête standardisée > java frontend/Main input.xsb input.mov output.xsb \n - input.xsb chemin vers un fichier xsb de base \n - input.mov chemin vers le fichier mov à appliquer à l'input \n - output.mov chemin vers le fichier de sortie");
		}

    }
    
}
