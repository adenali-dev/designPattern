package mediator;

import elements.PinballElement;

public interface Mediator {
    void notify(PinballElement sender, String event);
}