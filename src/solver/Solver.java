package solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private List<Double> firstEquationFactors;
    private List<Double> secondEquationFactors;

    private String firstEquationSign;
    private String secondEquationSign;

    private List<Double> functionFactors;
    private List<Point> CrossPoints = new ArrayList<>();

    private boolean max;

    private Point V = new Point(0.0, 0.0);
    private Double GoalFunction = 0.0;


    public Solver(List<Double> firstEquationFactors, String firstEquationSign, List<Double> secondEquationFactors, String secondEquationSign, List<Double> functionFactors, boolean max) {
        this.firstEquationFactors = firstEquationFactors;
        this.firstEquationSign = firstEquationSign;
        this.secondEquationFactors = secondEquationFactors;
        this.secondEquationSign = secondEquationSign;
        this.functionFactors = functionFactors;
        this.max = max;
    }

    public List<Double> getFirstEquationFactors() {
        return firstEquationFactors;
    }

    public List<Point> getCrossPoints() {
        return CrossPoints;
    }

    public List<Double> getSecondEquationFactors() {
        return secondEquationFactors;
    }

    public List<Double> getFunctionFactors() {
        return functionFactors;
    }

    public boolean isMax() {
        return max;
    }

    public void findCrossPoints() {
        for (int i = 0; i < functionFactors.size() - 1; i++) {
            for (int n = i + 1; n < functionFactors.size(); n++) {
                Double det = ((firstEquationFactors.get(i) * secondEquationFactors.get(n)) - (secondEquationFactors.get(i) * firstEquationFactors.get(n)));
                Double x = ((((secondEquationFactors.get(n)) * functionFactors.get(i)) - (secondEquationFactors.get(i) * functionFactors.get(n))) / det);
                Double y = ((((firstEquationFactors.get(i)) * functionFactors.get(n)) - (firstEquationFactors.get(n) * functionFactors.get(i))) / det);
                if( x> 0 && y > 0) {
                    this.CrossPoints.add(new Point(x, y));
                }
            }
        }
    }

    public Point minValuePoint() {
        Double minValue = Double.MAX_VALUE;
        Point V = new Point(0.0, 0.0);
        for (Point P : CrossPoints) {
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
        for (Point P : CrossPoints) {
            Double currentValue = firstEquationFactors.get(firstEquationFactors.size() - 1) * P.getX() + secondEquationFactors.get(secondEquationFactors.size() - 1) * P.getY();
            if (currentValue > maxValue) {
                maxValue = currentValue;
                V.setX(P.getX());
                V.setY(P.getY());
            }

        }
        return V;
    }

    public Double findGoalFunctionxD() {
        List<Double> finalFirstEquationFactors = new ArrayList<>();
        List<Double> finalSecondEquationFactors = new ArrayList<>();
        List<Double> finalFunctionFactors = new ArrayList<>();
        for(Integer i = 0; i < functionFactors.size(); i++){
            System.out.println(functionFactors.get(i));
            System.out.println((V.getX() * firstEquationFactors.get(i) + V.getY() * secondEquationFactors.get(i) ));
            if(((V.getX() * firstEquationFactors.get(i) + V.getY() * secondEquationFactors.get(i) )== functionFactors.get(i)))
                finalFirstEquationFactors.add(firstEquationFactors.get(i));
                finalSecondEquationFactors.add(secondEquationFactors.get(i));
                finalFunctionFactors.add(functionFactors.get(i));
        }
        System.out.println(finalFirstEquationFactors);
        System.out.println(finalSecondEquationFactors);
        System.out.println(finalFunctionFactors);
        return GoalFunction;
    }

    /* TODO: public double bestValue() {

        double bestValue = 0;

        for(double i :  this.functionFactors) {
            bestValue = bestValue + ;
        }

        return bestValue;
    }*/
}
