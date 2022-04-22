public class Main {
    public static void main(String[] args) {
        // write your code here
        test game = new test();
        game.start();
        while (!game.gameIsDone) {
            game.play();
        }
    }
}
