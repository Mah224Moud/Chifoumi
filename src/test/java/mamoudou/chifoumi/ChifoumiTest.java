package mamoudou.chifoumi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChifoumiTest {
    ArrayList<String> options;
    Chifoumi chifoumi;

    @BeforeEach
    void setUp() {
        options = new ArrayList<>(Arrays.asList("rock", "paper", "scissor"));
        chifoumi = new Chifoumi();
    }

    @Test
    void validGetComputerChoiceTest(){
        for (int i = 0; i < 100; i++) {
            String choice =  chifoumi.getComputerChoice();
            assertTrue(options.contains(choice), "Le choix doit être parmi " + options);
        }
    }

    @Test
    void initGameTest(){
        int length = chifoumi.choices.size();
        assertFalse(chifoumi.reset.isEnabled(), "Le bouton reset doit être désactivé avant le démarrage");
        assertEquals(3, length, "Il doit y avoir 3 choix");
    }

    @Test
    void checkWinsEqualityTest(){
        for (String element: options){
            String value = chifoumi.checkWins(element, element);
            assertEquals("equality", value, "Il ne doit pas y avoir de victoire");
        }
    }

    @Test
    void checkWinsRockVsPaperTest(){
        String paper = chifoumi.checkWins("rock", "paper");
        assertEquals("paper", paper, "Le vainquer doit etre Papier");
    }
    @Test
    void checkWinsPaperVsRockTest(){
        String paper = chifoumi.checkWins("paper", "rock");
        assertEquals("paper", paper, "Le vainquer doit etre Papier");
    }
    @Test
    void checkWinsScissorVsRockTest(){
        String rock = chifoumi.checkWins("scissor", "rock");
        assertEquals("rock", rock, "Le vainquer doit etre Pierre");
    }
    @Test
    void checkWinsRockVsScissorTest(){
        String rock = chifoumi.checkWins("rock", "scissor");
        assertEquals("rock", rock, "Le vainquer doit etre Pierre");
    }
    @Test
    void checkWinsScissorVsPaperTest(){
        String scissor = chifoumi.checkWins("scissor", "paper");
        assertEquals("scissor", scissor, "Le vainquer doit etre Ciseau");
    }
    @Test
    void checkWinsPaperVsScissorTest(){
        String scissor = chifoumi.checkWins("paper", "scissor");
        assertEquals("scissor", scissor, "Le vainquer doit etre Ciseau");
    }
    @Test
    void computerWinsWithRockTest(){
        String rock = chifoumi.checkWins("rock", "scissor");
        chifoumi.wins(rock, "rock", "scissor");
        String result = "Victoire de l'ordinateur !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'ordinateur !!!");
    }
    @Test
    void computerWinsWithPaperTest(){
        String paper = chifoumi.checkWins("paper", "rock");
        chifoumi.wins(paper, "paper", "rock");
        String result = "Victoire de l'ordinateur !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'ordinateur !!!");
    }
    @Test
    void computerWinsWithScissorTest(){
        String scissor = chifoumi.checkWins("scissor", "paper");
        chifoumi.wins(scissor, "scissor", "paper");
        String result = "Victoire de l'ordinateur !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'ordinateur !!!");
    }
    @Test
    void userWinsWithScissorTest(){
        String scissor = chifoumi.checkWins("paper", "scissor");
        chifoumi.wins(scissor, "paper", "scissor");
        String result = "Vous avez gagné !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'utilisateur !!!");
    }
    @Test
    void userWinsWithRockTest(){
        String rock = chifoumi.checkWins("scissor", "rock");
        chifoumi.wins(rock, "scissor", "rock");
        String result = "Vous avez gagné !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'utilisateur !!!");
    }
    @Test
    void userWinsWithPaperTest(){
        String paper = chifoumi.checkWins("rock", "paper");
        chifoumi.wins(paper, "rock", "paper");
        String result = "Vous avez gagné !!!";
        assertEquals(result, chifoumi.getResult(), "La victoire de  doit être attribué à l'utilisateur !!!");
    }
    @Test
    void equalityWinsTest(){
        for (String element: options){
            String choice = chifoumi.checkWins(element, element);
            chifoumi.wins(choice, element, element);
            String result = "Vous êtes à égalité !!!";
            assertEquals(result, chifoumi.getResult(), "Personne ne gagne il y'a égalité !!!");
        }
    }
    @Test
    void resetGameTest(){
        chifoumi.resetGame();
        assertEquals(0, chifoumi.uScore);
        assertEquals(0, chifoumi.cScore);
        assertEquals("Ordinteur: 0", chifoumi.computerScoreLabel.getText());
        assertEquals("Toi: 0", chifoumi.yourScoreLabel.getText());
    }
    @Test
    void resetResultTest(){
        chifoumi.resetResult();
        assertTrue(chifoumi.reset.isEnabled());
        assertEquals(0, chifoumi.centerResults.getComponentCount());
        assertEquals(0, chifoumi.cScore);
        assertEquals("", chifoumi.getResult());
    }
}