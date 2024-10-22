package elements;

import command.Command;
import visitor.Visitor;

public class Ramp extends PinballElement {
    private boolean open = false;

    public Ramp(String name, Command command) {
        super(name, command);
    }

    public void open() {
        if (!open) {
            open = true;
            System.out.println(name + " is now open.");
        }
    }

    public void close() {
        if (open) {
            open = false;
            System.out.println(name + " is now closed.");
        }
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}