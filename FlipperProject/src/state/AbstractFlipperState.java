package state;

public abstract class AbstractFlipperState implements FlipperState {
    protected final Flipper flipper;

    public AbstractFlipperState(Flipper flipper) {

        this.flipper = flipper;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        this.flipper.setCredit(this.flipper.getCredit() + 1);
    }

    @Override
    public void pressStartButton() {
        System.out.println("Start button pressed.");
    }

    @Override
    public void ballLost() {
        System.out.println("Ball lost.");
    }
}
