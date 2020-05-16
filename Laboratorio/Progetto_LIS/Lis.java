public class Lis {
  /*
   * Part I
   */ 
  private static final int UNKNOWN = -1; // because 0 are our base cases and lengths must be >= 1

  public static int llis(int[] s) {
    final int n = s.length;
    int[][] h = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    return llisMem(s, 0, 0, h);
  }

  private static int getIdxFromArr(int el, int[] arr) {
    // if arr doesn't include el, we return -1 for convention
    int idx = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == el) {
        idx = i;
      }
    }
    return idx;
  }

  private static int llisMem(int[] s, int i, int t, int[][] h) {
    final int n = s.length;
    final int j = t == 0 ? n : getIdxFromArr(t, s);
    if (h[i][j] == UNKNOWN) {
      if (i == n) {
        h[i][j] = 0;
      } else if (s[i] <= t) {
        h[i][j] = llisMem(s, i+1, t, h);
      } else {
        h[i][j] = Math.max(llisMem(s, i+1, s[i], h)+1, llisMem(s, i+1, t, h));
      }
    }
    return h[i][j];
  }

  /*
   * Part II
   */
  private static final IntSList UNKNOWN2 = null;

  public static IntSList lis(int[] s) {
    final int n = s.length;
    IntSList[][] h = new IntSList[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        h[i][j] = UNKNOWN2;
      }
    }
    return lisMem(s, 0, 0, h);
  }

  private static IntSList lisMem(int[] s, int i, int t, IntSList[][] h) {
    final int n = s.length;
    final int j = t == 0 ? n : getIdxFromArr(t, s);
    if (h[i][j] == UNKNOWN2) {
      if (i == n) {
        h[i][j] = new IntSList();
      } else if (s[i] <= t) {
        h[i][j] = lisMem(s, i+1, t, h);
      } else {
        IntSList fork1 = new IntSList().cons(s[i]).append(lisMem(s, i+1, s[i], h));
        IntSList fork2 = lisMem(s, i+1, t, h);
        h[i][j] = longest(fork1, fork2);
      }
    }
    return h[i][j];
  }

  private static IntSList longest(IntSList l1, IntSList l2) {
    final int m = l1.length();
    final int n = l2.length();
    if (m > n) {
      return l1;
    } else if (m < n) {
      return l2;
    } else if (Math.random() > .5) {
      return l2;
    } else {
      return l1;
    }
  }

  /*
   * Part III
   */

  public static int[][] llisSeeded(int[] s) {
    final int n = s.length;
    int[][] h = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    llisMem(s, 0, 0, h);
    return h;
  }
  
  private static void renderMatrix(int[][] m) {
    for (int i = 0; i < m.length; i++) {
      String row = "";
      for (int j = 0; j < m.length; j++) {
        if (m[i][j] >= 0 && m[i][j] < 10) {
          // better lining up for elements
          row += " ";
        }
        row += m[i][j] + " ";
      }
      System.out.println(row);
    }

    String separator = "";
    for (int i = 0; i < m.length; i++) {
      separator += "---";
    }
    System.out.println(separator);
  }

  public static void main(String[] args) {
    // describe part I
    System.out.println(llis( new int[] {5, 4, 3, 2, 1}));                          // 1
    System.out.println(llis( new int[] {47, 38, 39, 25, 44} ));                    // 3
    System.out.println(llis( new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} ));    // 4
    System.out.println(llis( new int[] {9, 46, 54, 71, 60, 47, 0, 32, 25, 61} ));  // 5
    System.out.println(llis( new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1} )); // 3

    // describe part 2
    System.out.println(lis( new int[] {5, 4, 3, 2, 1}));                          // (1)
    System.out.println(lis( new int[] {47, 38, 39, 25, 44} ));                    // (38, 39, 44)
    System.out.println(lis( new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} ));    // (27, 29, 49, 53) or (7, 29, 49, 53)
    System.out.println(lis( new int[] {9, 46, 54, 71, 60, 47, 0, 32, 25, 61} ));  // (9, 46, 54, 60, 61)
    System.out.println(lis( new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1} )); // (33, 37, 53), (14, 37, 61), or (14, 40, 53)

    // describe part 3
    renderMatrix(llisSeeded(new int[] {5, 4, 3, 2, 1}));
    renderMatrix(llisSeeded(new int[] {47, 38, 39, 25, 44} ));
    renderMatrix(llisSeeded(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}));
    renderMatrix(llisSeeded(new int[] {9, 46, 54, 71, 60, 47, 0, 32, 25, 61}));
    renderMatrix(llisSeeded(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
    /*
     * Explanation:
     * 
     * Let's consider the proper squared matrix composed only by s elements.
     * This matrix has a lower triangular configuration because we are testing the subsequence starting from i against another element of s.
     * When s[i] is compared against itself, it means that the lis, from taht point on, will never include s[i], so its row is erased.
     * In other words, matrix elements in the form (i, j), where j >= i, will never be reached because they can no more be part of lis.
     * 
     * Now let's consider the artificial row where i = n. This is our base case row so it will be always filled with 0.
     * 
     * The artificial column where j = n is the column which carries the solutions. In particular, the wanted value is in (0, n) because that means that we tested the entire
     * sequence (i = 0 means that we are considerng a subsequence of s which start from index 0) against the first non-acceptable value 0. We shall remember that for hypothesis
     * s elements are > 0 and element of N, or in other words they are positive integer number.
     * That means that every element which compares against 0 will be greater, so can be the start of an increasing sequence. Thus we can explore every increasing sequence and test
     * which element can be part of that.
     * Starting from the bottom, when the value increase, we have found an element that can fit into the lis. 
     */
  }
} 