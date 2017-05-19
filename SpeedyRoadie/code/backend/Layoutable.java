/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 * Interface informant sur le type d'un objet contenu dans le Board. Voir rapport partie "Implémentation d'une partie" pour plus de détails
 * @author Corentin Dachy
 */
public interface Layoutable {

    /**
     *
     * @return le type de l'object (goal, mur,..)
     */
    String getType();  //renvoit "goal", "player", "cbox" ou "emptycase"
    
    char toChar();
    
}
