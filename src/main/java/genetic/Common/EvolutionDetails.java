package genetic.Common;

import java.util.LinkedList;

public class EvolutionDetails {

    private LinkedList<Details> linkedDetailsList;
    private GeneticParameters geneticParameters;
    private LinkedList<String> listOfAverageFitnessFunction;

    public LinkedList<String> getListOfAverageFitnessFunction() {
        return listOfAverageFitnessFunction;
    }

    public void setListOfAverageFitnessFunction(LinkedList<String> listOfAverageFitnessFunction) {
        this.listOfAverageFitnessFunction = listOfAverageFitnessFunction;
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
}
