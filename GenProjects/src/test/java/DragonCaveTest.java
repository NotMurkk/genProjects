
import org.junit.Test;
import static org.testng.AssertJUnit.assertEquals;


public class DragonCaveTest {

@Test
    public void passingNumber2ShouldPass(){
    DragonCave dragonCave = new DragonCave();
    assertEquals(dragonCave.validNumber(2),dragonCave.validNumber(2));
}
@Test
    public void passingNumber1ShouldPass(){
    DragonCave dragonCave = new DragonCave();
    assertEquals(dragonCave.validNumber(1),dragonCave.validNumber(1));
    }

    @Test
    public void passingNumber3ShouldFail(){
        DragonCave dragonCave = new DragonCave();
        assertEquals(dragonCave.validNumber(-1),dragonCave.validNumber(3));
    }
    @Test
    public void passingANegativeNumberShouldFail(){
        DragonCave dragonCave = new DragonCave();
        assertEquals(dragonCave.validNumber(-1),dragonCave.validNumber(-1));
    }
}


