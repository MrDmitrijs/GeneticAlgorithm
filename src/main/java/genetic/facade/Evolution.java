package genetic.facade;

import genetic.Common.Details;
import genetic.Common.GeneticParameters;

import java.util.*;

import static genetic.Common.Constants.*;
import static genetic.facade.FunctionManager.getResultOfFitnessFunction;
import static genetic.helper.Helper.getRandomNumber;
import static genetic.helper.Helper.makeBin;
import static java.lang.Integer.toBinaryString;

public class Evolution {

    public Evolution() {
    }

    public static LinkedList<Details> initializeTable(final int numberOfPopulation) {
        final LinkedList<Details> initializer = new LinkedList<>();
        for (int i = 0; i < numberOfPopulation; i++) {

            final double x = getRandomNumber(startX, endX);
            final double y = getRandomNumber(startY, endY);
            final String xBinary = makeBin(toBinaryString(xList.indexOf(x)));
            final String yBinary = makeBin(toBinaryString(yList.indexOf(y)));
            final String fullBinaryCode = xBinary + yBinary;

            final Details details = new Details();
            details.setFullBinaryCode(fullBinaryCode);
            details.setValueOfX(x);
            details.setValueOfY(y);
            details.setValueOfFitnessFunction(getResultOfFitnessFunction(x, y));
            initializer.add(details);
        }
        return initializer;
    }


    public static LinkedList<Details> makeAnEvolution(final LinkedList<Details> population, final GeneticParameters geneticParameters) {
        LinkedList<String> fullBinaryCodeList = new LinkedList<>();
        for (final Details details : population) {
            fullBinaryCodeList.add(details.getFullBinaryCode());
        }

        crossing(fullBinaryCodeList, geneticParameters.getpCrossing());
        mutation(fullBinaryCodeList, geneticParameters.getpMutation());
        final LinkedList<Details> newList = covertIntoDetails(fullBinaryCodeList);
        return tournament(newList, geneticParameters.getPopulationNumber());
    }

    //private methods


    private static Map<String, String> twoPointCrossing(String firstBinaryCode, String secondBinaryCode) {
        final Random random = new Random();
        final int firstPoint = random.nextInt(firstBinaryCode.length()) + 1;
        Map<String, String> resultOfCrossing = new HashMap<>();
        if (firstPoint < firstBinaryCode.length() - 1) {
            final int secondPoint = random.nextInt(secondBinaryCode.length() - firstPoint + 1) + firstPoint;
            final String firstPartOfFirstBinaryCode = firstBinaryCode.substring(0, firstPoint);
            final String secondPartOfFirstBinaryCode = firstBinaryCode.substring(secondPoint, firstBinaryCode.length());
            final String firstPartOfSecondBinaryCode = secondBinaryCode.substring(0, firstPoint);
            final String secondPartOSecondBinaryCode = secondBinaryCode.substring(secondPoint, secondBinaryCode.length());
            final String changeablePartOfFirstBinaryCode = firstBinaryCode.substring(firstPoint, secondPoint);
            final String changeablePartOSecondBinaryCode = secondBinaryCode.substring(firstPoint, secondPoint);
            resultOfCrossing.put("firstBinaryCode", firstPartOfFirstBinaryCode + changeablePartOSecondBinaryCode + secondPartOfFirstBinaryCode);
            resultOfCrossing.put("secondBinaryCode", firstPartOfSecondBinaryCode + changeablePartOfFirstBinaryCode + secondPartOSecondBinaryCode);
        }
        return resultOfCrossing;
    }

    private static void crossing(final LinkedList<String> fullBinaryCodeList, final double pCrossing) {
        final Random random = new Random();
        ArrayList<String> bufferList = new ArrayList<>();
        for (String nextString : fullBinaryCodeList) {
            if (random.nextDouble() < pCrossing) {
                bufferList.add(nextString);
            }
        }
        if (bufferList.size() % 2 != 0) bufferList.remove(0);
        for (int i = 0; i < bufferList.size(); i += 2) {
            final Map<String, String> resultOfCrossing = twoPointCrossing(bufferList.get(i), bufferList.get(i + 1));
            if (!resultOfCrossing.isEmpty()) {
                bufferList.set(i, resultOfCrossing.get("firstBinaryCode"));
                bufferList.set(i + 1, resultOfCrossing.get("secondBinaryCode"));
            }
        }
        fullBinaryCodeList.addAll(bufferList);
    }

    private static void mutation(final LinkedList<String> fullBinaryCodeList, final double pMutation) {
        final Random random = new Random();
        final ArrayList<String> bufferList = new ArrayList<>();
        for (final String fullBinaryCode : fullBinaryCodeList) {
            final String[] arrayStr = fullBinaryCode.split("");
            StringBuilder finalString = new StringBuilder();
            for (String anArrayStr : arrayStr) {
                if (random.nextDouble() < pMutation) {
                    finalString.append(String.valueOf(Integer.valueOf(anArrayStr) == 1 ? 0 : 1));
                } else {
                    finalString.append(anArrayStr);
                }
            }
            bufferList.add(finalString.toString());
        }
        fullBinaryCodeList.clear();
        fullBinaryCodeList.addAll(bufferList);
    }

    private static LinkedList<Details> covertIntoDetails(final LinkedList<String> fullBinaryCodeList) {
        int i = 1;
        LinkedList<Details> newListOfPopulation = new LinkedList<>();
        for (final String fullBinaryCode : fullBinaryCodeList) {
            final Details details = new Details();
            details.setFullBinaryCode(fullBinaryCode);
            final int xPosition = Integer.parseInt(fullBinaryCode.substring(0, 10), 2);
            final int yPosition = Integer.parseInt(fullBinaryCode.substring(10, 20), 2);
            if (xPosition >= xList.size() || yPosition >= yList.size()) {
                continue;
            }
            final double x = xList.get(xPosition);
            final double y = yList.get(yPosition);
            details.setValueOfX(x);
            details.setValueOfY(y);
            details.setValueOfFitnessFunction(getResultOfFitnessFunction(x, y));
            newListOfPopulation.add(details);
            i++;
        }
        return newListOfPopulation;
    }

    private static LinkedList<Details> tournament(final LinkedList<Details> list, int numberOfPopulation) {
        final LinkedList<Details> finalList = new LinkedList<>();
        final Random random = new Random();

        for (int i = 0; i < numberOfPopulation; i++) {
            final Details firstCandidate = list.get(random.nextInt(list.size()));
            final Details secondCandidate = list.get(random.nextInt(list.size()));
            if (firstCandidate.getValueOfFitnessFunction() > secondCandidate.getValueOfFitnessFunction()) {
                finalList.add(firstCandidate);
            } else {
                finalList.add(secondCandidate);
            }
        }
        return finalList;
    }
}
