package state;

import java.util.ArrayList;
import java.util.List;

import command.*;
import visitor.*;
import elements.*;
import factory.DisplayFactory;
import factory.FontFamilyADisplayFactory;
import mediator.PinballMediator;

public class Flipper {
    private FlipperState flipperState;
    private int credit = 0;
    private int ballsLost = 0;
    private int totalBalls = 3;
    private int score = 0;
    private DisplayFactory displayFactory;
    private List<PinballElement> elements = new ArrayList<>();

    public Flipper() {
        flipperState = new NoCreditState(this);
    }

    public void play() {
        flipperState.insertCoin();
        System.out.println("Credit: " + getCredit());
        flipperState.pressStartButton();

        // Create elements
        PinballMediator mediator = new PinballMediator();

        Target target1 = new Target("Target 1", new AwardPointsCommand(this, 50), mediator);
        Target target2 = new Target("Target 2", new AwardPointsCommand(this, 50), mediator);
        Target target3 = new Target("Target 3", new AwardPointsCommand(this, 50), mediator);

        mediator.registerTarget(target1);
        mediator.registerTarget(target2);
        mediator.registerTarget(target3);

        Ramp ramp = new Ramp("Ramp", null);
        mediator.setRamp(ramp);

        Bumper bumper = new Bumper("Bumper", new AwardPointsCommand(this, 10));
        Hole hole = new Hole("Hole", new MacroCommand() {{
            addCommand(new AwardPointsCommand(thisFlipper(), 100));
            addCommand(new GuessNumberCommand(thisFlipper()));
        }});

        elements.add(target1);
        elements.add(target2);
        elements.add(target3);
        elements.add(ramp);
        elements.add(bumper);
        elements.add(hole);

        // Game simulation
        target1.hit();
        target2.hit();
        bumper.hit();
        target3.hit();
        hole.hit();

        // Ball lost
        flipperState.ballLost();

        // Next ball
        flipperState.ballLost();

        // Third ball
        flipperState.ballLost();

        // Game over
        flipperState.pressStartButton();
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

    public int getTotalBalls() {
        return totalBalls;
    }

    public void resetBalls() {
        ballsLost = 0;
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

    private Flipper thisFlipper() {
        return this;
    }

    public static void main(String[] args) {
        Flipper flipper = new Flipper();
        flipper.setDisplayFactory(new FontFamilyADisplayFactory());
        flipper.play();
    }
}