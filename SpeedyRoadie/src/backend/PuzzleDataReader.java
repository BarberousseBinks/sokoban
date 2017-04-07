/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe comprenant les outils nécessaire pour tout ce qui concerne la lecture et l'écriture des fichier relatifs à notre Sokoban
 * @author Alfa
 */
public class PuzzleDataReader {
    
    /**
     * Renvoit une représentation du niveau sous forme de liste de char à partir d'un fichier contenant cette représentation. 
     * Susceptible de générer des exception si le fichier spécifié est introuvable ou si les données qui le compose ne sont pas des caractères
     * @param cheminTxtLab
     * @return char[] représentation du niveau
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<char[]> ReadPuzzleData(String cheminTxtLab) throws FileNotFoundException, IOException{

            BufferedReader in = new BufferedReader(new FileReader(cheminTxtLab));

            ArrayList<char[]> tab = new ArrayList<char[]>();
            char[] tabline;

            String line=in.readLine();

            while(line!=null){
                tabline=line.toCharArray();
                tab.add(tabline);
                line=in.readLine();
            }
            in.close();
            return tab;
        }
}
