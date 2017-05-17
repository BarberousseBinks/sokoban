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
 * Classe representant le mode Histoire du jeu
 * Est une liste simplement chaînee d'elements LevelNode dont l'objet StoryMode est le curseur
 * @see LevelNode
 * @author Louis Dhanis
 */
public class StoryMode {
    private File xmlSave = new File("ClassicMode/sauvegarde.xml");
    
    private LevelNode node;
    /**
     * Constructeur sans parametre du Story Mode
     */
    public StoryMode(){
        this.node = null;
    }
    
    /**
     * Constructeur prenant le premier niveau en parametre
     * @param level (premier niveau courrant)
     */
    StoryMode(LevelNode node){
        this();
        this.node = node;
    }
    
    /**
     * Renvoie le niveau actuel
     * @return level le niveau du node actuel
     */
    public Game getLevel(){
        return this.node.getLevel();
    }
    
    /**
     * getNode
     * @return le noeud actuel de la liste
     **/
    public LevelNode getNode(){
        return this.node;
    }
    
    /**
     * Renvoie le dernier node ajoute au mode histoire
     * Complexite de l'alogrithme en O(n) car il faut parcourir toute la liste pour obtenir le dernier element
     * @return lastNode le dernier node de l'histoire
     **/
     
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
    
    /**
     * Verifie si on est en bout de liste ou non
     * @return isNull true si le noeud actuel n'existe pas (si on est arrive au bout de la liste de niveaux StoryMode
     **/ 
    public boolean isNull(){
        if(this.node == null){
            return true;
        }
        return false;
    }
    
    /**
     * Place le curseur de la liste chaînee sur le niveau suivant
     */
    public void goNextNode(){
        LevelNode temp = this.node;
        this.node = temp.getNextNode();
    }
    
    /**
     * Ajoute un niveau au mode histoire (utile pour l'initialisation)
     * @param node le node a ajouter au mode histoire
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
    
    /**
     * Methode qui instancie la chaine de niveaux en fonction de l'etat du fichier XML.
     * Chaque element du fichier XML represente un niveau.
     * Chaque niveau sera represente par un LevelNode.
     **/
    
    public void initStory(){ //Code adapte de https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        this.node = null; //Met la chaîne a 0
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.xmlSave);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("level");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element) nList.item(temp); // On recupere le noeud actuel

                // On recupere chaque element le constituant par son nom
                String text = eElement.getElementsByTagName("text").item(0).getTextContent();
                int nbSteps = Integer.parseInt(eElement.getElementsByTagName("nbsteps").item(0).getTextContent());
                int id = Integer.parseInt(eElement.getAttribute("id"));
                String doable = eElement.getElementsByTagName("doable").item(0).getTextContent();
                boolean consDoable;
                
                if("true".equals(doable)){
                    consDoable = true;
                }else{
                    consDoable = false;
                }
                String path = "ClassicMode/maps/"+id+".xsb";

                this.addLevel(new LevelNode(new Game(path),text,nbSteps,id, consDoable));
            }
        } 
        catch (ParserConfigurationException | SAXException | IOException | DOMException e) {}
    }
    
    /**
     * Methode qui met a jour le fichier XML de sauvegarde quand un niveau vient d'etre fini
     * @param steps le nombre de pas qu'il a fallu pour terminer le niveau
     **/
    public void updateSave(int steps) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.xmlSave);

            Element racine = doc.getDocumentElement();
            NodeList levelList = doc.getElementsByTagName("level");
            Element niveau = (Element) levelList.item(this.node.id);
            niveau.getElementsByTagName("doable").item(0).setTextContent("true");
            String strSteps = ""+steps;
            niveau.getElementsByTagName("nbsteps").item(0).setTextContent(strSteps);
            
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
