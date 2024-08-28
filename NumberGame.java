import java.util.*;

public class NumberGame {

    public static void main(String[] args) {
        numberGuessingGame();
    }

    public static void numberGuessingGame() {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int attempts = 10;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100.");
        System.out.println("You have " + attempts + " attempts to guess it.");

        while (attempts > 0) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess < numberToGuess) {
                System.out.println("Too low!");
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                System.out.println("Congratulations! You've guessed the number!");
                break;
            }
            attempts--;
            System.out.println("You have " + attempts + " attempts left.");
        }

        if (attempts == 0) {
            System.out.println("Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
        }

        scanner.close();
    }
}