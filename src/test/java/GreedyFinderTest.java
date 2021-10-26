import Data.Loader;
import Model.Problem;
import Algorithm.GreedyFinder;
import Algorithm.Solution;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class GreedyFinderTest {
  public void runTest(Problem problem){
    Solution solution = new GreedyFinder().findSolution(problem);

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
    runTest(new Loader("src/main/resources/smallest.txt").loadProblem());
  }

  @Test
  public void findSolutionN32K5Test(){
    runTest(new Loader("src/main/resources/A-n32-k5.txt").loadProblem());
  }

  @Test
  public void findSolutionN37K6Test(){
    runTest(new Loader("src/main/resources/A-n37-k6.txt").loadProblem());
  }
}
