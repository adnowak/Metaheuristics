package Algorithm.EvolutionAlgorithm;

import Algorithm.EvolutionAlgorithm.CrossoverRelated.Crossover;
import Algorithm.EvolutionAlgorithm.CrossoverRelated.OrderedCrossover;
import Algorithm.EvolutionAlgorithm.MutationRelated.Inversion;
import Algorithm.EvolutionAlgorithm.MutationRelated.Mutation;
import Algorithm.EvolutionAlgorithm.MutationRelated.Swap;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Roulette;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Selection;
import Algorithm.EvolutionAlgorithm.SelectionRelated.Tournament;
import Algorithm.GreedyFinder;
import Algorithm.RandomFinder;
import Model.Problem;
import Algorithm.Solution;
import Algorithm.SolutionHeuristicStrategy;

import Model.Utils;
import java.util.ArrayList;

public class EvolutionFinder extends SolutionHeuristicStrategy {
  private Selection selectionStrategy;
  private Mutation mutationStrategy;
  private Crossover crossoverStrategy;
  private int generationsAmount;
  private int populationSize;
  private double crossoverProb;
  private double mutationProb;

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
    Population recentPopulation = null;
    System.out.println("Generation;Best;Avg;Worst;Std");
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
      System.out.println(i+";"+recentPopulation.getBestIndividual().getFitness()+";"+recentPopulation.getAverageFitness()+";"+recentPopulation.getWorstIndividual().getFitness()+";"+recentPopulation.getStandardDeviation());
    }

    return recentPopulation.getBestIndividual();
  }

  private ArrayList<Solution> initiateIndividualsList(Problem problem, int populationSize) {
    ArrayList<Solution> individualsList = new ArrayList<>();
    individualsList.add(new GreedyFinder().findSolution(problem));
    for(int i=0; i < populationSize-1; i++){
      individualsList.add(new RandomFinder().findSolution(problem));
    }
    return individualsList;
  }
}
