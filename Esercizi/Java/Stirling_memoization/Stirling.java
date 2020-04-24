/**
 * Defines a procedure that generate the Stirling number of the second kind generated with n and k parameters
 * The procedure is optimized with the memoization strategy
 * Because 0 is an actual value of the function (used to mark invalid values), it can't be used to label values not yet calculated.
 * We will use -1, because stirling numbers are always positive
 */
public class Stirling {
  private static final long UNKNOWN = -1;

  public static long generate(int n, int k) {
    long[][] h = new long[n+1][k+1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= k; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    return stirlingMem(n, k, h);
  }
  
  private static long stirlingMem(int n, int k, long[][] h) {
    if (h[n][k] == UNKNOWN) {
      if (n < k) {
        h[n][k] = 0;
      } else if (n == 1 || k == 1) {
        h[n][k] = 1;
      } else {
        long newPartition = stirlingMem(n-1, k-1, h);
        long addToPartitions = k * stirlingMem(n-1, k, h);
        h[n][k] = newPartition + addToPartitions;
      }
    }
    return h[n][k];
  }

  public static long badGeneration(int n, int k) {
    if (n < k) {
      return 0;
    } else if (n == 1 || k == 1) {
      return 1;
    }
    long newPartition = badGeneration(n-1, k-1);
    long addToPartitions = k * badGeneration(n-1, k);
    return newPartition + addToPartitions;
  }
}