/**
 * Defines a procedure that generate the Stirling number of the second kind generated with n and k parameters
 * The procedure is optimized with a bottom-up dynamic programming strategy
 */

 public class StirlingBU {
  public static long stirlingBU(int n, int k) {
    long[][] h = new long[n+1][k+1];
    /* 
     * We need only the upper and the upper-left element to compute every other element in the matrix.
     * The formula is upper-left value + k * upper-value
     * We can compute following the western reading order, so left to right, top to bottom:
     * 
     * 0   1   2   3   4  = k
     * 
     * 1   1   0   0   0
     *       \ |   
     * 2   1   1   0   0
     *       \ | \ | 
     * 3   1   3   1   0
     *       \ | \ | \ |
     * 4   1   7   6   1
     * ||
     * n
     */
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k; j++) {
        if (j == 1) {
          // base case k == 1 -> 1
          h[i][j] = 1;
        } else if (i < j) {
          // base case n < k -> 0
          h[i][j] = 0;
        } else {
          h[i][j] = h[i-1][j-1] + j * h[i-1][j];
        }
      }
    }
    return h[n][k];
  }

  public static long stirling(int n, int k) {
    if (n < k) {
      return 0;
    } else if (n == 1 || k == 1) {
      return 1;
    }
    long newPartition = stirling(n-1, k-1);
    long addToPartitions = k * stirling(n-1, k);
    return newPartition + addToPartitions;
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    long val = stirlingBU(35, 20); // 1 ms
    long stop = System.currentTimeMillis();
    long duration = stop - start;
    System.out.println("calculated " + val + " in " + duration + " milliseconds.");

    long start2 = System.currentTimeMillis();
    long val2 = stirling(35, 20); // 11 seconds
    long stop2 = System.currentTimeMillis();
    long duration2 = stop2 - start2;
    System.out.println("calculated " + val2 + " in " + duration2 + " milliseconds.");
  }
 }