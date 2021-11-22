package Algorithm;

import Model.City;
import Model.Problem;

import java.util.Arrays;

public class Solution {
  private Problem problem;
  private City[] path;

  public Solution(Problem problem, City[] path) {
    this.problem = problem;
    this.path = path;
  }

  public Solution(Solution base){
    this.problem = new Problem(base.problem);
    this.path = new City[base.path.length];
    for(int i=0; i<base.path.length; i++){
      this.path[i] = base.path[i];
    }
  }

  public double getFitness(){
    double fitness = 0.0;
    City recentCity = problem.getDepot();
    City nextCity = path[0];
    fitness += problem.getDistanceMatrix()[recentCity.getName()-1][nextCity.getName()-1];

    for(int i=0; i<path.length-1; i++){
      if(path[i+1]!=null){
        recentCity = path[i];
        nextCity = path[i+1];
        fitness += problem.getDistanceMatrix()[recentCity.getName()-1][nextCity.getName()-1];
      }
    }
    return fitness;
  }

  public Problem getProblem() {
    return problem;
  }

  public void setProblem(Problem problem) {
    this.problem = problem;
  }

  public City[] getPath() {
    return path;
  }

  public City[] getPathWithoutNulls(){
    City[] pathWithoutNulls = null;

    int notNullAmount = 0;
    for(int i=0; i<path.length; i++){
      if(path[i]!=null){
        notNullAmount++;
      }
    }
    pathWithoutNulls = new City[notNullAmount];

    for(int i=0; i<notNullAmount; i++){
      pathWithoutNulls[i] = path[i];
    }

    return pathWithoutNulls;
  }

  public String getPathWithDemandString(){
     return Arrays.toString(Arrays.stream(getPathWithoutNulls()).map(city -> "{ name: "+city.getName()+", demand: "+city.getDemand()+"}").toArray());
  }

  public void setPathAt(int index, City newCity) {
    this.path[index] = newCity;
  }

  public boolean equals(Object object){
    Solution solution = (Solution) object;
    for(int i=0; i<solution.getPathWithoutNulls().length; i++){
      if(!getPathWithoutNulls()[i].equals(solution.getPathWithoutNulls()[i])){
        return false;
      }
    }
    return true;
  }
}
