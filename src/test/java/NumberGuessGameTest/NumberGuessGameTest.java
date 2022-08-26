package NumberGuessGameTest;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import org.junit.Assert;
import org.junit.Test;


public class NumberGuessGameTest {


@Test
    public void equalsTest() {

        //Given
        int expected = NumberGuessGame.playerGuess;
        int actual =                                                                                                                                                                                                                                                                                                                                       NumberGuessGame.randomNumber;


//When
        NumberGuessGame output = new NumberGuessGame();


// Then
        Assert.assertEquals(expected, actual);
    }
}