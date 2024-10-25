package elements;

import command.Command;
import visitor.Element;
import visitor.Visitor;

public abstract class PinballElement implements Element {
    protected String name;
    protected Command command;

    public PinballElement(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public void hit() {
        System.out.println(name + " was hit.");
        if (command != null) {
            command.execute();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract void accept( Visitor visitor);
}