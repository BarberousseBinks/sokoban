/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import static backend.PuzzleDataManager.getMovesSaved;
import backend.PuzzleGenerator;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private Game level = null;
    private ArrayList<Integer> mov = null;

    
    public GuiFrame() throws IOException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanel = guiMenu();
        this.mainPanel.setFocusable(true);
        this.setFocusable(false);
        this.setContentPane(this.mainPanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.setVisible(true);
    }
    
    private void setPane(JPanel panel, boolean focus){
        this.mainPanel = panel;
        this.mainPanel.setFocusable(focus);
        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }
    
    private void readLevel(GuiGamePanel gamePanel, ArrayList<Integer> mov) throws InterruptedException{
        this.setPane(gamePanel, false);
        ClockListener move = new ClockListener(gamePanel, mov);
        
        Timer t = new Timer(200, move);

        t.start();
        this.mainPanel.setFocusable(true);
    }
    
    private class ClockListener implements ActionListener {

        private final GuiGamePanel gamePanel;
        private final ArrayList<Integer> movements;
        private int counter = 0;
        
        private ClockListener(GuiGamePanel gamePanel, ArrayList<Integer> movements){
            this.gamePanel = gamePanel;
            this.movements = movements;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {

            if(counter == movements.size()){
                //System.out.println("finito");
                Timer t = (Timer)e.getSource();
                t.stop();
            }
            else{
                gamePanel.playReader(movements.get(counter));
                counter++;
            }
        }
    }
    
    private SpeedyBackground guiMenu(){
        SpeedyBackground menu = new SpeedyBackground("gameGraphics/welcomeBG.jpg");
        menu.setLayout(new BorderLayout());
        SpeedyBackground buttons = new SpeedyBackground("gameGraphics/steel.jpg");
        menu.add(buttons, BorderLayout.SOUTH);
        
        story = new GuiBgButton("Mode histoire");
        random = new GuiBgButton("Mode aléatoire");
        loadGame = new GuiBgButton("Charger une partie");

        story.addActionListener(this);
        random.addActionListener(this);
        loadGame.addActionListener(this);
        
        buttons.add(story);
        buttons.add(random);
        buttons.add(loadGame);
        
        return menu;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source == story){
            
        }
        else if(source == random){
            
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

                        JFileChooser mov = new JFileChooser(); 
                        mov.setFileFilter(new FileNameExtensionFilter("Fichier de sauvegarde (mov)", "mov"));
                        mov.setDialogTitle("Choisissez un fichier de sauvegarde mov");
                        mov.setCurrentDirectory(new File(System.getProperty("user.home")));
                        int movResult = mov.showOpenDialog(this.getContentPane());

                        if (xsbResult == JFileChooser.APPROVE_OPTION){
                            File saveFile = mov.getSelectedFile();//MOV FILE

                            try (BufferedReader moveHistoryReader = new BufferedReader(new FileReader(saveFile))) {
				
				this.mov = getMovesSaved(saveFile.getPath());
                                
                                if(this.mov.isEmpty()){
                                    this.setPane(new GuiGamePanel(this.level, this), true);
                                }

                                else{

                                    this.readLevel(new GuiGamePanel(this.level, this), this.mov);
                                }
                            }   
                        }
                    }
                    else{
                        this.setPane(new GuiGamePanel(this.level, this), true);
                    } 
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
