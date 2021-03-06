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
    
    /**
     * GuiFrame
     * Fenêtre du jeu
     * Principale interface entre l'utilisateur et le jeu
     * @throws IOException en cas d'erreur de chargement du fichier .mov ou .xsb de la PermanentSave
     */
    public GuiFrame() throws IOException{
		
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pour quitter l'execution du programme avec la croix en haut a droite
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 	//Pour mettre en plein ecran
        this.setUndecorated(true); //Pour retirer les bordures et les menus contextuels (sous Windows ça fait un vrai mode fullscreen)
        storyChain = new StoryMode(); //initialisation de la chaîne des niveaux
        setMenuScreen();
        
        //Si on a quitte le jeu alors qu'un niveau etait en cours d'execution, on affiche une petite fenêtre pour savoir si le joueur veut continuer son niveau
        
        if(PuzzleDataManager.psHasSave()){ //Methodes de Corentin pour savoir si une partie en cours est existante
			
            int n = JOptionPane.showConfirmDialog(null,"Voulez-vous continuer la partie en cours?","Partie quittee inopinement",JOptionPane.YES_NO_OPTION); // On affiche un JOptionPane. L'entier "n" contiendra la reponse de l'utilisateur
            
            if(n == 0){//Si le joueur veut continuer la partie
				
                try {
					
                    this.level = new Game("PermanSave/permanBoardSave.xsb"); //On charge la map sauvegardee dans la permanentSave
                    this.mov = PuzzleDataManager.psGetMovesSaved(); //On charge le .mov qui lui est assoscie sous forme d'ArrayList grâce a la methode de Corentin
                    this.readLevel(new GuiGamePanel(this.level, this, this.mov), this.mov); //On lit l'avancement en mode cinematique
                    
                } 
                catch (InterruptedException ex) {
					
                    Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex); //S'il y a une erreur au niveau de la lecture du niveau on catch une exception
                    
                }
            }
            else{//Sinon 
				
                PuzzleDataManager.psBoardReset(); //On reset le board de la PermanentSave
                PuzzleDataManager.psResetSave(); //On reset le .mov de la PermanentSave
                
            }
        }
    }
    
    /**
     * Definit le JPanel a mettre en contentPane de notre JFrame (visible)
     * @param panel le JPanel a mettre en contentPane
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
     * Methode de lecture progressive du niveau
     * Rejoue tous les pas joues dans l'arrayList fourni en parametre
     * @param gamePanel Le GamePanel dans lequel on va rejouer le mov
     * @param mov l'historique des mouvements a rejouer
     * @throws InterruptedException 
     */
     
    private void readLevel(GuiGamePanel gamePanel, ArrayList<Integer> mov) throws InterruptedException{
		
        gamePanel.userEditable = false;
        int delay = 250;
        double del = delay/1000;
        this.setPane(gamePanel, false);
        ClockListener timedMoves = new ClockListener(gamePanel, mov);
        Timer t = new Timer(delay, timedMoves);
        t.start();
        this.mainPanel.setFocusable(true);
        this.mainPanel.grabFocus();

    }
    
    /**
     * Action Listener qui rejoue les mouvements
     * @param gamePanel le panel de la partie (pour y faire les deplacements)
     * @param movements l'arrayList de mouvements a effectuer
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
     * Affiche un ecran de felicitations.
     * Si nous sommes dans un gameMode = 0 (mode Histoire) un bouton s'affiche pour proposer au joueur de lancer le niveau suivant
     * @param steps le nombre de pas qu'il a fallu pour terminer le niveau
     */
    public void setWonScreen(int steps){
		
        try {
			
            PuzzleDataManager.psBoardReset();
            
        } 
        catch (FileNotFoundException | UnsupportedEncodingException ex) {
			
            Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        PuzzleDataManager.psResetSave();
        GuiBgPanel menu = new GuiBgPanel("run/frontend/misc/grph/wowBG.jpg");
        menu.setLayout(new BorderLayout());
        GuiBgPanel buttons = new GuiBgPanel("run/frontend/misc/grph/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        backToMenu = new GuiStdButton("Retour a l'accueil");
        backToMenu.addActionListener(this);  
        buttons.add(backToMenu);
        
        if(this.gameMode == 0){
			
            this.storyChain.updateSave(steps);
            nextLevel = new GuiLevelSelectorBtn("Niveau suivant...", this.currentLevel.id+1, true);
            nextLevel.addActionListener(this);
            buttons.add(nextLevel);
            
        }
        else if(this.gameMode == 1){
            
        }
        
        
        setPane(menu, true);
    }
    /**
     * affiche le menu d'accueil du jeu en remplacant le JPanel actuel par celui du menu
     */
    public void setMenuScreen(){
		
        GuiBgPanel menu = new GuiBgPanel("run/frontend/misc/grph/welcomeBG.jpg");
        menu.setLayout(new BorderLayout());
        GuiBgPanel buttons = new GuiBgPanel("run/frontend/misc/grph/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        this.storyChain.initStory();
        //On initialise les boutons enregistres en variable de classe. Plus facile pour y affecter des actions
        story = new GuiStdButton("Mode histoire"); //GAMEMODE 0
        random = new GuiStdButton("Mode aleatoire");//GAMEMODE 1
        loadGame = new GuiStdButton("Charger une partie");//GAMEMODE 2
        exitGame = new GuiStdButton("Quitter le jeu");
        //On leur ajoute l'actionListener
        story.addActionListener(this);
        random.addActionListener(this);
        loadGame.addActionListener(this);
        exitGame.addActionListener(this);
        //On les ajoute au JPANEL
        buttons.add(story);
        buttons.add(random);
        buttons.add(loadGame);
        buttons.add(exitGame);
        //On rend le menu visible sur le Frame
        setPane(menu, true);
        
    }
    
    /**
     * Affiche le texte lie a un niveau. Le niveau en question est un LevelNode
     * Ce LevelNode est enregistre en variable de classe (pas de parametre a passer)
     */
    
    public void readStory(){
		
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        GuiBgPanel menu = new GuiBgPanel("run/frontend/misc/grph/fade.jpg");
        JPanel blankContainer = new JPanel();
        
        playLevel = new GuiStdButton("Commencer le niveau");
        playLevel.addActionListener(this);
        
        LevelNode tempNode = storyChain.getNode();
        
        GuiStdLabel text = new GuiStdLabel("<html> <body style='width: 350 px'>"+ tempNode.text);
        
        blankContainer.add(text);
        
        menu.setLayout(new GridBagLayout());
        menu.add(blankContainer);
        
        container.add(menu, BorderLayout.CENTER);
        container.add(playLevel, BorderLayout.SOUTH);
        
        setPane(container, true);
    }
    
    /**
     * Affiche les niveaux du mode Classic en remplacant le JPanel actuel par celui des niveaux du mode histoire
     */
     
    public void showStoryLevels(){
        GuiBgPanel menu = new GuiBgPanel("run/frontend/misc/grph/welcomeBG.jpg");
        GuiBgPanel buttons = new GuiBgPanel("run/frontend/misc/grph/steel.jpg");
        LevelNode tempNode = storyChain.getNode();
        
        boolean isLast = false;
        while(!isLast){
            
            GuiLevelSelectorBtn tempButton = new GuiLevelSelectorBtn(""+tempNode.id, tempNode.id, tempNode.doable);
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
     * Permet de recuperer les actions jouees par les boutons
     * @param ae l'action event (les boutons des menus declanchent des actions)
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
        else if(source == random){ //GAMEMODE 1 - Marche a suivre pour le mode aleatoire
            try {
                String[] difficulty = { "Facile", "Moyen", "Difficile", "HARDCOOOOORE" };
                String levelDifficultySelector = (String) JOptionPane.showInputDialog(this, "Veuillez selectionner un niveau de difficulte", "Partie aleatoire", JOptionPane.QUESTION_MESSAGE, null, difficulty, difficulty[1]);
                this.mov = new ArrayList<>();
                if(levelDifficultySelector == null){
                    System.out.println("User didn't select a difficulty.");
                }
                else{
                    switch(levelDifficultySelector){
                        case "Facile":
                            this.level = new Game(PuzzleGenerator.generateBoard(2,2,5));
                            break;
                        case "Moyen":
                            this.level = new Game(PuzzleGenerator.generateBoard(3,3,8));
                            break;
                        case "Difficile":
                            this.level = new Game(PuzzleGenerator.generateBoard(4,3,11));
                            break;
                        case "HARDCOOOOORE":
                            this.level = new Game(PuzzleGenerator.generateBoard(5,5,16));
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
     * @param message le message a afficher
     * @param title le titre du JOptionPane
     */
    public static void guiInformFrame(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
