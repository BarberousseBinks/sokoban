/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Louis Dhanis
 */
public final class Game {
    
    private ArrayList<Box> boxes;
    private ArrayList<Wall> walls;
    private ArrayList<Goal> goals;
    private Player speedy;
    private Board level;
    //protected

    public Game(int width){
        this.boxes = new ArrayList();
        this.walls = new ArrayList();
        this.goals = new ArrayList();
        this.speedy = new Player();
        this.level = new Board(BoardGenerator.gen(width));
        this.level.analyzeBoard(this.speedy, this.boxes, this.goals, this.walls);
    }
    /**
     * La partie est symbolisée par un objet Game.
     * Cet objet contient toutes les informations et méthodes permettant le bon déroulement du jeu
     * Pour instancier une partie, il faut donner en paramètre à son constructeur le chemin vers le fichier XSB permettant de l'initialiser.
     * Renvoie des exceptions (fichier pas trouvé ou autres)
     * @param path le chemin vers le fichier
     * @throws FileNotFoundException
     */
    public Game(String path) throws FileNotFoundException{
        this.boxes = new ArrayList();
        this.walls = new ArrayList();
        this.goals = new ArrayList();
        this.speedy = new Player();
        this.level = new Board(loadMap(path));
        this.level.analyzeBoard(this.speedy, this.boxes, this.goals, this.walls);
    }
    
    /**
     * La partie est symbolisée par un objet Game.
     * Une partie est composée d'une map (ici le Board nommé level)
     * De cette map on extrait les éléments composant la map (boîtes, murs, objectifs et joueur) et leurs positions
     * @param board un tableau de char représentant la map
     */
    public Game(char[][] board){
        this.boxes = new ArrayList();
        this.walls = new ArrayList();
        this.goals = new ArrayList();
        this.speedy = new Player();
        this.level = new Board(board);
        this.level.analyzeBoard(this.speedy, this.boxes, this.goals, this.walls);
    }
    /**
    public boolean isWon(){
        
    }
    
    public void updateBoxes(){
        for(int i = 0; i < this.boxes.size(); i++){
            Box tempBox = this.boxes.get(i);
            for(int j = 0; j < this.goals.size(); j++){
                Goal tempGoal = this.goals.get(j);
            }
        }
    }
    **/
    public int getWidth(){
        return this.level.width;
    }
    
    public int getHeight(){
        return this.level.height;
    }
    
    
    /**
     * Méthode générant un tableau de caractères en 2D à partir d'un fichier texte.
     * @param path
     * @return Le tableau de caractères du board
     * @throws FileNotFoundException
     */
    
    public static char[][] loadMap(String path) throws FileNotFoundException{
        File file = new File(path);
        int x;
        int y;
        // Explorons le fichier une première fois pour en avoir les dimensions (pour créer le tableau de caractères)
        try (Scanner scanner = new Scanner(file)) {
            x = 0;
            y = 0;
            while(scanner.hasNextLine()){
                String str = scanner.nextLine();
                y++;
                if(x < str.length()){
                    x = str.length();
                }
            }
        }

        // Nous avons maintenant sa taille, parcourons le fichier une seconde fois pour en extraire les informations.
        char[][] mapCode;
        try (Scanner mapScanner = new Scanner(file)) {
            // Voilà le tableau créé aux dimensions du fichier
            mapCode = new char[y][x];
            int j = 0;
            while(mapScanner.hasNextLine()){
                String str = mapScanner.nextLine();
                for(int i = 0; i < str.length(); i++){
                    mapCode[j][i] = str.charAt(i);
                }
                j++;
            }
        }
        // On peut renvoyer le code du tableau de caractères
        return mapCode;
    }
    
    /**
     * Renvoie le plateau (l'objet)
     * @return
     */
    public Board getBoard(){
        return this.level;
    }
    
    public char[][] getBoardCode(){
        return this.level.getBoardCode();
    }
    
    public void moveTo(int x, int y) throws IllegalMove{
        Position temp = this.speedy.getPosition().clone();
        temp.subRes(x, y);
        movePlayer(temp.getX(), temp.getY());
    }
    
    /**
     * Méthode de déplacement du joueur au sein de la partie.
     * Vérifie si les mouvements du joueur sont valides par rapport aux rêgles du jeu.
     * 
     * @param x la composante en "x" à essayer d'ajouter
     * @param y la composante en "y" à essayer d'ajouter
     * @throws IllegalMove dans le cas où le joueur pousse une caisse suivie d'un mur ou d'une autre caisse
     */
    
    public void movePlayer(int x, int y) throws IllegalMove{
        if(abs(x)+abs(y) != 1){
            throw new IllegalMove();
        }
        //On récupère la position du joueur à laquelle on additionne les coordonnées
        //Le tout dans un nouvel objet pour ne pas écraser sa position actuelle avec une position potentiellement fausse
        Position temp1 = this.speedy.getPosition().clone();
        temp1.add(x, y);
        Position temp2 = temp1.clone();
        temp2.add(x, y);
        //temp1 contient maintenant la future position du joueur et temp 2 ce qu'il y a à une distance 1*(x,y) de sa future position
        
        //Vérifions ce qu'il y a dans sa direction (à une distance 1*(x,y) de lui et à une distance 2*(x,y) de lui):
        char dist1 = ' ';
        char dist2 = ' ';
        
        Box firstBox = null;
        //On regarde pour les Boxes
        for(int i = 0; i < this.boxes.size(); i++){
            Box tempBox = this.boxes.get(i);
            if(temp1.equals(tempBox.getPosition())){
                dist1 = tempBox.getContent();
                firstBox = tempBox;
            }
            else if(temp2.equals(tempBox.getPosition())){
                dist2 = tempBox.getContent();
            }
        }
        
        //On regarde pour les Walls
        for(int i = 0; i < this.walls.size(); i++){
            Wall tempWall = this.walls.get(i);
            if(temp1.equals(tempWall.getPosition())){
                dist1 = tempWall.getContent();
                throw new IllegalMove();
            }
            else if(temp2.equals(tempWall.getPosition())){
                dist2 = tempWall.getContent();
            }
        }
        
        //Maintenant, dist1 et dist2 ont ce qu'il y a dans la position du joueur. il ne reste plus qu'à vérifier si ce sont des murs ou de l'air (un espace)
        if(dist1 == ' '){
            this.speedy.move(x,y);
        }
        else if(dist1 == '$' && dist2 == ' '){
            this.speedy.move(x, y);
            if(firstBox != null){
                firstBox.move(x, y);
            }
        }
        else{
            throw new IllegalMove();
        }
        
        this.level.updateBoard(this.speedy, this.boxes, this.goals);
    }
    
    /**
     * Met à jour le plateau au sein de la partie.
     * Appelle la méthode updateBoard.
     */
    public void updateGameBoard(){
        this.level.updateBoard(this.speedy, this.boxes, this.goals);
    }
    
    /**
     * Affiche la position du joueur dans l'invite de commandes.
     */
    public void printPlayerPos(){
        System.out.println(this.speedy.getPosition().getX()+" "+this.speedy.getPosition().getY());
    }
}
