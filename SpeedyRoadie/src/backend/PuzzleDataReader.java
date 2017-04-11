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
            int longmax=0;
            while(line!=null){
                tabline=line.toCharArray();
                if(tabline.length>longmax){
                    longmax=tabline.length;   
                }
                tab.add(tabline);
                line=in.readLine();
            }
            in.close();
            
            //Reste a rendre toute ligne de la même taille pour éviter l'out of range
            char[] tempLengthAdapted;
            
            for(int i=0;i<tab.size();i++){
                if(tab.get(i).length < longmax){
                    tempLengthAdapted=new char[longmax];
                    for(int j=0;j<longmax;j++){   
                        if(j<tab.get(i).length)   //On récupère les infos de la ligne
                            tempLengthAdapted[j]=tab.get(i)[j];
                        else                      //Puis on complète de blancs
                            tempLengthAdapted[j]='#';
                    }
                 tab.set(i, tempLengthAdapted);
                }
            }
            System.out.println("-----"+longmax+"------");
            for(int i=0;i<tab.size();i++){
                System.out.println(tab.get(i).length);
            }
            
            return tab;
        }
    
    /**
     * Met à jour la sauvegarde du niveau lvl (du mode classique) en ajoutant la move newMove
     * Signification des valeurs de newMove: 0 représente le haut, 1 la droite, 2 le bas, 3 la gauche
     * @param newMove
     * @param lvl
     */
    public static void updateClassicSave(int newMove, int lvl){
        if(newMove < 0 || newMove > 3){
            throw new IllegalArgumentException("newMove must take the value of 0, 1, 2 or 3");
        }
        
    }
    
    
}
