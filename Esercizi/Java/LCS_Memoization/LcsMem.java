/**
 * Returns one of the the Longest Common Sequences (LCS) of two given strings.
 * The alogrythm is optimized with the memoization strategy
 */

public class LcsMem {
  private static final String UNKNOWN = null;

  public static String lcsMem(String u, String v) {
    int m = u.length();
    int n = v.length();
    String[][] h = new String[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    return lcsMemRec(u, v, h);
  }

  private static String lcsMemRec(String u, String v, String[][] h) {
    int m = u.length();
    int n = v.length();

    if (h[m][n] == UNKNOWN) {
      if (m == 0 || n == 0) {
        h[m][n] = "";
      } else if (u.charAt(0) == v.charAt(0)) {
        h[m][n] = u.substring(0, 1) + lcsMemRec(u.substring(1), v.substring(1), h);
      } else {
        String fork1 = lcsMemRec(u.substring(1), v, h);
        String fork2 = lcsMemRec(u, v.substring(1), h);
        h[m][n] = longest(fork1, fork2);
      }
    }
    return h[m][n];
  }

  private static String longest(String u, String v) {
    int m = u.length();
    int n = v.length();
    if (m > n) {
      return u;
    } else if (m < n) {
      return v;
    } else if (Math.floor(Math.random() * 2) == 0) {
      return u;
    } else {
      return v;
    }
  }

  public static void main(String[] args) {
    System.out.println(lcsMem("atrio", "arto"));
  }
}