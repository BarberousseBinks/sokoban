/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 *
 * @author Louis Dhanis
 */
public class Position implements Cloneable{
    
    private int x;
    private int y;
    
    /**
     * L'objet Position formalise la position des éléments par le biais d'un couple
     * On peut de ce fait éviter des erreurs.
     * Seuls les objets implémentant l'interface Movable peuvent se déplacer
     * pour les autres, la position est fixe, ils n'ont pas la méthode "move"
     * @param x
     * @param y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Ajouter un couple, c'est déplacer la position dans la direction du vecteur symbolisé par le couple ajouté
     * @param x
     * @param y
     */
    protected void add(int x, int y){
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    protected void subRes(int x, int y){
        this.x = x - this.x;
        this.y = y - this.y;
    }
    
    /**
     * Renvoie la position sur l'axe des abscisses (de 0 à n)
     * n étant la longueur du tableau
     * @return x 
     */
    protected int getX(){
        return this.x;
    }
    
    /**
     * Renvoie la position sur l'axe des ordonnées (de 0 à n)
     * n étant la longueur du tableau (en vertical)
     * contrairement à la logique mathématique, le 0 est en "haut" et n est en "bas"
     * @return y
     */
    protected int getY(){
        return this.y;
    }
    
    /**
     * Renvoie vrai si les deux positions sont égales. Renvoie faux sinon
     * @param pos
     * @return
     */
    protected boolean equals(Position pos){
        return this.x == pos.getX() && this.y == pos.getY();
    }
    
    /**
     * Permet de définir la position
     * @param x
     * @param y
     */
    protected void set(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    
    /**
     * Permet de cloner la position (pour ne pas écraser la position du joueur en vérifiant ce qu'il y a dans la direction de son déplacement)
     * On ajoute en effet à sa position des coordonnées via pos.add(x,y); 
     * Or add(x,y) travaille sur la variable d'instance et on ne veut pas déplacer le joueur (ou les box) avant d'avoir vérifié ce qu'il y avait dans sa direction
     * @return
     */
    @Override
    public Position clone(){
        Position temp = null;
        try{
            temp = (Position) super.clone();
        }
        catch(CloneNotSupportedException e){
            e.printStackTrace(System.err);
        }
        
        return temp;
    }
}
