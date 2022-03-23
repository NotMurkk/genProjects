
import java.util.InputMismatchException;
import java.util.Scanner;


public class NumberGame {
    public static void main(String[] args) {
        System.out.println("Hello! What is your name?");
        Scanner user = new Scanner(System.in);
        String userName = user.next();
        while (true) {
            System.out.println("Well, " + userName + ", I am thinking of a number between 1 and 20.");
            Short attempts = 0;
            int myNumber = (int) Math.round(Math.random() * 20);
            while (true) {
                try {
                    if (attempts == 6) {
                        System.out.println("Better luck next time! The number was " + myNumber);
                        break;
                    }
                    System.out.println("Take a guess");
                    Short userGuess = user.nextShort();
                    String usersGuess = UserGuess(myNumber, userGuess);
                    if (usersGuess.equals("true")) {
                        System.out.println("Good job, " + userName + "! You guessed my numbner in " +attempts + " guesses!");
                        break;
                    }
                    System.out.println(usersGuess);
                    attempts++;
                } catch (InputMismatchException e) {
                    System.out.println("You must enter a number!");
                    user.next();
                }
            }
            System.out.println("Would you like to play again? (y or n)");
            String userAnswer = user.next();
            if ( userAnswer.equals("n")) {
                break;
            }
        }
        user.close();
        System.exit(0);
    }




    public static String UserGuess(int answer, int userGuess) {
        if (userGuess == answer) {
            return "true";
        } else if (userGuess > answer) {
            return "You guess is too high.";
        } return "You guess is too low.";
    }
}