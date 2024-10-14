import java.util.Random;
import java.util.Scanner;

public class NumberExample {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int score = 0;
    private static int roundsPlayed = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");
        playGame();
    }

    private static void playGame() {
        while (true) {
            int targetNumber = generateRandomNumber();
            int attempts = 0;
            int maxAttempts = 7; 

            System.out.println("\nNew Round! Guess the number between 1 and 100.");
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                int guess = getUserGuess(attempts, maxAttempts);
                attempts++;

                guessedCorrectly = checkGuess(guess, targetNumber);

                if (guessedCorrectly) {
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.printf("Sorry, you've used all your attempts! The number was %d.%n", targetNumber);
            }

            roundsPlayed++;
            askToPlayAgain();
        }
    }

    private static int generateRandomNumber() {
        return random.nextInt(100) + 1;
         // Random number between 1 and 100
    }

    private static int getUserGuess(int attempts, int maxAttempts) {
        System.out.printf("Attempt %d/%d: Your guess: ", attempts + 1, maxAttempts);
        return scanner.nextInt();
    }

    private static boolean checkGuess(int guess, int targetNumber) {
        if (guess < targetNumber) {
            System.out.println("Too low! Try again.");
            return false;
        } else if (guess > targetNumber) {
            System.out.println("Too high! Try again.");
            return false;
        } else {
            System.out.printf("Congratulations! You guessed the number %d in %d attempts.%n", targetNumber, roundsPlayed);
            score += 8 - (roundsPlayed % 8);
            return true;
        }
    }

    private static void askToPlayAgain() {
        System.out.print("\nDo you want to play another round? (yes/no): ");
        String playAgain = scanner.next();
        if (!playAgain.equalsIgnoreCase("yes")) {
            endGame();
        }
    }

    private static void endGame() {
        System.out.printf("\nGame Over! You played %d rounds and your score is %d.%n", roundsPlayed, score);
        scanner.close();
        System.exit(0);
    }
}
