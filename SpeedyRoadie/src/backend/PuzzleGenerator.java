/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Alfatta
 */
public class PuzzleGenerator {
    
    //public static final Pattern[] patternTab=
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
    public static final SPattern SPattern6=new SPattern(SPdata6,'L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L');
    
    public static final SPattern[] SPatternTab={SPattern1};
    

    public static char[][] getPatern(char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14,char c15,char c16){
        
        //SDIOQSJDIOSQFHSQIUH
        
        char[][] pattern = {{'t','k',' '},{' ',' ',' '}};
        return pattern;
    }
    
    public static char[][] generateEmptyRoom(int height, int width){ //heigth et widith le nombre de bloc 3*3 à mettre
        int mapheight=(3*height)+2;
        int mapwidth=(3*width)+2;
        
        char[][] map = new char[mapheight][mapwidth];
       
        //On génère une map vide entourée de murs
        for(int i=0;i<(3*height)+2;i++){
            for(int j=0;j<(3*width)+2;j++){
                if(i == 0 || j == 0 || i == (mapheight-1) || j == (mapwidth-1)){
                    map[i][j] = '#';
                }
                else{
                    map[i][j] = ' '; 
                }
            }
        }
        
        //On remplit la map avec des patterns
        char[][] tempPattern;
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
                else{                                // Sinon le coin inferieur droit n'est pas implémenté
                    c12='L';
                }
                
                
                tempPattern=getPatern(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16); //On récupère le pattern à ajouter à la map
                
                for(int y=0;y<3;y++){  //On ajoute le pattern à la map
                    for(int x=0;x<3;x++){
                        map[(3*i)+1+y][(3*j)+1+x]=tempPattern[y][x];
                    }
                }
            }
        }
        return map;
        
        
    }
    
  
    
    
}

