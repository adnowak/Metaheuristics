package Algorithm.SimulatedAnnealing.Annealing;

public abstract class Annealing {
  protected double startTemp;

  public Annealing(double startTemp) {
    this.startTemp = startTemp;
  }

  public abstract double getTemperature(int iterationIndex, int iterationsAmount);
}
