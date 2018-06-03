package solver;

import java.util.ArrayList;
import java.util.List;

public class PD {
    private List<Point> showCrossPoints(List<Double> a, List<Double> b, List<Double> P){
        List<Point> PointList = new ArrayList<>();
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
}
