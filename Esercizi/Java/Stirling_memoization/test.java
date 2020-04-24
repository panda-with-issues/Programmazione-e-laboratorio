
public class test {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    long val = Stirling.generate(35, 20); // 1 ms
    long stop = System.currentTimeMillis();
    long duration = stop - start;
    System.out.println("calculated " + val + " in " + duration + " milliseconds.");
    long start2 = System.currentTimeMillis();
    long val2 = Stirling.badGeneration(35, 20); // 11 seconds
    long stop2 = System.currentTimeMillis();
    long duration2 = stop2 - start2;
    System.out.println("calculated " + val2 + " in " + duration2 + " milliseconds.");
  }
}