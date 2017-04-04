/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Alfa
 */
public interface Layoutable {

    /**
     *
     * @return le type de l'object (goal, mur,..)
     */
    String getType();  //renvoit "goal", "player", "box" ou "emptycase"
    
    char toChar();
    
}
