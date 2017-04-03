package speedyRoadieFrontEnd.speedyRoadieBackEnd;

/**
 * Exception lancée lorsque le joueur essaye un déplacement invalide selon les rêgles du Sokoban.
 * @author John Doe
 */
public class IllegalMove extends Exception{

    /**
     * Va permettre d'arrêter la tentative de déplacement si il y a un déplacement impossible
     * @return
     */
    @Override
	public String toString(){
		return "Deplacement impossible detecte";
	}
}
