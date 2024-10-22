package state;

import elements.PinballElement;
import visitor.PointsVisitor;
import visitor.ResetVisitor;

public class PlayingState extends AbstractFlipperState {
    public PlayingState(Flipper flipper) {
        super(flipper);
    }

    @Override
    public void insertCoin() {
        super.insertCoin();
        System.out.println("Credit increased. Current credit: " + flipper.getCredit());
    }

    @Override
    public void pressStartButton() {
        System.out.println("Software authors: John Doe, Jane Smith.");
    }

    @Override
    public void ballLost() {
        this.flipper.incrementBallsLost();
        System.out.println("Ball lost! Balls lost: " + flipper.getBallsLost());

        // Points calculation with PointsVisitor
        PointsVisitor pointsVisitor = new PointsVisitor();
        for (PinballElement element : flipper.getElements()) {
            element.accept(pointsVisitor);
        }
        int roundPoints = pointsVisitor.getTotalPoints();
        System.out.println("Points this round: " + roundPoints);
        flipper.addScore(roundPoints);

        if (this.flipper.getBallsLost() >= this.flipper.getTotalBalls()) {
            System.out.println("All balls lost!");
            this.flipper.setState(new EndState(this.flipper));
        } else {
            System.out.println("Next ball!");
            // Reset elements for the next ball
            ResetVisitor resetVisitor = new ResetVisitor();
            for (PinballElement element : flipper.getElements()) {
                element.accept(resetVisitor);
            }
        }
    }
}