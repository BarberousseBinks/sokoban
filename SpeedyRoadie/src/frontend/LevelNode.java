/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;

/**
 * Noeud de niveau
 * élément de la liste chaînée représentant le mode histoire du jeu
 * permet de récupérer facilement le niveau suivant
 * @author Louis Dhanis
 */
public class LevelNode {
    private final Game current;
    private LevelNode next = null;
    
    LevelNode(Game current){
        this.current = current;
    }
    
    /**
     * Retourne le niveau actuel
     * @return niveau actuel
     */
    public Game getLevel(){
        return this.current;
    }
    
    /**
     * Retourne le noeud suivant de la liste chaînée
     * @return noeud suivant
     */
    public LevelNode getNextNode(){
        return this.next;
    }
    
    /**
     * Définit le noeud suivant
     * @param next (le noeud suivant)
     */
    public void setNextNode(LevelNode next){
        this.next = next;
    }
    
    /**
     * Renvoie true s'il existe un noeud après celui-ci
     * @return isNextNode
     */
    public boolean isNextNode(){
        if(this.next == null){
            return false;
        }
        return true;
    }
}
