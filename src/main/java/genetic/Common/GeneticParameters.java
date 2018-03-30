package genetic.Common;

public class GeneticParameters {

    private int populationNumber = 50;
    private double pCrossing = 0.7;
    private double pMutation = 0.1;

    public GeneticParameters(){}

    public GeneticParameters(final int populationNumber, final double pCrossing, final double pMutation){
        this.populationNumber = populationNumber;
        this.pCrossing = pCrossing;
        this.pMutation = pMutation;
    }

    public int getPopulationNumber() {
        return populationNumber;
    }

    public void setPopulationNumber(int populationNumber) {
        this.populationNumber = populationNumber;
    }

    public double getpCrossing() {
        return pCrossing;
    }

    public void setpCrossing(double pCrossing) {
        this.pCrossing = pCrossing;
    }

    public double getpMutation() {
        return pMutation;
    }

    public void setpMutation(double pMutation) {
        this.pMutation = pMutation;
    }
}
