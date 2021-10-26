import Algorithm.EvolutionAlgorithm.CrossoverRelated.Crossover;
import Algorithm.EvolutionAlgorithm.CrossoverRelated.CycleCrossover;
import Algorithm.EvolutionAlgorithm.CrossoverRelated.OrderedCrossover;
import Algorithm.EvolutionAlgorithm.EvolutionFinder;
import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Roulette;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Selection;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Tournament;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Data.Loader;
import Model.Problem;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class EvolutionFinderTest {
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
    Selection selectionStrategy = new Tournament(10);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new CycleCrossover();
    int generationsAmount = 100;
    int populationSize = 10;
    double crossoverProb = 0.9;
    double mutationProb = 0.03;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5SwapTest(){
    Selection selectionStrategy = new Tournament(100);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.02;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN54K7SwapTest(){
    Selection selectionStrategy = new Tournament(20);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new CycleCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.8;
    double mutationProb = 0.02;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n54-k7.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5InversionTest(){
    Selection selectionStrategy = new Tournament(20);
    Mutation mutationStrategy = new Inversion();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.5;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5RouletteTest(){
    Selection selectionStrategy = new Roulette();
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.7;
    double mutationProb = 0.01;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }

  @Test
  public void findSolutionN32K5Tournament1Test(){
    Selection selectionStrategy = new Tournament(1);
    Mutation mutationStrategy = new Swap();
    Crossover crossoverStrategy = new OrderedCrossover();
    int generationsAmount = 100;
    int populationSize = 100;
    double crossoverProb = 0.0;
    double mutationProb = 0.01;
    SolutionHeuristicStrategy finder= new EvolutionFinder(selectionStrategy, mutationStrategy, crossoverStrategy, generationsAmount, populationSize, crossoverProb, mutationProb);
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem(), finder);
  }
}
