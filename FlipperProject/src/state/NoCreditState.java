package state;

public class NoCreditState extends AbstractFlipperState {
    public NoCreditState(Flipper flipper) {

        super(flipper);
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted in NoCreditState");
        this.flipper.setCredit( flipper.getCredit() + 1);
        this.flipper.setState(new ReadyState(this.flipper)); // Transition to ReadyState after inserting a coin
    }

    @Override
    public void pressStartButton() {
        System.out.println("No credit! Please insert a coin.");
    }

    @Override
    public void ballLost() {
        System.out.println("No action, as no game is running.");
    }
}