import java.util.Stack;

public class esercitazioneDiEsame2 {
  // exercise 1
  public static int llcs3(String t, String u, String v) {
    if (t.equals("") || u.equals("") || v.equals("")) {
      return 0;
    } else if (t.charAt(0) == u.charAt(0) && u.charAt(0) == v.charAt(0)) {
      return 1 + llcs3(t.substring(1), u.substring(1), v.substring(1));
    } else {
      return Math.max(
        Math.max(llcs3(t.substring(1), u, v), llcs3(t, u.substring(1), v)),
        llcs3(t, u, v.substring(1))
      );
    }
  }

  private static final int UNKNOWN = -1;
  public static int llcs3Mem(String t, String u, String v) {
    int x = t.length(), y = u.length(), z = v.length();
    int[][][] h = new int[x+1][y+1][z+1];
    for (int i = 0; i <= x; i++) {
      for (int j = 0; j <= y; j++) {
        for (int k = 0; k <= z; k++) {
          h[i][j][k] = UNKNOWN;
        }
      }
    }
    return llcs3Main(t, u, v, h);
  }

  private static int llcs3Main(String t, String u, String v, int[][][] h) {
    int x = t.length(), y = u.length(), z = v.length();
    if (h[x][y][z] == UNKNOWN) {
      if (t.equals("") || u.equals("") || v.equals("")) {
        h[x][y][z] = 0;
      } else if (t.charAt(0) == u.charAt(0) && u.charAt(0) == v.charAt(0)) {
        h[x][y][z] = 1 + llcs3(t.substring(1), u.substring(1), v.substring(1));
      } else {
        h[x][y][z] = Math.max(
          Math.max(llcs3(t.substring(1), u, v), llcs3(t, u.substring(1), v)),
          llcs3(t, u, v.substring(1))
        );
      }
    }
    return h[x][y][z];
  }

  /* public static void main(String[] args) {
    System.out.println(llcs3("contato", "cantare", "centrato"));
    System.out.println(llcs3Mem("contato", "cantare", "centrato"));
  } */

  // exercise 2

  public static boolean isSymm(int[][] m) { // m is a square matrix
    int n = m.length;  // matrix dimension
    if (n <= 1) {
      return true;
    } else {
      // to minimize the comparisons number, we can consider m to be a triangular matrix
      // we can then visit only half of the matrix
      // we set m[0][0] apart because it will compare against itself (and it's covered in base case if it is the only element)
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if ( j <= i && m[i][j] != m[j][i]) { // read row untill the element a[i][j] where i = j (that is the element in the main diagonal)
            return false;
          }
        }
      }
      return true;
    }
  }

  /* public static void main(String[] args) {
    int[][] m = new int[][] {{ 1, 2, 3 }, { 2, -1, 0 }, { 3, 0, 5 }};
    System.out.println(isSymm(m));
    int[][] m2 = new int[][] {{ 1, 90, 3 }, { 2, -1, 0 }, { 3, 0, 5 }};
    System.out.println(isSymm(m2));
  } */

  // exercise 3
  // inspect BoardVar.java
  public static void listOfCompletions( BoardVar b ) {
    int n = b.size(); int q = b.queensOn();
    if ( q == n ) {
      System.out.println( b.arrangement() );
    } else {
      int i = 1;
      while ( !b.isFreeRow(i) ) {
        i = i + 1; // ricerca di una riga libera
      }
      for ( int j=1; j<=n; j=j+1 ) {
        if ( ! b.underAttack(i,j) ) {
          b.addQueen( i, j );
          listOfCompletions( b );
          b.removeQueen( i, j );
    }}}
  }

 /*  public static void main(String[] args) {
    BoardVar bv = new BoardVar(5);
    System.out.println(Queens.listOfAllSolutions(5));
    listOfCompletions(bv);
  } */

  // exercise 4

  /* public static int shortestCodeLength( Node root ) {
    int sc = 8;
    Stack<Node> stack = new Stack<Node>();
    Stack<Integer> depth = new Stack<Integer>();
    stack.push( root );
    depth.push( 0 );
    do {
      Node n = stack.pop();
      int d = depth.pop();
      if ( n.isLeaf() ) {
        sc = Math.min( sc, d );
      } else if ( d+1 < sc) {
        stack.push(n.left());
        stack.push(n.right());
        depth.push(d+1);
        depth.push(d+1);
      }
    } while ( !stack.empty());
    return sc;
  } */

  // exercise 5

  public static double[] closestPair(double[] arr) {
    double diff = 0; // this will be the greatest number in arr, so every difference will be lower
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > diff) {
        diff = arr[i];
      }
    }
    int i = 0, j = 0;
    // consider array as a triangular matrix where the entrance are the difference of elements i j
    for (int x = 0; x < arr.length; x++) {
      for (int y = 0; y < arr.length; y++) {
        if (y < x) { // read as a triangular matrix not considering the difference between an element and itself
          double currentDiff = Math.abs(arr[x] - arr[y]);
          if (currentDiff < diff) {
            diff = currentDiff;
            i = x;
            j = y;
          }
        }
      }
    }
    return new double[] { arr[i], arr[j] };
  }

  public static void main(String[] args) {
    double[] arr = new double[] { 0.3, 0.1, 0.6, 0.8, 0.5, 1.1 };
    double [] sol = closestPair(arr);
    System.out.println(sol[0] + " " + sol[1]); // 0.5 0.6
  }
}