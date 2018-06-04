package solver;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    Solver solver;

    @BeforeEach
    void preparation() {

        solver = new Solver();
    }

    @org.junit.jupiter.api.Test
    void findCrossPointTest1() {
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
    }

    @org.junit.jupiter.api.Test
    void minValuePoint() {
    }

    @org.junit.jupiter.api.Test
    void maxValuePoint() {
    }
}
