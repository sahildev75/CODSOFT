import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int totalScore = 0;
    private static int roundsPlayed = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");
        playGame();
    }

    private static void playGame() {
        boolean playAgain;

        do {
            int targetNumber = generateRandomNumber();
            int attempts = 0;
            int maxAttempts = 7; 

            System.out.println("\nNew Round! Guess the number between 1 and 100.");

            while (attempts < maxAttempts) {
                int guess = getUserGuess(attempts, maxAttempts);
                attempts++;

                if (checkGuess(guess, targetNumber)) {
                    totalScore += (maxAttempts - attempts + 1);  
                    break; 
                }
            }

            if (attempts == maxAttempts) {
                System.out.printf("Sorry, you've used all your attempts! The number was %d.%n", targetNumber);
            }

            roundsPlayed++;
            playAgain = askToPlayAgain();
        } while (playAgain);

        endGame();
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
            System.out.printf("Congratulations! You guessed the number %d!%n", targetNumber);
            return true;
        }
    }

    private static boolean askToPlayAgain() {
        System.out.print("Do you want to play another round? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }

    private static void endGame() {
        System.out.printf("\nGame Over! You played %d rounds and your total score is %d.%n", roundsPlayed, totalScore);
        scanner.close();
    }
}
