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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Louis Dhanis
 */
public class GuiFrame extends JFrame implements ActionListener{
    
    private JPanel mainPanel = null;
    private GuiBgButton story = null;
    private GuiBgButton random = null;
    private GuiBgButton loadGame = null;
    private GuiBgButton exitGame = null;
    private GuiBgButton backToMenu = null;
    private Game level = null;
    private ArrayList<Integer> mov = null;
    private boolean storyMode = false;

    
    public GuiFrame() throws IOException{
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        setMenuScreen();
        if(PuzzleDataManager.psHasSave()){
            int n = JOptionPane.showConfirmDialog(null,"Voulez-vous continuer la partie en cours?","Partie quittée inopinément",JOptionPane.YES_NO_OPTION);
            if(n == 0){//oui
                try {
                    this.level = new Game("PermanSave/permanBoardSave.xsb");
                    this.mov = PuzzleDataManager.psGetMovesSaved();
                    this.readLevel(new GuiGamePanel(this.level, this, this.mov), this.mov);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                PuzzleDataManager.psBoardReset();
                PuzzleDataManager.psResetSave();
            }
        }
    }
    
    private void setPane(JPanel panel, boolean focus){
        this.mainPanel = panel;
        this.mainPanel.setFocusable(focus);
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }
    
    private void readLevel(GuiGamePanel gamePanel, ArrayList<Integer> mov) throws InterruptedException{
        
        System.out.println("Live level reading");
        
        this.setPane(gamePanel, false);
        ClockListener timedMoves = new ClockListener(gamePanel, mov);
        System.out.println("One step each 0.2 seconds");
        Timer t = new Timer(200, timedMoves);

        t.start();
        this.mainPanel.setFocusable(true);
        this.mainPanel.grabFocus();
    }
    
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
    
    public void setWonScreen(int steps){
        try {
            PuzzleDataManager.psBoardReset();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        PuzzleDataManager.psResetSave();
        SpeedyBackground menu = new SpeedyBackground("gameGraphics/wowBG.jpg");
        menu.setLayout(new BorderLayout());
        SpeedyBackground buttons = new SpeedyBackground("gameGraphics/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        if(storyMode == true){
            story = new GuiBgButton("Niveau suivant");
            story.addActionListener(this);       
            buttons.add(story);
        }
        
        backToMenu = new GuiBgButton("Retour à l'accueil");
        backToMenu.addActionListener(this);       
        buttons.add(backToMenu);
        
        setPane(menu, true);
    }
    
    private void setMenuScreen(){
        SpeedyBackground menu = new SpeedyBackground("gameGraphics/welcomeBG.jpg");
        menu.setLayout(new BorderLayout());
        SpeedyBackground buttons = new SpeedyBackground("gameGraphics/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        story = new GuiBgButton("Mode histoire");
        random = new GuiBgButton("Mode aléatoire");
        loadGame = new GuiBgButton("Charger une partie");
        exitGame = new GuiBgButton("Quitter le jeu");

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
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source == story){
            
        }
        else if(source == random){
            try {
                String[] difficulty = { "Facile", "Moyen", "Difficile", "HARDCOOOOORE" };
                String levelDifficultySelector = (String) JOptionPane.showInputDialog(this, "Veuillez sélectionner un niveau de difficulté", "Partie aléatoire", JOptionPane.QUESTION_MESSAGE, null, difficulty, difficulty[1]);
                this.mov = new ArrayList<Integer>();
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
                this.setPane(new GuiGamePanel(this.level, this, this.mov), true);
            } catch (IOException ex) {
                Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == loadGame){ 

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
                                this.setPane(new GuiGamePanel(this.level, this, this.mov), true);//If ArrayList is empty then let the player play
                            }

                            else{
                                System.out.println("Ok reading "+saveFile.getPath());
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
        }
        
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
