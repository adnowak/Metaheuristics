import Algorithm.EvolutionAlgorithm.Population;
import Data.Loader;
import Model.City;
import Model.Problem;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import org.junit.Test;

import java.util.ArrayList;

public class RandomFinderTest {
  private void runTest(Problem problem){
    int populationSize = 10000;
    ArrayList<Solution> solutions = new ArrayList<>();
    for(int i=0; i<populationSize; i++){
      solutions.add(new RandomFinder().findSolution(problem));
    }
    Population population = new Population(solutions);

    System.out.println(population.getBestIndividual().getFitness()+";"+population.getAverageFitness()+";"+population.getWorstIndividual().getFitness()+";"+population.getStandardDeviation());
  }

  @Test
  public void findSolutionN32K5Test(){
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem());
  }

  @Test
  public void findSolutionN37K6Test(){
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem());
  }

  @Test
  public void findSolutionN39K5Test(){
    runTest(new Loader("src/main/resources/A-n39-k5.txt").loadProblem());
  }

  @Test
  public void findSolutionN45K6Test(){
    runTest(new Loader("src/main/resources/A-n45-k6.txt").loadProblem());
  }

  @Test
  public void findSolutionN48K7Test(){
    runTest(new Loader("src/main/resources/A-n48-k7.txt").loadProblem());
  }

  @Test
  public void findSolutionN54K7Test(){
    runTest(new Loader("src/main/resources/A-n54-k7.txt").loadProblem());
  }

  @Test
  public void findSolutionN60K9Test(){
    runTest(new Loader("src/main/resources/A-n60-k9.txt").loadProblem());
  }
}
