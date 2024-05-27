/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mamoudou.chifoumi;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author mamoudou
 */
public class Chifoumi extends JFrame{
    
    public Chifoumi(){
        initSpecs();
    }
    
    public void initSpecs(){
        this.pack();
        this.setTitle("Chifoumi");
        this.setLocation(500, 100);
        this.setMinimumSize(new Dimension(800, 800));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Chifoumi chifoumi = new Chifoumi();
        chifoumi.setVisible(true);
    }
}
