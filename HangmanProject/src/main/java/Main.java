import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    public static final String[] wordList = { "assert", "assign", "assignment", "attention", "awareness", "between", "border", "brush", "business",
            "cabinet", "calculate", "camera", "capable", "carefully", "century", "champion", "character", "chemical", "childhood", "complain", "declare",
            "degree", "dependent", "dialogue", "door", "ease", "eat", "economy", "egg", "elite", "exercise", "favor", "fence","figure", "flower", "forest",
            "future", "ghost", "grass", "guilty", "habitat", "hand", "happen", "happy", "heart", "highlight", "horizon",
            "husband", "imagine", "income", "index", "inform", "inspire", "insurance", "joke", "jump", "kitchen", "labor", "laugh",
            "maintain", "management", "match", "mean", "nurse", "noise", "observe", "ocean", "opponent", "page", "performance",
            "pie", "profile", "regional", "rest", "save", "security", "sick", "smoke"};

    public static int index = 0;
    public static String word;
    public static ArrayList<String> displayedWord = new ArrayList<>();
    public static HashSet<Character> lettersGuessed = new HashSet<>();
    public static HashSet<Character> lettersMissed = new HashSet<>();
    public static ArrayList<String> wordArray = new ArrayList<>();

    public static void main(String[] args){

        Scanner user = new Scanner(System.in);

        while(true){
            System.out.println("H A N G M A N");
            System.out.println("_____________");

            resetValues();

            Collections.addAll(wordArray, word.split(""));
            for(String ignored : wordArray){
                displayedWord.add("_");
            }

            while(true){
                StringBuilder tempDisplay = new StringBuilder();
                for(String s : displayedWord){
                    tempDisplay.append(s).append(" ");
                }
                printHangman(index);
                System.out.println("Missed Letters: " + lettersMissed.toString());
                System.out.println(tempDisplay);

                int indexGuess;
                String userGuess;

                while(true){
                    System.out.println("\nGuess a letter or the word:");
                    userGuess = user.next().toLowerCase();
                    indexGuess = guessLetter(userGuess);
                    if (indexGuess == 402){
                        System.out.println("You must guess a letter or word.");
                    }
                    else if (indexGuess != 401){
                        break;
                    }
                    else{
                        System.out.println("You have already guessed that letter!");
                    }
                }
                if(interpretGuess(indexGuess, userGuess)){
                    break;
                }if(displayedWord.equals(wordArray)){
                    System.out.println("You guess the word correctly! It was " + word);
                    break;
                }if(index == 7){
                    printHangman(index);
                    System.out.println("You Lost the game, the word was " + word + ".");
                    break;
                }
            }
            System.out.println("\nWould you like to play again? (N or any key to continue)");

            String continueGame = user.next().toLowerCase();
            if(continueGame.equals("n") || continueGame.equals("no")){
                break;
            }
        }
        user.close();
    }

    public static int guessLetter(String guess){
        if(guess.length() > 1 && !Pattern.matches("[a-zA-Z]+", guess)){
            return 402;
        }
        else if(guess.length() > 1 && !word.equals(guess)){
            return 400;
        }
        else if(guess.length() > 1){
            return 200;
        }
        else if(lettersGuessed.contains(guess.charAt(0))){
            return 401;
        }
        else if(!Pattern.matches("[a-zA-Z]+", guess)){
            return 402;
        }
        else if(wordArray.contains(guess)){
            return 201;
        }
        else return 404;
    }

    public static boolean interpretGuess(int indexGuess, String userGuess){
        if(indexGuess == 404){
            index++;
            lettersMissed.add(userGuess.charAt(0));
            lettersGuessed.add(userGuess.charAt(0));
        } else if(indexGuess == 400){
            System.out.println("You did not guess the word right. Minus 2 points instead of one.");
            index += 2;
        } else if(indexGuess == 200){
            System.out.println("You guess the word correctly!");
            return true;
        } else{
            int i = 0;
            for (String s : wordArray){
                if (s.equals(userGuess)){
                    displayedWord.set(i, userGuess);
                }
                i++;
            }
            lettersGuessed.add(userGuess.charAt(0));
        }
        return false;
    }

    private static void printHangman(int index){
        System.out.println("\n +---+");
        if (index >= 1){
            System.out.println(" O   |");
        }
        else{
            System.out.println("     |");
        }
        if (index >= 4){
            System.out.println("/|\\  |");
        }
        else if (index >= 3){
            System.out.println("/|   |");
        }
        else if (index >= 2){
            System.out.println(" |   |");
        }
        else{
            System.out.println("     |");
        }
        if (index >= 5){
            System.out.println(" |   |");
        }
        else{
            System.out.println("     |");
        }
        if (index >= 7){
            System.out.println("/ \\  |");
        }
        else if (index >=6){
            System.out.println("/    |");
        }
        else{
            System.out.println("     |");
        }
        System.out.println("    ===");
    }

    public static void resetValues(){
        index = 0;
        word = wordList[(int) Math.floor(Math.random() * 101)].toLowerCase();
        lettersGuessed = new HashSet<>();
        lettersMissed = new HashSet<>();
        displayedWord = new ArrayList<>();
        wordArray = new ArrayList<>();
    }
}