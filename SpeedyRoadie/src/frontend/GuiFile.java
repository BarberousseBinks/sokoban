/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import javax.swing.JButton;

/**
 *
 * @author Louis Dhanis
 */
public class GuiFile extends JButton{
    private final String path;
    
    public String getPath(){
        return this.path;
    }
    
    public GuiFile(String path){
        this.path = path;
        this.setText(path);
    }
}
