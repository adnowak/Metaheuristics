package Data;

import Model.Problem;
import Model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {
  private String filename;

  public Loader(String filename) {
    this.filename = filename;
  }

  public Problem loadProblem(){
    String name = null;
    int dimension = -1;
    int capacity = -1;
    City depot = null;
    City[] cities = null;

    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);

      int lineIndex = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        if(lineIndex==0){
          name = data.replace("NAME : ", "");
        }else if(lineIndex==3){
          dimension = Integer.parseInt(data.replace("DIMENSION : ", ""))-1;
          cities = new City[dimension];
        }else if(lineIndex==5){
          capacity = Integer.parseInt(data.replace("CAPACITY : ", ""));
        }else if(lineIndex==7){
          String[] cityData = data.split(" ");
          depot = new City(Integer.parseInt(cityData[1]), Integer.parseInt(cityData[2]), Integer.parseInt(cityData[3]));
        }else if(capacity != -1 && lineIndex > 7 && lineIndex < dimension + 8){
          String[] cityData = data.split(" ");
          cities[lineIndex-8] = new City(Integer.parseInt(cityData[1]), Integer.parseInt(cityData[2]), Integer.parseInt(cityData[3]));
        }else if(capacity != -1 && lineIndex>dimension +9 && lineIndex < dimension*2 + 10){
          String[] cityData = data.split(" ");
          cities[lineIndex-dimension-10].setDemand(Integer.parseInt(cityData[1]));
        }
        lineIndex++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return new Problem(name, capacity, depot, cities);
  }
}
