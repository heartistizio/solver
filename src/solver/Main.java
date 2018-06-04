package solver;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Double> firstEquationFactors;
        List<Double> secondEquationFactors;
        List<Double> functionFactors;

        System.out.println("Input first equation factors: ");
        String aFactors = scanner.nextLine();
        System.out.println("Input first equation sign (ex. <=, >=): ");
        String firstEquationSign = scanner.nextLine();
        System.out.println("Input second equation factors: ");
        String bFactors = scanner.nextLine();
        System.out.println("Input second equation sign (ex. <=, >=): ");
        String secondEquationSign = scanner.nextLine();
        System.out.println("Input goal function factors: ");
        String funFactors = scanner.nextLine();
        System.out.println("Is your function 'min' or 'max'? ");
        String minMax = scanner.nextLine();

        scanner.close();

        try {

            boolean max;

            if (minMax.equals("max")) {
                max = true;
            } else if (minMax.equals("min")) {
                max = false;
            } else {
                throw new NumberFormatException();
            }

            firstEquationFactors = Factors.parse(aFactors);
            secondEquationFactors = Factors.parse(bFactors);
            functionFactors = Factors.parse(funFactors);
            Solver solver = new Solver(firstEquationFactors, firstEquationSign, secondEquationFactors, secondEquationSign, functionFactors, max);
            solver.findCrossPoints();
            List<Point> crossPoints = solver.getCrossPoints();

            // output duallines:
            for(Line line : solver.getDualProgramFactors()) {
                System.out.println(line.a + "x + " + line.b + line.sign  + line.c);
            }

            // output crosspoints:
            System.out.println("Constraints points list: ");
            for (Point i : crossPoints) {
                System.out.println(i.getX() + " " + i.getY());
            }

            solver.discardCrossPoints();

            // output crosspoints after discarding:
            System.out.println("Constraints points list after discarding: ");
            for (Point i : crossPoints) {
                System.out.println(i.getX() + " " + i.getY());
            }

            // output V point:
            System.out.println("Point V: ");

            if (solver.isMax()) {
                solver.minValuePoint().printPoint();
            } else {
                solver.maxValuePoint().printPoint();
            }

            // output goal function

            System.out.println("Goal Function Value: ");
            System.out.println(solver.findGoalFunctionValue());

        } catch (NumberFormatException e) {
            System.out.println("Wrong factors inserted.");
        }
    }
}

