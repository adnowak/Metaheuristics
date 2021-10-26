package Algorithm.EvolutionAlgorithm.SelectionRelated;

import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.Solution;
import Model.Utils;

public class Tournament extends Selection{
  int tournamentSize;

  public Tournament(int tournamentSize) {
    this.tournamentSize = tournamentSize;
  }

  @Override
  public Solution select(Population population) {
    Solution bestSolution = null;
    double bestFitness = -1;
    for(int i=0; i<tournamentSize; i++){
      if(bestSolution == null){
        bestSolution = population.getGeneration().get(Utils.getRandomNumber(0, population.getGeneration().size()));
        bestFitness = bestSolution.getFitness();
      }
      else {
        Solution recentSolution = population.getGeneration().get(Utils.getRandomNumber(0, population.getGeneration().size()));
        double recentFitness = recentSolution.getFitness();
        if(recentFitness < bestFitness){
          bestFitness = recentFitness;
          bestSolution = recentSolution;
        }
      }
    }
    return bestSolution;
  }
}
