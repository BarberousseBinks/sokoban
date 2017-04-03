/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public class Box extends Element implements Movable{
    
    /**
     *
     */
    protected boolean onGoal;
    /**
     * Constructeur de la classe Box.
     * les "Box" sont les Flight Cases que le Roadie pousse dans ce jeu
     * @param x
     * @param y
     */
    public Box(int x,int y){
        super(x,y);
        this.setContent('$');
        this.onGoal = false;
    }
    
    /**
     * Permet de déplacer l'objet "Box"
     * Est implémentée via l'interface Movable
     * @param x
     * @param y
     */
    @Override
    public void move(int x, int y){
        this.getPosition().add(x, y);
    }
    
    /**
     * Chaque boîte sur un objectif aura sa variable d'instance mise à jour en "true"
     * De base, on part sur le concept que les boîtes ne sont pas sur des objectifs
     * @param onGoal
     */
    public void setOnGoal(boolean onGoal){
        this.onGoal = onGoal;
    }
}
