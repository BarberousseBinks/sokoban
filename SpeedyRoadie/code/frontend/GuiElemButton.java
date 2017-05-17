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
 * Classe representant les elements (boutons cliquables du plateau de jeu)
 * 
 * @author Louis Dhanis
 */
public class GuiElemButton extends JButton{

    /**
     * x,y sont les coordonnees du bouton.
     * Cela permet de deplacer facilement le joueur.
     * En stockant les coordonnees de l'element dans la classe du bouton, je n'ai qu'a appeler ces variables locales lors de l'utilisation des methodes programmees dans la partie backend
     */
    protected int x, y;
    private final int height = 50;
    private final int width = 50;
    private char content;
    
    /**
     * Retourne la hauteur (standardisee a 50px) du bouton
     * @return height la hauteur (standardisee a 50px) du bouton
     */
    @Override
    public int getHeight(){
        return this.height;
    }
    
    /**
     * Retourne la largeur (standardisee a 50px) du bouton
     * @return width la largeur (standardisee a 50px) du bouton
     */
    @Override
    public int getWidth(){
        return this.width;
    }
    
    /**
     * Retourne le caractere representant l'element dans un plateau XSB
     * @return content le caractere representant l'element dans le plateau .xsb
     */
    public char getContent(){
        return this.content;
    }
    /**
     * Renvoie la coordonnee en x du bouton sur la map.
     * @return xPos la position en x 
     */
    public int getPosX(){
        return this.x;
    }

    /**
     * Renvoie la coordonnee en y du bouton sur la map.
     * @return yPos la position en y
     */
    public int getPosY(){
        return this.y;
    }
    
    /**
     * Modifie le caractere representant l'element dans le plateau XSB
     * @param content le caractere representant l'element dans le plateau .xsb
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
                //Code inspire de https://stackoverflow.com/questions/12691832/how-to-put-an-image-on-a-jbutton
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
     * Prend en parametre le contenu de l'element (son identifiant char sur le board)
     * Prend en parametre les coordonnees absolues sur le board.
     * 
     * @param content le caractere que le GuiElemButton represente sur le board (# pour les murs, @ pour le personnage, $ pour les caisses, etc.)
     * @param x la position en x du bouton
     * @param y la position en y du bouton
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
