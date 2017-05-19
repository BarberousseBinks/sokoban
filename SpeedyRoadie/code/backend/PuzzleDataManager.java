/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe comprenant les outils nécessaire pour tout ce qui concerne la lecture et l'écriture des fichier relatifs à notre Sokoban
 * @author Corentin Dachy
 */
public class PuzzleDataManager {
    
    /**
     * Renvoit une représentation du niveau sous forme de liste de char à partir d'un fichier contenant cette représentation. 
     * Susceptible de générer des exception si le fichier spécifié est introuvable ou si les données qui le compose ne sont pas des caractères
     * @param xsbPath
     * @return char[] représentation du niveau
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<char[]> readBoard(String xsbPath) throws FileNotFoundException, IOException{

            BufferedReader in = new BufferedReader(new FileReader(xsbPath));

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

            //On va compléter les vides éventuels, qui sont en dehors du plateau, par des murs plutot que des vide (pour des questions d'esthétique)
            int compt=0;
            for(int i=0;i<tab.size();i++){
                compt=0;
                while(tab.get(i)[compt]!='#'){
                    tab.get(i)[compt]='#';
                    compt++;
                }
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
     * @throws java.io.FileNotFoundException
     */
    public static void classicUpdateSave(int newMove, int lvl) throws FileNotFoundException, IOException{   
        String path = "ClassicMode/saves/"+String.valueOf(lvl)+".mov";
        updateSave(newMove,path);           
    }
    
    public static void psUpdateSave(int newMove) throws IOException{
        String path ="PermanSave/permanSave.mov";
        updateSave(newMove,path);        
    }
    
    public static void psUpdateSave(ArrayList<Integer> moveLis) throws IOException{
        for(int i=0;i<moveLis.size();i++){
            psUpdateSave(moveLis.get(i));
        }
    }
    
    public static void psSetBoard(String xsbPath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        ArrayList<char[]> BoardData= readBoard(xsbPath);
        PuzzleDataManager.psSetBoard(BoardData);
    }
    
    public static void psSetBoard(ArrayList<char[]> BoardData) throws FileNotFoundException, UnsupportedEncodingException{
        char[][] charBoardData= new char[BoardData.size()][BoardData.get(0).length];
        for(int i=0;i<BoardData.size();i++){
            for(int j=0;j<BoardData.get(i).length;j++){
                charBoardData[i][j]=BoardData.get(i)[j];
            }
        }
        psSetBoard(charBoardData);
    }
    
    public static void psSetBoard(char[][] BoardData) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("PermanSave/permanBoardSave.xsb", "UTF-8");
        for(int i=0;i<BoardData.length;i++){
            for(int j=0;j<BoardData[0].length;j++){
                writer.print(BoardData[i][j]);
            }
            writer.println("");
        }
        writer.close();
    }
    
    public static void updateSave(String path, ArrayList<Integer> moves) throws IOException{
        resetSave(path);
        for(int i=0;i<moves.size();i++){
            updateSave(moves.get(i),path);
        }
    }
    
    public static void updateSave(int newMove, String path) throws FileNotFoundException, IOException{
        if(newMove < 0 || newMove > 3){
            throw new IllegalArgumentException("newMove must take the value of 0, 1, 2 or 3");
        }
        /*
        BufferedReader in = new BufferedReader(new FileReader(path));

        int tabline;
        ArrayList<Integer> tabMoves=new ArrayList<Integer>();
        String line=in.readLine();
        while(line!=null){
            tabline=Integer.parseInt(line);
            tabMoves.add(tabline);
            line=in.readLine();
        }
        in.close();
            
           
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        for(int i=0;i<tabMoves.size();i++){
            writer.println(tabMoves.get(i));
        }
        writer.println(String.valueOf(newMove));
        writer.close();
        */
        try(FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
       // out.println("");
        out.println(Integer.toString(newMove));
        } catch (IOException e) {
            throw new IOException("Unexpected error while updating save");
        }
    }
    
    /**
     * Réinitialise la sauvegarde du niveau lvl
     * @param lvl
     * @throws FileNotFoundException
     */
    public static void classicResetSave(int lvl) throws FileNotFoundException{
        
        String path="ClassicMode/saves/"+String.valueOf(lvl)+".mov";
        resetSave(path);    
    }
    
    public static void psBoardReset() throws FileNotFoundException, UnsupportedEncodingException{
        resetSave("PermanSave/permanBoardSave.xsb");
    }
    
    public static void resetSave(String path){
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
             writer.print("");
        }
        catch(Exception e){
            System.out.println("bug");
        }
    }
    
    public static void psResetSave(){
        String path="PermanSave/permanSave.mov";
        resetSave(path);
    }
    
    
    
    /**
     * Renvoit "true" si le niveau lvl a une sauvegarde non vide, "false" si cette sauvegarde est vide (ou inexistante)
     * @param lvl
     * @return
     */
    public static boolean classicHasSave(int lvl){
        
        String path="ClassicMode/saves/"+String.valueOf(lvl)+".mov";
        return hasSave(path);
    }
    
    /**
     * Renvoit true si la permanSave est non vide. Sinon renvoit false.
     * @return boolean
     */
    public static boolean psHasSave(){  
        String path="PermanSave/permanSave.mov";
        return hasSave(path);
    }
    
    /**
     * Renvoit true sie le path (en .mov) existe et est non vide
     * @param path
     * @return
     */
    public static boolean hasSave(String path){
        try{
            BufferedReader in = new BufferedReader(new FileReader(path));
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
    
    /**
     * Renvoit l'ArrayList<char[]> associé a la permanSave (le path contenu dans permanBoardSave)
     * @return
     */
    public static Board psGetBoard() throws FileNotFoundException, IOException{
        return new Board("PermanSave/permanBoardSave.xsb");
    }
    
    public static ArrayList<Integer> classicGetMovesSaved(int lvl) throws FileNotFoundException, IOException{
        String path="ClassicMode/saves/"+String.valueOf(lvl)+".mov";
        return getMovesSaved(path);
    }
    
    public static ArrayList<Integer> psGetMovesSaved() throws IOException{
        String path="PermanSave/permanSave.mov";
        return getMovesSaved(path);
    }
    
    public static ArrayList<Integer> getMovesSaved(String path) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(path));

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
    public static Game classicGetSavedGame(int lvl) throws IOException{

        
        Game newGame=new Game("ClassicMode/maps/"+Integer.toString(lvl)+".xsb");
        ArrayList<Integer> movesPlayed=classicGetMovesSaved(lvl);
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
    
  
    public static Game psGetSavedGame() throws FileNotFoundException, UnsupportedEncodingException{ //Les exception sont pour le PsBoardReset()
        try {
            Game resultGame=getSavedGame("PermanSave/permanBoardSave.xsb","PermanSave/permanSave.mov");
            return resultGame;            
        } catch (Exception ex) {
            psBoardReset();
            psResetSave();
            return null;
        }
    }
    
    
    /**
     * Renvoit la Game avec le plateau de pathXSB et la sauvegarde de pathMOV
     * @param pathXSB
     * @param pathMOV
     * @return
     * @throws IOException
     */
    public static Game getSavedGame(String pathXSB, String pathMOV) throws IOException{ 
        Game newGame=new Game(pathXSB);
        ArrayList<Integer> movesPlayed=getMovesSaved(pathMOV);
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

    /**
     * Crée une sauvegarde de la partie game à l'emplacement path (qui doit donc se terminer par .xsb). 
     * Cette sauvegarde sera uniquement représentée dans un fichier xsb (les nombre de pas par rapport à une situation initiale sera donc perdu)
     * @param pathXSB
     * @param pathMOV
     * @return
     * @throws IOException
     */
    public static void xsbSaveGame(Game game, String path) throws IOException{
        char[][] reprGame = game.getRepr();
        String line="";
        PrintWriter writer = new PrintWriter(path);
        for(int i=0;i<reprGame.length;i++){
            for(int j=0;j<reprGame[i].length;j++){
                line=line+Character.toString(reprGame[i][j]);
            }
            writer.println(line);
            line="";
        }
        writer.close();
    }

    /**
     * Crée une sauvegarde de la partie initiée à partir de la map se trouvant en inputXSB avec la sauvegarde des mouvement se trouvant en inputMOV. 
     * Ce sauvegarde sera crée à l'emplacement outputPath (qui doit donc se terminer par .xsb). 
     * Cette sauvegarde sera uniquement représentée dans un fichier xsb (les nombre de pas par rapport à une situation initiale sera donc perdu)
     * @param pathXSB
     * @param pathMOV
     * @return
     * @throws IOException
     */
    public static void xsbSaveGame(String inputXSB, String inputMOV, String outputPath) throws IOException{
        xsbSaveGame(getSavedGame(inputXSB, inputMOV),outputPath);
    }
   
    
}
