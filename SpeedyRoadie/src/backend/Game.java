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

    public Game(Board board) {
        this.board = board;
    }
    
    public void printBoard(){
        this.board.printBoard();
    }
    
    public Game() {
    }
    
    public Game(String cheminTxtLab) throws IOException{
        board=new Board(cheminTxtLab);
    }
    
    public void movePlayer(int x,int y){
        board.movePlayer(x, y);
    }
    
    public boolean movePlayerMouse(int mx, int my){  //mx et my étant les valeurs d'une case dans le repère matriciel
        return board.movePlayerMouse(mx, my);        //(avec (0,0) en haut à gauche et (1,0) juste en dessous
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
