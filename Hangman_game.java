import java.util.Random;
import java.util.Scanner;

public class Hangman_game
{
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        System.out.println(CYAN + "🎮 Welcome to Hangman Game!" + RESET);
        Random rand = new Random();
        String[] words = new String[]{
                "apple", "banana", "cherry", "mango", "orange", "grape", "peach", "lemon", "melon", "kiwi",
                "tiger", "lion", "elephant", "monkey", "rabbit", "zebra", "giraffe",
                "river", "ocean", "mountain", "forest", "desert", "island",
                "school", "market", "garden", "bridge", "village"};
        String secretWord = words[rand.nextInt(words.length)];
        int guesses = 6;

        Scanner sc = new Scanner(System.in);
        String guessedLetters = "";
        int warnings = 3;
        System.out.println("Word length: " + secretWord.length());

        while (guesses > 0) {
            boolean won = true;
            System.out.print("\nWord: ");
            for (int i = 0; i < secretWord.length(); i++) {
                char c = secretWord.charAt(i);
                if (guessedLetters.indexOf(c) != -1) {
                    System.out.print(GREEN + c + " " + RESET);
                } else {
                    System.out.print("_ ");
                    won = false;
                }
            }
            System.out.println("\nGuessed letters: " + guessedLetters);

            if (won) {
                System.out.println(GREEN + "🎉 You WON!" + RESET);
                break;
            }
            System.out.print("Enter a letter: ");
            String input = sc.next().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println(YELLOW + "⚠ Invalid input!" + RESET);
                if (warnings > 0) {
                    warnings--;
                    System.out.println("Warnings left: " + warnings);
                } else {
                    guesses--;
                    System.out.println(RED + "No warnings left! Guess lost." + RESET);
                    showHangman(guesses);
                }
                continue;
            }
            char guess = input.charAt(0);

            if (guessedLetters.indexOf(guess) != -1) {
                System.out.println(YELLOW + "⚠ Already guessed!" + RESET);
                if (warnings > 0) warnings--;
                else {
                    guesses--;
                    showHangman(guesses);
                }
                continue;
            }

            guessedLetters += guess;
            if (secretWord.indexOf(guess) == -1) {
                guesses--;
                System.out.println(RED + "❌ Wrong guess!" + RESET);
                showHangman(guesses);

            } else {
                System.out.println(GREEN + "✅ Correct guess!" + RESET);
            }
            System.out.println("Guesses left: " + guesses);
            System.out.println("Warnings left: " + warnings);
        }

        if (guesses == 0) {
            System.out.println(RED + "\n💀 You lost the game!" + RESET);
            System.out.println("Correct word was: " + secretWord);
        }
        sc.close();
    }

    public static void showHangman(int guessesLeft) {
        String color;

        if (guessesLeft >= 4) color = GREEN;
        else if (guessesLeft >= 2) color = YELLOW;
        else color = RED;

        System.out.println(color);

        switch (guessesLeft) {
            case 6:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 5:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 4:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 3:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 2:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 1:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/    |");
                System.out.println("     |");
                System.out.println("=======");
                break;

            case 0:
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/ \\  |");
                System.out.println("     |");
                System.out.println("=======");
                break;
        }
        System.out.println(RESET);
    }
}