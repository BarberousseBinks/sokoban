/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

/**
 * Bouton de selection des niveaux du mode Classic
 * @author Louis Dhanis
 */
public class GuiLevelSelectorBtn extends GuiStdButton{
    public int node;
    /**
     * Constructeur du GuiLevelSelectorBtn
     * @param text Le texte a afficher sur le bouton
     * @param node L'entier representant le niveau (identifiant unique)
     * @param doable true si le niveau est accessible (si les niveaux precedents sont termines false sinon
     **/
    public GuiLevelSelectorBtn(String text, int node, boolean doable){
        super(text);
        this.setEnabled(doable);
        this.node = node;
    }
    
}
