package Algorithm.EvolutionAlgorithm.MutationRelated;

import Algorithm.Solution;
import Model.City;
import Model.Utils;

public class Inversion extends Mutation{
  @Override
  public void mutateIndividual(Solution solution, double mutationProb) {
    if(Utils.getRandomNumber(0.0,1.0) < mutationProb){
      int firstIndex = Utils.getRandomNumber(0, solution.getPathWithoutNulls().length);
      int secondIndex = Utils.getRandomNumber(0, solution.getPathWithoutNulls().length);

      if(secondIndex<firstIndex){
        int tmp = firstIndex;
        firstIndex = secondIndex;
        secondIndex = tmp;
      }

      City[] invertedGenes = new City[secondIndex-firstIndex+1];
      for(int i=0; i < secondIndex-firstIndex+1; i++){
        invertedGenes[i] = solution.getPath()[secondIndex-i];
      }

      for(int i=firstIndex; i<secondIndex+1; i++){
        solution.getPath()[i] = invertedGenes[i-firstIndex];
      }

      fixIndividual(solution);
    }
  }

  @Override
  public double getDefaultProb() {
    return 1.0;
  }
}
