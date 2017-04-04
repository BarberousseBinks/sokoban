/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

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

    public Game() {
    }
    
    public Game(String cheminTxtLab) throws IOException{
        board=new Board(cheminTxtLab);
    }
    
    public void movePlayer(int x,int y){
        board.movePlayer(x, y);
    }
    
    public char[][] getRepr(){
        return board.getRepr();
    }
}
