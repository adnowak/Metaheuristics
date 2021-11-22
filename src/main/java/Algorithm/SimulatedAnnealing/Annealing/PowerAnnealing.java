package Algorithm.SimulatedAnnealing.Annealing;

public class PowerAnnealing extends Annealing {
  public PowerAnnealing(double startTemp) {
    super(startTemp);
  }

  @Override
  public double getTemperature(int iterationIndex, int iterationsAmount) {
    return startTemp*Math.pow(0.995, iterationIndex);
  }
}
