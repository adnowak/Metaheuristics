package Algorithm.EvolutionAlgorithm.MutationRelated;

import Algorithm.Solution;
import Model.City;
import Model.Utils;

public class Swap extends Mutation{
  @Override
  public void mutateIndividual(Solution solution, double mutationProb) {
    int firstIndex = Utils.getRandomNumber(0, solution.getPathWithoutNulls().length);
    int secondIndex = Utils.getRandomNumber(0, solution.getPathWithoutNulls().length);

    for(int i=0; i<solution.getPathWithoutNulls().length; i++){
      if(Utils.getRandomNumber(0.0,1.0) < mutationProb){
        City originalFirstCity = solution.getPath()[firstIndex];
        solution.getPath()[firstIndex] = solution.getPath()[secondIndex];
        solution.getPath()[secondIndex] = originalFirstCity;
      }
    }

    fixIndividual(solution);
  }

  @Override
  public double getDefaultProb() {
    return 0.04;
  }


}
