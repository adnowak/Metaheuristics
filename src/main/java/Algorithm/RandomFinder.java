package Algorithm;

import Model.City;
import Model.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomFinder extends SolutionHeuristicStrategy{
  @Override
  public Solution findSolution(Problem problem) {
    City[] cities = new City[problem.getCities().length*2];
    List<City> citiesToShuffle = new ArrayList<>();
    List<City> citiesToGo = new ArrayList<>();
    for(int i=0; i<problem.getCities().length; i++){
      citiesToShuffle.add(problem.getCities()[i]);
    }

    Collections.shuffle(citiesToShuffle);

    int inStorage = 0;
    for(int i=0; i<citiesToShuffle.size(); i++){
      if(inStorage <= citiesToShuffle.get(i).getDemand()){
        citiesToGo.add(problem.getDepot());
        inStorage = problem.getCapacity();
      }
      citiesToGo.add(citiesToShuffle.get(i));
      inStorage -= citiesToShuffle.get(i).getDemand();
    }
    citiesToGo.add(problem.getDepot());

    for (int i=0; i<citiesToGo.size(); i++) {
      cities[i] = citiesToGo.get(i);
    }
    return new Solution(problem, cities);
  }
}
