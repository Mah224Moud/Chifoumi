/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mamoudou.chifoumi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.BorderFactory;
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

    JButton rock, paper, scissor, game, reset, left, right;

    ArrayList<JButton> choices;
    ArrayList<String> options = new ArrayList<>(Arrays.asList("rock", "paper", "scissor"));

    int uScore, cScore = 0;

    public Chifoumi() {
        initSpecs();
        initComponents();
        initGameBord();
        initGame();
        actions();
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

        computerScoreLabel = new JLabel("Ordinteur: " + cScore, JLabel.CENTER);
        yourScoreLabel = new JLabel("Toi: " + uScore, JLabel.CENTER);
        intro = new JLabel("Fais ton choix entre Pierre , Papier et Ciseaux️", JLabel.CENTER);
        result = new JLabel("", JLabel.CENTER);

        centerTop = new JPanel();
        centerTopChoices = new JPanel();
        centerCenter = new JPanel();
        centerResults = new JPanel();

        rock = new JButton();
        paper = new JButton();
        scissor = new JButton();
        left = new JButton();
        right = new JButton();
        game = new JButton("Commencer");
        reset = new JButton("Recommencer");

        rock.setName("rock");
        paper.setName("paper");
        scissor.setName("scissor");

        choices = new ArrayList<>();
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
        centerTopChoices.add(scissor);

        centerCenter.setLayout(new BorderLayout());
        centerCenter.add(result, BorderLayout.NORTH);
        centerCenter.add(centerResults, BorderLayout.CENTER);

        centerResults.setLayout(new GridLayout(1, 2));

        buttom.setLayout(new GridLayout(1, 2));
        buttom.add(game);
        buttom.add(reset);

        labelCustomer(intro, 25, 25);
        labelCustomer(result, 25, 10);
        labelCustomer(computerScoreLabel, 20, 5);
        labelCustomer(yourScoreLabel, 20, 5);

        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(buttom, BorderLayout.SOUTH);
    }

    public void initGame() {
        reset.setEnabled(false);
        setImages(false);
        choices.add(rock);
        choices.add(paper);
        choices.add(scissor);
    }

    public void initResult(String computer, String you) {
        centerResults.add(left);
        centerResults.add(right);

        setImage(left, computer, true);
        setImage(right, you, true);

        String check = checkWins(computer, you);
        wins(check, computer, you);
    }

    public void resetResult() {
        centerResults.removeAll();
        result.setText("");
        setImages(true);
        reset.setEnabled(true);
    }

    public void setImage(JButton button, String picture, boolean status) {
        if (picture != null) {
            String path = "src/main/java/mamoudou/chifoumi/assets/" + picture + ".png";
            ImageIcon originalIcon = new ImageIcon(path);

            int width = button.getWidth();
            int height = button.getHeight();
            if (width > 0 && height > 0) {
                Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(resizedImage));
            } else {
                button.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        int newWidth = button.getWidth();
                        int newHeight = button.getHeight();
                        if (newWidth > 0 && newHeight > 0) {
                            Image resizedImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                            button.setIcon(new ImageIcon(resizedImage));
                            button.setEnabled(status);
                            button.removeComponentListener(this);
                        }
                    }
                });
            }
        }
        button.setEnabled(status);
    }

    public void setImages(boolean status) {
        setImage(scissor, "scissor", status);
        setImage(rock, "rock", status);
        setImage(paper, "paper", status);
    }

    public void actions() {
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                setImages(true);
                reset.setEnabled(true);
                game.setEnabled(false);
            }

        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                resetGame();
                reset.setEnabled(false);
            }

        });

        for (JButton choice : choices) {
            choice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent action) {
                    String computer = getComputerChoice();
                    String you = choice.getName();
                    resetResult();
                    initResult(computer, you);
                }
            });
        }
    }

    public String getComputerChoice() {
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());
        return options.get(randomIndex);
    }

    public String checkWins(String computer, String user) {
        if ((computer.equals("rock") || user.equals("rock")) && (computer.equals("scissor") || user.equals("scissor"))) {
            return "rock";
        }
        if ((computer.equals("scissor") || user.equals("scissor")) && (computer.equals("paper") || user.equals("paper"))) {
            return "scissor";
        }
        if ((computer.equals("paper") || user.equals("paper")) && (computer.equals("rock") || user.equals("rock"))) {
            return "paper";
        }

        return "equality";
    }

    public void wins(String checkResult, String computer, String user) {

        if (checkResult.equals(computer) && !checkResult.equals(user)) {
            result.setText("Victoire de l'ordinateur !!!");
            cScore += 1;
        }
        if (!checkResult.equals(computer) && checkResult.equals(user)) {
            result.setText("Vous avez gagné !!!");
            uScore += 1;
        }

        if (checkResult.equals("equality")) {
            result.setText("Vous êtes à égalité !!!");
        }

        computerScoreLabel.setText("Ordinteur: " + cScore);
        yourScoreLabel.setText("Toi: " + uScore);
    }

    public void resetGame() {
        uScore = 0;
        cScore = 0;
        computerScoreLabel.setText("Ordinteur: " + cScore);
        yourScoreLabel.setText("Toi: " + uScore);
        resetResult();
    }

    public String getResult() {
        return result.getText();
    }

    public static void main(String[] args) {
        Chifoumi chifoumi = new Chifoumi();
        chifoumi.setVisible(true);
    }

}
