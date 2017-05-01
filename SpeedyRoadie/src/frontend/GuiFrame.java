/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import backend.PuzzleDataManager;
import static backend.PuzzleDataManager.getMovesSaved;
import backend.PuzzleGenerator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Fenêtre principale du jeu de Sokoban SpeedyRoadie
 * @author Louis Dhanis
 */
public class GuiFrame extends JFrame implements ActionListener{
    
    private JPanel mainPanel = null;
    
    private GuiStdButton story = null;
    private GuiStdButton random = null;
    private GuiStdButton loadGame = null;
    private GuiStdButton exitGame = null;
    private GuiStdButton backToMenu = null;
    private GuiLevelSelectorBtn nextLevel;
    private GuiStdButton playLevel;
    
    private LevelNode currentLevel = null;
    private final StoryMode storyChain;
    private Game level = null;
    public ArrayList<Integer> mov = new ArrayList<>();
    private int gameMode = -1; 
    //GAME MODES:
    // **INIT : -1
    // *STORY MODE: 0
    // *RANDOM MODE: 1
    // *CUSTOM MODE: 2
    // ***FESTIVAL: 3 (to be implemented)
    
    /**
     * GuiFrame
     * Fenêtre du jeu
     * Principale interface entre l'utilisateur et le jeu
     * @throws IOException
     */
    public GuiFrame() throws IOException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        
        setMenuScreen();
        
        //Opens a litle frame if there's a game which was not finished
        
        if(PuzzleDataManager.psHasSave()){
            int n = JOptionPane.showConfirmDialog(null,"Voulez-vous continuer la partie en cours?","Partie quittée inopinément",JOptionPane.YES_NO_OPTION);
            if(n == 0){//YES
                try {
                    this.level = new Game("PermanSave/permanBoardSave.xsb");
                    this.mov = PuzzleDataManager.psGetMovesSaved();
                    this.readLevel(new GuiGamePanel(this.level, this, this.mov), this.mov);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{//ELSE
                PuzzleDataManager.psBoardReset();
                PuzzleDataManager.psResetSave();
            }
        }
        
        storyChain = new StoryMode(); //initialisation de la chaîne des niveaux
        storyChain.initStory(); //ParserXML
    }
    /**
     * Définit le JPanel à mettre en contentPane de notre JFrame (visible)
     * @param panel le JPanel à mettre en contentPane
     * @param focus s'il requiert le focus ou non
     */
    private void setPane(JPanel panel, boolean focus){
        this.mainPanel = panel;
        this.setContentPane(this.mainPanel);
        this.mainPanel.setFocusable(focus);
        if(focus){
            this.mainPanel.requestFocus();
            this.mainPanel.requestFocusInWindow();
        }
        this.setVisible(true);
    }
    /**
     * Méthode de lecture progressive du niveau
     * Rejoue tous les pas joués dans l'arrayList fourni en paramètre
     * @param gamePanel Le GamePanel dans lequel on va rejouer le mov
     * @param mov l'historique des mouvements à rejouer
     * @throws InterruptedException 
     */
    private void readLevel(GuiGamePanel gamePanel, ArrayList<Integer> mov) throws InterruptedException{
        gamePanel.userEditable = false;
        System.out.println("Live level reading");
        int delay = 250;
        double del = delay/1000;
        
        this.setPane(gamePanel, false);
        System.out.println("EDITABLE:   - "+gamePanel.isUserEditable());
        ClockListener timedMoves = new ClockListener(gamePanel, mov);
        System.out.println("One step each "+del+" seconds");
        Timer t = new Timer(delay, timedMoves);

        t.start();
        this.mainPanel.setFocusable(true);
        this.mainPanel.grabFocus();

    }
    
    /**
     * Action Listener qui rejoue les mouvements
     */
    private class ClockListener implements ActionListener { // Clock Listener class for timing the reading of the map

        private final GuiGamePanel gamePanel; //Current game panel
        private final ArrayList<Integer> movements; //ArrayList from the file
        private int counter = 0;
        
        private ClockListener(GuiGamePanel gamePanel, ArrayList<Integer> movements){
            this.gamePanel = gamePanel;
            this.movements = movements;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {

            if(counter == movements.size()){
                System.out.println(".MOV read successfully");
                gamePanel.userEditable = true;
                Timer t = (Timer)e.getSource();
                t.stop();
            }
            else{
                gamePanel.playReader(movements.get(counter));
                System.out.println(".MOV read: "+movements.get(counter));
                counter++;
            }
        }
    }
    
    /**
     * setWonScreen
     * Affiche un écran de félicitations.
     * Si nous sommes dans un gameMode = 0 (mode Histoire) un bouton s'affiche pour proposer au joueur de lancer le niveau suivant
     * @param steps
     */
    public void setWonScreen(int steps){
        try {
            PuzzleDataManager.psBoardReset();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        PuzzleDataManager.psResetSave();
        GuiBgPanel menu = new GuiBgPanel("gameGraphics/wowBG.jpg");
        menu.setLayout(new BorderLayout());
        GuiBgPanel buttons = new GuiBgPanel("gameGraphics/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        backToMenu = new GuiStdButton("Retour à l'accueil");
        backToMenu.addActionListener(this);  
        if(this.gameMode == 0){
            nextLevel = new GuiLevelSelectorBtn("Niveau suivant...", this.currentLevel.id+1);
            nextLevel.addActionListener(this);
            buttons.add(nextLevel);
        }
        buttons.add(backToMenu);
        
        setPane(menu, true);
    }
    /**
     * affiche le menu d'accueil du jeu
     */
    public void setMenuScreen(){
        GuiBgPanel menu = new GuiBgPanel("gameGraphics/welcomeBG.jpg");
        menu.setLayout(new BorderLayout());
        GuiBgPanel buttons = new GuiBgPanel("gameGraphics/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        story = new GuiStdButton("Mode histoire"); //GAMEMODE 0
        random = new GuiStdButton("Mode aléatoire");//GAMEMODE 1
        loadGame = new GuiStdButton("Charger une partie");//GAMEMODE 2
        exitGame = new GuiStdButton("Quitter le jeu");

        story.addActionListener(this);
        random.addActionListener(this);
        loadGame.addActionListener(this);
        exitGame.addActionListener(this);
        
        buttons.add(story);
        buttons.add(random);
        buttons.add(loadGame);
        buttons.add(exitGame);
        
        setPane(menu, true);
        
    }
    
    public void readStory(){
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        GuiBgPanel menu = new GuiBgPanel("gameGraphics/fade.jpg");
        JPanel blankContainer = new JPanel();
        
        playLevel = new GuiStdButton("Commencer le niveau");
        playLevel.addActionListener(this);
        
        LevelNode tempNode = storyChain.getNode();
        
        final String html1 = "<html><body style='width: ";
        final String html2 = "px'>";
        
        GuiStdLabel text = new GuiStdLabel(html1 + " 350 " + html2 + tempNode.text);
        
        blankContainer.add(text);
        
        menu.setLayout(new GridBagLayout());
        menu.add(blankContainer);
        
        container.add(menu, BorderLayout.CENTER);
        container.add(playLevel, BorderLayout.SOUTH);
        
        setPane(container, true);
    }
    
    public void showStoryLevels(){
        GuiBgPanel menu = new GuiBgPanel("gameGraphics/background.jpg");
        GuiBgPanel buttons = new GuiBgPanel("gameGraphics/steel.jpg");
        LevelNode tempNode = storyChain.getNode();
        
        boolean isLast = false;
        while(!isLast){
            
            GuiLevelSelectorBtn tempButton = new GuiLevelSelectorBtn(""+tempNode.id, tempNode.id);
            tempButton.addActionListener(this);
            
            buttons.add(tempButton);
            tempNode = tempNode.getNextNode();
            
            if(tempNode == null){
                isLast = true;
            }
        }
        
        menu.add(buttons);
        setPane(menu, true);
    }
    /**
     * Permet de récuperer les actions jouées par les boutons
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source == story){ //GAMEMODE 0
            this.gameMode = 0;
            showStoryLevels();
        }
        else if(source == playLevel){
            this.gameMode = 0;
            this.level = currentLevel.getLevel();
            setPane(new GuiGamePanel(this.level, this, this.mov), true);
        }
        else if(source == random){ //GAMEMODE 1 - Marche à suivre pour le mode aléatoire
            try {
                String[] difficulty = { "Facile", "Moyen", "Difficile", "HARDCOOOOORE" };
                String levelDifficultySelector = (String) JOptionPane.showInputDialog(this, "Veuillez sélectionner un niveau de difficulté", "Partie aléatoire", JOptionPane.QUESTION_MESSAGE, null, difficulty, difficulty[1]);
                this.mov = new ArrayList<>();
                if(levelDifficultySelector == null){
                    System.out.println("User didn't select a difficulty.");
                }
                else{
                    switch(levelDifficultySelector){
                        case "Facile":
                            this.level = new Game(PuzzleGenerator.generateBoard(4,4,5));
                            break;
                        case "Moyen":
                            this.level = new Game(PuzzleGenerator.generateBoard(4,4,7));
                            break;
                        case "Difficile":
                            this.level = new Game(PuzzleGenerator.generateBoard(5,5,10));
                            break;
                        case "HARDCOOOOORE":
                            this.level = new Game(PuzzleGenerator.generateBoard(5,5,15));
                            break; 
                    }
                    this.gameMode = 1;
                    this.setPane(new GuiGamePanel(this.level, this, this.mov), true);
                }
            } catch (IOException ex) {
                Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == loadGame){ //GAMEMODE 2

            JFileChooser xsb = new JFileChooser();
            xsb.setFileFilter(new FileNameExtensionFilter("Fichier du niveau (xsb)","xsb"));
            xsb.setDialogTitle("Choisissez un fichier xsb");
            xsb.setCurrentDirectory(new File(System.getProperty("user.home")));
            int xsbResult = xsb.showOpenDialog(this.getContentPane());

            if (xsbResult == JFileChooser.APPROVE_OPTION) {

                try {

                    File levelFile = xsb.getSelectedFile(); //XSB FILE
                    this.level = new Game(levelFile.getPath());
                    int n = JOptionPane.showConfirmDialog(null,"Voulez-vous charger une sauvegarde?","Charger un .mov",JOptionPane.YES_NO_OPTION);
                    
                    if(n == 0){//oui

                        JFileChooser movFileChooser = new JFileChooser(); 
                        movFileChooser.setFileFilter(new FileNameExtensionFilter("Fichier de sauvegarde (mov)", "mov"));
                        movFileChooser.setDialogTitle("Choisissez un fichier de sauvegarde mov");
                        movFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        int movResult = movFileChooser.showOpenDialog(this.getContentPane());

                        if (movResult == JFileChooser.APPROVE_OPTION){
                            File saveFile = movFileChooser.getSelectedFile();//MOV FILE
                            
                            this.mov = getMovesSaved(saveFile.getPath());//Getting ArrayList from file
                            System.out.println(saveFile.getPath());

                            if(this.mov.isEmpty()){
                                System.out.println("File was empty...");
                                this.gameMode = 2;
                                this.setPane(new GuiGamePanel(this.level, this, this.mov), true);//If ArrayList is empty then let the player play
                            }

                            else{
                                System.out.println("Ok reading "+saveFile.getPath());
                                this.gameMode = 2;
                                this.readLevel(new GuiGamePanel(this.level, this, this.mov), this.mov);//Else let's read the level step by step
                            }   
                        }
                    }
                    else{
                        this.setPane(new GuiGamePanel(this.level, this, this.mov), true);
                    } 
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(source == exitGame){
            System.exit(0);
        }
        else if(source == backToMenu){
            setMenuScreen();
            this.mov = new ArrayList<>();
        }
        else if(source.getClass() == GuiLevelSelectorBtn.class){
            
            GuiLevelSelectorBtn btn = (GuiLevelSelectorBtn) source;
            
            int id = btn.node;
            
            while(storyChain.getNode().id < id){
                storyChain.goNextNode();
            }
            
            currentLevel = storyChain.getNode();
            readStory();
        }

        
    }
    
    /**
     * Affiche un JOptionPane d'information
     * @param message le message à afficher
     * @param title le titre du JOptionPane
     */
    public static void guiInformFrame(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
