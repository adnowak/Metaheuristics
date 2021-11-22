package Algorithm.EvolutionAlgorithm;

import Algorithm.Solution;
import Model.Utils;

import java.util.List;

public class Population {
  private List<Solution> generation;

  public Population(List<Solution> generation) {
    this.generation = generation;
  }

  public List<Solution> getGeneration() {
    return generation;
  }

  public Solution getBestIndividual(){
    Solution bestIndividual = generation.get(0);
    double bestFitness = bestIndividual.getFitness();
    for(int i = 1; i< generation.size(); i++){
      if(generation.get(i).getFitness() < bestFitness){
        bestIndividual = generation.get(i);
        bestFitness = bestIndividual.getFitness();
      }
    }
    return bestIndividual;
  }

  public double getAverageFitness(){
    double fitnessSum = 0.0;
    for(int i = 0; i< generation.size(); i++){
      fitnessSum += generation.get(i).getFitness();
    }
    return fitnessSum/generation.size();
  }

  public Solution getWorstIndividual(){
    Solution worstIndividual = generation.get(0);
    double worstFitness = worstIndividual.getFitness();
    for(int i = 1; i< generation.size(); i++){
      if(generation.get(i).getFitness() > worstFitness){
        worstIndividual = generation.get(i);
        worstFitness = worstIndividual.getFitness();
      }
    }
    return worstIndividual;
  }

  public double getStandardDeviation(){
    double[] fitnessArray = new double[generation.size()];
    for(int i=0; i<generation.size(); i++){
      fitnessArray[i] = generation.get(i).getFitness();
    }
    return Utils.calculateSD(fitnessArray);
  }

  public String getStats(){
    StringBuilder stats = new StringBuilder();
    stats
        .append(getBestIndividual().getFitness())
        .append(";")
        .append(getAverageFitness())
        .append(";")
        .append(getWorstIndividual().getFitness())
        .append(";")
        .append(getStandardDeviation())
        .append("\n");
    return stats.toString();
  }
}
