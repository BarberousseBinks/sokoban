/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

import java.util.Random;

/**
 * Classe représentant un Pattenr pour le PuzzleGenerator
 * @author Corentin Dachy
 */
public class SPattern {
    char[][] patternContent;
    char c1;char c2;char c3;char c4;char c5;char c6;char c7;char c8;char c9;char c10;char c11;char c12;char c13;char c14;char c15;char c16;

    public SPattern() {
    }

    public SPattern(char[][] patternContent) {
        this.patternContent = patternContent;
    }

    public SPattern(char[][] patternContent, char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12, char c13, char c14, char c15, char c16) {
        this.patternContent = patternContent;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
        this.c12 = c12;
        this.c13 = c13;
        this.c14 = c14;
        this.c15 = c15;
        this.c16 = c16;
    }

    public char[][] getPatternContent() {
        return patternContent;
    }

    public char getC1() {
        return c1;
    }
    public char getC2() {
        return c2;
    }
    public char getC3() {
        return c3;
    }
    public char getC4() {
        return c4;
    }
    public char getC5() {
        return c5;
    }
    public char getC6() {
        return c6;
    }
    public char getC7() {
        return c7;
    }
    public char getC8() {
        return c8;
    }
    public char getC9() {
        return c9;
    }
    public char getC10() {
        return c10;
    }
    public char getC11() {
        return c11;
    }
    public char getC12() {
        return c12;
    }
    public char getC13() {
        return c13;
    }
    public char getC14() {
        return c14;
    }
    public char getC15() {
        return c15;
    }
    public char getC16() {
        return c16;
    }

    public void setCond(char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9, char c10, char c11, char c12,char c13, char c14, char c15, char c16) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
        this.c12 = c12;
        this.c13 = c13;
        this.c14 = c14;
        this.c15 = c15;
        this.c16 = c16;
    }
    
    public void setPatternContent(char[][] patternContent) {
        this.patternContent = patternContent;
    }
    
    /**
     * Fait une rotation d'un quarts de tour dans le sens horlogique (pour le SPattern this)
     */
    public void rotate(){  
        char temp1;                           //J'aurais pu utiliser les formules d'une rotation de 90° autour d'une origine mais redéfinir le milieu comme origine dans la double liste n'aurait pas prit beaucoup moins de temps
        char temp2;
        
        temp1=patternContent[0][0];
        patternContent[0][0]=patternContent[2][0];
        temp2=patternContent[0][2];
        patternContent[0][2]=temp1;
        temp1=patternContent[2][2];
        patternContent[2][2]=temp2;
        patternContent[2][0]=temp1;
        
        temp1=patternContent[0][1];
        patternContent[0][1]=patternContent[1][0];
        temp2=patternContent[1][2];
        patternContent[1][2]=temp1;
        temp1=patternContent[2][1];
        patternContent[2][1]=temp2;
        patternContent[1][0]=temp1;
        
        temp1=c1;
        c1=c13;
        temp2=c5;
        c5=temp1;
        temp1=c9;
        c9=temp2;
        c13=temp1;
        
        temp1=c2;
        c2=c14;
        temp2=c6;
        c6=temp1;
        temp1=c10;
        c10=temp2;
        c14=temp1;
        
        temp1=c3;
        c3=c15;
        temp2=c7;
        c7=temp1;
        temp1=c11;
        c11=temp2;
        c11=temp1;
        
        temp1=c4;
        c4=c16;
        temp2=c8;
        c8=temp1;
        temp1=c12;
        c12=temp2;
        c12=temp1;
    }
   
    /**
     * Effectue de 0 (comprit) à nb (non comprit) quarts de tours dans le sens horaire (pour le SPattern this)
     * @param nb
     */
    public void rotateRandom(int nb){   
        Random rnd = new Random();
        int r = rnd.nextInt(nb);
        for(int i=0;i<r;i++){
            rotate();   
        }    
    }
    
    /**
     * Transforme un SPattern en son image en miroir (symmétra axe horizontal passant par le millieu)
     */
    public void mirror(){
        char temp;
        temp=patternContent[0][0]; patternContent[0][0]=patternContent[0][2]; patternContent[0][2]=temp;
        temp=patternContent[1][0]; patternContent[1][0]=patternContent[1][2]; patternContent[1][2]=temp;
        temp=patternContent[2][0]; patternContent[2][0]=patternContent[2][2]; patternContent[2][2]=temp;
        temp=c1; c1=c3; c3=temp;
        temp=c11; c11=c9; c9=temp;
        temp=c16; c16=c4; c4=temp;
        temp=c15; c15=c5; c5=temp;
        temp=c14; c14=c6; c6=temp;
        temp=c13; c13=c7; c7=temp;
        temp=c12; c12=c8; c8=temp;
        
    }
    
    public void mirrorRandom(){
        Random rnd = new Random();
        if(rnd.nextInt(2) == 1){
            mirror();
        }
    }
    
    public void printSPattern(){  //Uniquement utile à des fins de débugage. Ne sert pas dans le programme
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(patternContent[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void printCond(){      //Uniquement utile à des fins de débugage. Ne sert pas dans le programme
        System.out.println(""+c16+" "+c1+" "+c2+" "+c3+" "+c4);
        System.out.println(""+c15+"       "+c5);
        System.out.println(""+c14+"       "+c6);
        System.out.println(""+c13+"       "+c7);
        System.out.println(""+c12+" "+c11+" "+c10+" "+c9+" "+c8);
    }

    
    public boolean isCompatible(char ic1, char ic2, char ic3, char ic4, char ic5, char ic6, char ic7, char ic8, char ic9, char ic10, char ic11, char ic12,char ic13, char ic14, char ic15, char ic16, char ca1,char ca2,char ca3, char ca4, char ca5,char ca6,char ca7,char ca8,char ca9){
        if((ic1!='L') && (c1!='L') && (c1 != ic1)) return false; 
        if((ic2!='L') && (c2!='L') && (c2 != ic2)) return false;
        if((ic3!='L') && (c3!='L') && (c3 != ic3)) return false;
        if((ic4!='L') && (c4!='L') && (c4 != ic4)) return false;
        if((ic5!='L') && (c5!='L') && (c5 != ic5)) return false;
        if((ic6!='L') && (c6!='L') && (c6 != ic6)) return false;
        if((ic7!='L') && (c7!='L') && (c7 != ic7)) return false;
        if((ic8!='L') && (c8!='L') && (c8 != ic8)) return false;
        if((ic9!='L') && (c9!='L') && (c9 != ic9)) return false;        
        if((ic10!='L') && (c10!='L') && (c10 != ic10)) return false;
        if((ic11!='L') && (c11!='L') && (c11 != ic11)) return false;
        if((ic12!='L') && (c12!='L') && (c12 != ic12)) return false;
        if((ic13!='L') && (c13!='L') && (c13 != ic13)) return false;
        if((ic14!='L') && (c14!='L') && (c14 != ic14)) return false;
        if((ic15!='L') && (c15!='L') && (c15 != ic15)) return false;
        if((ic16!='L') && (c16!='L') && (c16 != ic16)) return false;

        if((ca1 !='L') && (patternContent[0][0] != ca1)) return false;
        if((ca2 !='L') && (patternContent[0][1] != ca2)) return false;
        if((ca3 !='L') && (patternContent[0][2] != ca3)) return false;
        if((ca4 !='L') && (patternContent[1][0] != ca4)) return false;
        if((ca5 !='L') && (patternContent[1][1] != ca5)) return false;
        if((ca6 !='L') && (patternContent[1][2] != ca6)) return false;
        if((ca7 !='L') && (patternContent[2][0] != ca7)) return false;
        if((ca8 !='L') && (patternContent[2][1] != ca8)) return false;
        if((ca9 !='L') && (patternContent[2][2] != ca9)) return false;
        
        return true;
    }
}
