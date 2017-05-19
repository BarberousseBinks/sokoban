/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alfatta
 */
public class BoardTest {
    
    public BoardTest() {
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
    public void testGenerateBoard_ArrayList_Positions() {
        Board testboard = new Board();
        ArrayList<char[]> boardmap=new ArrayList<char[]>();
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#','@','$','.','#'};
        char[] line3={'#','#','#','#','#'};
        boardmap.add(line1);
        boardmap.add(line2);
        boardmap.add(line3);
        testboard.generateBoard(boardmap);
        char[][] expectedResult={line1,line2,line3};
        Assert.assertArrayEquals(expectedResult,testboard.getRepr());
    }
    
  
    @Test
    public void testGenerateBoard_ArrayList_pXpY() {
        Board testboard = new Board();
        ArrayList<char[]> boardmap=new ArrayList<char[]>();
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#','$','@','.','#'};
        char[] line3={'#','#','#','#','#'};
        boardmap.add(line1);
        boardmap.add(line2);
        boardmap.add(line3);
        testboard.generateBoard(boardmap);
        int[] expectedResult={2,1};
        int[] result={testboard.getPX(),testboard.getPY()};
        Assert.assertArrayEquals(expectedResult,result);
    }
    
  
    @Test
    public void testMovePlayer_simple() {
        Board testboard = new Board();
        ArrayList<char[]> boardmap=new ArrayList<char[]>();
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#','@',' ',' ','#'};
        char[] line3={'#','#','#','#','#'};
        boardmap.add(line1);
        boardmap.add(line2);
        boardmap.add(line3);
        testboard.generateBoard(boardmap);
        testboard.movePlayer(1, 0);
        char result=testboard.getRepr()[1][2];
        assertEquals('@', result);
    }
    

    @Test
    public void testIsGameWon() {
        Board testboard = new Board();
        ArrayList<char[]> boardmap=new ArrayList<char[]>();
        char[] line1={'#','#','#','#','#'};
        char[] line2={'#','@','$','.','#'};
        char[] line3={'#','#','#','#','#'};
        boardmap.add(line1);
        boardmap.add(line2);
        boardmap.add(line3);
        testboard.generateBoard(boardmap);
        boolean first=testboard.isGameWon();  //Doit être faux
        testboard.movePlayer(1, 0);
        boolean second=testboard.isGameWon(); //Doit être vrai

        boolean[] result={first,second};
        boolean[] expectedresult={false,true};
        Assert.assertArrayEquals(expectedresult, result);
    }

    
    
}
