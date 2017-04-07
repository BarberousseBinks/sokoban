package backend;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 * Cette classe contient tout ce qu'il faut pour générer un board (avec l'aide de PuzzleDataReader si c'est à partir d'un chemin) et pour y déplacer le joueur
 * @author Alfa
 */
public class Board {
    private ArrayList<ArrayList<Layoutable>> tab;
    private ArrayList<Objectif> objectives;
    private int pX;   //les coordonnées du joueur sur le plateau
    private int pY;   //(0,0) étant le point en haut à gauche 

    public Board() {
        tab = new ArrayList<ArrayList<Layoutable>>();
        objectives=new ArrayList<Objectif>();
    }
    
    
    public Board(String cheminTxtLab) throws FileNotFoundException, IOException{  //construire le jeu à partir d'un fichier .txt (constructeur)
        generateBoard(PuzzleDataReader.ReadPuzzleData(cheminTxtLab));
    }
    
    /**
     * Prend le chemin d'accès au fichier (par exemple "C:\\Users\\Alfatta\\Desktop\\testread\\level2.xsb") en génère le board en fonction du contenu du fichier. 
     * Méthode susceptible de générer une excpetion sur le fichier n'est pas trouvé où qu'il ne correspond pas aux normes de fichier de data sokoban
     * @param cheminTxtLab le chemin d'accès au fichier
     */
    public void generateBoard(String cheminTxtLab) throws FileNtFoundException,IOException{  //construire le jeu à partir d'un fichier .txt
        generateBoard(PuzzleDataReader.ReadPuzzleData(cheminTxtLab));
    }
    
    /**
     * Génère le board à l'aide d'un tableau de char le représentant
     * @param puzzleData le tableau de char représentant le puzzle
     */
    public void generateBoard(ArrayList<char[]> puzzleData){     //construire le jeu à partir d'une ArrayList de tableau de char [#, ,i,b,o]
        objectives=new ArrayList<Objectif>();
        tab=new ArrayList<ArrayList<Layoutable>>(); 
        
        for (int i=0;i<puzzleData.size();i++) {
            ArrayList<Layoutable> line=new ArrayList<Layoutable>();
            for(int j=0;j<puzzleData.get(i).length;j++){
                line.add(generateLayoutPiece(puzzleData.get(i)[j],j,i));
            }
            tab.add(line);
        }
        
    }
    
    /**
     * Génère un objet Layoutable en fonction du paramètre d. 
     * Si il s'agit du joueur, met automatiquement ses coordonnées à jour.
     * Si il s'agit d'un goal, l'ajoute automatiquement à la liste des objectifs.
     * @param d le charactère représentant le Layoutable que l'on génère
     * @param x la coordonnée x du point que l'on génère
     * @param y la coordonnée y du point que l'on génère
     * @return
     */
    public Layoutable generateLayoutPiece(char d,int x,int y){          //Non static car si la pièce générée est un objectif, cette méthode
        if(d=='#'){                                                     //l'ajoutera automatiquement à la liste des objectifs. 
            return new Wall();                                          //           si la pièce génétée est le joueur, cette méthode
        }                                                               //cette méthode ajoutera automatiquement ses coordonnées à pX,pY
        else if (d==' '){                                               
            return new Emptycase();
        }
        else if (d=='@'){           //Il faudrait implémenter une erreur qui rejeterait un .txt avec 
            pX=x;                   //moins d'un joueur ou aucun joueur, et ceux pour lesquels il y a moins
            pY=y;                   //de caisses que d'objectifs
            return new Player();
        }
        else if (d=='$'){
            return new ClassicBox();
        }
        else if (d=='.'){
            Goal newgoal=new Goal();
            objectives.add(newgoal);
            return newgoal;
        }
        else{
            throw new IllegalArgumentException("only [#, ,@,$,.] are accepted in the .txt representing a puzzle");
        }
    }
    
    public void printBoard(){      //Cette méthode est uniquement là à des fin de débugage. Elle ne sert pas dans le programme
         for(int i=0;i<tab.size();i++){
            for(int j=0;j<tab.get(i).size();j++){
                System.out.print(tab.get(i).get(j));
            }
            System.out.println("");        
        }  
         System.out.println("Les coordonées du joueur sont:("+pX+"."+pY+")");
    }
    
    /**
     * Méthode faisant avancer le joueur vers la case (mx,my) si cela est possible
     * @param mx la coordonnée en x d'une case
     * @param my la coordonnée en y d'une case
     * @return
     */
    public boolean movePlayerMouse(int mx, int my){  //(mx,my) étant les coordonées de la case qui a été clickée
        int relativeX=mx-pX;       //déplacement entre (convMX,convMY) et (pX,pY)
        int relativeY=my-pY;
        relativeY=-relativeY; //car on est en valeur matricielle et movePlayer prend comme valeur dans un repère conventionnel
        if(abs(relativeX)+abs(relativeY) != 1){
            return false;       //Le test est fait même si il est refait dans movePlayer. Ici cela n'engendre pas
        }                       //d'exception, c'est juste un mouvement impossible. Dans movePlayer(int x,int y), cela
        else{                   //engendre une exception faisant planter le programme car il y a un problème quelque part (si ça arrive)
            return movePlayer(relativeX,relativeY);
        }
    }
    
    /**
     * Déplace le joueur, si c'est possible, dans une direction donnée par le vecteur unitaire (x,y) aux connrdonnée donnée dans un repère cartésien classique.
     * @param x +1 si on avance dans le sens conventionnel des x, -1 inversément, 0 si le déplacement se fait selon l'axe des y
     * @param y +1 si on avance dans le sens conventionnel des y, -1 inversément, 0 si le déplacement se fait selon l'axe des y
     * @return
     */
    public boolean movePlayer(int x, int y){  //Fait avancer le joueur du vecteur (x,y)
        if(abs(x)+abs(y) != 1){
            throw new IllegalArgumentException("movePlayer(int x,int y) only takes [(0,1),(0,-1),(1,0)or(-1,0)] as parameters (x,y)"); 
        }     //Il n'est pas nécessaire de vérifier si on tombe dans un cas outOfRange car le joueur ne sera jamais en position limite car le plateau est bordé de murs
              //Par exemple il ne sera jamais nécessaire de vérifier que si x=-1 alors pX ne doit pas valoir 0 pour éviter l'outOfRange car pX ne vaudra jamais 0)
        
        y=-y; //Pour faire avancer de (x,y) dans un repère conventiel, il faut faire avancer de (x,-y) dans le repère déroulant vers le bas du board ((0,0) est en haut à gauche)
        
        if("player".equals(tab.get(pY).get(pX).getType())){ //Le joueur ne se trouve pas sur un objectif
            return movePlayerFromEmpty(x,y);
        }
        else{
            return movePlayerFromGoal(x,y);
        }
    }
    
    private boolean movePlayerFromEmpty(int x,int y){
                                  
        int targetPositionX=pX+x; 
        int targetPositionY=pY+y; 
        
        //Cas où on veut aller sur du vide
        if("emptycase".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            Player temp=(Player) tab.get(pY).get(pX);     //Cette manipulation d'échange n'est pas obligatoir, j'aurais pu créer un nouveaux Player et Emptycase
            tab.get(pY).set(pX,tab.get(targetPositionY).get(targetPositionX));  //Echanger me semblait plus propre que de créer de nouvelles instances et oublier les anciennes
            pX=targetPositionX;                                      //Cette remarque est également valable pour les autres cas
            pY=targetPositionY;
            tab.get(pY).set(pX, temp);
            return true;
        }
        
        //Cas où on veut aller sur une boite
        if("cbox".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            int secondTargetPositionX=targetPositionX+x;
            int secondTargetPositionY=targetPositionY+y;
            if("emptycase".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){  //Ok après c'est du vide
                Player temp=(Player) tab.get(pY).get(pX);
                tab.get(pY).set(pX,tab.get(secondTargetPositionY).get(secondTargetPositionX));
                tab.get(secondTargetPositionY).set(secondTargetPositionX,tab.get(targetPositionY).get(targetPositionX));
                pX=targetPositionX;                                         
                pY=targetPositionY;                                           
                tab.get(pY).set(pX, temp);  
                return true;
            }
            if("goal".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){ //Après c'est un goal
                Goal targetGoal=(Goal) tab.get(secondTargetPositionY).get(secondTargetPositionX);
                if(targetGoal.isEmpty()){       //Ce goal est vide
                    Player temp=(Player) tab.get(pY).get(pX);
                    tab.get(pY).set(pX,targetGoal.getContent());
                    targetGoal.setContent(tab.get(targetPositionY).get(targetPositionX));
                    pX=targetPositionX;                                         
                    pY=targetPositionY;
                    tab.get(pY).set(pX,temp);
                    isGameWon();
                    return true;
                }
                else{                           //Il y a une boite sur ce goal
                    return false;
                }
            }
            else{
                return false;
            }
        }       
        
        //Cas où on arrive sur un goal
        if("goal".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            Goal targetGoal=(Goal) tab.get(targetPositionY).get(targetPositionX);
            if(targetGoal.isEmpty()){     //Sous cas où on va sur un goal vide
                Player temp=(Player) tab.get(pY).get(pX);
                tab.get(pY).set(pX, targetGoal.getContent());
                pX=targetPositionX;            
                pY=targetPositionY;
                targetGoal.setPlayerOn(temp);
                return true;
            }
            if(targetGoal.cboxOn()){    //Sous cas où on va sur un goal sur lequel se trouve déjà une box
                int secondTargetPositionX=targetPositionX+x;
                int secondTargetPositionY=targetPositionY+y;
                if("emptycase".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){  //Ok après c'est du vide
                    Player temp=(Player) tab.get(pY).get(pX);
                    tab.get(pY).set(pX,tab.get(secondTargetPositionY).get(secondTargetPositionX));
                    tab.get(secondTargetPositionY).set(secondTargetPositionX,targetGoal.getContent());
                    pX=targetPositionX;                                         
                    pY=targetPositionY;
                    targetGoal.setContent(temp);
                    return true;
                }
                if("goal".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){       //Après c'est un second goal
                    Goal secondTargetGoal=(Goal) tab.get(secondTargetPositionY).get(secondTargetPositionX);
                    if(secondTargetGoal.isEmpty()){  //Ce second goal est vide
                        Player temp=(Player) tab.get(pY).get(pX);
                        tab.get(pY).set(pX, secondTargetGoal.getContent());
                        secondTargetGoal.setContent(targetGoal.getContent());
                        pX=targetPositionX;                                         
                        pY=targetPositionY;
                        targetGoal.setContent(temp);
                        return true;
                    }
                    else{                            //Il y a une caisse sur ce second goal
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        return false;    
    }
        
    private boolean movePlayerFromGoal(int x,int y){
        int targetPositionX=pX+x; 
        int targetPositionY=pY+y; 
        Goal departGoal=(Goal) tab.get(pY).get(pX);
        
        //Cas où on veut aller sur du vide
        if("emptycase".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            Player temp=(Player) departGoal.getContent();     
            departGoal.setContent(tab.get(targetPositionY).get(targetPositionX));  
            pX=targetPositionX;                                      
            pY=targetPositionY;
            tab.get(pY).set(pX, temp);
            return true;
        }
        
        //Cas où on veut aller sur une boite
        if("cbox".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            int secondTargetPositionX=targetPositionX+x;
            int secondTargetPositionY=targetPositionY+y;
            if("emptycase".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){  //Ok après c'est du vide
                Player temp=(Player) departGoal.getContent();
                departGoal.setContent(tab.get(secondTargetPositionY).get(secondTargetPositionX));
                tab.get(secondTargetPositionY).set(secondTargetPositionX,tab.get(targetPositionY).get(targetPositionX));
                pX=targetPositionX;                                         
                pY=targetPositionY;                                           
                tab.get(pY).set(pX, temp);  
                return true;
            }
            if("goal".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){    //Après c'est un goal
                Goal targetGoal=(Goal) tab.get(secondTargetPositionY).get(secondTargetPositionX);
                if(targetGoal.isEmpty()){          //Ce goal est vide
                    Player temp=(Player) departGoal.getContent();
                    departGoal.setContent(targetGoal.getContent());
                    targetGoal.setContent(tab.get(targetPositionY).get(targetPositionX));
                    pX=targetPositionX;                                         
                    pY=targetPositionY;
                    tab.get(pY).set(pX,temp);
                    isGameWon();
                    return true;
                }
                else{                                //Il y a une boite sur ce goal
                    return false;
                }
            }
            
            
            
            
            
            
            else{
                return false;
            }
        }
        
        //Cas où on arrive sur un goal
        if("goal".equals(tab.get(targetPositionY).get(targetPositionX).getType())){
            Goal targetGoal=(Goal) tab.get(targetPositionY).get(targetPositionX);
            if(targetGoal.isEmpty()){     //Sous cas où on va sur un goal vide
                Player temp=(Player) departGoal.getContent();
                departGoal.setContent(targetGoal.getContent());
                pX=targetPositionX;            
                pY=targetPositionY;
                targetGoal.setPlayerOn(temp);
                return true;
            }
            if(targetGoal.cboxOn()){    //Sous cas où on va sur un goal sur lequel se trouve déjà une box
                int secondTargetPositionX=targetPositionX+x;
                int secondTargetPositionY=targetPositionY+y;
                if("emptycase".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){  //Ok après c'est du vide
                    Player temp=(Player) departGoal.getContent();
                    departGoal.setContent(tab.get(secondTargetPositionY).get(secondTargetPositionX));
                    tab.get(secondTargetPositionY).set(secondTargetPositionX,targetGoal.getContent());
                    pX=targetPositionX;                                         
                    pY=targetPositionY;
                    targetGoal.setContent(temp);
                    return true;
                }
                if("goal".equals(tab.get(secondTargetPositionY).get(secondTargetPositionX).getType())){       //Après c'est encore un goal
                    Goal secondTargetGoal=(Goal) tab.get(secondTargetPositionY).get(secondTargetPositionX);
                    if(secondTargetGoal.isEmpty()){  //Ce second goal est vide
                        Player temp=(Player) departGoal.getContent();
                        departGoal.setContent(secondTargetGoal.getContent());
                        secondTargetGoal.setContent(targetGoal.getContent());
                        pX=targetPositionX;                                         
                        pY=targetPositionY;
                        targetGoal.setContent(temp);
                        return true;
                    }
                    else{                            //Il y a une caisse sur ce second goal
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        
        return false;
    }
        
    private void winMethod(){
        System.out.println("GG EASY");
    }
    
    /**
     * Vérifie si la partie est gagnée, et lance la procédure de réussite si c'est le cas
     */
    protected boolean isGameWon(){// J'ai ajouté un if / else à la fin renvoyant un booléen pour gérer isGameWon dans Game() pour pouvoir gérer la fin de niveau dans l'interface graphique
        boolean check=true;
        
        for(int i=0;i<objectives.size();i++){
            if(! objectives.get(i).isCompleted()){
                check=false;
                break;
            }
        }
        
        if(check){
            winMethod();
            return true;
        }     
        return false;
    }
    
    /**
     * @return un tableau de char représentant le board. '.' objectif vide. '!' objectif avec une box. '*' objectif avec le joueur. '#' mur. ' ' vide. '@' joueur
     */
    public char[][] getRepr(){
        if(tab.size()==0){
            return new char[0][0];
        }
        char[][] repr=new char[tab.size()][tab.get(0).size()];
        for(int i=0;i<tab.size();i++){
            for(int j=0;j<tab.get(i).size();j++){
                repr[i][j]=tab.get(i).get(j).toChar();
            }      
        }  
        return repr;
    }
    
    /**
     * @return la largeur du board
     */
    public int getWidth(){
        if(tab.size()==0){
            return 0;
        }
        return tab.get(0).size();
    }
    
    /**
     * @return la hauteur du board
     */
    public int getHeight(){
        return tab.size();
    }
    
}
