/**
 * Define a procedure which returns the last knight with jug after the eenie meenie minie moe described by Josephus (T. Flavius).
 * Accept the initial soldiers number (int) as argument.
 */
public class Josephus {
  public static int last(int knights) {
    RoundTable rt = new RoundTable(knights);
    while(rt.knightsNum() > 1) {
      rt.remove2nd();
    }
    return rt.knightWithJug();
  }

  public static void main(String[] args) {
    for (int i = 3; i <= 20; i++) {
      System.out.print("With " + i + " soldiers: ");
      System.out.println(Josephus.last(i));
    }
  }
}