/*
package solver;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private Solver solver;

    @BeforeEach
    void preparation() {

        solver = new Solver();
    }

    @org.junit.jupiter.api.Test
    void findCrossPoint() {
        Line lineA = new Line(1,1, "<=");
        Line lineB = new Line(0,2, "<=");

        assertEquals(new Point(1.0,2.0), solver.findCrossPoint(lineA, lineB));

    }

    @org.junit.jupiter.api.Test
    void findCrossPoints() {

    }

    @org.junit.jupiter.api.Test
    void discardCrossPoints() {
    }

    @org.junit.jupiter.api.Test
    void isPointInRange() {

        Line line = new Line(1,1, "<=");
        Point point = new Point(0.0,0.0);
        assertTrue(solver.isPointInRange(line.a, line.b, line.sign, point));

        line = new Line(1,0, "<=");
        point = new Point(0.0,0.0);
        assertTrue(solver.isPointInRange(line.a, line.b, line.sign, point));

        line = new Line(1,0, ">");
        point = new Point(0.0,0.1);
        assertTrue(solver.isPointInRange(line.a, line.b, line.sign, point));

    }

    @org.junit.jupiter.api.Test
    void minValuePoint() {
    }

    @org.junit.jupiter.api.Test
    void maxValuePoint() {
    }
}
*/
