/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;

/**
 *
 * @author Alfa
 */
public class Game {
    Board board;
    int nbSteps;
            
    public Game(Board board) {
        this.board = board;
        nbSteps=0;
    }
    
    public void printBoard(){
        this.board.printBoard();
    }
    
    public Game() {
        nbSteps=0;
    }
    
    public Game(String cheminTxtLab) throws IOException{
        board=new Board(cheminTxtLab);
        nbSteps=0;
    }
    
    public boolean movePlayer(int x,int y){
        if(board.movePlayer(x, y)){
            nbSteps=nbSteps+1;
            return true;
        }
        return false;
    }
    
    public boolean movePlayerMouse(int mx, int my){  //mx et my étant les valeurs d'une case dans le repère matriciel
        if(board.movePlayerMouse(mx, my)){        //(avec (0,0) en haut à gauche et (1,0) juste en dessous
            nbSteps=nbSteps+1;
            return true;
        }
        return false;
    }
    
    private void resetNbSteps(){
        nbSteps=0;
    }
    
    public int getNbSteps(){
        return nbSteps;
    }
    
    public char[][] getRepr(){
        return board.getRepr();
    }
    
    public int getWidth(){
        return board.getWidth();
    }
    
    public int getHeight(){
        return board.getHeight();
    }
}
