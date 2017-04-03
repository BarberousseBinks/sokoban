/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public class Player extends Element implements Movable{

    /**
     * Constructeur de la classe Player.
     * Le joueur ne sera instancié qu'une seule fois. De base, on lui donne une position initiale de 0,0 tant qu'on n'a pas encore défini sa position effective via le plateau de jeu
     * 
     */
    public Player(){
        super(0,0);
        this.setContent('@');
    }
    
    /**
     * Permet de déplacer l'objet "Player"
     * Est implémentée via l'interface Movable
     * @param x
     * @param y
     */
    @Override
    public void move(int x, int y){
        this.getPosition().add(x, y);
    }
    
    /**
     * Player est la seule classe à pouvoir définir littéralement sa position via une méthode et sans "déplacement" via "move" (donc en étant movable)
     * @param x
     * @param y
     */
    public void setPosition(int x, int y){
        this.getPosition().set(x, y);
    }
}
