package elements;

import command.Command;
import visitor.Visitor;

public class Hole extends PinballElement {
    public Hole(String name, Command command) {
        super(name, command);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}