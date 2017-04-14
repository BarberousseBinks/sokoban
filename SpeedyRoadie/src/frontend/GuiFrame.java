/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import backend.PuzzleGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Louis Dhanis
 */
public class GuiFrame extends JFrame implements KeyListener{
    
    private final GuiGamePanel mainFrame;
    
    public GuiFrame() throws IOException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) { 
                requestFocus();	
                }
	});
        this.mainFrame = new GuiGamePanel(new Game(PuzzleGenerator.generateBoard(3,3,2)));
        this.setContentPane(this.mainFrame);
        pack();
        this.setVisible(true);
        
    }


    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case 38:
                this.mainFrame.moveKey(0,1);
                break;
            case 40:
                this.mainFrame.moveKey(0,-1);
                break;
            case 39:
                this.mainFrame.moveKey(1,0);
                break;
            case 37:
                this.mainFrame.moveKey(-1,0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("KEYRELEASED");
    }
    
}
