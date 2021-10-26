import Data.Loader;
import Model.Problem;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import org.junit.Test;

public class RandomFinderTest {
  private void runTest(Problem problem){
    int runsAmount = 10000;
    double distanceSum = 0;
    Solution solution = null;
    for(int i=0; i<runsAmount; i++){
      solution = new RandomFinder().findSolution(problem);
      distanceSum += solution.getFitness();
    }

    System.out.println("Last path: "+solution.getPathWithDemandString());
    System.out.println("Average distance: "+distanceSum/runsAmount);
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
