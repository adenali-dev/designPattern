package factory;

public class FontFamilyADisplayFactory implements DisplayFactory {
    @Override
    public String createGameOverMessage() {
        return  "  _______      ___      .___  ___.  _______      ______   ____    ____  _______ .______      \n" +
                " /  _____|    /   \\     |   \\/   | |   ____|    /  __  \\  \\   \\  /   / |   ____||   _  \\     \n" +
                "|  |  __     /  ^  \\    |  \\  /  | |  |__      |  |  |  |  \\   \\/   /  |  |__   |  |_)  |    \n" +
                "|  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|     |  |  |  |   \\      /   |   __|  |      /     \n" +
                "|  |__| |  /  _____  \\  |  |  |  | |  |____    |  `--'  |    \\    /    |  |____ |  |\\  \\----.\n" +
                " \\______| /__/     \\__\\ |__|  |__| |_______|    \\______/      \\__/     |_______|| _| `._____|\n" +
                "                                                                                             \n";
    }

    @Override
    public String createPressStartMessage() {
        return   "     _______.___________.    ___      .______     .__________.\n" +
                "    /       |           |   /   \\     |   _  \\    |          |\n" +
                "   |   (----`---|  |----`  /  ^  \\    |  |_)  |   `---|  |---`\n" +
                "    \\   \\       |  |      /  /_\\  \\   |      /        |  |     \n" +
                ".----)   |      |  |     /  _____  \\  |  |\\  \\----.   |  |     \n" +
                "|_______/       |__|    /__/     \\__\\ | _| `._____|   |__|     \n" +
                "                                                               \n";
    }

    @Override
    public String createBallNumberMessage(int ballNumber) {
        return "Ball " + ballNumber;
    }
}