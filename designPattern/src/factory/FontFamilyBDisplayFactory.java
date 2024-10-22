package factory;

public class FontFamilyBDisplayFactory implements DisplayFactory {
    @Override
    public String createGameOverMessage() {
        return "==== GAME OVER ====";
    }

    @Override
    public String createPressStartMessage() {
        return "*** PRESS START ***";
    }

    @Override
    public String createBallNumberMessage(int ballNumber) {
        return "Ball #" + ballNumber;
    }
}