/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package backend;

/**
 *
 * @author Alfatta
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
    
    
    public boolean isCompatible(char ic1, char ic2, char ic3, char ic4, char ic5, char ic6, char ic7, char ic8, char ic9, char ic10, char ic11, char ic12,char ic13, char ic14, char ic15, char ic16){
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

        return true;
    }
}
