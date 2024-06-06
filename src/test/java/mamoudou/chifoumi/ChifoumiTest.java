package mamoudou.chifoumi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChifoumiTest {
    ArrayList<String> options = new ArrayList<>(Arrays.asList("rock", "paper", "cisor"));
    Chifoumi getChifoumi(){
        return new Chifoumi();
    }
    @Test
    void validGetComputerChoiceTest(){
        for (int i = 0; i < 100; i++) {
            String choice =  this.getChifoumi().getComputerChoice();
            assertTrue(options.contains(choice), "Le choix doit être parmi " + options);
        }
    }

    @Test
    void initGameTest(){
        int length = this.getChifoumi().choices.size();
        assertFalse(this.getChifoumi().reset.isEnabled(), "Le bouton reset doit être désactivé avant le démarrage");
        assertEquals(3, length, "Il doit y avoir 3 choix");
    }

    @Test
    void checkWinsTest(){
        String paper = this.getChifoumi().checkWins("rock", "paper");
        String rock = this.getChifoumi().checkWins("rock", "cisor");
        String cisor = this.getChifoumi().checkWins("paper", "cisor");
        assertEquals("paper", paper, "Le vainquer doit etre Papier");
        assertEquals("rock", rock, "Le vainquer doit etre Pierre");
        assertEquals("cisor", cisor, "Le vainquer doit etre Ciseau");
        for (String element: options){
            String value = this.getChifoumi().checkWins(element, element);
            assertEquals("Egalité", value, "Il ne doit pas y avoir de victoire");
        }
    }
}