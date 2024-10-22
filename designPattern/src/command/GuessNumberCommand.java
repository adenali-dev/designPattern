package command;

import state.Flipper;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberCommand implements Command {
    private Flipper flipper;

    public GuessNumberCommand(Flipper flipper) {
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int correctNumber = rand.nextInt(3) + 1;
        System.out.print("Guess a number between 1 and 3: ");
        int guess = scanner.nextInt();
        if (guess == correctNumber) {
            System.out.println("Correct! Awarding bonus points.");
            flipper.addScore(200);
        } else {
            System.out.println("Incorrect. No bonus points.");
        }
    }
}