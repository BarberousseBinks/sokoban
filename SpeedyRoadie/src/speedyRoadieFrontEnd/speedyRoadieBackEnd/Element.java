/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public abstract class Element {
    
    /**
     * Les éléments du jeu (éléments visibles tels que les Box, Walls, Goals et Player) sont toutes classes filles de Element
     * Un élément a donc au minimum une position (symbolisée par un objet de type Position, un couple d'entiers)
     * Cela permet de formaliser la position des objets et d'éviter de faire des erreurs de programmation.
     */
    private final Position pos;
    private char content;
    
    /**
     * Constructeur de l'élément (une classe abstraite ne peut être instanciée mais sert de moule pour les classes qui en héritent)
     * @param x
     * @param y
     */
    public Element(int x, int y){
        this.pos = new Position(x,y);
    }
    
    /**
     * Renvoie la position de l'élément ciblé
     * @return
     */
    public Position getPosition(){
        return this.pos;
    }
    
    /**
     * Permet de définir ce que contient l'élément (son caractère sur la carte)
     * Facilite la mise à jour du Board en n'utilisant que l'objet Element  et ses classes filles et non des conditions sur le type de l'objet.
     * @param x
     */
    public void setContent(char x){
        this.content = x;
    }
    
    /**
     * Renvoie ce que contient l'élément (ce qui la symbolise sur le plateau)
     * à savoir:
     * @ pour le joueur
     * $ pour les boîtes
     * # pour les murs
     * . pour l'objectif
     * @return
     */
    public char getContent(){
        return this.content;
    }
}
