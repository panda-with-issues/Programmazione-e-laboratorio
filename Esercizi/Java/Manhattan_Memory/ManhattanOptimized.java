/**
 * Returns the number of path between two points in a Manhattan grid.
 * The algorythm is optimized with a dynamic programming bottom up strategy.
 * In order to save memory, instead of a matrix, we allocate only an array
 */

public class ManhattanOptimized {
  public static int manhOpt(int x, int y) {
    int[] h = new int[y + 1];
    h[0] = 1; // base case: if j = 0 -> 1

    for (int i = 0; i <= x; i++) {
      for (int j = 1; j <= y; j++) {
        // we don't need to check every time the base case (and, as we implemented the algorythm, it'll also raise OutOfBound Exception)
        if (i == 0) {
          // base case: i == 0 -> 1
          h[j] = 1;
        } else {
          h[j] = h[j - 1] + h[j];
        }
      }
    }
    return h[y];    
  }

  public static void main(String[] args) {
    System.out.println(manhOpt(0, 0));
    System.out.println(manhOpt(0, 3));
    System.out.println(manhOpt(3, 0));
    System.out.println(manhOpt(2, 3));
    System.out.println(manhOpt(3, 2));
    System.out.println(manhOpt(6, 3));
    System.out.println(manhOpt(24, 24));
  }
}