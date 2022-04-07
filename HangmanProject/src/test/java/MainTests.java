import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class MainTests {
    @Before
    public void StartTest() {
        Main.resetScore();
        Main.word = "assign";
        Collections.addAll(Main.wordArray, Main.word.split(""));
    }

    @Test
    public void guessingCorrectWordShouldReturnMessage() {
        assertEquals(200, Main.letterGuess(Main.word));
        assertTrue(Main.interpretGuess(200, Main.word));
    }


    @Test
    public void guessingTheSameLetterShouldReturnAlreadyGuessed() {
        Main.lettersGuessed.add("b".charAt(0));
        assertEquals(Main.letterGuess("b"), 401);

    }
    @Test
    public void guessingCorrectLetterShouldReplaceDash() {
        for (String ignored : Main.wordArray){
            Main.displayedWord.add("_");
        }
        ArrayList<String> testDisplay = new ArrayList<>();
        for (String ignored : Main.wordArray){
            testDisplay.add("_");
        }
        int i = 0;
        for (String s : Main.wordArray){
            if (s.equals("a")){
                testDisplay.set(i, "a");
            }
            i++;
        }
        assertEquals(201, Main.letterGuess("a"));
        assertFalse(Main.interpretGuess(201, "a"));
        assertEquals(testDisplay, Main.displayedWord);
        assertTrue(Main.lettersGuessed.contains("a".charAt(0)));
    }
    @Test
    public void guessingCorrectlyShouldReturnWinMessage() {
        assertEquals(200, Main.letterGuess(Main.word));
        assertTrue(Main.interpretGuess(200, Main.word));
    }
}