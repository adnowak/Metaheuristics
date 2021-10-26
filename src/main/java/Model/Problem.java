package Model;

public class Problem {
  private String name;
  private int capacity;
  private City depot;
  private City[] cities;
  private double[][] distanceMatrix;

  public Problem(String name, int capacity, City depot, City[] cities) {
    this.name = name;
    this.capacity = capacity;
    this.depot = depot;
    this.cities = cities;
    initiateDistanceMatrix();
  }

  public Problem(Problem base){
    this.name = base.name;
    this.capacity = base.capacity;
    this.depot = base.depot;
    this.cities = base.cities;
    this.distanceMatrix = base.distanceMatrix;
  }

  private void initiateDistanceMatrix(){
    City[] citiesWithDepot = new City[cities.length+1];
    citiesWithDepot[0] = depot;

    for(int i=0; i<cities.length; i++){
      citiesWithDepot[i+1] = cities[i];
    }

    distanceMatrix = new double[citiesWithDepot.length][citiesWithDepot.length];
    for(int i=0; i<citiesWithDepot.length; i++){
      for(int j=0; j<citiesWithDepot.length; j++){
        distanceMatrix[i][j] = Math.sqrt(
            (citiesWithDepot[i].getxPos()-citiesWithDepot[j].getxPos())*
            (citiesWithDepot[i].getxPos()-citiesWithDepot[j].getxPos())+
            (citiesWithDepot[i].getyPos()-citiesWithDepot[j].getyPos())*
            (citiesWithDepot[i].getyPos()-citiesWithDepot[j].getyPos())
        );
      }
    }
  }

  public double[][] getDistanceMatrix() {
    return distanceMatrix;
  }

  public City[] getCities() {
    return cities;
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }

  public City getDepot() {
    return depot;
  }
}
