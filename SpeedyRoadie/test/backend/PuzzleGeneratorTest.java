/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

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
    
    
    /**
     * Test of generateBoard method, of class PuzzleGenerator.
     * @throws java.lang.Exception
     */
    @Test
    public void generateBoardSmokeTest() throws Exception {
        System.out.println("generateBoardSmokeTest");
        int height = 2;
        int width = 2;
        int nbBox = 2;
        boolean check=true;
        Board result = PuzzleGenerator.generateBoard(height, width, nbBox);
        int nb_box;
        int nb_goal;
        char[][] repr= result.getRepr();
        for(int i=0;i<repr.length;i++){
            for(int j=0;j<repr[0].length;j++){
                
            }
        }
        assertTrue(check);
    }
    
    
    
}
