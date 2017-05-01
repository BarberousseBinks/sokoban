/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Classe représentant le mode Histoire du jeu
 * Est une liste simplement chaînée d'éléments LevelNode dont l'objet StoryMode est le curseur
 * @see LevelNode
 * @author Louis Dhanis
 */
public class StoryMode {
    
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
    StoryMode(Game level){
        this();
        this.node = new LevelNode(level);
    }
    
    /**
     * Renvoie le niveau actuel
     * @return level (le niveau actuel)
     */
    public Game getLevel(){
        return this.node.getLevel();
    }
    
    /**
     * Place le curseur de la liste chaînée sur le niveau suivant
     */
    public void goNextLevel(){
        LevelNode temp = this.node;
        this.node = temp.getNextNode();
    }
    
    /**
     * Ajoute un niveau au mode histoire (utile pour l'initialisation)
     * @param level (le niveau à ajouter)
     */
    public void addLevel(Game level){
        if(this.node == null){
            this.node = new LevelNode(level);
        }
        else{
            LevelNode parser = this.node;
            while(parser.isNextNode()){
                LevelNode temp = parser;
                parser = temp.getNextNode();
            }
            parser.setNextNode(new LevelNode(level));
        }
    }
    
    public void initStory(){ //Code adapté de https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {
            File fXmlFile = new File("ClassicMode/sauvegarde.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("level");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Level id : " + eElement.getAttribute("id"));
                    System.out.println("Text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
                    System.out.println("Finished : " + eElement.getElementsByTagName("finished").item(0).getTextContent());
                    System.out.println("Nbsteps : " + eElement.getElementsByTagName("nbsteps").item(0).getTextContent());

                }
            }
        } 
        catch (ParserConfigurationException | SAXException | IOException | DOMException e) {}
    }
    
}
