package visitor;

import elements.*;

public interface Visitor {
    void visit(Target target);
    void visit(Bumper bumper);
    void visit(Ramp ramp);
    void visit(Hole hole);
}