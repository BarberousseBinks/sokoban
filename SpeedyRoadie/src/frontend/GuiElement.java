/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Louis Dhanis
 */
public class GuiElement extends JButton{
    
    public int getPosX(){
        return this.x;
    }
    public int getPosY(){
        return this.y;
    }
    
    protected int x;
    protected int y;
    public GuiElement(char content, int x, int y){
        this.x = x; 
        this.y = y;
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBackground(null);
        this.setSize(new Dimension(50,50));
        
        switch(content){
            case '@':
                //Code inspiré de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
                try{
                    Image img = ImageIO.read(getClass().getResource("sprites/roadie.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
                break;
            case '#':
                try{
                    Image img = ImageIO.read(getClass().getResource("sprites/wall.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
                break;
            case '$':
                try{
                    Image img = ImageIO.read(getClass().getResource("sprites/case.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
                break;
            case ' ':
                try{
                    Image img = ImageIO.read(getClass().getResource("sprites/ground.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
                break;
            case '.':
                try{
                    Image img = ImageIO.read(getClass().getResource("sprites/goal.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
                break;
        }
    }
}
