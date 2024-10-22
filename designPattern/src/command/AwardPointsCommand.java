package command;

import state.Flipper;

public class AwardPointsCommand implements Command {
    private Flipper flipper;
    private int points;

    public AwardPointsCommand(Flipper flipper, int points) {
        this.flipper = flipper;
        this.points = points;
    }

    @Override
    public void execute() {
        flipper.addScore(points);
        System.out.println("Awarding " + points + " points. Total score: " + flipper.getScore());
    }
}