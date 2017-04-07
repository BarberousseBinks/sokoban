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
        this.path = path;
        this.setText(path);
    }
}
