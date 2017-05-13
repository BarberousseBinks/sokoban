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
 * Classe représentant les éléments (boutons cliquables du plateau de jeu)
 * 
 * @author Louis Dhanis
 */
public class GuiElemButton extends JButton{

    /**
     * x,y sont les coordonnées du bouton.
     * Cela permet de déplacer facilement le joueur.
     * En stockant les coordonnées de l'élément dans la classe du bouton, je n'ai qu'à appeler ces variables locales lors de l'utilisation des méthodes programmées dans la partie backend
     */
    protected int x, y;
    private final int height = 50;
    private final int width = 50;
    private char content;
    
    /**
     * Retourne la hauteur (standardisée à 50px) du bouton
     * @return height
     */
    @Override
    public int getHeight(){
        return this.height;
    }
    
    /**
     * Retourne la largeur (standardisée à 50px) du bouton
     * @return width
     */
    @Override
    public int getWidth(){
        return this.width;
    }
    
    /**
     * Retourne le caractère représentant l'élément dans un plateau XSB
     * @return content
     */
    public char getContent(){
        return this.content;
    }
    /**
     * Renvoie la coordonnée en x du bouton sur la map.
     * @return xPos
     */
    public int getPosX(){
        return this.x;
    }

    /**
     * Renvoie la coordonnée en y du bouton sur la map.
     * @return yPos
     */
    public int getPosY(){
        return this.y;
    }
    
    /**
     * Modifie le caractère représentant l'élément dans le plateau XSB
     * @param content
     */
    public void setContent(char content){
        this.setFocusable(false);
        this.content = content;
        switch(content){
            case '*':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/DEATHROADIE.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case '!':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/boxongoal.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case '@':
                //Code inspiré de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/roadie.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case '#':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/wall.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case '$':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/case.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case ' ':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/ground.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
            case '.':
                try{
                    Image img = ImageIO.read(getClass().getResource("misc/goal.gif"));
                    img = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                    System.out.println("Erreur lors de la lecture du Sprite");
                }
            break;
        }
    }
    
    /**
     * Constructeur de l'objet GuiElement (qui est un bouton)
     * Prend en paramètre le contenu de l'élément (son identifiant char sur le board)
     * Prend en paramètre les coordonnées absolues sur le board.
     * 
     * @param content
     * @param x
     * @param y
     */
    public GuiElemButton(char content, int x, int y){
        setContent(content);
        this.x = x; 
        this.y = y;
        this.content = content;
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBackground(null);
        this.setSize(new Dimension(50,50));

    }
}