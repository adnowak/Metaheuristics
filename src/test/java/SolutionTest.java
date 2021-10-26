import Algorithm.Solution;
import Data.Loader;
import Model.City;
import Model.Problem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
  @Test
  public void getFitnessTest(){
    Problem problem = new Loader("src/main/resources/smallest.txt").loadProblem();
    City[] cities = new City[problem.getCities().length*2];
    cities[0] = problem.getDepot();
    cities[1] = problem.getCities()[0];
    cities[2] = problem.getCities()[3];
    cities[3] = problem.getDepot();
    cities[4] = problem.getCities()[2];
    cities[5] = problem.getCities()[1];
    cities[6] = problem.getCities()[4];
    cities[7] = problem.getDepot();
    Solution solution = new Solution(problem, cities);

    assertEquals(265.0, solution.getFitness(), 0.5);
  }
}
