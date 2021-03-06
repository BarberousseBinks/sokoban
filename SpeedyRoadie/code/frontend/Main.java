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
     * Point d'entree du programme
     * @param args les arguments qui sont donnes en parametre lors du lancement via le terminal
     * @throws java.io.IOException sle fichier XSB ou le fichier MOV n'existe pas 
     */
    public static void main(String[] args) throws IOException{
		
		if(args.length == 0){ //S'il n'y a pas d'arguments
			GuiFrame game = new GuiFrame();
		}
		else if(args.length == 3){
			try{
				PuzzleDataManager.xsbSaveGame(args[0], args[1], args[2]);
			}
			catch(IOException e){
				System.out.println("Erreur, fichier .mov ou ficher .xsb incorrect");
			}
		}	
		else{
			String argChain = "";
			for(int i = 0; i < args.length; i++){
				argChain = argChain+ " " + args[i];
			}
			System.out.println("Argument manquant: \n votre requête > java frontend/Main "+ argChain +" \n requête standardisee > java frontend/Main input.xsb input.mov output.xsb \n - input.xsb chemin vers un fichier xsb de base \n - input.mov chemin vers le fichier mov a appliquer a l'input \n - output.mov chemin vers le fichier de sortie");
		}

    }
    
}
