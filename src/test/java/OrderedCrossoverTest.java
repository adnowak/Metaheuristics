import Algorithm.EvolutionAlgorithm.CrossoverRelated.OrderedCrossover;
import Algorithm.EvolutionAlgorithm.EvolutionFinder;
import Algorithm.GreedyFinder;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Data.Loader;
import Model.Problem;
import org.junit.Test;

public class OrderedCrossoverTest {
  @Test
  public void test(){
    OrderedCrossover orderedCrossover = new OrderedCrossover();
    Problem problem = new Loader("src/main/resources/A-n32-k5.txt").loadProblem();
    Solution solution1 = new GreedyFinder().findSolution(problem);
    Solution solution2 = new RandomFinder().findSolution(problem);

    Solution crossed1 = orderedCrossover.crossoverParents(solution1, solution2);
    Solution crossed2 = orderedCrossover.crossoverParents(solution2, solution1);

    System.out.println("Parents: ");
    System.out.println(solution1.getPathWithDemandString());
    System.out.println(solution1.getFitness());
    System.out.println(solution2.getPathWithDemandString());
    System.out.println(solution2.getFitness());
    System.out.println("Children: ");
    System.out.println(crossed1.getPathWithDemandString());
    System.out.println(crossed1.getFitness());
    System.out.println(crossed2.getPathWithDemandString());
    System.out.println(crossed2.getFitness());
  }
}
