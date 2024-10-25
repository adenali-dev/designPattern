package mediator;

import elements.PinballElement;
import elements.Ramp;
import elements.Target;

import java.util.ArrayList;
import java.util.List;

public class PinballMediator implements Mediator {
    private List<Target> targets = new ArrayList<>();
    private Ramp ramp;

    public void registerTarget(Target target) {
        targets.add(target);
    }

    public void setRamp(Ramp ramp) {
        this.ramp = ramp;
    }

    @Override
    public void notify(PinballElement sender, String event) {
        if (event.equals("TargetHit")) {
            boolean allTargetsHit = true;
            for (Target t : targets) {
                if (!t.isHit()) {
                    allTargetsHit = false;
                    break;
                }
            }
            // Nur öffnen, wenn die Rampe noch geschlossen ist und alle Targets getroffen wurden
            if (allTargetsHit && !ramp.isOpen()) {
                System.out.println("All targets hit.");
                ramp.open();
            }
        }
    }
}