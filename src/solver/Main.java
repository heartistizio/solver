package solver;

import java.util.List;
import java.util.Scanner;

public class Main {

   /* TODO: goalFunction
    public double goalFunction(List<Double> , List<Double> point) {

        double result = 0;



        return;
    }*/

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
            PD object = new PD(firstEquationFactors, secondEquationFactors, functionFactors);
            object.showCrossPoints();
            System.out.println(object.minValuePoint());

        } catch(NumberFormatException e) {
            System.out.println("Wrong factors inserted.");
        }





       /* TODO: OUTPUT

       System.out.println("Constraints points list: ");
        for(int i= 0; i< ; i++) {
            System.out.println("");
        }


        System.out.println("Point V coordinates: ");


        System.out.println("");*/

    }
}

