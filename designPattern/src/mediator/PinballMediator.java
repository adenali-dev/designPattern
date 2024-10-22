package mediator;

import java.util.ArrayList;
import java.util.List;
import elements.*;

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
            if (allTargetsHit) {
                ramp.open();
                System.out.println("All targets hit. Ramp is now open!");
            }
        }
    }
}