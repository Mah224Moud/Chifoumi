/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mamoudou.chifoumi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mamoudou
 */
public class Chifoumi extends JFrame {

    JPanel top, center, buttom, centerTop, centerTopChoices, centerCenter, centerResults;

    JLabel computerScoreLabel, yourScoreLabel, intro, result;

    JButton rock, paper, cisor, game, next, left, right;

    public Chifoumi() {
        initSpecs();
        initComponents();
        initGameBord();
        initGame();
    }

    public void initSpecs() {
        this.pack();
        this.setTitle("Chifoumi");
        this.setLocation(500, 100);
        this.setMinimumSize(new Dimension(800, 800));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {
        top = new JPanel();
        center = new JPanel();
        buttom = new JPanel();

        computerScoreLabel = new JLabel("Ordinteur: 0", JLabel.CENTER);
        yourScoreLabel = new JLabel("Toi: 0", JLabel.CENTER);
        intro = new JLabel("Fais ton choix entre Pierre , Papier et Ciseaux️", JLabel.CENTER);
        result = new JLabel("", JLabel.CENTER);

        centerTop = new JPanel();
        centerTopChoices = new JPanel();
        centerCenter = new JPanel();
        centerResults = new JPanel();

        rock = new JButton();
        paper = new JButton();
        cisor = new JButton();
        left = new JButton();
        right = new JButton();
        game = new JButton("Commencer");
        next = new JButton("Continuer");
    }

    public void labelCustomer(JLabel label, int fontSize, int marginTop) {
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        label.setBorder(BorderFactory.createEmptyBorder(marginTop, 5, 5, 5));
    }

    public void initGameBord() {
        top.setLayout(new GridLayout(1, 2));
        top.add(computerScoreLabel);
        top.add(yourScoreLabel);

        center.setLayout(new GridLayout(2, 1));
        center.add(centerTop);
        center.add(centerCenter);

        centerTop.setLayout(new BorderLayout());
        centerTop.add(intro, BorderLayout.NORTH);
        centerTop.add(centerTopChoices, BorderLayout.CENTER);

        centerTopChoices.setLayout(new GridLayout(1, 3));
        centerTopChoices.add(rock);
        centerTopChoices.add(paper);
        centerTopChoices.add(cisor);

        centerCenter.setLayout(new BorderLayout());
        centerCenter.add(result, BorderLayout.NORTH);
        centerCenter.add(centerResults, BorderLayout.CENTER);

        centerResults.setLayout(new GridLayout(1, 2));

        buttom.setLayout(new GridLayout(1, 2));
        buttom.add(game);
        buttom.add(next);

        labelCustomer(intro, 25, 25);
        labelCustomer(result, 25, 10);
        labelCustomer(computerScoreLabel, 20, 5);
        labelCustomer(yourScoreLabel, 20, 5);

        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(buttom, BorderLayout.SOUTH);
    }

    public void initGame() {
        next.setEnabled(false);
        setImages(false);
        
    }

    public void initResult(String message, String computer, String you) {
        centerResults.add(left);
        centerResults.add(right);
        result.setText(message);
        next.setEnabled(true);
        setImage(cisor, you, true);
    }
    
    public void resetResult(){
        centerResults.removeAll();
        result.setText("");
        next.setEnabled(false);
        setImages(true);
    }

    public void setImage(JButton button, String picture, boolean status) {
        if (picture != null) {
            String path = "src/main/java/mamoudou/chifoumi/assets/" + picture + ".png";
            ImageIcon originalIcon = new ImageIcon(path);

            button.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int width = button.getWidth();
                    int height = button.getHeight();
                    if (width > 0 && height > 0) {
                        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        button.setIcon(new ImageIcon(resizedImage));
                    }
                }
            });
            
            button.setEnabled(false);
        }
    }
    
    public void setImages(boolean status){
        setImage(cisor, "cisor", status);
        setImage(rock, "rock", status);
        setImage(paper, "paper", status);
    }

    public static void main(String[] args) {
        Chifoumi chifoumi = new Chifoumi();
        chifoumi.setVisible(true);
    }

}
