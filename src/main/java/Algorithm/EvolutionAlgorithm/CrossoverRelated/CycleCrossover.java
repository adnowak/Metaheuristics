package Algorithm.EvolutionAlgorithm.CrossoverRelated;

import Algorithm.Solution;
import Model.City;

import java.util.ArrayList;
import java.util.List;

public class CycleCrossover extends Crossover{
  @Override
  public Solution crossoverParents(Solution parent1, Solution parent2) {
    ArrayList<City> parent1CitiesWithoutDepot = new ArrayList<>();
    for(int i=0; i<parent1.getPath().length; i++){
      if(parent1.getPath()[i]!=null){
        if(parent1.getPath()[i].getName()!=1){
          parent1CitiesWithoutDepot.add(parent1.getPath()[i]);
        }
      }
    }
    ArrayList<City> parent2CitiesWithoutDepot = new ArrayList<>();
    for(int i=0; i<parent2.getPath().length; i++){
      if(parent2.getPath()[i]!=null){
        if(parent2.getPath()[i].getName()!=1){
          parent2CitiesWithoutDepot.add(parent2.getPath()[i]);
        }
      }
    }

    List<City> citiesToGo = (List<City>) parent1CitiesWithoutDepot.clone();
    ArrayList<City> cycle = new ArrayList<>();
    City recentCity = parent1CitiesWithoutDepot.get(0);
    while (true){
      cycle.add(recentCity);
      recentCity = parent2CitiesWithoutDepot.get(parent1CitiesWithoutDepot.indexOf(recentCity));
      if(recentCity.equals(parent1CitiesWithoutDepot.get(0))){
        break;
      }
    }

    for(int i=0; i<parent1CitiesWithoutDepot.size(); i++){
      if(!cycle.contains(parent1CitiesWithoutDepot.get(i))){
        citiesToGo.set(i, parent2CitiesWithoutDepot.get(i));
      }
    }

    City[] cities = new City[parent1.getProblem().getCities().length*2];
    for (int i=0; i<citiesToGo.size(); i++) {
      cities[i] = citiesToGo.get(i);
    }

    return new Solution(parent1.getProblem(), cities);
  }
}
