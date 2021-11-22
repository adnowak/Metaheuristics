import Algorithm.EvolutionAlgorithm.CrossoverRelated.Crossover;
import Algorithm.EvolutionAlgorithm.CrossoverRelated.CycleCrossover;
import Algorithm.EvolutionAlgorithm.CrossoverRelated.OrderedCrossover;
import Algorithm.EvolutionAlgorithm.EvolutionFinder;
import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Roulette;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Selection;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Tournament;
import Algorithm.Solution;
import Data.Loader;
import Data.Research;
import Model.Problem;
import org.junit.Test;

import java.util.ArrayList;

public class EvolutionFinderTest {
  public void runTest(Problem problem, EvolutionFinder finder){
    ArrayList<Solution> statistics = new ArrayList<>();
    for(int i=0; i<10; i++){
      finder.setResearch(new Research());
      Solution solution = finder.findSolution(problem);
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
      statistics.add(solution);
      finder.getResearch().saveToFile("i", i);
    }

    Population population = new Population(statistics);
    System.out.println(population.getStats());
  }

  @Test
  public void findSolutionSmallestTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new CycleCrossover();
    int generationsAmount = 100;
    int populationSize = 10;
    double crossoverProb = 0.9;
    double mutationProb = 0.03;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionTest(){
    Selection selectionStrategy = new Tournament(7);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 1000;
    int populationSize = 200;
    double crossoverProb = 0.7;
    double mutationProb = 0.5;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionP10G10Test(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 10;
    int populationSize = 10;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionP100G10Test(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 10;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionP100G100Test(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionP200G100Test(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 200;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN37K6InversionTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN39K5InversionTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n39-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN45K6InversionTest(){
    Selection selectionStrategy = new Tournament(100);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n45-k6.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN48K7InversionTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n48-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN54K7InversionTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n54-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN60K9InversionTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 1000;
    int populationSize = 200;
    double crossoverProb = 0.7;
    double mutationProb = 0.2;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n60-k9.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5RouletteTest(){
    Selection selectionStrategy = new Roulette();
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Tournament1Test(){
    Selection selectionStrategy = new Tournament(1);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.01;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5TournamentCXTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new CycleCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5TournamentSwapTest(){
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.08;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Tournament100Test(){
    Selection selectionStrategy = new Tournament(100);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.01;
    EvolutionFinder finder = new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }
}
