import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Algorithm.TabuSearch.TabooSearchFinder;
import Data.Loader;
import Data.Research;
import Model.Problem;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TabooSearchOptimizationTest {
  public int runTest(Problem problem, TabooSearchFinder finder){
    Research stats = new Research();
    ArrayList<Solution> solutions = new ArrayList<>();
    for(int i=0; i<10; i++){
      finder.setResearch(new Research());
      Solution solution = finder.findSolution(problem);
      solutions.add(solution);
      stats.addToResearch(new Population(finder.getLastNeighbourhoodList()));

      finder.getResearch().saveToFileTabooAnnealing("taboo/"
              +problem.getName()
              +"_i"+finder.getIterationsAmount()
              +"_n"+finder.getNeighbourhoodSize()
              +"_t"+finder.getTabooSize()
              +"_m"+finder.getNeighbourhoodStrategy().getClass().getSimpleName()
          , i);
    }
    System.out.println(new Population(solutions).getStats());
    return 0;
  }

  public void optimize(Problem problem){
    List<Integer> iterationAmounts = Arrays.asList(100, 500, 1000, 2000, 5000);
    List<Integer> neighbourhoodSizes = Arrays.asList(2, 4, 8, 16, 20, 32, 48, 100);
    List<Integer> tabooSizes = Arrays.asList(1, 2, 5, 8, 10, 12, 15, 20);
    List<Mutation> mutationStrategies = Arrays.asList(new Inversion(), new Swap());

    for (Integer iterationsAmount: iterationAmounts) {
      for (Integer neighbourhoodSize: neighbourhoodSizes) {
        for (Integer tabooSize: tabooSizes) {
          for (Mutation mutationStrategy: mutationStrategies) {
            TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, new RandomFinder());
            runTest(problem, finder);
          }
        }
      }
    }
  }

  @Test
  public void optimizeSmallestTest(){
    optimize(new Loader("src/main/resources/smallest.txt").loadProblem());
  }
}
