package factory;

public class FontFamilyBDisplayFactory implements DisplayFactory {
    @Override
    public String createGameOverMessage() {
        return "######################\n" +
                "##    GAME OVER!    ##\n" +
                "######################\n";
    }

    @Override
    public String createPressStartMessage() {
        return "######################\n" +
                "##  Welcome to Flipper  ##\n" +
                "######################\n";
    }
    @Override
    public String createBallNumberMessage(int ballNumber) {
        return "Ball #" + ballNumber;
    }
}