package visitor;

import elements.*;

public class ResetVisitor implements Visitor {
    @Override
    public void visit(Target target) {
        target.reset();
    }

    @Override
    public void visit(Bumper bumper) {
        bumper.reset();
    }

    @Override
    public void visit(Ramp ramp) {
        ramp.close();
    }

    @Override
    public void visit(Hole hole) {
        System.out.println("No reset required for " + hole.getName() + ".");
    }
}