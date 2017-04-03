/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

import java.util.ArrayList;
import java.util.Random;

/**
 * Générateur de plateaux de jeu.
 * En me basant sur https://larc.unt.edu/techreports/LARC-2011-01.pdf j'en suis venu à la conclusion que le plus facile pour générer des cartes de Sokoban c'était:
 * D'abord etablir des patterns prédéfinis qui, lorsqu'on les assemble, peuvent éventuellement donner une carte fonctionnelle
 * Ensuite vérifier avec une IA si la map est valide et faisable.
 * Enfin stocker une solution possible (le chemin que l'IA a emprunté pour atteindre la solution.
 * Ici, on donne un entier en paramètre pour définir la longueur et la largeur de la map.
 * Si on donne un entier 'l', la map fera (l*3 + 2) de longueur et de largeur car les patterns font 3*3 et il faut ajouter des murs tout au tour (ça serait fâcheux que le joueur puisse s'en aller de la map)
 * @author Louis Dhanis
 */
public class BoardGenerator {
    
    /**
     * Génère un plateau de Sokoban.
     * @param gridSize nombre de patterns de 3*3 sur une longueur. La longueur totale fera gridSize*3 + 2. Identique pour la largeur
     * @return un plateau de jeu Sokoban
     */
    public static char[][] gen(int gridSize){
        
        System.out.println("Génération d'une map");
        
        int mapLength = 3*gridSize + 2;
        char[][] newMap = new char[mapLength][mapLength];
        
        //On place les murs autour de la map
        for(int i = 0; i < mapLength; i++){
            for(int j = 0; j < mapLength; j++){
                if(i == 0 || j == 0 || i == (mapLength-1) || j == (mapLength-1)){
                    newMap[i][j] = '#';
                }
                else{
                    newMap[i][j] = ' '; 
                }
            }
        }
        
        //Ajoutons des patterns
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                //On s'est positionnés dans la grille
                char[][] tempPattern = getRandPattern();
                for(int y = 0; y < 3; y++){
                    for(int x = 0; x < 3; x++){
                        newMap[i * 3 + y + 1][j * 3 + x + 1] = tempPattern[y][x];
                    }
                }
            }
        }
        
        
        Random rnd = new Random();
        
        boolean done;
        //On place autant de boîtes et d'objectifs qu'il y a de patterns
        //La boîte ne peut pas être placée contre un mur extérieur (pour éviter que l'objectif se trouve aussi sur un mur mais de l'autre côté)
        for(int i = 0; i < gridSize; i++){
            done = false;
            while(!done){
                int randX = rnd.nextInt(mapLength - 4) + 2;
                int randY = rnd.nextInt(mapLength - 4) + 2;

                if(newMap[randY][randX] == ' '){
                    newMap[randY][randX] = '$';
                    done = true;
                }
            }
            done = false;
            while(!done){
                int randX = rnd.nextInt(mapLength - 2) + 1;
                int randY = rnd.nextInt(mapLength - 2) + 1;

                if(newMap[randY][randX] == ' '){
                    newMap[randY][randX] = '.';
                    done = true;
                }
            }
        }
        //On place aléatoirement le joueur
        //J'utilise une boucle while car il est possible que la position aléatoire soit déjà occupée par un élément du jeu.
        done = false;
        while(!done){
            int randX = rnd.nextInt(mapLength - 2) + 1;
            int randY = rnd.nextInt(mapLength - 2) + 1;
            
            if(newMap[randY][randX] == ' '){
                newMap[randY][randX] = '@';
                done = true;
                System.out.println("Position du joueur trouvée");
            }
            
            System.out.println("Position impossible trouvée");
        }
        
        
        return newMap;
                
    }
    //Les patterns servant à la génération de maps sont renvoyés aléatoirement via getRandPattern

    /**
     * Renvoie un pattern aléatoire pour la génération de map
     * Chaque pattern est en 3*3
     * @return un pattern aléatoire
     */
        public static char[][] getRandPattern(){
       char[][][] pattern = {
           {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}},
           {{' ',' ',' '},{' ','#','#'},{' ',' ',' '}},           
           {{' ',' ',' '},{' ','#',' '},{' ',' ',' '}}
       };
       
       Random rnd = new Random();
       int randID = rnd.nextInt(pattern.length);
       return pattern[randID]; 
    }
}
