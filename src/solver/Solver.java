package solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private List<Double> firstEquationFactors;

    public Solver() {
    } // for testing purposes

    private List<Double> secondEquationFactors;

    private int programSize;
    private double GoalFunctionValue;

    private Point V = new Point(0.0, 0.0);

    private String firstEquationSign;
    private String secondEquationSign;

    private List<Double> functionFactors;
    private List<Point> crossPoints = new ArrayList<>();

    public List<Line> getDualProgramFactors() {
        return dualProgramFactors;
    }

    private List<Line> dualProgramFactors = new ArrayList<>();

    private boolean max;

    public Solver(List<Double> firstEquationFactors, String firstEquationSign, List<Double> secondEquationFactors, String secondEquationSign, List<Double> functionFactors, boolean max) {
        this.firstEquationFactors = firstEquationFactors;
        this.firstEquationSign = firstEquationSign;
        this.secondEquationFactors = secondEquationFactors;
        this.secondEquationSign = secondEquationSign;
        this.functionFactors = functionFactors;
        this.max = max;
        this.programSize = firstEquationFactors.size();


        dualProgramFactors.add(new Line(0, 1, 0, ">="));
        dualProgramFactors.add(new Line(1, 0, 0, ">="));

        for (int i = 0; i < this.programSize - 1; i++) { // converting to dual program
            String sign = ">=";
            if (firstEquationSign == "<=") {
                sign = ">=";
            } else if (firstEquationSign == ">=") {
                sign = "<=";
            }
            dualProgramFactors.add(new Line(firstEquationFactors.get(i), secondEquationFactors.get(i), functionFactors.get(i), sign));
        }
    }

    public List<Point> getCrossPoints() {
        return crossPoints;
    }

    public boolean isMax() {
        return max;
    }

    public Point findCrossPoint(Line lineA, Line lineB) {
        if (lineA.a * lineB.b == lineB.a * lineA.b) { // pararell functions
            return null;
        }
        double det = (lineA.a * lineB.b) - (lineA.b * lineB.a);
        if (det == 0)
            return null;
        else {
            double x = ((lineB.b * lineA.c) - (lineA.b * lineB.c)) / det;
            double y = ((lineA.a * lineB.c) - (lineB.a * lineA.c)) / det;
            return new Point(x, y);
        }
    }

    public void findCrossPoints() {
        for (int i = 0; i < dualProgramFactors.size() - 1; i++) {
            Line lineA = dualProgramFactors.get(i);
            for (int n = i + 1; n < dualProgramFactors.size(); n++) {
                Line lineB = dualProgramFactors.get(n);
                Point point = findCrossPoint(lineA, lineB);
                if (point != null) {
                    if (point.getX() == -0.0) {
                        point.setX(0.0);
                    }
                    if (point.getY() == -0.0) {
                        point.setY(0.0);
                    }

                    point.setX(Math.ceil(point.getX()));
                    point.setY(Math.ceil(point.getY()));

                    if (!this.crossPoints.contains(point)) {
                        this.crossPoints.add(point);
                    }
                }
            }
        }
    }

    public void discardCrossPoints() { // discarding crossing points that don't belong to the area
        for (Line line : dualProgramFactors) {
            crossPoints.removeIf(point -> !isPointInRange(line.a, line.b, line.c, line.sign, point));
        }

    }

    public boolean isPointInRange(double aLine, double bLine, double cLine, String sign, Point point) {
        if (sign.equals("<=")) {
            if (aLine * point.getX() + bLine * point.getY() <= cLine) {
                return true;
            }
        } else if (sign.equals(">=")) {
            if (aLine * point.getX() + bLine * point.getY() >= cLine) {
                return true;
            }
        } else if (sign.equals(">")) {
            if (aLine * point.getX() + bLine * point.getY() > cLine) {
                return true;
            }
        } else if (sign.equals("<")) {
            if (aLine * point.getX() + bLine * point.getY() < cLine) {
                return true;
            }
        } else if (sign.equals("==")) {
            if (aLine * point.getX() + bLine * point.getY() == cLine) {
                return true;
            }
        }
        return false;
    }

    public Point minValuePoint() {
        Double minValue = Double.MAX_VALUE;
        for (Point P : crossPoints) {
            Double currentValue = firstEquationFactors.get(firstEquationFactors.size() - 1) * P.getX() + secondEquationFactors.get(secondEquationFactors.size() - 1) * P.getY();
            if (currentValue < minValue) {
                minValue = currentValue;
                V.setX(P.getX());
                V.setY(P.getY());
            }
        }
        return V;
    }

    public Point maxValuePoint() {
        Double maxValue = Double.MIN_VALUE;
        for (Point P : crossPoints) {
            Double currentValue = firstEquationFactors.get(firstEquationFactors.size() - 1) * P.getX() + secondEquationFactors.get(secondEquationFactors.size() - 1) * P.getY();
            if (currentValue > maxValue) {
                maxValue = currentValue;
                V.setX(P.getX());
                V.setY(P.getY());
            }

        }
        return V;
    }

    public Double findGoalFunctionValue() {
        GoalFunctionValue = V.getX() * firstEquationFactors.get(firstEquationFactors.size() - 1) + V.getY() * secondEquationFactors.get(secondEquationFactors.size() - 1);
        return GoalFunctionValue;
    }
}
