package Algorithm.EvolutionAlgorithm;

import Algorithm.EvolutionAlgorithm.CrossoverRelated.Crossover;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Selection;
import Algorithm.RandomFinder;
import Algorithm.Solution;
import Data.Research;
import Model.Problem;
import Algorithm.SolutionHeuristicStrategy;

import Model.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EvolutionFinder extends SolutionHeuristicStrategy {
  private Research research;
  private Selection selectionStrategy;
  private Mutation mutationStrategy;
  private Crossover crossoverStrategy;
  private int generationsAmount;
  private int populationSize;
  private double crossoverProb;
  private double mutationProb;
  private Population recentPopulation;

  public EvolutionFinder(Selection selectionStrategy, Mutation mutationStrategy, Crossover crossoverStrategy, int generationsAmount, int populationSize, double crossoverProb, double mutationProb) {
    this.selectionStrategy = selectionStrategy;
    this.mutationStrategy = mutationStrategy;
    this.crossoverStrategy = crossoverStrategy;
    this.generationsAmount = generationsAmount;
    this.populationSize = populationSize;
    this.crossoverProb = crossoverProb;
    this.mutationProb = mutationProb;
  }

  @Override
  public Solution findSolution(Problem problem) {
    ArrayList<Solution> recentGeneration = initiateIndividualsList(problem, populationSize);
    recentPopulation = null;
    for(int i=0; i<generationsAmount; i++){
      recentPopulation = new Population(recentGeneration);
      ArrayList<Solution> newGeneration = new ArrayList<>();
      while (newGeneration.size() < recentPopulation.getGeneration().size()-2){
        Solution parent1 = selectionStrategy.select(recentPopulation);
        Solution parent2 = selectionStrategy.select(recentPopulation);
        Solution child1;
        Solution child2;
        if(Utils.getRandomNumber(0.0,1.0) < crossoverProb){
          child1 = crossoverStrategy.crossoverParents(parent1, parent2);
          child2 = crossoverStrategy.crossoverParents(parent2, parent1);
        }
        else{
          child1 = new Solution(parent1);
          child2 = new Solution(parent2);
        }
        mutationStrategy.mutateIndividual(child1, mutationProb);
        mutationStrategy.mutateIndividual(child2, mutationProb);
        newGeneration.add(child1);
        newGeneration.add(child2);
      }
      newGeneration.add(recentPopulation.getBestIndividual());
      newGeneration.add(recentPopulation.getGeneration().get(0));
      recentGeneration = newGeneration;
      research.addToResearch(recentPopulation);
    }

    return recentPopulation.getBestIndividual();
  }

  private ArrayList<Solution> initiateIndividualsList(Problem problem, int populationSize) {
    ArrayList<Solution> individualsList = new ArrayList<>();
//    individualsList.add(new GreedyFinder().findSolution(problem));
    for(int i=0; i < populationSize; i++){
      individualsList.add(new RandomFinder().findSolution(problem));
    }
    return individualsList;
  }

  public Population getRecentPopulation() {
    return recentPopulation;
  }

  public Research getResearch() {
    return research;
  }

  public void setResearch(Research research) {
    this.research = research;
  }
}
