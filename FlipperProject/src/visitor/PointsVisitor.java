package visitor;

import elements.*;

public class PointsVisitor implements Visitor {
    private int totalPoints = 0;

    @Override
    public void visit(Target target) {
        if (target.isHit()) {
            totalPoints += 50;
            System.out.println("Points from " + target.getName() + ": 50");
        }
    }

    @Override
    public void visit(Bumper bumper) {
        int points = bumper.getHitCount() * 10;
        totalPoints += points;
        System.out.println("Points from " + bumper.getName() + ": " + points);
    }

    @Override
    public void visit(Ramp ramp) {
        if (ramp.isOpen()) {
            totalPoints += 100;
            System.out.println("Points from " + ramp.getName() + ": 100");
        }
    }

    @Override
    public void visit(Hole hole) {
        totalPoints += 30;
        System.out.println("Points from " + hole.getName() + ": 30");
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
