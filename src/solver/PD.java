package solver;

import java.util.ArrayList;
import java.util.List;

public class PD {
    private List<Point> PointList = new ArrayList<>();
    public List<Point> showCrossPoints(List<Double> a, List<Double> b, List<Double> P){
        for(int i = 0; i < P.size(); i++){
            for(int n = 0; n < i; i++){
                Double det = ((a.get(i) * b.get(n)) - (b.get(i) * a.get(n)));
                Double x = ((((b.get(n)) * P.get(i)) - (b.get(i) * P.get(n))) / det);
                Double y = ((((a.get(i)) * P.get(n)) - (a.get(n) * P.get(i))) / det);
                PointList.add(new Point (x, y));
            }
        }

        for(int i = 0; i < PointList.size(); i++){
            PointList.get(i).printPoint();
        }

        return PointList;
    }

    public Point V(){
        Double minValue = Double.MAX_VALUE;
        Point V = new Point(0, 0);
        for(Point P : PointList){
            Double currentValue = a.get(a.size()) * P.getX() + b.get(b.size()) * P.getY();
            if(currentValue < minValue) {
                minValue = currentValue;
                V.setX(P.getX());
                V.setY(P.getY());
            }
            return V;


        }

    }
}
