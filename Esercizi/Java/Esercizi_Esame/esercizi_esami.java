import java.util.Stack;
import java.util.Vector;

public class esercizi_esami {
  /*
   * 2019 A
   */

   // esercizio 2

  public static String[] diff( String u, String v ) {
  if ( u.equals("") || v.equals("") ) {
    return new String[] { u, v };
  } else if ( u.charAt(0) == v.charAt(0) ) {
    return diff( u.substring(1), v.substring(1) );
  } else {
    String[] x = diff( u.substring(1), v );
    String[] y = diff( u, v.substring(1) );
    if ( x[0].length() < y[0].length() ) {
      return new String[] { u.charAt(0)+x[0], x[1] };
    } else {
      return new String[] { y[0], v.charAt(0)+y[1] };
    }}
  }

  private static final String[] UNKNOWN = new String[] {null, null};
  public static String[] diffMem(String u, String v) {
    int n = u.length();
    int m = v.length();
    String[][][] h = new String[n+1][m+1][2];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    return diffMain(u, v, h);
  }

  private static String[] diffMain(String u, String v, String[][][] h) {
    int i = u.length();
    int j = v.length();
    if (h[i][j] == UNKNOWN) {
        if ( u.equals("") || v.equals("") ) {
            h[i][j] = new String[] { u, v };
          } else if ( u.charAt(0) == v.charAt(0) ) {
            h[i][j] = diffMain( u.substring(1), v.substring(1), h);
          } else {
            String[] x = diffMain( u.substring(1), v, h );
            String[] y = diffMain( u, v.substring(1), h );
            if ( x[0].length() < y[0].length() ) {
              h[i][j] = new String[] { u.charAt(0)+x[0], x[1] };
            } else {
              h[i][j] = new String[] { y[0], v.charAt(0)+y[1] };
            }}
    }
    return h[i][j];
  }

 /*  public static void main(String[] args) {
      String[] a = diff("esserci", "esercizio");
      for (int i = 0; i < a.length; i++) {
          System.out.print(a[i] + " ");
      }
      String[] b = diffMem("esserci", "esercizio");
      for (int i = 0; i < b.length; i++) {
          System.out.print(b[i] + " ");
      }
  } */

  // esercizio 3

  public static long st( int n, int k ) {  // n, k > 0
    long[] ct = new long[] { 0 };          // contatore: variabile di stato
    sRec( n, k, 1, ct );
    return ct[0];
  }
  private static void sRec( int n, int k, int q, long[] ct ) {
    if ( (k == 1) || (k == n) ) {
      ct[0] = ct[0] + q;
    } else {
      sRec( n-1, k-1, q, ct );
      sRec( n-1, k, k*q, ct );
    }
  }

  public static long stIter( int n, int k ) {  // n, k > 0
    long[] ct = new long[] { 0 };
    Stack<int[]> stack = new Stack<int[]>();
    int[] f = new int[] { n, k, 1 };
    stack.push( f );
    while ( !stack.isEmpty() ) {
      f = stack.pop();
    if ( (f[1] == 1) || ( f[1] == f[0]  ) ) {
      ct[0] = ct[0] + f[2];
    } else {
      stack.push( new int[] { f[0]-1, f[1]-1, f[2] } );
      stack.push( new int[] { f[0]-1, f[1], f[1]*f[2] } );
    }}
    return ct[0];
  }

  /* public static void main(String[] args) {
      System.out.println(st(6, 4));
      System.out.println(stIter(6, 4));
  } */

  // esercizio 4
  public static int intSqrt( int n ) {            //  Pre:n ≥ 0
    int  q = 0,  x = 0,  y = 1 ,  z = n - 1  ;
    while ( x <= z ) {                            //  Inv:0 ≤ q ≤ √n,   x = q^2,   y = 2q+1,   y+z = n
                                                  //  Term: n - q
      q = q + 1;
      x = x + y;
      y = y + 2;
      z = z - 2;
    }
    return q;                                     //  Post:  valore restituito:  ⎣√n⎦
  }

  /* public static void main(String[] args) {
      System.out.println(intSqrt(2));
      System.out.println(intSqrt(4));
      System.out.println(intSqrt(20));
      System.out.println(intSqrt(30));
  } */

  /*
   * 2018 A
   */

  public static int q( int[] s ) {  // s.length > 0
    int n = s.length;
    int[] t = new int[ n ];
    t[0] = s[0];
    for ( int k=1; k<n; k=k+1 ) {
      int i=k-1;
      while ( (i >= 0) && (t[i] > s[k]) ) {
          t[i+1] = t[i];
          i = i - 1;
      }
      t[i+1] = s[k];
    }
    return qRec( s, t, n, 0, 0 );
  }
  
  private static int qRec( int[] s, int[] t, int n, int i, int j ) {
    if ( (i == n) || (j == n) ) {
      return 0;
    } else if ( s[i] == t[j] ) {
      return 1 + qRec( s, t, n, i+1, j+1 );
    } else {
      return Math.max( qRec(s,t,n,i+1,j), qRec(s,t,n,i,j+1) );
    }
  }

  private static final int UNKNOWN18 = -1;
  public static int qMem(int[] s) {
    int n = s.length;
    int[] t = new int[ n ];
    t[0] = s[0];
    for ( int k=1; k<n; k=k+1 ) {
      int i=k-1;
      while ( (i >= 0) && (t[i] > s[k]) ) {
          t[i+1] = t[i];
          i = i - 1;
      }
      t[i+1] = s[k];
    }
    int[][] h = new int[n+1][n+1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        h[i][j] = UNKNOWN18;
      }
    }
    return qMain( s, t, n, 0, 0, h );
  }

  private static int qMain( int[] s, int[] t, int n, int i, int j, int[][] h ) {
    if (h[i][j] == UNKNOWN18) {
        if ( (i == n) || (j == n) ) {
            h[i][j] = 0;
          } else if ( s[i] == t[j] ) {
            h[i][j] = 1 + qMain( s, t, n, i+1, j+1, h );
          } else {
            h[i][j] = Math.max( qMain(s,t,n,i+1,j, h), qMain(s,t,n,i,j+1, h) );
          }
    }
    return h[i][j];
  }

  /* public static void main(String[] args) {
      System.out.println(q(new int[] {1, 3, 5, 7, 9, 10, 256}));
      System.out.println(qMem(new int[] {1, 3, 5, 7, 9, 10, 256}));
  } */

  /*
   * 3 / 9 / 2019
   */

  // esercizio 5
  public static int pow4( int n ) {  //  Pre:  n > 0
    int x =  0,  y = 0;
    int z =  1,  u = 1;
    while ( x < u ) {                //  Inv:  (x ≤ n^2) ∧ (y = x^2) ∧ (z = 2x+1) ∧ (u = min(y+z,n^2))
      x = x + 1;
      y = y + z;
      z = z + 2;
      if ( x < n  ) {
        u = y + z;
      }
    }
    return y;                        //  Post:  y = n^4
  }

  /* public static void main(String[] args) {
    System.out.println(pow4(2));
    System.out.println(pow4(3));
    System.out.println(pow4(4));
    System.out.println(pow4(5));
    
  } */

  /**
   * 24 settembre 2019 es. 3
   */

  public static int commonStretches(String u, String v) {
    int stretches = 0;
    for (int i = 0; i < u.length(); i++) {
      String x = u.substring(0, i);
      String y = v.substring(0, i);
      if (zeroCount(x) == zeroCount(y) && u.charAt(i) == v.charAt(i)) {
        stretches++;
      }
    }
    return stretches;
  }

  private static int zeroCount(String s) {
    int counter = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') {
        counter++;
      }
    }
    return counter;
  }

  /* public static void main(String[] args) {
    System.out.println(commonStretches("1010110110", "1100011101"));
  } */

  /**
   * 3 settembre 2019 esercizio 4
   */

  public static Vector<String> perm( String u ) {  //  u non vuota
      Vector<String> p = new Vector<String>();
      permRec( u, 0, p );
      return p;
    }
    
  public static void permRec( String u, int k, Vector<String> p ) {
    int n = u.length();
    if ( k == n-1 ) {
      p.add( u );
    } else {
      permRec( u, k+1, p );
      for ( int i=k+1; i<n; i=i+1 ) {
        String v = u.substring( 0, k ) + u.substring( i, i+1 ) + u.substring( k+1, i )
                                       + u.substring( k, k+1 ) + u.substring( i+1, n );
        permRec( v, k+1, p );
      }
    }  
  }

  public static Vector<String> permIter( String u ) {  //  u non vuota
    Vector<String> p = new Vector<String>();
    int n = u.length(), k;
    Stack<String>  s = new Stack<String>();
    Stack<Integer> t = new Stack<Integer>();
    s.push( u );  t.push( 0 );
    do {
      u = s.pop(); k = t.pop(); 
      if ( k == n-1 ) {
        p.add( u );
      } else {
        for ( int i=k+1; i<n; i=i+1 ) {
          String v = u.substring( 0, k ) + u.substring( i, i+1 ) + u.substring( k+1, i ) + u.substring( k, k+1 ) + u.substring( i+1, n );
          s.push(v);
          t.push(k+1);
        }
        s.push(u); t.push(k+1);
      }
    } while ( !s.empty() );
    return p;
  }

  /* public static void main(String[] args) {
    Vector<String> vec1 = perm("tre");
    Vector<String> vec2 = permIter("tre");
    for (int i = 0; i < vec1.size(); i++) {
      System.out.print(vec1.get(i) + " ");
    }
    System.out.println();
    for (int i = 0; i < vec2.size(); i++) {
      System.out.print(vec2.get(i) + " ");
    }
  } */

  /**
   * 9 luglio 2018
   */

  // esercizio 4

  public static int q2( int[] s ) {  // s.length > 0
    int n = s.length; 
    int[] t = new int[ n ];  t[0] = s[0];
    for ( int k=1; k<n; k=k+1 ) {
      int i=k-1;
      while ( (i >= 0) && (t[i] > s[k]) ) {
        t[i+1] = t[i];  i = i - 1;
      }
      t[i+1] = s[k];
    }
    return qRec2( s, t, n, 0, 0 );
  }
  
  private static int qRec2( int[] s, int[] t, int n, int i, int j ) {
    if ( (i == n) || (j == n) ) {
      return 0;
    } else if ( s[i] == t[j] ) {
      return 1 + qRec( s, t, n, i+1, j+1 );
    } else {
      return Math.max( qRec(s,t,n,i+1,j), qRec(s,t,n,i,j+1) );
    }
  }

  public static int qDP2 (int[] s) {
    int n = s.length; 
    int[] t = new int[ n ];  t[0] = s[0];
    for ( int k=1; k<n; k=k+1 ) {
      int i=k-1;
      while ( (i >= 0) && (t[i] > s[k]) ) {
        t[i+1] = t[i];  i = i - 1;
      }
      t[i+1] = s[k];
    }
    // return qRec2( s, t, n, 0, 0 );
    int[][] h = new int[n+1][n+1];
    for (int x = 0; x <= n; x++) {
      for (int y = 0; y <= n; y++) {
        if (x == 0 || y == 0) {
          h[x][y] = 0;
        } else if (s[n-x] == t[n-y]) {
          h[x][y] = 1 + h[x-1][y-1];
        } else {
          h[x][y] = Math.max(h[x-1][y], h[x][y-1]);
        }
      }
    }
    return h[n][n];
  }

  public static void main(String[] args) {
    int[] s = new int[] { 8, 3, 5, 6, 2, 4, 1, 7, 10, 9 } ;
    System.out.println(q2(s));
    System.out.println(qDP2(s));
  }
}