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
public class ClassicBox extends Box{
    
    public String getType(){
        //String a=super.getType();
        //a="c"+a;
        //return a;
        return "cbox"; 
    }
          
    @Override
    public String toString() {
        return "$";
    }
    
    @Override
    public char toChar(){
        return '$';
    }
    
}
