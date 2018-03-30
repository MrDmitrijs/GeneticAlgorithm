package genetic.helper;

import genetic.Common.Details;

import java.util.LinkedList;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Helper {

    public static double roundToTwoDecimal(final double number) {
        return round(number * 100.0) / 100.0;
    }

    public static double getRandomNumber(final int low, final int high) {
        return roundToTwoDecimal(low + random() * (high - low));
    }

    public static String makeBin(final String shortBin)
    {
        int binLength = shortBin.length();
        StringBuilder longBin = new StringBuilder();
        if (binLength < 10)
            for (int i = 0; i < 10 - binLength; i++)
            {
                longBin.append("0");
            }
        return longBin + shortBin;
    }

    public static double getAverageFitnessFunction(final LinkedList<Details> linkedLists) {
        double sum = 0;
        for (final Details details : linkedLists) {
            sum += details.getValueOfFitnessFunction();
        }
        return roundToTwoDecimal(sum/linkedLists.size());
    }

}
