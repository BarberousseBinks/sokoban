/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

/**
 * Cette classe est comme ClassicBox mais comporte une variable suplémentaire, lastMove. 
 * Elle sert uniquement dans la construction d'un niveau avec PuzzleGenerator
 * @author Corentin Dachy
 */
public class ConstructionClassicBox extends ClassicBox {
    protected int lastMove; //0 = Pas encore déplace, 1 = Haut, 2 = Droite, 3 = Bas, 4 = Gauche

    public ConstructionClassicBox() {
        lastMove=0;
    }
    
    public int getLastMove(){
        return lastMove;
    }
    
    /**
     * Met à jour lastMove avec la newMove
     * Retourne true si cette nouvelle move est différent, false si elle est identique à la précédente (lastMove)
     * @param newMove
     * @return
     */
    public boolean setLastMove(int newMove){
        if(lastMove==newMove){
            return false;
        }
        else{
            lastMove=newMove;
            return true;
        }
        
    }
    
    
    
}
