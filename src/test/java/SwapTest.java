import Algorithm.EvolutionAlgorithm.CrossoverRelated.OrderedCrossover;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Data.Loader;
import Model.Problem;
import org.junit.Test;

public class SwapTest {
  @Test
  public void test(){
    Swap swap = new Swap();
    Problem problem = new Loader("src/main/resources/A-n32-k5.txt").loadProblem();
    Solution solution1 = new RandomFinder().findSolution(problem);

    System.out.println(solution1.getPathWithDemandString());
    System.out.println(solution1.getFitness());
    swap.mutateIndividual(solution1, 0.01);

    System.out.println();
    System.out.println(solution1.getPathWithDemandString());
    System.out.println(solution1.getFitness());
  }
}
