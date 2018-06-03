package solver;

import java.util.ArrayList;
import java.util.List;

public class PD {

    private List<Double> firstEquationFactors;
    private List<Double> secondEquationFactors;
    private List<Double> functionFactors;
    private List<Point> CrossPointList = new ArrayList<>();

    public PD(List<Double> firstEquationFactors, List<Double> secondEquationFactors, List<Double> functionFactors) {
        this.firstEquationFactors = firstEquationFactors;
        this.secondEquationFactors = secondEquationFactors;
        this.functionFactors = functionFactors;
    }

    public List<Double> getFirstEquationFactors() {
        return firstEquationFactors;
    }

    public List<Double> getSecondEquationFactors() {
        return secondEquationFactors;
    }

    public List<Double> getFunctionFactors() {
        return functionFactors;
    }

    public List<Point> showCrossPoints() {
        for (int i = 0; i < functionFactors.size() - 1; i++) {
            for (int n = i + 1; n < functionFactors.size(); n++) {
                Double det = ((firstEquationFactors.get(i) * secondEquationFactors.get(n)) - (secondEquationFactors.get(i) * firstEquationFactors.get(n)));
                Double x = ((((secondEquationFactors.get(n)) * functionFactors.get(i)) - (secondEquationFactors.get(i) * functionFactors.get(n))) / det);
                Double y = ((((firstEquationFactors.get(i)) * functionFactors.get(n)) - (firstEquationFactors.get(n) * functionFactors.get(i))) / det);
                CrossPointList.add(new Point(x, y));
            }
        }

        for (int i = 0; i < CrossPointList.size(); i++) {
            System.out.println("Point " + i);
            CrossPointList.get(i).printPoint();

        }
        return CrossPointList;
    }

    public Point minValuePoint() {
        Double minValue = Double.MAX_VALUE;
        Point V = new Point(0.0, 0.0);
        for (Point P : CrossPointList) {
            Double currentValue = firstEquationFactors.get(firstEquationFactors.size() - 1) * P.getX() + secondEquationFactors.get(secondEquationFactors.size() - 1) * P.getY();
            if (currentValue < minValue) {
                minValue = currentValue;
                V.setX(P.getX());
                V.setY(P.getY());
            }

        }
        return V;
    }
}
