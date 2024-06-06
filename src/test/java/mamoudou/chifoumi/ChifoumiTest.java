package mamoudou.chifoumi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChifoumiTest {

    Chifoumi getChifoumi(){
        return new Chifoumi();
    }
    @Test
    void validGetComputerChoiceTest(){
        String choice =  this.getChifoumi().getComputerChoice();
        ArrayList<String> options = new ArrayList<>(Arrays.asList("rock", "paper", "cisor"));
        Assertions.assertTrue(options.contains(choice), "Le choix doit être entre "+options);
    }
}