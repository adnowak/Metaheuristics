package Algorithm.EvolutionAlgorithm.CrossoverRelated;

import Algorithm.Solution;

public abstract class Crossover {
  public abstract Solution crossoverParents(Solution parent1, Solution parent2);
}
