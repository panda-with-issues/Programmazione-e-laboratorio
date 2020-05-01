/**
 * Finds the solutions number of the Longest Common Sequences (LCS) among two given strings.
 * The algorythm is optimized with a dynamic programming bottom up strategy.
 * The matrix has got reversed index, thus the solution is holded in (0, 0) instead of (m, n).
 */

public class LlcsBUR {
  public static int llcsBUR(String u, String v) {
    int m = u.length();
    int n = v.length();
    int[][] h = new int [m + 1][n + 1];

    for (int i = m; i >= 0; i--) {
      for (int j = n; j >= 0; j--) {
        // i and j are the indices of suffix first char
        if (i == m || j == n) {
          // base case: suffix is ""
          h[i][j] = 0;
        } else if (u.charAt(i) == v.charAt(j)) {
          h[i][j] = 1 + h[i + 1][j + 1];
        } else {
          h[i][j] = Math.max(h[i+1][j], h[i][j+1]);
        }
      }
    }
    return h[0][0];
  }

  public static void main(String[] args) {
    System.out.println(llcsBUR("atrio", "arto"));
  }
}