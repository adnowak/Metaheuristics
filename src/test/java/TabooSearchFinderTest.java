import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Algorithm.TabuSearch.TabooSearchFinder;
import Algorithm.SolutionHeuristicStrategy;
import Data.Loader;
import Data.Research;
import Model.Problem;
import org.junit.Test;

import java.util.ArrayList;

public class TabooSearchFinderTest {
  public void runTest(Problem problem, TabooSearchFinder finder){
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
  }

  @Test
  public void findSolutionSmallestTest(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Iters100Test(){
    int iterationsAmount = 100;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5SwapMutationTest(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Taboo20Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Neigh100Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 100;
    int tabooSize = 10;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Iters100Neigh100Test(){
    int iterationsAmount = 100;
    int neighbourhoodSize = 100;
    int tabooSize = 10;
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder = new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN37K6Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN39K5Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n39-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN45K6Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n45-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN48K7Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 32;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n48-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN54K7Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 32;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n54-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN60K9Test(){
    int iterationsAmount = 5000;
    int neighbourhoodSize = 32;
    int tabooSize = 10;
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    TabooSearchFinder finder= new TabooSearchFinder(iterationsAmount, neighbourhoodSize, tabooSize, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n60-k9.txt").loadProblem(), finder);
  }
}
