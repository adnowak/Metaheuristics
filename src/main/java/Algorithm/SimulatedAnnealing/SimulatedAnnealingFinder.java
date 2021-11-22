package Algorithm.SimulatedAnnealing;

import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.SimulatedAnnealing.Annealing.Annealing;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Data.Research;
import Model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealingFinder extends SolutionHeuristicStrategy {
  private Research research;
  private int iterationsAmount;
  private int neighbourhoodSize;
  private Annealing annealingStrategy;
  private Mutation neighbourhoodStrategy;
  private SolutionHeuristicStrategy initiationStrategy;
  private List<Solution> lastNeighbourhoodList;

  public SimulatedAnnealingFinder(int iterationsAmount, int neighbourhoodSize, Annealing annealingStrategy, Mutation neighbourhoodStrategy, SolutionHeuristicStrategy initiationStrategy) {
    this.iterationsAmount = iterationsAmount;
    this.neighbourhoodSize = neighbourhoodSize;
    this.annealingStrategy = annealingStrategy;
    this.neighbourhoodStrategy = neighbourhoodStrategy;
    this.initiationStrategy = initiationStrategy;
  }

  @Override
  public Solution findSolution(Problem problem) {
    Solution recentSolution = initiationStrategy.findSolution(problem);
    Solution bestGlobal = recentSolution;
    double recentFitness = recentSolution.getFitness();
    double temperature;

    for(int i=0; i<iterationsAmount; i++){
      temperature = annealingStrategy.getTemperature(i, iterationsAmount);
      lastNeighbourhoodList = getNeighbourhoodList(recentSolution);

      Solution bestInNeighbourhood = null;
      double bestFitnessInNeighbourhood = 0.0;
      for(int j=0; j<neighbourhoodSize; j++){
        if(bestInNeighbourhood == null){
          bestInNeighbourhood = lastNeighbourhoodList.get(j);
          bestFitnessInNeighbourhood = bestInNeighbourhood.getFitness();
        }
        else if(lastNeighbourhoodList.get(j).getFitness() < bestFitnessInNeighbourhood){
          bestInNeighbourhood = lastNeighbourhoodList.get(j);
          bestFitnessInNeighbourhood = bestInNeighbourhood.getFitness();
        }
      }

      research.addToRecentSolutions(recentSolution);
      research.addToBestGlobals(bestGlobal);
      if(bestInNeighbourhood.getFitness() < recentFitness){
        recentSolution = bestInNeighbourhood;
        recentFitness = recentSolution.getFitness();
      }
      else {
//        System.out.println(Math.pow(2.71,(recentFitness-bestInNeighbourhood.getFitness())/temperature));
        if(new Random().nextDouble() < Math.pow(2.71,(recentFitness-bestInNeighbourhood.getFitness())/temperature)){
          recentSolution = bestInNeighbourhood;
          recentFitness = recentSolution.getFitness();
        }
      }

      if(recentFitness < bestGlobal.getFitness()){
        bestGlobal = recentSolution;
      }

      ArrayList<Solution> generation = new ArrayList<>();
      generation.addAll(lastNeighbourhoodList);
      research.addToResearch(new Population(generation));
    }

    return recentSolution;
  }

  private List<Solution> getNeighbourhoodList(Solution solution){
    ArrayList<Solution> neighbourhoodList = new ArrayList<>();

    for(int i=0; i<neighbourhoodSize; i++){
      Solution recentNeighbour = new Solution(solution);
      neighbourhoodStrategy.mutateIndividual(recentNeighbour, neighbourhoodStrategy.getDefaultProb());
      neighbourhoodList.add(recentNeighbour);
    }

    return neighbourhoodList;
  }

  public Research getResearch() {
    return research;
  }

  public List<Solution> getLastNeighbourhoodList() {
    return lastNeighbourhoodList;
  }

  public int getIterationsAmount() {
    return iterationsAmount;
  }

  public int getNeighbourhoodSize() {
    return neighbourhoodSize;
  }

  public Annealing getAnnealingStrategy() {
    return annealingStrategy;
  }

  public Mutation getNeighbourhoodStrategy() {
    return neighbourhoodStrategy;
  }

  public void setResearch(Research research) {
    this.research = research;
  }
}
