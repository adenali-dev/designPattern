package factory;

public interface DisplayFactory {
    String createGameOverMessage();
    String createPressStartMessage();
    String createBallNumberMessage(int ballNumber);
}