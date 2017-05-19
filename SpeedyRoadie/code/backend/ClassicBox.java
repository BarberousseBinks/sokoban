package backend;

/**
 * Une boite qui n'a rien de spécial. Une boite normale.
 * @author Corentin Dachy
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
