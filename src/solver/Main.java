package solver;

import java.util.List;
import java.util.Scanner;

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
            /*for (Line line : solver.dualProgramFactors) {
                System.out.println(line.a + "x + " + line.b + "y " + line.sign + " " + line.c);
            }*/

            solver.discardCrossPoints();

            // output crosspoints after discarding:
            System.out.println("Constraints points: ");
            for (Point i : crossPoints) {
                System.out.print("(" + i.getX() + ", " + i.getY() + ") ");
            }
            System.out.println();

            // output V point:
            System.out.println("Point V (PD): ");

            if (solver.isMax()) {
                solver.minValuePoint().printPoint();
            } else {
                solver.maxValuePoint().printPoint();
            }

            solver.checkIfWeTakePDLine();
            solver.evaluateV();

            System.out.println("Point V (PP): ");
            System.out.print("(");
            for (int i = 0; i < solver.V.length; i++) {
                System.out.print(solver.V[i]);
                if (i < solver.V.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")");
            System.out.println();

            solver.evaluateFV();

            System.out.println("F(V): " + solver.FV);

        } catch (NumberFormatException e) {
            System.out.println("Wrong factors inserted.");
        }
    }
}

