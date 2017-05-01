/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;

import backend.Game;
import backend.PuzzleDataManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Classe de la partie en cours
 * @author Louis Dhanis
 */
public class GuiGamePanel extends JPanel implements ActionListener, KeyListener{
    private final ArrayList<ArrayList<GuiElemButton>> elementArrayList;
    public boolean userEditable = true;
    private final int length;
    private final int width;
    private final Game game;
    private final GuiBgPanel infos;
    private final JPanel gameContainer;
    private final GuiBgPanel wrapper;
    private final GuiStdLabel steps;
    private final GuiStdButton exitGame;
    private final GuiStdButton saveGame;
    private final GuiFrame container;
    private final ArrayList<Integer> moveHistory;
    
    /**
     * Constructeur de la partie
     * @param game (le niveau à jouer, c'est un objet du type Game)
     * @see Game
     * @param container (le JFrame contenant la partie, pour pouvoir le modifier depuis la partie courante)
     * @param moves (l'arrayList contenant l'historique des mouvements)
     */
    public GuiGamePanel(Game game, GuiFrame container, ArrayList<Integer> moves){
        this.moveHistory = moves;
        this.container = container;
        this.wrapper = new GuiBgPanel("gameGraphics/fade.jpg");
        this.grabFocus();
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());
        this.infos = new GuiBgPanel("gameGraphics/steel.jpg");
        this.gameContainer = new JPanel();
        //Récupérons le plateau sous forme de tableau de caractère
        //Pour stocker chaque élément dans un ArrayList<ArrayList<GuiElement>>
        //ça sera plus facile pour modifier leur contenu par la suite
        this.game = game;
        this.steps = new GuiStdLabel(""+this.game.getNbSteps());
        
        char[][] initBoard = this.game.getRepr();
        
        try {
            //Stockons ce tableau dans la permanentSave (si l'utilisateur quitte inopinément la partie, le niveau sera stocké dans le dossier PermanentSave)
            PuzzleDataManager.psSetBoard(initBoard);
            
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(GuiGamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //parcourons ce tableau et stockons chaque élément dans l'arrayList d'arrayList
        this.elementArrayList = new ArrayList<ArrayList<GuiElemButton>>();
        
        //Paramétrons le JPanel interne
        width = initBoard.length;
        length = initBoard[0].length;
        this.exitGame = new GuiStdButton("Exit game...");
        this.exitGame.addActionListener(this);
        this.saveGame = new GuiStdButton("Save .mov");
        this.saveGame.addActionListener(this);
        this.gameContainer.setSize(width*50, length*50);
        this.setSize(length*50 + 200, width*50 + 200);
        this.gameContainer.setPreferredSize(new Dimension(length*50, width*50));
        this.gameContainer.setLayout(new GridLayout(width, length));
        
        for(int i = 0; i < initBoard.length; i++){
            elementArrayList.add(new ArrayList<GuiElemButton>());
            for(int j = 0; j < initBoard[i].length; j++){
                
                elementArrayList.get(i).add(new GuiElemButton(initBoard[i][j], j, i));
                this.gameContainer.add(elementArrayList.get(i).get(j));
                elementArrayList.get(i).get(j).addActionListener(this);
            }
        }
        this.wrapper.setLayout(new GridBagLayout());
        this.wrapper.add(this.gameContainer);
        this.infos.add(this.steps);
        this.infos.add(this.exitGame);
        this.infos.add(this.saveGame);
        this.add(this.infos, BorderLayout.NORTH);     
        this.add(this.wrapper, BorderLayout.CENTER);
    }
    
    public void setUserEditable(boolean edit){
        this.userEditable = edit;
    }
    
    public boolean isUserEditable(){
        return this.userEditable;
    }
    
    /**
     * Permet de sauvegarder la partie au format mov à tout moment de la partie
     */
    public void saveState(){
        // Code inspiré de https://stackoverflow.com/questions/356671/jfilechooser-showsavedialog-how-to-set-suggested-file-name
        // et de https://stackoverflow.com/questions/14589386/how-to-save-file-using-jfilechooser-in-java
        //String sb = moveHistory();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home"));
        chooser.setSelectedFile(new File("sauvegarde.mov"));
        
        int retrival = chooser.showSaveDialog(null);

        if (retrival == JFileChooser.APPROVE_OPTION) {
            File saveFile = chooser.getSelectedFile();
            String s = saveFile.getPath();
            try {
                PuzzleDataManager.updateSave(s, this.moveHistory);
            } catch (IOException ex) {
                Logger.getLogger(GuiGamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Méthode PlayReader pour jouer un mouvement depuis le JFrame (lecture du .mov pour le "replay" de la partie sauvegardée)
     * @param i le mouvement à jouer
     */
    public void playReader(int i){
        switch(i){
            case 0:
                this.moveKey(0, 1, false);
                break;
            case 2:
                this.moveKey(0, -1, false);
                break;
            case 3:
                this.moveKey(-1, 0, false);
                break;
            case 1:
                this.moveKey(1, 0, false);
                break;
                
        }
    }
    
    /**
     * Rafraîchir le JPanel
     * affiche le jeu en son état actuel dans sa représentation en tableau de caractère
     */
    public void updatePanel(){
        
        char[][] initBoard = this.game.getRepr();
        for(int i = 0; i < initBoard.length; i++){
            elementArrayList.add(new ArrayList<GuiElemButton>());
            for(int j = 0; j < initBoard[i].length; j++){
                if(initBoard[i][j] != elementArrayList.get(i).get(j).getContent()){
                    elementArrayList.get(i).get(j).setContent(initBoard[i][j]);
                }
            }
        }
        
        this.steps.setText(""+this.game.getNbSteps());
        
        if(this.gameWon()){
            this.container.setWonScreen(this.game.getNbSteps());
        }
    
    }   
    
    /**
     * Renvoie l'historique des mouvements sous forme de chaîne de caractère
     * @deprecated 
     * @return historique des mouvements
     */
    public String moveHistory(){
        String str = "";
        for (Object move : this.moveHistory) {
            str = str + move;
        }
        return str;
    }
    
    /**
     * Vérifie si la partie est gagnée ou non
     * @return true si la partie est gagnée, false sinon
     */
    public boolean gameWon(){
        return this.game.isGameWon();
    }
    
    /**
     * Modifie la position du joueur via le KeyListener ou via le déplacement en lecture du MOV
     * @param x la direction en x
     * @param y la directon en y
     * @param save true s'il faut enregistrer le mouvement dans l'arrayList (ce n'est pas le cas si la méthode est appelée pour déplacer via la lecture du MOV)
     */
    private void moveKey(int x, int y, boolean save){
        int move = this.game.movePlayer(x,y);
        if(move >= 0 && save == true){
            this.moveHistory.add(move);
            try{
                PuzzleDataManager.psUpdateSave(move);
            }
            catch(Exception e){}
        }
        this.updatePanel();
        this.setVisible(true);
    }
    
    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        System.out.println(this.userEditable);
        if(source.getClass() == GuiElemButton.class && this.userEditable){
            GuiElemButton temp = (GuiElemButton)source;
            int move = this.game.movePlayerMouse(temp.getPosX(), temp.getPosY());
            if(move >= 0){
                this.moveHistory.add(move);
            }
            this.updatePanel();
            this.setVisible(true);
        }
        else if(source == this.exitGame){
            System.exit(0);
        }
        else if(source == this.saveGame){
            saveState();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {

    }
    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println(this.userEditable);
        if(this.userEditable){
            switch(ke.getKeyCode()){
                case 38:
                    this.moveKey(0, 1, true);
                    break;
                case 40:
                    this.moveKey(0, -1, true);
                    break;
                case 37:
                    this.moveKey(-1, 0, true);
                    break;
                case 39:
                    this.moveKey(1, 0, true);
                    break;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
