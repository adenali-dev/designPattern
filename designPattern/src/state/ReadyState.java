package state;

public class ReadyState extends AbstractFlipperState {
    public ReadyState(Flipper flipper) {
        super(flipper);
    }

    @Override
    public void pressStartButton() {
        if (this.flipper.getCredit() > 0) {
            System.out.println("Game starting! ReadyState.");
            this.flipper.setCredit(this.flipper.getCredit() - 1);
            this.flipper.setState(new PlayingState(this.flipper));
            this.flipper.resetBalls();
            System.out.println(this.flipper.getDisplayFactory().createPressStartMessage());
        } else {
            System.out.println("No credit! Please insert a coin.");
            this.flipper.setState(new NoCreditState(this.flipper));
        }
    }

    @Override
    public void ballLost() {
        System.out.println("No action, as the game hasn't started yet.");
    }
}