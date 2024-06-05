import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;
        int totalRounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            totalRounds++;
            Random rand = new Random();
            int numberToGuess = rand.nextInt(100) + 1;
            int numberOfTries = 0;
            boolean win = false;
            int maxAttempts = 10; // Setting a limit for maximum attempts

            System.out.println("Round " + totalRounds + ": I have randomly selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (!win && numberOfTries < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = input.nextInt();
                numberOfTries++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Invalid guess. Please enter a number between 1 and 100.");
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    win = true;
                    totalAttempts += numberOfTries;
                    System.out.println("Congratulations! You've guessed the correct number.");
                    System.out.println("It took you " + numberOfTries + " tries.");
                }

                if (numberOfTries == maxAttempts && !win) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + numberToGuess + ".");
                }
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = input.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total attempts made: " + totalAttempts);
        double averageAttempts = totalRounds > 0 ? (double) totalAttempts / totalRounds : 0;
        System.out.println("Average attempts per round: " + averageAttempts);

        input.close();
    }
}