package genetic.Common;

import java.util.LinkedList;

public class EvolutionDetails {

    private LinkedList<Details> linkedDetailsList;
    private boolean endFunction;
    private int averageFitnessCount;
    private GeneticParameters geneticParameters;
    private double averageFitnessFunction;

    public double getAverageFitnessFunction() {
        return averageFitnessFunction;
    }

    public void setAverageFitnessFunction(double averageFitnessFunction) {
        this.averageFitnessFunction = averageFitnessFunction;
    }

    public GeneticParameters getGeneticParameters() {
        return geneticParameters;
    }

    public void setGeneticParameters(GeneticParameters geneticParameters) {
        this.geneticParameters = geneticParameters;
    }

    public LinkedList<Details> getLinkedDetailsList() {
        return linkedDetailsList;
    }

    public void setLinkedDetailsList(LinkedList<Details> linkedDetailsList) {
        this.linkedDetailsList = linkedDetailsList;
    }

    public boolean isEndFunction() {
        return endFunction;
    }

    public void setEndFunction(boolean endFunction) {
        this.endFunction = endFunction;
    }

    public int getAverageFitnessCount() {
        return averageFitnessCount;
    }

    public void setAverageFitnessCount(int averageFitnessCount) {
        this.averageFitnessCount = averageFitnessCount;
    }
}
