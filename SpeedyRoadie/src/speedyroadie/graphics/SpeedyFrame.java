/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyroadie.graphics;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Louis Dhanis
 */
public class SpeedyFrame extends JFrame{
    public SpeedyFrame(JPanel panel){
        this.setTitle("SpeedyRoadie!");
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.setSize(new Dimension(600,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
