/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    private final SpeedyBackground infos;
    private final JPanel gameContainer;
    private final SpeedyBackground wrapper;
    private final GuiLabel steps;
    private final GuiBgButton exitGame;
    private final GuiBgButton saveGame;
    private final GuiFrame container;
    private final ArrayList<Integer> moveHistory;
    
    public GuiGamePanel(Game game, GuiFrame container){
        this.moveHistory = new ArrayList<Integer>();
        this.container = container;
        this.wrapper = new SpeedyBackground("gameGraphics/fade.jpg");
        this.grabFocus();
        this.addKeyListener(this);
        
        this.setLayout(new BorderLayout());
        
        this.infos = new SpeedyBackground("gameGraphics/steelTexture.jpg");
        this.gameContainer = new JPanel();
        //Récupérons le plateau sous forme de tableau de caractère
        //Pour stocker chaque élément dans un ArrayList<ArrayList<GuiElement>>
        //ça sera plus facile pour modifier leur contenu par la suite
        this.game = game;
        
        this.steps = new GuiLabel(""+this.game.getNbSteps());
        
        char[][] initBoard = game.getRepr();
        //parcourons ce tableau et stockons chaque élément dans l'arrayList d'arrayList
        
        this.elementArrayList = new ArrayList<ArrayList<GuiElement>>();
        
        width = initBoard.length;
        length = initBoard[0].length;
        this.exitGame = new GuiBgButton("Exit game...");
        this.exitGame.addActionListener(this);
        this.saveGame = new GuiBgButton("Save .mov");
        this.saveGame.addActionListener(this);
        this.gameContainer.setSize(width*50, length*50);
        this.setSize(width*50 + 200, length*50 + 200);
        this.gameContainer.setPreferredSize(new Dimension(width*50, length*50));
        this.gameContainer.setLayout(new GridLayout(width, length));
        
        for(int i = 0; i < initBoard.length; i++){
            elementArrayList.add(new ArrayList<GuiElement>());
            for(int j = 0; j < initBoard[i].length; j++){
                
                elementArrayList.get(i).add(new GuiElement(initBoard[i][j], j, i));
                this.gameContainer.add(elementArrayList.get(i).get(j));
                elementArrayList.get(i).get(j).addActionListener(this);
                
            }
        }
        
        this.wrapper.add(this.gameContainer);
        this.infos.add(this.steps);
        this.infos.add(this.exitGame);
        this.infos.add(this.saveGame);
        this.add(this.infos, BorderLayout.NORTH);     
        this.add(this.wrapper, BorderLayout.CENTER);
        
        
    }
    
    public void saveState(){
        // Code inspiré de https://stackoverflow.com/questions/356671/jfilechooser-showsavedialog-how-to-set-suggested-file-name
        // et de https://stackoverflow.com/questions/14589386/how-to-save-file-using-jfilechooser-in-java
        String sb = moveHistory();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home"));
        chooser.setSelectedFile(new File("sauvegarde.mov"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try(FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                fw.write(sb);
            } catch (IOException ex) {
                Logger.getLogger(GuiGamePanel.class.getName()).log(Level.SEVERE, null, ex);
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
        
        this.steps.setText(""+this.game.getNbSteps());
        
        if(this.gameWon()){
            JPanel banane = new JPanel();
            for (Object move : this.moveHistory) {
                banane.add(new JLabel(""+move));
            }
            this.container.setContentPane(banane);
        }
    
    }   
    
    public String moveHistory(){
        String str = "";
        for (Object move : this.moveHistory) {
            str = str + move;
        }
        return str;
    }
    
    public boolean gameWon(){
        return this.game.isGameWon();
    }
    
    public void moveKey(int x, int y){
        int move = this.game.movePlayer(x,y);
        if(move >= 0){
            this.moveHistory.add(move);
        }
        this.updatePanel();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source.getClass() == GuiElement.class){
            GuiElement temp = (GuiElement)source;
            int move = this.game.movePlayerMouse(temp.getPosX(), temp.getPosY());
            if(move >= 0){
                this.moveHistory.add(move);
            }
            this.updatePanel();
            this.setVisible(true);
        }
        else if(source == this.exitGame){
            System.exit(0);
        }
        else if(source == this.saveGame){
            saveState();
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
