package solver;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Double> firstEquationFactors;
        List<Double> secondEquationFactors;
        List<Double> functionFactors;

        // pobierz wspolczynniki
        System.out.println("Input first equation factors: ");
        String aFactors = scanner.nextLine();
        System.out.println("Input second equation factors: ");
        String bFactors = scanner.nextLine();
        System.out.println("Input goal function xD factors: ");
        String funFactors = scanner.nextLine();

        scanner.close();

        try {
            firstEquationFactors = Factors.parse(aFactors);
            secondEquationFactors = Factors.parse(bFactors);
            functionFactors = Factors.parse(funFactors);


            for(double i : firstEquationFactors) {
                System.out.print(i);
                System.out.print(" ");
            }

            System.out.println();

            for(double i : secondEquationFactors) {
                System.out.print(i);
                System.out.print(" ");
            }

            System.out.println();

            for(double i : functionFactors) {
                System.out.print(i);
                System.out.print(" ");
            }

        } catch(NumberFormatException e) {
            System.out.println("Wrong factors inserted.");
        }
    }
}

