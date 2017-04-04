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

/**
 *
 * @author Alfa
 */
public class PuzzleDataReader {
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
