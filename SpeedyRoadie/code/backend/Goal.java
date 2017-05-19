/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 * Layoutable implémentant Objectif. Il doit contenir un objet de type Wall pour remplir sa condition de victoire (en tant qu'Objectif). Un goal peut contenir d'autres Layoutable que des caisses.
 * @author Corentin Dachy
 */
public class Goal extends Objectif implements Layoutable {
    //static final String type = "goal";
    private Layoutable content;

    public Goal() {
        content=new Emptycase();
    }

    public Goal(Layoutable c) {
        content=c;
    }
    
    /**
     * Renvoit true si le goal contient une emptycase, false sinon
     */
    public boolean isEmpty(){
        return "emptycase".equals(content.getType());
    }       
    /**
     * Renvoit true si le goal contient une ClassicBox, false sinon
     */
    public boolean cboxOn(){
        return "cbox".equals(content.getType());
    }  
    /**
     * Renvoit true si le goal contient le Player, false sinon
     */
    public boolean playerOn(){
        return "player".equals(content.getType());
    }
    

    public void setEmpty(Emptycase c){
        content=c;
    }
    public void setcBoxOn(ClassicBox c){
        content=c;
    }
    public void setPlayerOn(Player c){
        content=c;
    }
    /**
     * Configure le contenu du goal
     * @param c le Layoutable contenu dans le goal après appel de cette méthode
     */
    public void setContent(Layoutable c){
        if(c.getType()=="goal"){
            throw new IllegalArgumentException("a goal can't contain an another goal (only a player, an emptycase or a box)");
        }
        content=c;
    }
    
    public Layoutable getContent(){
        return content;
    }
    
    @Override
    public String getType(){
        return "goal";
    }

    @Override
    boolean isCompleted() {
        return cboxOn();
    }
    
    @Override
    public String toString() {
        if(isEmpty()){
            return ".";
        }
        if(cboxOn()){
            return "$";
        }
        else{
            return "@";
        }
            
    }
    
    public char toChar(){
        if(isEmpty()){
            return '.';
        }
        if(cboxOn()){
            return '!';
        }
        else{
            return '*';
        }
    }
}
