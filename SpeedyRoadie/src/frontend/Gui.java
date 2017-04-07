/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import backend.*;
import java.awt.BorderLayout;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Gui extends JFrame implements ActionListener{
    private Game level;
    private JButton randGame;
    private JButton loadLevel;
    private JButton back;
    
    public Gui() throws IOException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.level = null;
        this.setContentPane(guiWelcome());
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    private JPanel guiWelcome() throws IOException {
        SpeedyBackground panel = new SpeedyBackground("gameGraphics/welcomeBG.jpg");
        panel.setLayout(new FlowLayout());
        setResizable(false);
        this.setSize(600,400);
        randGame = new JButton("Nouvelle partie aléatoire");
        randGame.addActionListener(this);
        panel.add(randGame);
        loadLevel = new JButton("Charger un niveau");
        loadLevel.addActionListener(this);
        panel.add(loadLevel);
        return panel;
    }

    private JPanel guiGame(Game level){
        final JPanel guiGame = new JPanel();
        int height = level.getHeight()*50;
        int width = level.getWidth()*50;
        guiGame.setSize(height, width);
        guiGame.setLayout(new GridLayout(level.getHeight(), level.getWidth()));
        char[][] boardCode = level.getRepr();
        this.setSize(width, height);
        for(int i = 0; i < boardCode.length; i++){
            for(int j = 0; j < boardCode[i].length; j++){
                GuiElement elem = new GuiElement(boardCode[i][j], j ,i);
                elem.addActionListener(this);
                guiGame.add(elem);
            }
        }
        return guiGame;
    }
    
    private JPanel wowGG(){
        SpeedyBackground wowGG = new SpeedyBackground("gameGraphics/wowBG.jpg");
        
        return wowGG;
    }
    
    private JPanel levelLoader(String path){
        SpeedyBackground levelLoader = new SpeedyBackground("gameGraphics/wowBG.jpg");
        levelLoader.setLayout(new BorderLayout());
        File levelFolder = new File(path);
        File[] levelList = levelFolder.listFiles();
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout());
        
        back = new JButton("Retour");
        back.addActionListener(this);
        
        levelLoader.add(back, BorderLayout.NORTH);
        
        for (int i = 0; i < levelList.length; i++){
            if(levelList[i].isFile() && levelList[i].getName().endsWith(".xsb")){ //XSB est le format par défaut pour les fichiers de jeu
                System.out.println(levelList[i].getPath());
                GuiFile tempButton = new GuiFile(levelList[i].getPath());
                buttonContainer.add(tempButton);
                tempButton.addActionListener(this);
                levelLoader.add(buttonContainer);
                
            }
        }
        
        levelLoader.add(buttonContainer, BorderLayout.CENTER);
        
        return levelLoader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == randGame){
            try {
                System.out.println("TO BE IMPLEMENTED ASAP");
                this.level = new Game("gameMaps/level2.xsb");
                this.setContentPane(guiGame(this.level));
                this.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == loadLevel){
            this.setContentPane(levelLoader("gameMaps/")); //Dossier des niveaux par défaut
            this.setVisible(true);
        }
        else if(source == back){
            try {
                this.setContentPane(guiWelcome());
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(true);
        }
        else if(source.getClass() == GuiElement.class){
            GuiElement temp;
            temp = (GuiElement)source;
            System.out.println("Cliqué à "+temp.getPosX()+" "+temp.getPosY());
            this.level.movePlayerMouse(temp.getPosX(), temp.getPosY());
            this.level.printBoard();
            if(this.level.isGameWon()){
                this.setContentPane(wowGG());
                this.setVisible(true);
            }
            else{
                this.setContentPane(guiGame(this.level));
                this.setVisible(true);
            }
        }
        else if(source.getClass() == GuiFile.class) {
            GuiFile temp;
            temp = (GuiFile)source;
            try {
                this.level = new Game(temp.getPath());
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setContentPane(guiGame(this.level));
            this.setVisible(true);
        }
    }
    
    private void gameKeyPressed(java.awt.event.KeyEvent evt) {
        // Added to help find the ID of each 'arrow' key
        JOptionPane.showMessageDialog(null, "mainPanelKeyPressed"); 
    }
}
