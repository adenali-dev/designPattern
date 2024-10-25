package state;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import command.*;
import elements.*;
import factory.DisplayFactory;
import factory.FontFamilyADisplayFactory;
import mediator.PinballMediator;

public class Flipper {
    private FlipperState flipperState;
    private int credit = 0;
    private int ballsLost = 0;
    private int ballCount = 0;
    private int score = 0;
    private DisplayFactory displayFactory;
    private List<PinballElement> elements = new ArrayList<>();

    public Flipper() {
        flipperState = new NoCreditState(this);
    }

    public void setState(FlipperState flipperState) {
        this.flipperState = flipperState;
    }

    public FlipperState getState() {
        return this.flipperState;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void incrementBallsLost() {
        ballsLost++;
    }

    public int getBallsLost() {
        return ballsLost;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public void resetBalls() {
        ballsLost = 0;
        ballCount = 3;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public DisplayFactory getDisplayFactory() {
        return displayFactory;
    }

    public void setDisplayFactory(DisplayFactory displayFactory) {
        this.displayFactory = displayFactory;
    }

    public void addElement(PinballElement element) {
        elements.add(element);
    }

    public List<PinballElement> getElements() {
        return elements;
    }

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    public static void main(String[] args) {
        Flipper flipper = new Flipper();
        flipper.setDisplayFactory(new FontFamilyADisplayFactory());

        PinballMediator mediator = new PinballMediator();

        Bumper bumper = new Bumper("Bumper", new AwardPointsCommand(flipper, 10));
        Hole hole = new Hole("Hole", new MacroCommand() {{
            addCommand(new AwardPointsCommand(flipper, 100));
            addCommand(new GuessNumberCommand(flipper));
        }});
        Ramp ramp = new Ramp("Ramp", null);
        mediator.setRamp(ramp);

        Target target1 = new Target("Target 1", new AwardPointsCommand(flipper, 50), mediator);
        Target target2 = new Target("Target 2", new AwardPointsCommand(flipper, 50), mediator);
        Target target3 = new Target("Target 3", new AwardPointsCommand(flipper, 50), mediator);

        mediator.registerTarget(target1);
        mediator.registerTarget(target2);
        mediator.registerTarget(target3);

        flipper.addElement(target1);
        flipper.addElement(target2);
        flipper.addElement(target3);
        flipper.addElement(bumper);
        flipper.addElement(hole);
        flipper.addElement(ramp);

        ReadyState readyState = new ReadyState(flipper);
        PlayingState playingState = new PlayingState(flipper);
        EndState endState = new EndState(flipper);
        NoCreditState noCreditState = new NoCreditState(flipper);

        Scanner scanner = new Scanner(System.in);
        boolean notExit = true;

        while (notExit) {
            // Stilvolle Anzeige des Status und anderer Informationen
            System.out.println(BOLD + BLUE + "\n----> Flipper State: " + flipper.getState().getClass().getSimpleName() + RESET);
            System.out.println(GREEN + "Credit: " + flipper.getCredit() + RESET);
            System.out.println(YELLOW + "Score: " + flipper.getScore() + RESET);
            System.out.println(PURPLE + "Balls Remaining: " + flipper.getBallCount() + "\n" + RESET);

            System.out.println(CYAN + "What do you want to do next?" + RESET);
            System.out.println(BOLD + UNDERLINE + "---------------------------------" + RESET);
            System.out.println(BOLD + "Press " + GREEN + "c" + RESET + " to insert a coin");
            System.out.println(BOLD + "Press " + GREEN + "t" + RESET + " to hit a target");
            System.out.println(BOLD + "Press " + GREEN + "r" + RESET + " to hit the ramp");
            System.out.println(BOLD + "Press " + GREEN + "p" + RESET + " to press start");
            System.out.println(BOLD + "Press " + GREEN + "b" + RESET + " to hit the bumper");
            System.out.println(BOLD + "Press " + GREEN + "h" + RESET + " to hit the hole");
            System.out.println(BOLD + "Press " + GREEN + "l" + RESET + " if a ball was lost");
            System.out.println(BOLD + "Press " + RED + "e" + RESET + " to exit");
            System.out.println(BOLD + UNDERLINE + "---------------------------------" + RESET);

            // Visuell auffälliges Eingabefeld
            System.out.print(BOLD + ">>> " + RESET);

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "c" -> flipper.getState().insertCoin();
                case "p" -> flipper.getState().pressStartButton();
                case "l" -> flipper.getState().ballLost();
                case "t" -> {
                    if (flipper.getState() instanceof PlayingState) {
                        System.out.println(CYAN + "Which target do you want to hit? (1/2/3)" + RESET);
                        System.out.print(BOLD + ">>> " + RESET); // Eingabe für Zielauswahl
                        int targetNum = Integer.parseInt(scanner.nextLine());
                        switch (targetNum) {
                            case 1 -> {
                                target1.hit();
                                mediator.notify(target1, "TargetHit");
                            }
                            case 2 -> {
                                target2.hit();
                                mediator.notify(target2, "TargetHit");
                            }
                            case 3 -> {
                                target3.hit();
                                mediator.notify(target3, "TargetHit");
                            }
                            default -> System.out.println(RED + "Invalid target number." + RESET);
                        }
                    } else {
                        System.out.println(RED + "You are not playing yet." + RESET);
                    }
                }
                case "b" -> {
                    if (flipper.getState() instanceof PlayingState) {
                        bumper.hit();
                    } else {
                        System.out.println(RED + "You are not playing yet." + RESET);
                    }
                }
                case "h" -> {
                    if (flipper.getState() instanceof PlayingState) {
                        hole.hit();
                    } else {
                        System.out.println(RED + "You are not playing yet." + RESET);
                    }
                }
                case "r" -> {
                    if (flipper.getState() instanceof PlayingState) {
                        if (ramp.isOpen()) {
                            ((PlayingState) flipper.getState()).hitRamp(ramp);
                        } else {
                            System.out.println(RED + "Ramp is not open, try to hit the targets." + RESET);
                        }
                    } else {
                        System.out.println(RED + "You are not playing yet." + RESET);
                    }
                }
                case "e" -> {
                    System.out.println(flipper.getDisplayFactory().createGameOverMessage());
                    System.out.println("Total score: " + flipper.getScore());
                    System.out.println(BOLD + RED + "Game Over. Thank you for playing!" + RESET);
                    notExit = false;
                }
                default -> System.out.println(RED + "Invalid input. Please try again." + RESET);
            }

            // Check if all balls are lost and switch state if necessary
            if (flipper.getBallCount() == 0 && flipper.getState() instanceof PlayingState) {
                System.out.println(RED + "\nAll balls lost!" + RESET);
                flipper.setState(endState);
            }

            if (!(flipper.getState() instanceof PlayingState)) {
                if (flipper.getCredit() > 0) {
                    flipper.setState(readyState);
                } else {
                    flipper.setState(noCreditState);
                }
            }
        }

        scanner.close();
    }
}