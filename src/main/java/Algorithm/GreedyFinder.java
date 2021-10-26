package Algorithm;

import Model.City;
import Model.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GreedyFinder extends SolutionHeuristicStrategy{
  @Override
  public Solution findSolution(Problem problem) {
    City[] cities;
    List<City> citiesToGo;
    Solution bestSolution = null;

    for(int i=0; i<problem.getCities().length; i++){
      citiesToGo = new ArrayList<>();
      cities = new City[problem.getCities().length*2];
      HashSet<City> usedCities = new HashSet<>();

      City recentCity = problem.getDepot();
      citiesToGo.add(recentCity);
      recentCity = problem.getCities()[i];
      citiesToGo.add(recentCity);
      usedCities.add(recentCity);
      int inStorage = problem.getCapacity() - recentCity.getDemand();
      while(usedCities.size()<problem.getCities().length){
        if(!recentCity.equals(problem.getDepot()) && inStorage <= findClosestCity(problem, recentCity, usedCities).getDemand()){
          recentCity = problem.getDepot();
          citiesToGo.add(problem.getDepot());
          inStorage = problem.getCapacity();
        }else {
          recentCity = findClosestCity(problem, recentCity, usedCities);
          citiesToGo.add(recentCity);
          usedCities.add(recentCity);
          inStorage -= recentCity.getDemand();
        }
      }
      citiesToGo.add(problem.getDepot());

      for (int j=0; j<citiesToGo.size(); j++) {
        cities[j] = citiesToGo.get(j);
      }
      Solution recentSolution = new Solution(problem, cities);
      if(bestSolution==null){
        bestSolution = recentSolution;
      }
      else if(recentSolution.getFitness()<bestSolution.getFitness()){
        bestSolution = recentSolution;
      }
//      System.out.println(recentSolution.getFitness());
    }
    return bestSolution;
  }

  private City findClosestCity(Problem problem, City recentCity, HashSet<City> usedCities){
    City recentShortestPathCity = null;
    double recentShortestPath = -1;
    for(int i=0; i<problem.getCities().length; i++){
      if((recentShortestPathCity == null||
              problem.getDistanceMatrix()[recentCity.getName()-1][i+1] < recentShortestPath)&&
              !usedCities.contains(problem.getCities()[i])
          ) {
        recentShortestPath = problem.getDistanceMatrix()[recentCity.getName()-1][i+1];
        recentShortestPathCity = problem.getCities()[i];
      }
    }
    return recentShortestPathCity;
  }
}
