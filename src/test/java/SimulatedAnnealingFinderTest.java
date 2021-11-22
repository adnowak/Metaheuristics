import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.RandomFinder;
import Algorithm.SimulatedAnnealing.Annealing.Annealing;
import Algorithm.SimulatedAnnealing.Annealing.LinearAnnealing;
import Algorithm.SimulatedAnnealing.Annealing.PowerAnnealing;
import Algorithm.SimulatedAnnealing.SimulatedAnnealingFinder;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Data.Loader;
import Data.Research;
import Model.Problem;
import org.junit.Test;

import java.util.ArrayList;

public class SimulatedAnnealingFinderTest {
  public void runTest(Problem problem, SimulatedAnnealingFinder finder){
    Research stats = new Research();
    ArrayList<Solution> solutions = new ArrayList<>();
    for(int i=0; i<10; i++){
      finder.setResearch(new Research());
      Solution solution = finder.findSolution(problem);
      solutions.add(solution);
//      System.out.println(solution.getPathWithDemandString());
//      System.out.println(solution.getFitness());
//      System.out.println(solution.getPathWithoutNulls().length);
//      System.out.println(Arrays.stream(solution.getPathWithoutNulls()).distinct().count());
//      HashSet<Integer> cityNames = new HashSet<>();
//      int depotsAmount = 0;
//      for (int j=0; j<solution.getPathWithoutNulls().length; j++){
//        cityNames.add(solution.getPathWithoutNulls()[j].getName());
//        if(solution.getPathWithoutNulls()[j].getName()==1){
//          depotsAmount++;
//        }
//      }
//      System.out.println(depotsAmount);
//      System.out.println(cityNames);
//      System.out.println();
      stats.addToResearch(new Population(finder.getLastNeighbourhoodList()));
      finder.getResearch().saveToFileTabooAnnealing("annealing/"
              +problem.getName()
              +"_i"+finder.getIterationsAmount()
              +"_n"+finder.getNeighbourhoodSize()
              +"_a"+finder.getAnnealingStrategy().getClass().getSimpleName()
              +"_m"+finder.getNeighbourhoodStrategy().getClass().getSimpleName()
          , i);
    }

    System.out.println(new Population(solutions).getStats());
  }

  @Test
  public void findSolutionSmallestTest(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Iters100Test(){
    int iterationsAmount = 100;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Neigh100Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 100;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Iters100Neigh100Test(){
    int iterationsAmount = 100;
    int neighbourhoodSize = 100;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5LinearAnnealingTest(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new LinearAnnealing(1000);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5SwapNeighbourhoodTest(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Swap();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN37K6Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN39K5Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n39-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN45K6Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n45-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN48K7Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n48-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN54K7Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n54-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN60K9Test(){
    int iterationsAmount = 2000;
    int neighbourhoodSize = 20;
    Annealing annealingStrategy = new PowerAnnealing(1000.0);
    Mutation mutationStrategy = new Inversion();
    SolutionHeuristicStrategy initiationStrategy = new RandomFinder();
    SimulatedAnnealingFinder finder = new SimulatedAnnealingFinder(iterationsAmount, neighbourhoodSize, annealingStrategy, mutationStrategy, initiationStrategy);
    runTest(new Loader("src/main/resources/A-n60-k9.txt").loadProblem(), finder);
  }
}
