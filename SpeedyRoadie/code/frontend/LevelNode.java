/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;

/**
 * Noeud de niveau
 * element de la liste chaînee representant le mode histoire du jeu
 * permet de recuperer facilement le niveau suivant
 * @author Louis Dhanis
 */
public class LevelNode {
    private final Game current;
    private LevelNode next = null;
    public String text;
    public int steps;
    public int id;
    public boolean doable;
    
    /**
     * Constructeur du LevelNode.
     * @param current la partie que represente le LevelNode
     * @param text le texte a afficher avant de lancer le niveau
     * @param steps le nombre de pas effectues (dans sauvegarde.xml)
     * @param doable true si les niveaux precedents ont ete reussis, false sinon (permet d'instancier les GuiLevelSelectorBtn)
     **/
    
    LevelNode(Game current, String text, int steps, int id, boolean doable){
        this.current = current;
        this.text = text;
        this.steps = steps;
        this.id = id;
        this.doable = doable;
    }
    
    @Override
    public String toString(){
        String descr = "LEVEL "+id+" - "+text;
        return descr;
    }
    
    /**
     * Retourne le niveau actuel
     * @return niveau le niveau actuel
     */
    public Game getLevel(){
        return this.current;
    }
    
    /**
     * Retourne le noeud suivant de la liste chaînee
     * @return noeud le noeud suivant
     */
    public LevelNode getNextNode(){
        return this.next;
    }
    
    /**
     * Definit le noeud suivant
     * @param next (le noeud suivant)
     */
    public void setNextNode(LevelNode next){
        this.next = next;
    }
    
    /**
     * Renvoie true s'il existe un noeud apres celui-ci
     * @return hasNextNode true s'il y a un node apres, false sinon
     */
    public boolean hasNextNode(){
        if(this.next == null){
            return false;
        }
        return true;
    }
}
