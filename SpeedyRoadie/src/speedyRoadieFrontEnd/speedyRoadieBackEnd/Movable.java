/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public interface Movable {

    /**
     * Cette interface définit les objets visés comme "Movables"
     * Grâce à cette interface, on force les objets "Movables" à implémenter la méthode move
     * @param x
     * @param y
     */
    public void move(int x, int y);
}
