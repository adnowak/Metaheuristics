package Algorithm.SimulatedAnnealing.Annealing;

public class LinearAnnealing extends Annealing {
  public LinearAnnealing(double startTemp) {
    super(startTemp);
  }

  @Override
  public double getTemperature(int iterationIndex, int iterationsAmount) {
    return startTemp*(1.0-((double) iterationIndex / (double) iterationsAmount));
  }
}
