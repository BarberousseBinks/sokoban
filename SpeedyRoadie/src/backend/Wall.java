/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Alfa
 */
public class Wall implements Layoutable{

    @Override
    public String getType() {
        return "wall";
    }
        
    @Override
    public String toString() {
        return "#";
    }
    
    @Override
    public char toChar(){
        return '#';
    }
}
