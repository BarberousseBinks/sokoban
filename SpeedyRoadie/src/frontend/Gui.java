/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import backend.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe de l'interface graphique.
 * Dans ce fichier, on retrouve toute l'implémentation graphique du projet
 * @author Louis Dhanis
 */
public class Gui extends JFrame implements ActionListener, KeyListener{
    private Game level;
    private GuiBgButton randGame;
    private GuiBgButton loadLevel;
    private GuiBgButton classicMode;
    private GuiBgButton back;
    
    /**
     * Classe de l'interface graphique.
     * étend JFrame (conteneur principal)
     * @throws IOException
     */
    public Gui() throws IOException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.level = null;
        guiWelcome();
        addKeyListener(this);
    }
    // Méthode Private car uniquement chargée par Gui()
    // Affiche le menu d'accueil
    private void guiWelcome() throws IOException {
        JPanel panel = new JPanel();
        SpeedyBackground backgroundPanel = new SpeedyBackground("gameGraphics/newGif.gif");
        panel.setLayout(new BorderLayout());
        SpeedyBackground buttonContainer = new SpeedyBackground("gameGraphics/steelTexture.jpg");
        setResizable(false);
        this.setSize(800,600);
        
        randGame = new GuiBgButton("Aléatoire");
        randGame.addActionListener(this);
        
        classicMode = new GuiBgButton("Mode classique");
        classicMode.addActionListener(this);
        
        loadLevel = new GuiBgButton("Charger un niveau");
        loadLevel.addActionListener(this);
        
        buttonContainer.add(randGame);
        buttonContainer.add(loadLevel);
        buttonContainer.add(classicMode);
        panel.add(backgroundPanel, BorderLayout.CENTER);
        panel.add(buttonContainer, BorderLayout.SOUTH);
        this.setContentPane(panel);
        this.setVisible(true);
    }
    // Méthode Private car uniquement chargée par Gui()
    // Affiche le JPanel du jeu
    // Prend en paramètre un niveau du jeu
    private void guiGame(Game level){
        if(this.level.isGameWon()){
            SpeedyBackground wowGG = new SpeedyBackground("gameGraphics/wowBG.jpg");
            this.setSize(800,600);
            this.setContentPane(wowGG);
            this.setVisible(true);
        }
        else{
            setResizable(true);
            requestFocus();
            final SpeedyBackground infos = new SpeedyBackground("gameGraphics/steelTexture.jpg");
            infos.setLayout(new FlowLayout());
            final GuiLabel nbSteps = new GuiLabel("Nombre de pas: " + level.getNbSteps());
            infos.add(nbSteps);
            final GuiBgButton undo = new GuiBgButton("Annuler un mouvement");
            infos.add(undo);

            final JPanel gameContent = new JPanel();
            int height = level.getHeight()*50;
            int width = level.getWidth()*50;

            gameContent.setSize(width, height);
            gameContent.setPreferredSize(new Dimension(width, height));
            gameContent.setLayout(new GridLayout(level.getHeight(), level.getWidth()));

            char[][] boardCode = level.getRepr();
            //Ajout des boutons relatifs au plateau
            for(int i = 0; i < boardCode.length; i++){
                for(int j = 0; j < boardCode[i].length; j++){
                    GuiElement elem = new GuiElement(boardCode[i][j], j ,i);
                    elem.addActionListener(this);
                    gameContent.add(elem);
                }
            }
            final SpeedyBackground wrapperPanel = new SpeedyBackground("gameGraphics/fade.jpg");
            wrapperPanel.setLayout(new FlowLayout());
            final JPanel guiGame = new JPanel();
            guiGame.setSize(height + 100, width + 100);
            guiGame.setLayout(new BorderLayout());
            guiGame.add(infos, BorderLayout.NORTH);
            wrapperPanel.add(gameContent);
            guiGame.add(wrapperPanel, BorderLayout.CENTER);
            this.setContentPane(guiGame);
            this.setVisible(true);
        }
    }
    

    
    private void classicMode(){
        JPanel classicPanel = new JPanel();
        this.setSize(800,600);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Emplacement du fichier de sauvegarde
            File saves = new File("gameMaps/saves/save.xml");
            //Parsing
            Document xml = builder.parse(saves);
            Element root = xml.getDocumentElement();
            
            NodeList nodes = root.getChildNodes();
            int nbNodes = nodes.getLength();
            
            for(int i = 0; i < nbNodes; i++){
                Node n = nodes.item(i);
                System.out.println("* Niveau N°" + (i+1));
                int nbChilds = n.getChildNodes().getLength();                
                NodeList childNodes = n.getChildNodes();
                
                for(int j = 0; j < nbChilds; j++){
 
                    Node m = childNodes.item(j);
                    System.out.println("* - " + m.getNodeName() + " - " + m.getTextContent());
                }
            }      
            
        }
        catch(ParserConfigurationException | SAXException | IOException e){
            System.out.println("Erreur du fichier de sauvegarde");
        }
        this.setContentPane(classicPanel);
        this.setVisible(true);
    }
    
    //Affiche le contenu du dossier des maps
    //Permet de charger une partie personnalisée autre que celle du classic mode
    private void levelLoader(String path){

        JPanel levelLoader = new JPanel();
        levelLoader.setLayout(new BorderLayout());
        
        File levelFolder = new File(path);
        File[] levelList = levelFolder.listFiles();
        
        SpeedyBackground buttonContainer = new SpeedyBackground("gameGraphics/steelTexture.jpg");
        int height = Math.round(levelList.length / 2);
        buttonContainer.setLayout(new GridLayout(height , 2, 5, 5));
        back = new GuiBgButton("Retour");
        back.addActionListener(this);
        levelLoader.add(back, BorderLayout.NORTH);
        
        for (File levelList1 : levelList) {
            if (levelList1.isFile() && levelList1.getName().endsWith(".xsb")) {
                //XSB est le format par défaut pour les fichiers de jeu
                System.out.println(levelList1.getPath());
                GuiFile tempButton = new GuiFile(levelList1.getPath());
                buttonContainer.add(tempButton);
                tempButton.addActionListener(this);
                levelLoader.add(buttonContainer);
            }
        }
        JScrollPane scroller = new JScrollPane(buttonContainer);
        levelLoader.add(scroller, BorderLayout.CENTER);
        GuiLabel notice = new GuiLabel("Pour ajouter vos fichiers xsb, entrez-les dans le dossier "+ path);
        levelLoader.add(notice, BorderLayout.SOUTH);
        this.setSize(800,600);
        this.setResizable(true);
        this.setContentPane(levelLoader);
        this.setVisible(true);
    }

    /**
     * actionPerformed, gestionnaire des ActionEvent.
     * Permet d'établir la correspondance entre les boutons et ce qu'ils font.
     * Tous les boutons de l'interface graphique sont configurés dans cette partie
     * Méthode issue de l'interface ActionListener
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == randGame){
            try {
                this.level = new Game(PuzzleGenerator.generateBoard(3,3,3));
                guiGame(this.level);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == loadLevel){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this.getContentPane());
            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile.getPath());

                try {
                    this.level = new Game(selectedFile.getPath());
                    guiGame(this.level);
                } catch (Exception ex) {
                    infoBox("Une erreur est survenue avec ce fichier XSB, veuillez vérifier s'il comporte des caractères autres que [#,@,.,$,!]", "Erreur, fichier XSB incorrect");
                }
            }
        }
        else if(source == classicMode){
            classicMode();
        }
        else if(source == back){
            try {
                guiWelcome();
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source.getClass() == GuiElement.class){
            GuiElement temp;
            temp = (GuiElement)source;
            this.level.movePlayerMouse(temp.getPosX(), temp.getPosY());
            guiGame(this.level);
        }
        else if(source.getClass() == GuiFile.class) {
            GuiFile temp;
            temp = (GuiFile)source;
            try {
                this.level = new Game(temp.getPath());
            } catch (Exception ex) {
                infoBox("Une erreur est survenue avec ce fichier XSB, veuillez vérifier s'il comporte des caractères autres que [#,@,.,$,!]", "Erreur, fichier XSB incorrect");
            }
            guiGame(this.level);
        }
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("KeyTyped");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case 38:
                this.level.movePlayer(0,1);
                break;
            case 40:
                this.level.movePlayer(0,-1);
                break;
            case 39:
                this.level.movePlayer(1,0);
                break;
            case 37:
                this.level.movePlayer(-1,0);
                break;
        }
        guiGame(this.level);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("KeyReleased");
    }
}
