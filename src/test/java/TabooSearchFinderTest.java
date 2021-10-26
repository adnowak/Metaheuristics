import Algorithm.EvolutionAlgorithm.EvolutionFinder;
import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.GreedyFinder;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Algorithm.TabuSearch.TabooSearchFinder;
import Data.Loader;
import Model.Problem;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class TabooSearchFinderTest {
  public void runTest(Problem problem, SolutionHeuristicStrategy finder){
    Solution solution = finder.findSolution(problem);

    System.out.println(solution.getPathWithDemandString());
    System.out.println(solution.getFitness());
    System.out.println(solution.getPathWithoutNulls().length);
    System.out.println(Arrays.stream(solution.getPathWithoutNulls()).distinct().count());
    HashSet<Integer> cityNames = new HashSet<>();
    int depotsAmount = 0;
    for (int i=0; i<solution.getPathWithoutNulls().length; i++){
      cityNames.add(solution.getPathWithoutNulls()[i].getName());
      if(solution.getPathWithoutNulls()[i].getName()==1){
        depotsAmount++;
      }
    }
    System.out.println(depotsAmount);
    System.out.println(cityNames);
  }

  @Test
  public void findSolutionSmallestTest(){
    int iterationsAmount = 1000;
    int neighbourhoodSize = 10;
    int tabooSize = 20;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SolutionHeuristicStrategy finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Test(){
    int iterationsAmount = 1000;
    int neighbourhoodSize = 50;
    int tabooSize = 20;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new GreedyFinder();
    SolutionHeuristicStrategy finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN37K6Test(){
    int iterationsAmount = 1000;
    int neighbourhoodSize = 100;
    int tabooSize = 20;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new GreedyFinder();
    SolutionHeuristicStrategy finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem(), finder);
  }
}
