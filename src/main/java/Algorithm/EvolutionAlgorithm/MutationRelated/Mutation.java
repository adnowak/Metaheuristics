package Algorithm.EvolutionAlgorithm.MutationRelated;

import Algorithm.Solution;
import Model.City;

import java.util.ArrayList;

public abstract class Mutation {
  public abstract void mutateIndividual(Solution solution, double mutationProb);

  protected void fixIndividual(Solution solution){
    ArrayList<City> mutatedCities = new ArrayList<>();
    ArrayList<City> citiesToGo = new ArrayList<>();
    for(int i=0; i<solution.getPathWithoutNulls().length; i++){
      if(solution.getPathWithoutNulls()[i].getName()!=solution.getProblem().getDepot().getName()){
        mutatedCities.add(solution.getPathWithoutNulls()[i]);
      }
    }

    int inStorage = 0;
    for(int i=0; i<mutatedCities.size(); i++){
      if(inStorage <= mutatedCities.get(i).getDemand()){
        citiesToGo.add(solution.getProblem().getDepot());
        inStorage = solution.getProblem().getCapacity();
      }
      citiesToGo.add(mutatedCities.get(i));
      inStorage -= mutatedCities.get(i).getDemand();
    }
    citiesToGo.add(solution.getProblem().getDepot());

    for (int i=0; i<solution.getPath().length; i++) {
      solution.setPathAt(i, null);
    }
    for (int i=0; i<citiesToGo.size(); i++) {
      solution.setPathAt(i, citiesToGo.get(i));
    }
  }
}
