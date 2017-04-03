/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package speedyRoadieFrontEnd.speedyRoadieBackEnd;

import java.util.ArrayList;

/**
 * Une classe Board à part entière permet de ne pas faire d'erreurs: 
 * tout est géré ici, on analyse le tableau extrait du fichier XSB,
 * on met à jour le plateau, ... (voir la doc de chaque méthode)
 * @author Louis Dhanis
 */
public class Board {
    
    private char[][] boardCode;
    protected int width;
    protected final int height;

    /**
     * Constructeur du plateau de jeu
     * @param boardCode
     */
    public Board(char[][] boardCode){
        this.width = 0;
        this.boardCode = boardCode;
        this.height = this.boardCode.length;
        for(int i = 0; i < this.height; i ++){
            if(this.width < this.boardCode[i].length){
                this.width = this.boardCode[i].length;
            }
        }
    }

    /**
     * Renvoie le code du plateau (le tableau de caractères)
     * @return
     */
    public char[][] getBoardCode(){
        return this.boardCode;
    }

    /**
     * Setter du tableau (pour l'update à chaque move dans le jeu)
     * @param boardCode
     */
    public void setBoardCode(char[][] boardCode){
        this.boardCode = boardCode;
    }

    /**
     * Affiche dans la console le tableau (pratique pour le débugging)
     */
    public void displayBoard(){
        for(int j = 0; j < this.boardCode.length; j++){
            for(int i = 0; i < this.boardCode[j].length; i++){	
                System.out.print(this.boardCode[j][i]);
            }
            System.out.print("\n");	
        }
    }

    /**
     * Renvoie l'élément (le char) à l'emplacement pos
     * @param pos
     * @return
     */
    public char getElementAt(Position pos){
         return this.boardCode[pos.getY()][pos.getX()];
    }

    /**
     * Met à jour l'élément dans le plateau
     * @param elem
     */
    public void updateElement(Element elem){
        this.boardCode[elem.getPosition().getY()][elem.getPosition().getX()] = elem.getContent();
    }
    
    /**
     * Initialise les éléments grâce au Board (à ne faire qu'une seule fois au début de la partie)
     * @param roadie
     * @param boxes
     * @param walls
     * @param goals
     */
    public void analyzeBoard(Player roadie, ArrayList<Box> boxes, ArrayList<Goal> goals, ArrayList<Wall> walls){
        for(int y = 0; y < this.boardCode.length; y++){
            for(int x = 0; x < this.boardCode[y].length; x++){	
                switch(this.boardCode[y][x]){
                    case '.':
                        goals.add(new Goal(x,y));
                        System.out.println("Nouveau Goal a"+x+" "+y);
                        break;
                    case '#':
                        walls.add(new Wall(x,y));
                        System.out.println("Nouveau Wall a"+x+" "+y);
                        break;
                    case '$':
                        boxes.add(new Box(x,y));
                        System.out.println("Nouvelle Box a"+x+" "+y);
                        break;
                    case '@':
                        roadie.setPosition(x,y);
                        System.out.println("Roadie a"+x+" "+y);
                        break;
                    case ' ':
                        break;
                }
            }
        } 
    }
    
    /**
     *
     * @param roadie
     * @param boxes
     * @param goals
     */
    public void updateBoard(Player roadie, ArrayList<Box> boxes, ArrayList<Goal> goals){
        //D'abord on va vider le plateau en ne laissant plus que les murs (seuls les murs ne sont pas déplacés ni cachés. Il se peut qu'une boîte recouvre un objectif, ou que le joueur recouvre l'objectif puis qu'il se déplace)
        for(int j = 0; j < this.boardCode.length; j++){
            for(int i = 0; i < this.boardCode[j].length; i++){
                switch(this.boardCode[j][i]){
                    case '.':
                        this.boardCode[j][i] = ' ';
                        break;
                    case '$':
                        this.boardCode[j][i] = ' ';
                        break;
                    case '@':
                        this.boardCode[j][i] = ' ';
                        break;
                    case ' ':
                        break;
                }
            }
        }
        //On update chaque élément sur le plateau de jeu
        //D'abord les objectifs (il est possible que des objectifs soient recouverts)
        for(int i = 0; i < goals.size(); i++){
            Goal temp = goals.get(i);
            updateElement(temp);
        }
        //Ensuite les boîtes (flightcases)
        for(int i = 0; i < boxes.size(); i++){
            Box temp = boxes.get(i);
            updateElement(temp);
        }
        //Enfin le joueur
        updateElement(roadie);
    }
}   

