package Data;

import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.Solution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Research {
  private ArrayList<Population> populations;
  private ArrayList<Solution> recentSolutions;
  private ArrayList<Solution> bestGlobals;

  public Research() {
    populations = new ArrayList<>();
    recentSolutions = new ArrayList<>();
    bestGlobals = new ArrayList<>();
  }

  public void addToBestGlobals(Solution solution){
    bestGlobals.add(solution);
  }

  public void addToRecentSolutions(Solution solution){
    recentSolutions.add(solution);
  }

  public void addToResearch(Population population){
    populations.add(population);
  }

  public double getAverageBest(){
    double sum = 0.0;
    for (Population population: populations) {
      sum += population.getBestIndividual().getFitness();
    }
    return sum/populations.size();
  }

  public double getAverageAverage(){
    double sum = 0.0;
    for (Population population: populations) {
      sum += population.getAverageFitness();
    }
    return sum/populations.size();
  }

  public double getAverageWorst(){
    double sum = 0.0;
    for (Population population: populations) {
      sum += population.getWorstIndividual().getFitness();
    }
    return sum/populations.size();
  }

  public double getAverageStd(){
    double sum = 0.0;
    for (Population population: populations) {
      sum += population.getStandardDeviation();
    }
    return sum/populations.size();
  }

  public String getStats(){
    StringBuilder stats = new StringBuilder();
    stats
        .append(getAverageBest())
        .append(";")
        .append(getAverageAverage())
        .append(";")
        .append(getAverageWorst())
        .append(";")
        .append(getAverageStd())
        .append("\n");
    return stats.toString();
  }

  public void saveToFile(String researchIdentifier, int researchIndex) {
    StringBuilder stats = new StringBuilder();
    stats.append("Generation;Best;Avg;Worst;Std\n");
    for(int i=0; i<populations.size(); i++){
      stats
          .append(i)
          .append(";")
          .append(populations.get(i).getBestIndividual().getFitness())
          .append(";")
          .append(populations.get(i).getAverageFitness())
          .append(";")
          .append(populations.get(i).getWorstIndividual().getFitness())
          .append(";")
          .append(populations.get(i).getStandardDeviation())
          .append("\n");
    }
    String filename = "out/"+researchIdentifier+"_"+researchIndex+".txt";
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(String.valueOf(stats));
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void saveToFileTabooAnnealing(String researchIdentifier, int researchIndex) {
    StringBuilder stats = new StringBuilder();
    stats.append("Generation;Best;Recent\n");
    for(int i=0; i<populations.size(); i++){
      stats
          .append(i)
          .append(";")
          .append(bestGlobals.get(i).getFitness())
          .append(";")
          .append(recentSolutions.get(i).getFitness())
          .append("\n");
    }
    String filename = "out/"+researchIdentifier+"_"+researchIndex+".txt";
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(String.valueOf(stats));
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
