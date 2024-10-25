package state;

import elements.PinballElement;
import elements.Ramp;
import visitor.PointsVisitor;
import visitor.ResetVisitor;

import static java.awt.Font.BOLD;
import static jdk.internal.org.jline.utils.AttributedStyle.CYAN;
import static state.Flipper.PURPLE;
import static state.Flipper.YELLOW;

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
        System.out.println(BOLD + CYAN + "\n########################################");
        System.out.println("         FlIPPER GAME PROJECT");
        System.out.println("########################################");
        System.out.println(PURPLE + "   Developed by:");
        System.out.println(YELLOW + "   Aden Ali \n Nadine-Ann Villaluz \n Betuel Yigit");
    }

    @Override
    public void ballLost() {
        // Sicherstellen, dass der Ballabzug nur erfolgt, wenn noch Bälle vorhanden sind
        if (flipper.getBallCount() > 0) {
            flipper.setBallCount(flipper.getBallCount() - 1); // Ballanzahl um 1 reduzieren
            this.flipper.incrementBallsLost();
            System.out.println("Ball lost! Balls remaining: " + flipper.getBallCount());

            // Punkteberechnung mit PointsVisitor
            PointsVisitor pointsVisitor = new PointsVisitor();
            for (PinballElement element : flipper.getElements()) {
                element.accept(pointsVisitor);
            }
            int roundPoints = pointsVisitor.getTotalPoints();
            System.out.println("Points this round: " + roundPoints);
            flipper.addScore(roundPoints);

            // Überprüfen, ob alle Bälle verloren wurden
            if (flipper.getBallCount() <= 0) {
                System.out.println("All balls lost!");
                this.flipper.setState(new EndState(this.flipper));
            } else {
                System.out.println("Next ball!");
                // Zurücksetzen der Elemente für den nächsten Ball
                ResetVisitor resetVisitor = new ResetVisitor();
                for (PinballElement element : flipper.getElements()) {
                    element.accept(resetVisitor);
                }
            }
        }
    }

    public void hitRamp(Ramp ramp) {
        if (ramp.isOpen()) {
            ramp.hit();
            //System.out.println("Ramp was hit.");

            // Verwenden von ResetVisitor, um Ziele zurückzusetzen
            ResetVisitor resetVisitor = new ResetVisitor();
            for (PinballElement element : flipper.getElements()) {
                element.accept(resetVisitor);
            }
        } else {
            System.out.println("Ramp is not open, try to hit the targets.");
        }
    }
}