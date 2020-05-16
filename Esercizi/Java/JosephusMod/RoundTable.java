/**
 * Implements Josephus's (Titus Flavius) version of eenie meenie minee moe, where every 2nd soldier is removed (actual euphemism for "sent to be killed by the next chosen").
 * The algorythm is state-controlled, optimized in memory by using modular arithmetic
 * 
 * Protocol:
 * 
 * RoundTable rt = new RoundTable(n)  // initialize the object with n soldiers
 * rt.knightsNum()    : int           // return the number of soldiers not removed
 * rt.knigthWithJug() : int           // return the index of soldier with jug 
 * rt.remove2nd()                     // remove the 2nd soldier from jug index
 */

public class RoundTable {
  private int[] knights;
  private int jug;
  private int knightsNum;

  public RoundTable(int n) {
    knights = new int[n];
    for (int i = 0; i < n; i++) {
      knights[i] = i+1;
    }
    jug = 0;
    knightsNum = n;
  }

  public int knightsNum() {
    return knightsNum;
  }

  public int knightWithJug() {
    return knights[jug];
  }
  
  public void remove2nd() {
    final int len = knights.length;
    final int idx = (jug + knightsNum) % len;
    knights[idx] = knights[jug];
    jug = (jug + 2) % len;
    knightsNum--;
  }
}