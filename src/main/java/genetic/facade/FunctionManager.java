package genetic.facade;

import static genetic.helper.Helper.roundToTwoDecimal;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class FunctionManager {

    public static double getResultOfFitnessFunction(final double x, final double y) {
        return roundToTwoDecimal(2 * sin(x) + 4 * x * x + cos(y) + 30 + 2 * y);
    }

}
