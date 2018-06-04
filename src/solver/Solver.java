package solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private List<Double> firstEquationFactors;

    public Solver() {} // for testing purposes

    private List<Double> secondEquationFactors;

    private int programSize;

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
        if (lineA.a == lineB.a) { // pararell functions
            return null;
        }
        double x = -(lineA.b - lineB.b) / (lineA.a - lineB.a);
        double y = lineA.a * x + lineA.b;

        return new Point(x, y);
    }

    public void findCrossPoints() {
        for (int i = 0; i < dualProgramFactors.size() - 1; i++) {
            Line lineA = dualProgramFactors.get(i);
            for (int n = i + 1; n < dualProgramFactors.size(); n++) {
                Line lineB = dualProgramFactors.get(n);
                Point point = findCrossPoint(lineA, lineB);
                this.crossPoints.add(point);
            }
        }
    }

    public void discardCrossPoints() { // discarding crossing points that don't belong to the area
        for (Line line : dualProgramFactors) {
            crossPoints.removeIf(point -> !isPointInRange(line.a, line.b, line.sign, point));
        }
    }

    public boolean isPointInRange(double aLine, double bLine, String sign, Point point) {
        if (sign.equals("<=")) {
            if (point.getY() <= aLine * point.getX() + bLine) {
                return true;
            }
        } else if (sign.equals(">=")) {
            if (point.getY() >= aLine * point.getX() + bLine) {
                return true;
            }
        } else if (sign.equals(">")) {
            if (point.getY() > aLine * point.getX() + bLine) {
                return true;
            }
        } else if (sign.equals("<")) {
            if (point.getY() < aLine * point.getX() + bLine) {
                return true;
            }
        } else if (sign.equals("==")) {
            if (point.getY() == aLine * point.getX() + bLine) {
                return true;
            }
        }
        return false;
    }

    public Point minValuePoint() {
        Double minValue = Double.MAX_VALUE;
        Point V = new Point(0.0, 0.0);
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
        Point V = new Point(0.0, 0.0);
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

    /* TODO: public double bestValue() {



        double bestValue = 0;

        for(double i :  this.functionFactors) {
            bestValue = bestValue + ;
        }

        return bestValue;
    }*/
}