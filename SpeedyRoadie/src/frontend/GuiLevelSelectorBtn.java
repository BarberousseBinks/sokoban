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
    public GuiLevelSelectorBtn(String text, int node){
        super(text);
        this.node = node;
    }
    
}
