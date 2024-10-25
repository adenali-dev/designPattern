package elements;

import command.Command;
import visitor.Visitor;

public class Bumper extends PinballElement {
    private int hitCount = 0;

    public Bumper(String name, Command command) {
        super(name, command);
    }

    @Override
    public void hit() {
        hitCount++;
        super.hit();
    }

    public int getHitCount() {
        return hitCount;
    }

    public void reset() {
        hitCount = 0;
        System.out.println(name + " has been reset.");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}