package solver;

import java.util.ArrayList;
import java.util.List;

public class Factors {

    public static List<Double> parse(String factorsString) throws NumberFormatException {

        String[] preConversionFactors;
        List<Double> factors = new ArrayList<>();

        double currentFactor;

        preConversionFactors = factorsString.split(" ");

        for(String i : preConversionFactors) {
            factors.add(Double.parseDouble(i));
        }
        return factors;
    }
}
