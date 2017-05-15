package backend;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe représentant une partie. Bon nombre de ses informations viennent du board. Elle ajoute d'autres information tel que le nombre de pas (pourrait rajouter un timer mais non implémenté)
 * réalisés ainsi que le temps écoulé (à implémenter). Elle sert également de communication avec l'interphace graphique une fois une instance de Game
 * passé en paramètre
 * @author Alfa
 */
public class Game {
    Board board;
    int nbSteps;
            
    public Game(Board board) {
        this.board = board;
        nbSteps=0;
    }
    
    public Game(ArrayList<char[]> puzzleData){
        Board newboard= new Board();
        newboard.generateBoard(puzzleData);
        this.board=newboard;
    }
    
    public void printBoard(){
        this.board.printBoard();
    }
    
    public Game() {
        nbSteps=0;
    }
    
    /**
     * Prend le chemin d'accès au fichier (par exemple "C:\\Users\\Alfatta\\Desktop\\testread\\level2.xsb") en génère une game en fonction du contenu du fichier. 
     * Méthode susceptible de générer une excpetion sur le fichier n'est pas trouvé où qu'il ne correspond pas aux normes de fichier de data sokoban
     * @param cheminTxtLab le chemin d'accès au fichier
     * @throws IOException
     */
    public Game(String cheminTxtLab) throws IOException{
        board=new Board(cheminTxtLab);
        nbSteps=0;
    }
    
    /**
     * Déplace le joueur, si c'est possible, dans une direction donnée par le vecteur unitaire (x,y) aux connrdonnée donnée dans un repère cartésien classique.
     * @param x +1 si on avance dans le sens conventionnel des x, -1 inversément, 0 si le déplacement se fait selon l'axe des y
     * @param y +1 si on avance dans le sens conventionnel des y, -1 inversément, 0 si le déplacement se fait selon l'axe des y
     * @return
     */
    public int movePlayer(int x,int y){
        int result=board.movePlayer(x, y);
        System.out.println("moving: "+result);
        if(result!=-1){
            nbSteps=nbSteps+1;
        }
        return result;
    }
    
    /**
     * Méthode faisant avancer le joueur vers la case (mx,my) si cela est possible
     * @param mx la coordonnée en x d'une case
     * @param my la coordonnée en y d'une case
     * @return
     */
    public int movePlayerMouse(int mx, int my){  //mx et my étant les valeurs d'une case dans le repère matriciel
        int result=board.movePlayerMouse(mx, my); //(avec (0,0) en haut à gauche et (1,0) juste en dessous
        System.out.println(result);
        if(result !=-1){       
            nbSteps=nbSteps+1;
        }
        return result;
    }
    
    
    private void resetNbSteps(){
        nbSteps=0;
    }
    
    
    
    
    /**
     *
     * @return Le nombre de pas réalisé dans cette partie
     */
    public int getNbSteps(){
        return nbSteps;
    }
    
    /**
     * @return un tableau de char représentant le board. '.' objectif vide. '!' objectif avec une box. '*' objectif avec le joueur. '#' mur. ' ' vide. '@' joueur
     */
    public char[][] getRepr(){
        return board.getRepr();
    }
    
    public int getWidth(){
        return board.getWidth();
    }
    
    public int getHeight(){
        return board.getHeight();
    }
    
    // J'ai ajouté cette méthode booleenne pour pouvoir gérer plus facilement la fin de partie dans l'interface graphique
    public boolean isGameWon(){
        return this.board.isGameWon();
    }
}
