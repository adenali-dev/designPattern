package elements;

import command.Command;
import mediator.Mediator;
import visitor.Visitor;

public class Target extends PinballElement {
    private boolean hit = false;
    private Mediator mediator;

    public Target(String name, Command command, Mediator mediator) {
        super(name, command);
        this.mediator = mediator;
    }

    @Override
    public void hit() {
        if (!hit) {
            super.hit();
            hit = true;
            mediator.notify(this, "TargetHit");
        } else {
            System.out.println(name + " has already been hit.");
        }
    }

    public boolean isHit() {
        return hit;
    }

    public void reset() {
        hit = false;
        System.out.println(name + " has been reset.");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}