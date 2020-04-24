/**
 * Define a procedure which returns the last 2 soldier alive after the eenie meenie minie moe described by Josephus (T. Flavius).
 * Accept the initial soldiers number (int) as argument.
 */
public class Josephus {
  public static IntSList lastTwo(int soldiers) {
    RoundTable rt = new RoundTable(soldiers);
    while(rt.alives() > 2) {
      rt = rt.remove3rd();
    }
    return rt.getSoldiers();
  }
}