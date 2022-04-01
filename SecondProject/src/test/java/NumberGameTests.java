import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class NumberGameTests {
    @Test
    public void UserGuessShouldReturnTrueWhenNumbersAreTheSame(){
        NumberGame numberGame = new NumberGame();
        assertEquals("true",numberGame.UserGuess(2,2));
    }
    @Test
    public void ShouldReturnTooHighWhenNumberIsGreaterThanAnswer(){
        NumberGame numberGame = new NumberGame();
        assertEquals("Your guess is too high.",numberGame.UserGuess(15,19));
    }
    @Test
    public void ShouldReturnTooHighWhenNumberIsLessThanAnswer(){
        NumberGame numberGame = new NumberGame();
        assertEquals("Your guess is too low.",numberGame.UserGuess(15,3));
    }

}
