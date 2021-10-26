package Model;

public class Utils {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }
    public static double getRandomNumber(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }

    public static double calculateSD(double numArray[]){
      double sum = 0.0, standardDeviation = 0.0;
      int length = numArray.length;

      for(double num : numArray) {
        sum += num;
      }

      double mean = sum/length;

      for(double num: numArray) {
        standardDeviation += Math.pow(num - mean, 2);
      }

      return Math.sqrt(standardDeviation/length);
    }
}
