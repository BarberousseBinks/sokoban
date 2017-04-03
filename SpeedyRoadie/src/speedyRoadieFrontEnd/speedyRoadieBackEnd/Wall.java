/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public class Wall extends Element{

    /**
     * Constructeur de la classe Wall.
     * les "Wall" sont les limites que le joueur ne peut pas franchir. Ni les box... Rien ne peut franchir les murs.
     * @param x
     * @param y
     */
    public Wall(int x, int y){
        super(x,y);
        this.setContent('#');
    }
}
