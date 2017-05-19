/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

import static backend.PuzzleGenerator.checkMap;
import static backend.PuzzleGenerator.generateBoard;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alfa
 */
public class PuzzleGeneratorTest {
    
    public PuzzleGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    

    @Test
    public void testCheckMap_enoughPlace(){
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#',' ',' ',' ','#'};
        char[] line3={'#',' ',' ',' ','#'};
        char[] line4={'#','#','#','#','#'};
        char[][] map={line1,line2,line3};
        assertFalse(checkMap(map, 3));
    }
    

    @Test
    public void testCheckMap_deadEnd(){
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#',' ',' ',' ','#'};
        char[] line3={'#','#',' ',' ','#'};
        char[] line4={'#','#','#','#','#'};
        char[][] map={line1,line2,line3};
        assertFalse(checkMap(map, 1));
    }

    
    @Test
    public void testCheckMap_continuous(){
        char[] line1={'#','#','#','#','#','#'};
        char[] line2={'#',' ',' ',' ',' ','#'};
        char[] line3={'#',' ',' ',' ',' ','#'};
        char[] line4={'#','#','#','#','#','#'};
        char[] line5={'#',' ',' ',' ',' ','#'};
        char[] line6={'#',' ',' ',' ',' ','#'};
        char[] line7={'#','#','#','#','#','#'};
        char[][] map={line1,line2,line3,line4,line5,line6,line7};
        assertFalse(checkMap(map, 1));
    }

    
    @Test
    public void testCheckMap_allOK(){
        char[] line1={'#','#','#','#','#','#'};
        char[] line2={'#',' ',' ',' ',' ','#'};
        char[] line3={'#',' ',' ',' ',' ','#'};
        char[] line4={'#','#',' ','#','#','#'};
        char[] line5={'#',' ',' ',' ',' ','#'};
        char[] line6={'#',' ',' ',' ',' ','#'};
        char[] line7={'#','#','#','#','#','#'};
        char[][] map={line1,line2,line3,line4,line5,line6,line7};
        assertTrue(checkMap(map, 3));
    }
    
    @Test(timeout=1000)
    public void generatedBoardPossible() throws IOException{
        Board generatedBoard=generateBoard(1,1,1);
        char[][] repr=generatedBoard.getRepr();
            for(int i=0;i<repr.length;i++){
                for(int j=0;j<repr[0].length;j++){
                    System.out.print(repr[i][j]);
                }
                System.out.println("");
            }
    }
    
    @Test
    public void testGenerateBoard() throws IOException{
        
        int nbBox=0;
        int nbGoals=0;
        int nbPlayers=0;
        
        for(int nb=0;nb<50;nb++){
            Board generatedBoard=generateBoard(1,2,2);
        
            char[][] repr=generatedBoard.getRepr();
        
            if(repr.length!=5 || repr[0].length!=8)
                fail("Wrong board size");
        
            nbBox=0;
            nbGoals=0;
            nbPlayers=0;
            for(int i=0;i<repr.length;i++){
                for(int j=0;j<repr[0].length;j++){
                    switch(repr[i][j]){
                        case '$':
                            nbBox++;
                            break;
                        case '@':
                            nbPlayers++;
                            break;
                        case '.':
                            nbGoals++;
                            break;
                        case '!':
                            nbGoals++;
                            nbBox++;
                            break;
                        case'*':
                            nbGoals++;
                            nbPlayers++;
                        default:
                            break;
                    }
                }
            }
            if(nbBox!=2 || nbGoals!=2 || nbPlayers!=1)
                fail("Wrong box/goal/player amount");
        }
        assertTrue(true);
    }
    
}
