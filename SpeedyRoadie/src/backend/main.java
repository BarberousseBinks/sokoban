/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Alfatta
 */
public class main {
    
    public static void printTab(char[][] tab){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[0].length;j++){
                System.out.print(tab[i][j]);
            }
            System.out.println("");
        }
    }
    
    public static SPattern generateTestSPattern(){
       char[][] content=new char[3][3];
       content[0][0]='1';
       content[0][1]='2';
       content[0][2]='3';
       content[1][0]='4';
       content[1][1]='5';
       content[1][2]='6';
       content[2][0]='7';
       content[2][1]='8';
       content[2][2]='9';      
       SPattern ex=new SPattern(content,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p');
       return ex;
    }
    
    public static void main(String[] args) throws IOException {

       
        char[][] newmap= PuzzleGenerator.generateEmptyRoom(3,4,3);
        System.out.println("-----------------");
        printTab(newmap); 
        
       
       
       
      
       
       /*
       ArrayList<char[]> map = PuzzleDataReader.ReadPuzzleData("C:\\Users\\Alfatta\\Desktop\\testread\\ested.txt");
       char[][] charmap = new char[map.size()][map.get(0).length];
       for(int i=0;i<map.size();i++){
           charmap[i]=map.get(i);
       }
       printTab(charmap);
       */

    }  
}