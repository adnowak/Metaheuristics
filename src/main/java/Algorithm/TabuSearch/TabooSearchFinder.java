package Algorithm.TabuSearch;

import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.GreedyFinder;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Model.Problem;

import java.util.ArrayList;
import java.util.List;

public class TabooSearchFinder extends SolutionHeuristicStrategy {
  private int iterationsAmount;
  private int neighbourhoodSize;
  private int tabooSize;
  private Mutation neighbourhoodStrategy;
  private SolutionHeuristicStrategy initiationStrategy;

  public TabooSearchFinder(int iterationsAmount, int neighbourhoodSize, int tabooSize, Mutation neighbourhoodStrategy, SolutionHeuristicStrategy initiationStrategy) {
    this.iterationsAmount = iterationsAmount;
    this.neighbourhoodSize = neighbourhoodSize;
    this.tabooSize = tabooSize;
    this.neighbourhoodStrategy = neighbourhoodStrategy;
    this.initiationStrategy = initiationStrategy;
  }

  @Override
  public Solution findSolution(Problem problem) {
    Solution bestSolution = initiationStrategy.findSolution(problem);
    double bestFitness = bestSolution.getFitness();
    List<Solution> tabooList = new ArrayList<>();
    tabooList.add(bestSolution);
    List<Solution> neighbourhoodList;

    for(int i=0; i<iterationsAmount; i++){
      neighbourhoodList = getNeighbourhoodList(bestSolution);

      Solution bestInNeighbourhood = null;
      double bestFitnessInNeighbourhood = 0.0;
      for(int j=0; j<neighbourhoodSize; j++){
        if(!tabooList.contains(neighbourhoodList.get(j))){
          if(bestInNeighbourhood == null){
            bestInNeighbourhood = neighbourhoodList.get(j);
            bestFitnessInNeighbourhood = bestInNeighbourhood.getFitness();
          }
          else if(neighbourhoodList.get(j).getFitness() < bestFitnessInNeighbourhood){
            bestInNeighbourhood = neighbourhoodList.get(j);
            bestFitnessInNeighbourhood = neighbourhoodList.get(j).getFitness();
          }
        }
      }

      if(bestInNeighbourhood.getFitness() < bestFitness){
        bestSolution = bestInNeighbourhood;
        bestFitness = bestSolution.getFitness();
        if(tabooList.size() == tabooSize){
          tabooList.remove(0);
        }
        tabooList.add(bestInNeighbourhood);
      }

      ArrayList<Solution> generation = new ArrayList<>();
      generation.addAll(neighbourhoodList);
      generation.add(bestSolution);
      Population recentPopulation = new Population(generation);
      System.out.println(i+";"+recentPopulation.getBestIndividual().getFitness()+";"+recentPopulation.getAverageFitness()+";"+recentPopulation.getWorstIndividual().getFitness()+";"+recentPopulation.getStandardDeviation());
    }

    return bestSolution;
  }

  private List<Solution> getNeighbourhoodList(Solution solution){
    ArrayList<Solution> neighbourhoodList = new ArrayList<>();

    for(int i=0; i<neighbourhoodSize; i++){
      Solution recentNeighbour = new Solution(solution);
      neighbourhoodStrategy.mutateIndividual(recentNeighbour, 1.0);
      neighbourhoodList.add(recentNeighbour);
    }

    return neighbourhoodList;
  }
}
