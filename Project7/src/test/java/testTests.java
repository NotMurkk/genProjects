import org.junit.Before;
import org.junit.Test;


public class testTests {
    test n = new test();
    @Before
    public void StartTest() {
        test n = new test();
    }

    @Test
    public void leaderBoardShouldPrintTop3() {
        n.printLeaderboard();
    }
    @Test
    public void printStageShouldPrintDefaultStage(){

        n.printStage();
    }
    @Test
    public void losingShouldReturnStage5(){
        n.isLoss();
    }

}