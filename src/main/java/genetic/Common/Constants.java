package genetic.Common;

import java.util.LinkedList;

import static genetic.helper.Helper.roundToTwoDecimal;

public final class Constants {

    public final static String SESSION_ATTRIBUTE = "genetic";

    public final static int startX = -3;
    public final static int endX = 3;
    public final static int startY = -3;
    public final static int endY = 3;
    public final static double step = 0.01;
    public final static LinkedList<Double> xList = getXList();
    public final static LinkedList<Double> yList = getYList();

    private static LinkedList<Double> getXList() {
        final LinkedList<Double> xList = new LinkedList<>();
        for (double x = startX; x <= endX; x += step) {
            xList.add(roundToTwoDecimal(x));
        }
        return xList;
    }

    private static LinkedList<Double> getYList() {
        final LinkedList<Double> yList = new LinkedList<>();
        for (double y = startY; y <= endY; y += step) {
            yList.add(roundToTwoDecimal(y));
        }
        return yList;
    }
}
