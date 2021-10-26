package Algorithm.EvolutionAlgorithm.SelectionRelated;

import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.Solution;
import java.util.Random;

public class Roulette extends Selection{
  @Override
  public Solution select(Population population) {
    double[] weightsArray = new double[population.getGeneration().size()];
    for(int i=0 ; i<population.getGeneration().size(); i++){
      weightsArray[i] = 1/ Math.pow(population.getGeneration().get(i).getFitness(), 22);
    }

    return population.getGeneration().get(selectRandomWeighted(weightsArray, new Random()));
  }

  private static int selectRandomWeighted(double[] wts, Random rnd) {
    int selected = 0;
    double total = wts[0];

    for(int i = 1; i < wts.length; i++){
      total += wts[i];
      if(rnd.nextDouble() <= (wts[i] / total)) selected = i;
    }

    return selected;
  }
}
