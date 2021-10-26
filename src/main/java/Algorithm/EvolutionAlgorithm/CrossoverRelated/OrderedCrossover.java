package Algorithm.EvolutionAlgorithm.CrossoverRelated;

import Algorithm.Solution;
import Model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderedCrossover extends Crossover{
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

    int firstGeneIndex = new Random().nextInt(parent1CitiesWithoutDepot.size());
    int secondGeneIndex = new Random().nextInt(parent1CitiesWithoutDepot.size());

    if(firstGeneIndex>secondGeneIndex){
      int temp = firstGeneIndex;
      firstGeneIndex = secondGeneIndex;
      secondGeneIndex = temp;
    }

    ArrayList<City> cutPart = new ArrayList<>();
    for(int i = 0; i<parent1CitiesWithoutDepot.size(); i++){
      if(i>=firstGeneIndex&&i<=secondGeneIndex){
        cutPart.add(parent1CitiesWithoutDepot.get(i));
      }
    }
    ArrayList<City> resultGenome = new ArrayList<>();

    for(City gene : cutPart){
      parent2CitiesWithoutDepot.remove(gene);
    }

    for(int i=0; i<firstGeneIndex; i++){
      resultGenome.add(parent2CitiesWithoutDepot.get(i));
    }
    for(int i=0; i<=secondGeneIndex-firstGeneIndex; i++){
      resultGenome.add(cutPart.get(i));
    }
    for(int i=secondGeneIndex+1; i<parent1CitiesWithoutDepot.size(); i++){
      try{
        resultGenome.add(parent2CitiesWithoutDepot.get(i-cutPart.size()));
      }
      catch (Exception e){
        System.out.println();
      }
    }

    City[] cities = new City[parent1.getProblem().getCities().length*2];
    List<City> citiesToGo = new ArrayList<>();
    int inStorage = 0;
    for(int i=0; i<resultGenome.size(); i++){
      if(inStorage < resultGenome.get(i).getDemand()){
        citiesToGo.add(parent1.getProblem().getDepot());
        inStorage = parent1.getProblem().getCapacity();
      }
      citiesToGo.add(resultGenome.get(i));
      inStorage -= resultGenome.get(i).getDemand();
    }

    for (int i=0; i<citiesToGo.size(); i++) {
      cities[i] = citiesToGo.get(i);
    }

    return new Solution(parent1.getProblem(), cities);
  }
}
