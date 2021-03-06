package Algorithm.TabuSearch;

import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.Population;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;
import Data.Research;
import Model.Problem;

import java.util.ArrayList;
import java.util.List;

public class TabooSearchFinder extends SolutionHeuristicStrategy {
  private Research research;
  private int iterationsAmount;
  private int neighbourhoodSize;
  private int tabooSize;
  private List<Solution> lastNeighbourhoodList;
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
    Solution recentSolution = initiationStrategy.findSolution(problem);
    Solution bestGlobal = recentSolution;
    double recentFitness = recentSolution.getFitness();
    List<Solution> tabooList = new ArrayList<>();
    tabooList.add(recentSolution);

    for(int i=0; i<iterationsAmount; i++){
      lastNeighbourhoodList = getNeighbourhoodList(recentSolution);

      Solution bestInNeighbourhood = lastNeighbourhoodList.get(0);
      double bestFitnessInNeighbourhood = bestInNeighbourhood.getFitness();
      for(int j=1; j<neighbourhoodSize; j++){
        if(!tabooList.contains(lastNeighbourhoodList.get(j))
            && lastNeighbourhoodList.get(j).getFitness() < bestFitnessInNeighbourhood){
          bestInNeighbourhood = lastNeighbourhoodList.get(j);
          bestFitnessInNeighbourhood = bestInNeighbourhood.getFitness();
        }
      }

      research.addToRecentSolutions(bestInNeighbourhood);
      research.addToBestGlobals(bestGlobal);
      recentSolution = bestInNeighbourhood;
      recentFitness = recentSolution.getFitness();
      if(tabooList.size() == tabooSize){
        tabooList.remove(0);
      }
      tabooList.add(recentSolution);

      if(recentFitness < bestGlobal.getFitness()){
        bestGlobal = recentSolution;
      }

      ArrayList<Solution> generation = new ArrayList<>();
      generation.addAll(lastNeighbourhoodList);
      research.addToResearch(new Population(generation));
    }

    return bestGlobal;
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

  public void setResearch(Research research) {
    this.research = research;
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

  public int getTabooSize() {
    return tabooSize;
  }

  public Mutation getNeighbourhoodStrategy() {
    return neighbourhoodStrategy;
  }

  public SolutionHeuristicStrategy getInitiationStrategy() {
    return initiationStrategy;
  }
}
