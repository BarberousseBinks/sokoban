/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Louis Dhanis
 */
public class GuiGamePanel extends JPanel implements ActionListener, KeyListener{
    private final ArrayList<ArrayList<GuiElement>> elementArrayList;
    private final int length;
    private final int width;
    private final Game game;
    
    public GuiGamePanel(Game game){
        this.grabFocus();
        this.addKeyListener(this);
        
        //Récupérons le plateau sous forme de tableau de caractère
        //Pour stocker chaque élément dans un ArrayList<ArrayList<GuiElement>>
        //ça sera plus facile pour modifier leur contenu par la suite
        this.game = game;
        char[][] initBoard = game.getRepr();
        //parcourons ce tableau et stockons chaque élément dans l'arrayList d'arrayList
        
        this.elementArrayList = new ArrayList<ArrayList<GuiElement>>();
        
        width = initBoard.length;
        length = initBoard[0].length;
        
        System.out.println(width+":"+length);
        
        this.setLayout(new GridLayout(width, length));
        
        for(int i = 0; i < initBoard.length; i++){
            elementArrayList.add(new ArrayList<GuiElement>());
            for(int j = 0; j < initBoard[i].length; j++){
                elementArrayList.get(i).add(new GuiElement(initBoard[i][j], j, i));
                this.add(elementArrayList.get(i).get(j));
                elementArrayList.get(i).get(j).addActionListener(this);
                System.out.println(i+":"+j);
            }
        }
        
        
        
    }
    
    public void updatePanel(){
        
        char[][] initBoard = this.game.getRepr();
        for(int i = 0; i < initBoard.length; i++){
            elementArrayList.add(new ArrayList<GuiElement>());
            for(int j = 0; j < initBoard[i].length; j++){
                if(initBoard[i][j] != elementArrayList.get(i).get(j).getContent()){
                    elementArrayList.get(i).get(j).setContent(initBoard[i][j]);
                }
            }
        }

    }   
    
    public void moveKey(int x, int y){
        this.game.movePlayer(x,y);
        this.updatePanel();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.getClass() == GuiElement.class){
            GuiElement temp = (GuiElement)source;
            this.game.movePlayerMouse(temp.getPosX(), temp.getPosY());
            this.updatePanel();
            this.setVisible(true);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case 38:
                this.moveKey(0, 1);
                break;
            case 40:
                this.moveKey(0, -1);
                break;
            case 37:
                this.moveKey(-1, 0);
                break;
            case 39:
                this.moveKey(1, 0 );
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
