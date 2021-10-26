package Model;

public class City {
  private int name;
  private int xPos;
  private int yPos;
  private int demand;

  public City(int name, int xPos, int yPos, int demand) {
    this.name = name;
    this.xPos = xPos;
    this.yPos = yPos;
    this.demand = demand;
  }

  public City(int name, int xPos, int yPos) {
    this.name = name;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public int getName() {
    return name;
  }

  public void setName(int name) {
    this.name = name;
  }

  public int getxPos() {
    return xPos;
  }

  public void setxPos(int xPos) {
    this.xPos = xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public void setyPos(int yPos) {
    this.yPos = yPos;
  }

  public int getDemand() {
    return demand;
  }

  public void setDemand(int demand) {
    this.demand = demand;
  }
}
