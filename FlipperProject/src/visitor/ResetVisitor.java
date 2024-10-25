package visitor;

import elements.*;

public class ResetVisitor implements Visitor {

    @Override
    public void visit(Target target) {
        target.reset(); // Setzt jedes einzelne Target zurück
    }

    @Override
    public void visit(Bumper bumper) {
        bumper.reset();
    }

    @Override
    public void visit(Ramp ramp) {
        ramp.close(); // Schließt die Rampe wieder
    }

    @Override
    public void visit(Hole hole) {
        System.out.println("No reset required for " + hole.getName() + ".");
    }
}