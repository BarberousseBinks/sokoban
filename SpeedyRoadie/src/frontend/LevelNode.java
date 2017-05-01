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
    public String text;
    public int steps;
    public int id;
    
    LevelNode(Game current, String text, int steps, int id){
        this.current = current;
        this.text = text;
        this.steps = steps;
        this.id = id;
    }
    
    @Override
    public String toString(){
        String descr = "LEVEL "+id+" - "+text;
        return descr;
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
     * @return hasNextNode
     */
    public boolean hasNextNode(){
        if(this.next == null){
            return false;
        }
        return true;
    }
}
