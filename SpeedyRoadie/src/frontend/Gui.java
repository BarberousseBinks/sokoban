/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import backend.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Gui extends JFrame implements ActionListener{
    private Game level;
    private JButton randGame;
    public Gui() throws IOException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.level = null;
        this.setContentPane(guiWelcome());
        this.setVisible(true);
    }

    private JPanel guiWelcome() throws IOException {
        SpeedyWelcome panel = new SpeedyWelcome();
        panel.setLayout(new FlowLayout());
        randGame = new JButton("Nouvelle partie aléatoire");
        randGame.addActionListener(this);
        panel.add(randGame);
        JButton loadLevel = new JButton("Charger un niveau");
        loadLevel.addActionListener(this);
        panel.add(loadLevel);
        return panel;
    }

    private JPanel guiGame(Game level){
        JPanel guiGame = new JPanel();
        int height = level.getHeight()*50;
        int width = level.getWidth()*50;
        guiGame.setSize(height, width);
        guiGame.setLayout(new GridLayout(level.getHeight(), level.getWidth()));
        char[][] boardCode = level.getRepr();
        for(int i = 0; i < boardCode.length; i++){
            for(int j = 0; j < boardCode[i].length; j++){
                GuiElement elem = new GuiElement(boardCode[j][i], i, j);
                elem.addActionListener(this);
                guiGame.add(elem);
            }
        }
        return guiGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == randGame){
            try {
                this.level = new Game("gameMaps/level1.xsb");
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setContentPane(guiGame(level));
            this.setVisible(true);
        }
        else if(source.getClass() == GuiElement.class){
            GuiElement temp;
            temp = (GuiElement)source;
            this.level.movePlayerMouse(temp.getPosX(), temp.getPosY());
        }
        this.setContentPane(guiGame(level));
        this.setVisible(true);
    }
}
