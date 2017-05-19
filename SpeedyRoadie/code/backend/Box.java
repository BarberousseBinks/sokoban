
package backend;

/**
 * Classe abstraite représentant les boites. Laissant la possibilitée à l'implémentation de boite particulière
 * @author Corentin Dachy
 * 
 */
public abstract class Box implements Layoutable {
     @Override
     public String getType(){
        return "box"; 
     }
     
     
}

