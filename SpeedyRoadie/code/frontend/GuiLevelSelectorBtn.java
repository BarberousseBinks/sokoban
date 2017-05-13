/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

/**
 *
 * @author Louis Dhanis
 */
public class GuiLevelSelectorBtn extends GuiStdButton{
    public int node;
    public GuiLevelSelectorBtn(String text, int node, boolean doable){
        super(text);
        this.setEnabled(doable);
        this.node = node;
    }
    
}
