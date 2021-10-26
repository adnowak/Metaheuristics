package Data;

import Algorithm.EvolutionAlgorithm.Population;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Saver {
  private File fileToWriteInto;

  public Saver(File fileToWriteInto) {
    this.fileToWriteInto = fileToWriteInto;
  }

  public void showPopulationData(int populationNumber, Population population){
    String data = populationNumber+"; "+population.getBestIndividual().getFitness()+"; "+-population.getAverageFitness()+"; "+population.getWorstIndividual().getFitness()+";"+ population.getStandardDeviation()+";\n";
    writeUsingOutputStream(data.replaceAll("\\.", ","), fileToWriteInto);
  }

  private void writeUsingOutputStream(String data, File file) {
    OutputStream os = null;
    try {
      os = new FileOutputStream(file, true);
      os.write(data.getBytes(), 0, data.length());
    } catch (IOException e) {
      e.printStackTrace();
    }finally{
      try {
        os.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
