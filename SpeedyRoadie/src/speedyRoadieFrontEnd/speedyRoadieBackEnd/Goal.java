/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public class Goal extends Element{

    /**
     * Constructeur de la classe Goal
     * L'objectif dans ce jeu n'est pas forcément l'élément le plus compliqué à comprendre
     * il ne bouge pas.
     * En gros, cet élément ne sert qu'à vérifier si les Box sont onGoal() == true
     * @see setOnGoal()
     * @param x
     * @param y
     */
    public Goal(int x, int y){
        super(x,y);
        this.setContent('.');
    }
}
