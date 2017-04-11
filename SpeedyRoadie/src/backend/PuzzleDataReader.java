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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
     * Cette méthode commencera une nouvelle sauvegarde si le fichier sauvegarde est vide
     * Exception non controllée si lvl ne réfère pas à un niveau existant
     * @param newMove
     * @param lvl
     */
    public static void updateSave(int newMove, int lvl) throws FileNotFoundException, IOException{
        if(newMove < 0 || newMove > 3){
            throw new IllegalArgumentException("newMove must take the value of 0, 1, 2 or 3");
        }
            
        BufferedReader in = new BufferedReader(new FileReader("gameMaps\\saves\\classic\\"+String.valueOf(lvl)+".mov"));

        int tabline;
        ArrayList<Integer> tabMoves=new ArrayList<Integer>();
        String line=in.readLine();
        while(line!=null){
            tabline=Integer.parseInt(line);
            tabMoves.add(tabline);
            line=in.readLine();
        }
        in.close();
            
           
        PrintWriter writer = new PrintWriter("gameMaps\\saves\\classic\\"+String.valueOf(lvl)+".mov", "UTF-8");
        for(int i=0;i<tabMoves.size();i++){
            writer.println(tabMoves.get(i));
        }
        writer.println(String.valueOf(newMove));
        writer.close();
            
            
    }
    
    /**
     * Réinitialise la sauvegarde du niveau lvl
     * @param lvl
     * @throws FileNotFoundException
     */
    public static void resetLevelSave(int lvl) throws FileNotFoundException{
        try{
            PrintWriter writer = new PrintWriter("gameMaps\\saves\\classic\\"+String.valueOf(lvl)+".mov", "UTF-8");
             writer.print("");
        }
        catch(Exception e){
            System.out.println("bug");
        }
       
    }
    
    /**
     * Renvoit "true" si le niveau lvl a une sauvegarde non vide, "false" si cette sauvegarde est vide (ou inexistante)
     * @param lvl
     * @return
     */
    public static boolean hasSave(int lvl){
        try{
            BufferedReader in = new BufferedReader(new FileReader("gameMaps\\saves\\classic\\"+String.valueOf(lvl)+".mov"));
            String line=in.readLine();
            if(line.length()!=0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
    
    
    public static ArrayList<Integer> getMovesSaved(int lvl) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader("gameMaps\\saves\\classic\\"+String.valueOf(lvl)+".mov"));

        ArrayList<Integer> movesPlayed=new ArrayList<Integer>();
        String line=in.readLine();
            while(line!=null){
                movesPlayed.add(Integer.parseInt(line));
                line=in.readLine();
            }
            in.close();
        return movesPlayed;
    }
    
    /**
     * Renvoit une instance de Game du niveau lvl avec les moves enregistrée par lvl
     * @param lvl
     * @return
     * @throws IOException
     */
    public static Game getSavedGame(int lvl) throws IOException{
        Game newGame=new Game("gameMaps\\"+String.valueOf(lvl)+".xsb");
        ArrayList<Integer> movesPlayed=getMovesSaved(lvl);
        for(int i=0;i<movesPlayed.size();i++){
            if(movesPlayed.get(i)==0){
                newGame.movePlayer(0,1);
            }
            else if(movesPlayed.get(i)==1){
                newGame.movePlayer(1,0);
            }
            else if(movesPlayed.get(i)==2){
                newGame.movePlayer(0, -1);
            }
            else if(movesPlayed.get(i)==3){
                newGame.movePlayer(-1,0);
            }
            else{
                throw new IOException("the .mov file contains other informations than 0, 1, 2 and 3");
            }
        }
        return newGame;
    }
    
    
}
