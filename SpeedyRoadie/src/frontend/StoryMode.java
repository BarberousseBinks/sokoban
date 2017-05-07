/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Classe représentant le mode Histoire du jeu
 * Est une liste simplement chaînée d'éléments LevelNode dont l'objet StoryMode est le curseur
 * @see LevelNode
 * @author Louis Dhanis
 */
public class StoryMode {
    private File xmlSave = new File("ClassicMode/sauvegarde.xml");
    
    private LevelNode node;
    /**
     * Constructeur sans paramètre du Story Mode
     */
    public StoryMode(){
        this.node = null;
    }
    
    /**
     * Constructeur prenant le premier niveau en paramètre
     * @param level (premier niveau courrant)
     */
    StoryMode(LevelNode node){
        this();
        this.node = node;
    }
    
    /**
     * Renvoie le niveau actuel
     * @return level (le niveau actuel)
     */
    public Game getLevel(){
        return this.node.getLevel();
    }
    
    public LevelNode getNode(){
        return this.node;
    }
    
    public LevelNode getLastNode(){
        if(this.node == null){
            return null;
        }
        else{
            LevelNode temp = this.node;
            while(temp.hasNextNode()){
                temp = temp.getNextNode();
            }
            return temp;
        }
    }
    
    public boolean isNull(){
        if(this.node == null){
            return true;
        }
        return false;
    }
    
    /**
     * Place le curseur de la liste chaînée sur le niveau suivant
     */
    public void goNextNode(){
        LevelNode temp = this.node;
        this.node = temp.getNextNode();
    }
    
    /**
     * Ajoute un niveau au mode histoire (utile pour l'initialisation)
     * @param node
     */
    public void addLevel(LevelNode node){
        if(this.node == null){
            this.node = node;
        }
        else{
            LevelNode parser = this.node;
            while(parser.hasNextNode()){
                LevelNode temp = parser;
                parser = temp.getNextNode();
            }
            parser.setNextNode(node);
        }
    }
    
    public void initStory(){ //Code adapté de https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        this.node = null; //Met la chaîne à 0
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.xmlSave);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("level");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element) nList.item(temp); // On récupère le noeud actuel

                // On récupère chaque élément le constituant par son nom
                String text = eElement.getElementsByTagName("text").item(0).getTextContent();
                int nbSteps = Integer.parseInt(eElement.getElementsByTagName("nbsteps").item(0).getTextContent());
                int id = Integer.parseInt(eElement.getAttribute("id"));
                String doable = eElement.getElementsByTagName("finished").item(0).getTextContent();
                boolean consDoable;
                
                if("true".equals(doable)){
                    consDoable = true;
                }else{
                    consDoable = false;
                }
                String path = "ClassicMode/maps/"+id+".xsb";

                this.addLevel(new LevelNode(new Game(path),text,nbSteps,id, consDoable));
                System.out.println(this.getLastNode().toString());
            }
        } 
        catch (ParserConfigurationException | SAXException | IOException | DOMException e) {}
    }
    
    public void updateSave() {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.xmlSave);

            Element racine = doc.getDocumentElement();
            NodeList levelList = doc.getElementsByTagName("level");
            Element niveau = (Element) levelList.item(this.node.id);
            niveau.getElementsByTagName("finished").item(0).setTextContent("true");
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(this.xmlSave);
            
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        }
        catch (ParserConfigurationException | SAXException | IOException | DOMException e) {} catch (TransformerException ex) {
            Logger.getLogger(StoryMode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
