package state;

public class ReadyState extends AbstractFlipperState {
    public ReadyState(Flipper flipper) {
        super(flipper);
    }

    @Override
    public void insertCoin() {
        this.flipper.setCredit(flipper.getCredit() + 1);
        System.out.println("Credit increased. Current credit: " + flipper.getCredit());
    }

    @Override
    public void pressStartButton() {
        if (flipper.getCredit() > 0) {
            System.out.println("Game starting! Balls set to 3.");
            this.flipper.setCredit(flipper.getCredit() - 1);
            this.flipper.setBallCount(3); // Ballanzahl auf 3 setzen
            this.flipper.setState(new PlayingState(this.flipper));
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