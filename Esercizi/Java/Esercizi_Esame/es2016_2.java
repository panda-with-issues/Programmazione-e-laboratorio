public class es2016_2 {
  public static long q( int i, int j, String x ) {
    int u = x.length();
    if ( i + j < u ) {  
      return 0;
    } else if ( i + j == 0 ) {
      return 1;
    } else {
      long k = 0;
      if ( i > 0 ) {
        if ( (u > 0) && (x.charAt(0) == '0') ) {
          k = k + q( i-1, j, x.substring(1) );
        } else {
          k = k + q( i-1, j, x );
        }
      }
      if ( j > 0 ) {
        if ( (u > 0) && (x.charAt(0) == '1') ) {
          k = k + q( i, j-1, x.substring(1) );
        } else {
          k = k + q( i, j-1, x );
        }
      }
      return k;
    }
  }

  private static final long UNKNOWN = -1;
  public static long qMem(int i, int j, String x) {
    int u = x.length();
    long[][][] h = new long[i + 1][j + 1][u+1];
    for (int n = 0; n <= i; n++) {
      for (int m = 0; m <= j; m++) {
        for (int z = 0; z <= u; z++) {
          h[n][m][z] = UNKNOWN;
        }
      }
    }
    return qMain(i, j, x, h);
  }

  private static long qMain (int i, int j, String x, long[][][] h) {
    int u = x.length();

    if (h[i][j][u] == UNKNOWN) {
      if ( i + j < u ) {  
        h[i][j][u] = 0;
      } else if ( i + j == 0 ) {
        h[i][j][u] = 1;
      } else {
        long k = 0;
        if ( i > 0 ) {
          if ( (u > 0) && (x.charAt(0) == '0') ) {
            k = k + qMain( i-1, j, x.substring(1), h );
          } else {
            k = k + qMain( i-1, j, x, h );
          }
        }
        if ( j > 0 ) {
          if ( (u > 0) && (x.charAt(0) == '1') ) {
            k = k + qMain( i, j-1, x.substring(1), h );
          } else {
            k = k + qMain( i, j-1, x, h );
          }
        }
        h[i][j][u] = k;
      }
    }
    return h[i][j][u];
  }

  public static void main(String[] args) {
    System.out.println(q(4, 10, "110100"));
    System.out.println(qMem(4, 10, "110100"));
  }

}