/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Louis Dhanis
 */
public class GuiFile extends GuiBgButton{
    private final String path;
    
    /**
     * Renvoie le chemin relatif du fichier XSB.
     * Ce chemin relatif est stocké dans l'objet GuiFile pour le retrouver facilement dans l'ActionListener.
     * @return
     */
    public String getPath(){
        return this.path;
    }
    
    /**
     * Constructeur de l'objet GuiFile.
     * Prend en paramètre le chemin relatif du fichier XSB.
     * @param path
     */
    public GuiFile(String path){
        super();
        this.path = path;
        this.setText(path);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setSize(50, 50);
    }

}
