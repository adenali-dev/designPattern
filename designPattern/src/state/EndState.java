package state;

public class EndState extends AbstractFlipperState {
    public EndState(Flipper flipper) {
        super(flipper);
    }

    @Override
    public void pressStartButton() {
        System.out.println(flipper.getDisplayFactory().createGameOverMessage());
        System.out.println("Total score: " + flipper.getScore());
        if (this.flipper.getCredit() > 0) {
            this.flipper.setState(new ReadyState(this.flipper));
        } else {
            this.flipper.setState(new NoCreditState(this.flipper));
        }
    }

    @Override
    public void insertCoin() {
        super.insertCoin();
        if (this.flipper.getCredit() > 0) {
            this.flipper.setState(new ReadyState(this.flipper));
        }
    }

    @Override
    public void ballLost() {
        System.out.println("Game is already over.");
    }
}