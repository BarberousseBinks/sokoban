/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;

/**
 * Classe représentant le mode Histoire du jeu
 * Est une liste simplement chaînée d'éléments LevelNode dont l'objet StoryMode est le curseur
 * @see LevelNode
 * @author Louis Dhanis
 */
public class StoryMode {
    
    private LevelNode node;
    /**
     * Constructeur sans paramètre du Story Mode
     */
    public StoryMode(){
        this.node = null;
    }
    
    /**
     * Constructeur prenant le premier niveau en paramètre
     * @param level (premier niveau courrant)
     */
    StoryMode(Game level){
        this();
        this.node = new LevelNode(level);
    }
    
    /**
     * Renvoie le niveau actuel
     * @return level (le niveau actuel)
     */
    public Game getLevel(){
        return this.node.getLevel();
    }
    
    /**
     * Place le curseur de la liste chaînée sur le niveau suivant
     */
    public void goNextLevel(){
        LevelNode temp = this.node;
        this.node = temp.getNextNode();
    }
    
    /**
     * Ajoute un niveau au mode histoire (utile pour l'initialisation)
     * @param level (le niveau à ajouter)
     */
    public void addLevel(Game level){
        LevelNode parser = this.node;
        while(parser.isNextNode()){
            LevelNode temp = parser;
            parser = temp.getNextNode();
        }
        parser.setNextNode(new LevelNode(level));
    }
    
}
