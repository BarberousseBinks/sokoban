/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alfa
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    public static void a (char[][] tabb){
        for(int i=0;i<tabb.length;i++){
            for(int j=0;j<tabb[i].length;j++){
                //System.out.print("|"+i+"."+j);
                System.out.print(tabb[i][j]);
            }
            System.out.println("");
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {

            try {
            Board myBoard = new Board("C:\\Users\\Alfa\\Desktop\\testread\\ested.txt");
            myBoard.printBoard();
            myBoard.movePlayer(1,0);
            System.out.println("-----");

            a(myBoard.getRepr());

            } catch (IOException ex) {
            System.out.println("ERROR ERROR ERROR");
            }
     
    }
    
}
