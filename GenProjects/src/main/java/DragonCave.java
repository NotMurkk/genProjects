import java.util.Scanner;

public class DragonCave {



    public int validNumber(int sc){
        String fail = "Invalid input, please enter a valid number";
        int input = sc;
        if (input != 1 && input != 2) {
            System.out.println("Invalid input, please enter a valid number");
            return -1;
        }
        return input;
    }


    public static void main(String[] args) {
        System.out.println("You are in a land full of dragons. In front of you,");
        System.out.println("you see two caves. In one cave the dragon is friendly");
        System.out.println("and will share his treasure with you. The other dragon");
        System.out.println("is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");
        int faitChoice = 0;

        Scanner choice = new Scanner(System.in);
        int userChoice = choice.nextInt();
        try {

            if (Math.random() < 0.5) {
                faitChoice = 2;
            } else {
                faitChoice = 1;
            }

            if (userChoice == faitChoice) {
                System.out.println("You are safe! Pass through the cave!");
                System.out.println("Take some free treasure on the way out!");
            } else {
                System.out.println("You approach the cave...");
                System.out.println("It is dark and spooky...");
                System.out.println("A large dragon jumps out in front of you! He opens his jaws and...");
                System.out.println("Gobbles you down in one bite!");
            }
        }catch(Exception e){
            System.out.println("undetermined outcome. Try again.");
        }
    }
}

