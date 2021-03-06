package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import static java.lang.Math.abs;

/**
 * Classe comportant tout ce qui est nécessaire à la création de puzzle aléatoire pour notre Sokoban
 * @author Corentin Dachy
 */
public class PuzzleGenerator {
    
    //L'ensemble de tout les Pattern
    public static int nbMovePerTry = 2000;    //Le nombre de mouvements qui sera réalisé aléatoirement lorsqu'on déplacera le joueur à reculons
    public static int nbTryLayout = 50;
    public static final char[][] SPdata1={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    public static final SPattern SPattern1=new SPattern(SPdata1,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata2={{'#',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    public static final SPattern SPattern2=new SPattern(SPdata2,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata3={{'#','#',' '},{' ',' ',' '},{' ',' ',' '}};
    public static final SPattern SPattern3=new SPattern(SPdata3,'L','L',' ',' ',' ','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata4={{'#','#','#'},{' ',' ',' '},{' ',' ',' '}};
    public static final SPattern SPattern4=new SPattern(SPdata4,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata5={{'#','#','#'},{'#',' ',' '},{'#',' ',' '}};
    public static final SPattern SPattern5=new SPattern(SPdata5,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata6={{'#',' ',' '},{' ',' ',' '},{' ',' ','#'}};
    public static final SPattern SPattern6=new SPattern(SPdata6,'L',' ','L','L','L','L','L','L','L','L','L','L','L',' ','L','L');
    public static final char[][] SPdata7={{'#',' ',' '},{' ',' ',' '},{'#',' ',' '}};
    public static final SPattern SPattern7=new SPattern(SPdata7,'L','L','L','L','L','L','L','L','L','L','L','L','L',' ','L','L');
    public static final char[][] SPdata8={{'#',' ',' '},{' ',' ',' '},{'#',' ','#'}};
    public static final SPattern SPattern8=new SPattern(SPdata8,'L',' ','L','L','L','L','L','L','L',' ','L','L','L',' ','L','L');
    public static final char[][] SPdata9={{'#',' ','#'},{' ',' ',' '},{'#',' ','#'}};
    public static final SPattern SPattern9=new SPattern(SPdata9,'L',' ','L','L','L',' ','L','L','L',' ','L','L','L',' ','L','L');
    public static final char[][] SPdata10={{'#',' ','#'},{'#',' ',' '},{'#','#','#'}};
    public static final SPattern SPattern10=new SPattern(SPdata10,'L',' ','L','L','L',' ','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata11={{'#','#','#'},{' ',' ',' '},{'#','#','#'}};
    public static final SPattern SPattern11=new SPattern(SPdata11,'L','L','L','L','L',' ','L','L','L','L','L','L','L',' ','L','L');
    public static final char[][] SPdata12={{' ',' ',' '},{' ','#',' '},{' ',' ',' '}};
    public static final SPattern SPattern12=new SPattern(SPdata12,'L','L','L','L',' ',' ','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata13={{'#','#','#'},{'#','#','#'},{'#','#','#'}};
    public static final SPattern SPattern13=new SPattern(SPdata13,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    public static final char[][] SPdata14={{'#','#','#'},{'#',' ',' '},{' ',' ',' '}};
    public static final SPattern SPattern14=new SPattern(SPdata14,'L','L','L','L','L','L','L','L','L','L',' ',' ',' ','L','L','L');
    public static final char[][] SPdata15={{' ',' ',' '},{'#',' ','#'},{' ',' ',' '}};
    public static final SPattern SPattern15=new SPattern(SPdata15,' ','L',' ','L','L','L','L','L',' ','L',' ','L','L','L','L','L');    
    public static final char[][] SPdata16={{'#','#','#'},{'#','#','#'},{' ',' ',' '}};
    public static final SPattern SPattern16=new SPattern(SPdata16,'L','L','L','L','L','L','L','L',' ',' ',' ','L','L','L','L','L');
    public static final char[][] SPdata17={{'#','#','#'},{' ','#',' '},{' ',' ',' '}};
    public static final SPattern SPattern17=new SPattern(SPdata17,'L','L','L','L','L',' ','L','L','L',' ',' ','L','L',' ','L','L');
    
    public static final SPattern[] SPatternTab={SPattern1,SPattern2,SPattern3,SPattern4,SPattern5,SPattern6,SPattern7,SPattern8,SPattern9,SPattern10,SPattern11,SPattern12,SPattern13,SPattern14,SPattern15,SPattern16,SPattern17,};
    
    /**
     * Renvoit un Pattern compatible avec la position ciblée. Et donc avec toutes les conditions passées en paramètre.
     * c1 - c16 représente les conditions sur les cases en bordues. ca1-ca9 représente les éventuelles conditions sur les cases déjà dans la zone 3*3 du pattern
     *
     * @return Un pattern adapté aux conditions
     */
    private static SPattern getSPattern(char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14,char c15,char c16,char ca1, char ca2, char ca3, char ca4, char ca5, char ca6,char ca7,char ca8, char ca9 ){
        Random rnd = new Random();
   //     int a=1;
        while(true){
     //       System.out.println(a);
     //       a=a+1;
            int r = rnd.nextInt(17);
            SPatternTab[r].rotateRandom(4);
            SPatternTab[r].mirrorRandom();
            if(SPatternTab[r].isCompatible(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,ca1,ca2,ca3,ca4,ca5,ca6,ca7,ca8,ca9)){
                return SPatternTab[r];
            }
        }
    }
    
    /**
     * Crée une map de taille [height*3 +2][width*3 +2]. Cette map est entourée de murs (#)
     * La map sera générée de part la stratégie des patterns et respectera les conditions de la méthode checkMap(char[][] map, int nbBox)
     * @param height
     * @param width
     * @param nbBox le nombre de boites que l'on souhaite mettre sur cette map (afin d'éviter d'en générer une trop petite)
     * @return
     */
    private static char[][] generateEmptyRoom(int height, int width, int nbBox){ //heigth et widith le nombre de bloc 3*3 à mettre
        int mapheight=(3*height)+2;
        int mapwidth=(3*width)+2;
        
        char[][] map = new char[mapheight][mapwidth];
        do{  
           //On génère une map vide entourée de murs
            for(int i=0;i<(3*height)+2;i++){
                for(int j=0;j<(3*width)+2;j++){
                    if(i == 0 || j == 0 || i == (mapheight-1) || j == (mapwidth-1)){
                        map[i][j] = '#';
                    }
                    else{
                        map[i][j] = 'L'; 
                    }
                }
            }
            //On remplit la map avec des patterns
            SPattern tempSPattern;
            char c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0,c10=0,c11=0,c12=0,c13=0,c14=0,c15=0,c16=0;
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    c1=map[i*3][(j*3)+1];c2=map[i*3][(j*3)+2];c3=map[i*3][(j*3)+3];     //On avance de gauche à droite de ligne en ligne. Le cases  
                    c15=map[(i*3)+1][j*3];c14=map[(i*3)+2][j*3];c13=map[(i*3)+3][j*3]; //du haut seront donc toujours déjà implémentées, ainsi que les cases à gauche
                    
                    if(j==(width-1)){  //On est à l'extrémité droite, à coté de murs
                        c5='#';c6='#';c7='#';
                    }   
                    else{              //Ce qui est à droite n'est pas encore implémenté
                        c5='L';c6='L';c7='L';
                    }
                
                    if(i==(height-1)){ //On en tout en bas, a coté des murs inférieurs
                        c9='#';c10='#';c11='#';
                    }
                    else{              //Ce qui est en bas n'est pas encore implémenté
                        c9='L';c10='L';c11='L';
                    }
                
                    c16=map[i*3][j*3];        //toujours implémenté
                    c4=map[i*3][((j+1)*3)+1]; //toujours implémenté
                    if( (i == (height-1)) || (j == (width-1)) ){      //Si on touche la droite ou le bas
                        c8='#';
                    }
                    else{                                             //Sinon le coin inferieur droit n'est pas implémenté
                        c8='L';
                    }
                    if( (i == 0) || (j == (width-1)) ){  // Si on touche la gauche ou le bas
                        c12='#';
                    }
                    else{                                // Sinon le coin inferieur gauche n'est pas implémenté
                        c12='L';
                    }
                
                
                    tempSPattern=getSPattern(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,map[(i*3)+1][(j*3)+1],map[(i*3)+1][(j*3)+2],map[(i*3)+1][(j*3)+3],map[(i*3)+2][(j*3)+1],map[(i*3)+2][(j*3)+2],map[(i*3)+2][(j*3)+3],map[(i*3)+3][(j*3)+1],map[(i*3)+3][(j*3)+2],map[(i*3)+3][(j*3)+3]); //On récupère le pattern à ajouter à la map
                
                    for(int y=0;y<3;y++){  //On ajoute le pattern à la map
                        for(int x=0;x<3;x++){
                            map[(3*i)+1+y][(3*j)+1+x]=tempSPattern.getPatternContent()[y][x];
                        }
                    }
                
                    if(j != 0){   //La c12 est d'application pour la suite
                        map[((i+1)*3)+1][j*3]=tempSPattern.getC12();
                    }
                
                    if(i != (height-1)){  //Les c11, c10 et c9 sont d'application pour la suite
                        map[((i+1)*3)+1][(j*3)+1]=tempSPattern.getC11();
                        map[((i+1)*3)+1][(j*3)+2]=tempSPattern.getC10();
                        map[((i+1)*3)+1][(j*3)+3]=tempSPattern.getC9();
                    }
                
                    if((i != (height-1) && (j != (width-1)))){  //La c8 est d'application pour la suite
                        map[((i+1)*3)+1][((j+1)*3)+1]=tempSPattern.getC8();
                    }
                
                    if(j != (width-1)){   //Les c7, c6 et c5 sont d'application pour la suite
                        map[(i*3)+1][((j+1)*3)+1]=tempSPattern.getC5();
                        map[(i*3)+2][((j+1)*3)+1]=tempSPattern.getC6();
                        map[(i*3)+3][((j+1)*3)+1]=tempSPattern.getC7();
                    }
                
                
                }
            }
            for(int k=0;k<map[0].length;k++){
                map[map.length-1][k]='#';           //Je ne sais pas pourquoi, mais deux murs de la rangée de mur du fond deviennent des N à la fin de l'algorithme. Avant de trouver d'où vient le problème je le corrige en dur ici
            }
        }while(!checkMap(map,nbBox));
       
        
        //a vérifier: 1Tout les vide se touchent (la map n'est pas divisée en deux). 2(Caisse*2+2) vides mini  3Rip si cul de sac
        
        return map;
        
        
    }
    
    /**
     * Vérifie que la map passée en paramètre respecte ces trois condition:
     * -1: Il y a suffisament de place libre sur la map. A savoir (2*nbBox)+2. A savoir une place pour chaque box, une pour chaque goal, une pour le joueur et minimum une place libre
     * -2: La map n'est pas divisée en deux parties (ou plus). Tout les espaces libres (sans mur) sont raccordés ensemble
     * -3: Il n'y a pas de cul de sac. Car ils sont sans grand interet dans le cadre de Sokoban
     * La fonction renvoit true si ces trois conditions sont respectées et false si au moins une des trois n'est pas respectée
     * @param map
     * @param nbBox
     * @return boolean
     */
    public static boolean checkMap(char[][] map, int nbBox){
        ArrayList<int[]> listEmpty = new ArrayList<int[]>(); //Liste pour stocker les coordonnées des cases vides de la map (taille: [nb cases vides][2])
        int[] tempCoord;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]==' '){
                    tempCoord=new int[2];
                    tempCoord[0]=i;
                    tempCoord[1]=j;
                    listEmpty.add(tempCoord);                    
                }
            }
        }
        
        
        //Test suffisance de place libre
        if(listEmpty.size() < ((nbBox*2)+2)){  
            return false;
        }
        
        //Test absence de cul de sac
        int tempNbAdjacentWalls;
        int currentX;
        int currentY;
        for(int i=0;i<listEmpty.size();i++){
            tempNbAdjacentWalls=0;
            currentY = listEmpty.get(i)[0];
            currentX = listEmpty.get(i)[1];
            if(map[currentY-1][currentX] == '#') tempNbAdjacentWalls=tempNbAdjacentWalls+1;
            if(map[currentY+1][currentX] == '#') tempNbAdjacentWalls=tempNbAdjacentWalls+1;
            if(map[currentY][currentX-1] == '#') tempNbAdjacentWalls=tempNbAdjacentWalls+1;
            if(map[currentY][currentX+1] == '#') tempNbAdjacentWalls=tempNbAdjacentWalls+1;            
            if(tempNbAdjacentWalls>2){
                return false;
            }
        }
       
        //Test connectivité de tout les espaces libres
        ArrayList<int[]> connectedEmpty = new ArrayList<int[]>(); //Liste des coordonnées des cases vides connectées les unes aux autres
        connectedEmpty.add(listEmpty.get(0));
        boolean added;
        do{
            added=false;
            for(int a=1;a<listEmpty.size();a++){
                for(int b=0;b<connectedEmpty.size();b++){
                    if( ((abs((listEmpty.get(a)[0])-connectedEmpty.get(b)[0]) + abs((listEmpty.get(a)[1]-connectedEmpty.get(b)[1]))) == 1) && (!connectedEmpty.contains(listEmpty.get(a)))){
                        connectedEmpty.add(listEmpty.get(a));
                        added=true;
                    }
                }
            }
        }while(added);
        if(connectedEmpty.size() == listEmpty.size()){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    private static Object[] generateLayout(char[][] map, int nbBox) throws IOException{
        //Initialisation du nouveau Board
        ArrayList<char[]> arraymap = new ArrayList<char[]>();
        for(int i=0;i<map.length;i++){
            arraymap.add(map[i]);
        }
        Board newBoard= new Board();
        newBoard.generateBoard(arraymap);
        
        ArrayList<int[]> listEmpty = new ArrayList<int[]>(); //Liste pour stocker les coordonnées des cases vides de la map (taille: [nb cases vides][2])
        int[] tempCoord;                                     //Même code que pour checkMap. Je pourrais faire une fonction ou même garder la liste calculée pour checkMap
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]==' '){
                    tempCoord=new int[2];
                    tempCoord[0]=i;
                    tempCoord[1]=j;
                    listEmpty.add(tempCoord);                    
                }
            }
        }
        
        Layoutable lastBoxDragged=null;  //Je garde le pointeur en Layoutable et non en ClassicBox pour être sur de ne pas avoir de problemes avec obj1.equals(obj2) (pour plus tard, avec les reverseMovePlayer)
        //Maintenant que l'on a la liste des cases vides, on va y ajouter des objectifs avec des caisses dessus
        Random rnd = new Random();
        Goal generatedNewGoal;
        int[] selectedPlace;
        for(int b=0;b<nbBox;b++){
            selectedPlace= listEmpty.get(rnd.nextInt(listEmpty.size()));
            listEmpty.remove(selectedPlace);    //Cette case ne va bientot plus être empty
            generatedNewGoal=new Goal(new ClassicBox());
            lastBoxDragged=generatedNewGoal.getContent(); //Juste question d'avoir une boite dans cette variable
            newBoard.setLayout(selectedPlace[1],selectedPlace[0],generatedNewGoal);
        }
        
        
        
        //Et on va désormais ajouter le joueur
        selectedPlace=listEmpty.get(rnd.nextInt(listEmpty.size()));
        listEmpty.remove(selectedPlace);
        newBoard.setLayout(selectedPlace[1],selectedPlace[0],new Player());
        
        //CAISSES ET CHEMIN FORME ZONE DE RESET 
        
        //newBoard.printBoard();
        int range=0;
        int lastDragDirection=-1;
        
        int moveChoice;    //0 représente le haut, 1 la droite, 2 le bas, 3 la gauche
        for(int i=0;i<nbMovePerTry;i++){
            moveChoice=rnd.nextInt(4);
            switch (moveChoice) {
                case 0:
                    if(newBoard.reverseMovePlayer(0,-1)){ //Si on s'est effectivement déplacé
                        //Goal sur la case précédente
                        if("goal".equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()+1).getType()) ){
                            Goal prevCaseGoal=(Goal)newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()+1);
                            if( lastBoxDragged.equals(prevCaseGoal.getContent())){ //Goal avec la lastDraggedBox dessus, qui a donc été tirée
                                if(lastDragDirection != 0){    //lastDraggedBox a été tirée dans une nouvelle direction
                                    range=range+1;
                                    lastDragDirection=0;
                                }
                            }
                            else{              //Il n'y a pas la lastDraggerBox sur ce goal
                                if("cbox".equals(prevCaseGoal.getContent().getType())){ //On a tout de même tiré une autre caisse
                                    lastDragDirection=0;
                                    range=range+1;
                                    lastBoxDragged=prevCaseGoal.getContent();
                                }
                            }
                        }
                        
                        //La case précédente ne contient pas un goal
                        else{
                            if(lastBoxDragged.equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()+1))  ){ //Et on a déplacé la même caisse que précédemment
                                if(lastDragDirection != 0){    //Dans une autre direction 
                                   range=range+1;
                                   lastDragDirection=0;
                                }
                            }          
                            else{                                                                                   //On ne vient pas de tirer la dernière caisse tirée
                                if("cbox".equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()+1).getType())){ //Mais on vient tout de même de tirer une caisse
                                    lastDragDirection=0;
                                    range=range+1;
                                    lastBoxDragged=newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()+1);
                                }
                            }
                        }
                    }
                    break;
                case 1:                    
                    if(newBoard.reverseMovePlayer(1,0)){ //Si on s'est effectivement déplacé
                        //Goal sur la case précédente
                        if("goal".equals(newBoard.getLayoutable(newBoard.getPX()-1,newBoard.getPY()).getType()) ){
                            Goal prevCaseGoal=(Goal)newBoard.getLayoutable(newBoard.getPX()-1,newBoard.getPY());
                            if( lastBoxDragged.equals(prevCaseGoal.getContent())){ //Goal avec la lastDraggedBox dessus, qui a donc été tirée
                                if(lastDragDirection != 1){    //lastDraggedBox a été tirée dans une nouvelle direction
                                    range=range+1;
                                    lastDragDirection=1;
                                }
                            }
                            else{              //Il n'y a pas la lastDraggerBox sur ce goal
                                if("cbox".equals(prevCaseGoal.getContent().getType())){ //On a tout de même tiré une autre caisse
                                    lastDragDirection=1;
                                    range=range+1;
                                    lastBoxDragged=prevCaseGoal.getContent();
                                }
                            }
                        }
                        
                        //La case précédente ne contient pas un goal
                        else{
                            if(lastBoxDragged.equals(newBoard.getLayoutable(newBoard.getPX()-1,newBoard.getPY()))  ){ //Et on a déplacé la même caisse que précédemment
                                if(lastDragDirection != 1){    //Dans une autre direction 
                                   range=range+1;
                                   lastDragDirection=1;
                                }
                            }          
                            else{                                                                                   //On ne vient pas de tirer la dernière caisse tirée
                                if("cbox".equals(newBoard.getLayoutable(newBoard.getPX()-1,newBoard.getPY()).getType())){ //Mais on vient tout de même de tirer une caisse
                                    lastDragDirection=1;
                                    range=range+1;
                                    lastBoxDragged=newBoard.getLayoutable(newBoard.getPX()-1,newBoard.getPY());
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    if(newBoard.reverseMovePlayer(0,1)){ //Si on s'est effectivement déplacé
                        //Goal sur la case précédente
                        if("goal".equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()-1).getType()) ){
                            Goal prevCaseGoal=(Goal)newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()-1);
                            if( lastBoxDragged.equals(prevCaseGoal.getContent())){ //Goal avec la lastDraggedBox dessus, qui a donc été tirée
                                if(lastDragDirection != 2){    //lastDraggedBox a été tirée dans une nouvelle direction
                                    range=range+1;
                                    lastDragDirection=2;
                                }
                            }
                            else{              //Il n'y a pas la lastDraggerBox sur ce goal
                                if("cbox".equals(prevCaseGoal.getContent().getType())){ //On a tout de même tiré une autre caisse
                                    lastDragDirection=2;
                                    range=range+1;
                                    lastBoxDragged=prevCaseGoal.getContent();
                                }
                            }
                        }
                        
                        //La case précédente ne contient pas un goal
                        else{
                            if(lastBoxDragged.equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()-1))  ){ //Et on a déplacé la même caisse que précédemment
                                if(lastDragDirection != 2){    //Dans une autre direction 
                                   range=range+1;
                                   lastDragDirection=2;
                                }
                            }          
                            else{                                                                                   //On ne vient pas de tirer la dernière caisse tirée
                                if("cbox".equals(newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()-1).getType())){ //Mais on vient tout de même de tirer une caisse
                                    lastDragDirection=2;
                                    range=range+1;
                                    lastBoxDragged=newBoard.getLayoutable(newBoard.getPX(),newBoard.getPY()-1);
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    if(newBoard.reverseMovePlayer(-1,0)){ //Si on s'est effectivement déplacé
                        //Goal sur la case précédente
                        if("goal".equals(newBoard.getLayoutable(newBoard.getPX()+1,newBoard.getPY()).getType()) ){
                            Goal prevCaseGoal=(Goal)newBoard.getLayoutable(newBoard.getPX()+1,newBoard.getPY());
                            if( lastBoxDragged.equals(prevCaseGoal.getContent())){ //Goal avec la lastDraggedBox dessus, qui a donc été tirée
                                if(lastDragDirection != 3){    //lastDraggedBox a été tirée dans une nouvelle direction
                                    range=range+1;
                                    lastDragDirection=3;
                                }
                            }
                            else{              //Il n'y a pas la lastDraggerBox sur ce goal
                                if("cbox".equals(prevCaseGoal.getContent().getType())){ //On a tout de même tiré une autre caisse
                                    lastDragDirection=3;
                                    range=range+1;
                                    lastBoxDragged=prevCaseGoal.getContent();
                                }
                            }
                        }
                        
                        //La case précédente ne contient pas un goal
                        else{
                            if(lastBoxDragged.equals(newBoard.getLayoutable(newBoard.getPX()+1,newBoard.getPY()))  ){ //Et on a déplacé la même caisse que précédemment
                                if(lastDragDirection != 3){    //Dans une autre direction 
                                   range=range+1;
                                   lastDragDirection=3;
                                }
                            }          
                            else{                                                                                   //On ne vient pas de tirer la dernière caisse tirée
                                if("cbox".equals(newBoard.getLayoutable(newBoard.getPX()+1,newBoard.getPY()).getType())){ //Mais on vient tout de même de tirer une caisse
                                    lastDragDirection=3;
                                    range=range+1;
                                    lastBoxDragged=newBoard.getLayoutable(newBoard.getPX()+1,newBoard.getPY());
                                }
                            }
                        }
                    }
                    break;
                default:
             //       throw new IOException("Unexpected Error: moveChoice must be 0/1/2/3");
                    break;
            }
        }
        
        Object[] result={newBoard,range};
        
       // return newBoard;
       return result;
    }
    

    /**
     * Génère un board aléatoirement.
     * @param height la hauteur souhaitée pour le Board, qui sera multipliée par 3 puis augmentée de 2 (murs exterieurs)
     * @param width la largeur souhaitée pour le Board, qui sera multipliée par 3 puis augmentée de 2 (murs exterieurs)
     * @param nbBox le nombre souhaité de boites (et de goals) qui le Board devra contenir
     * @return le board généré
     */
    public static Board generateBoard(int height, int width, int nbBox) throws IOException{ 
        char[][] map = generateEmptyRoom(height,width,nbBox);
       
        Board newBoard;
        int range;
        Object[] generatedLayoutTab;
        
        generatedLayoutTab = generateLayout(map, nbBox);
        newBoard=(Board) generatedLayoutTab[0];
        range=(int) generatedLayoutTab[1];
        
        Object[] tempGeneratedLayoutTab;
        
        
        for(int k=0;k<nbTryLayout-1;k++){    
            tempGeneratedLayoutTab = generateLayout(map, nbBox);
            if((int) tempGeneratedLayoutTab[1]>range){
                newBoard=(Board) tempGeneratedLayoutTab[0];
                range=(int) tempGeneratedLayoutTab[1];
            }
        }
        return newBoard;
    }
    
    
    
    
    
}

