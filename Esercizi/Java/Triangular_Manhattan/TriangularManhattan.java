public class TriangularManhattan {
  private static final int UNKNOWN = -1;

  public static long triangularManh(int x, int y) {
    long[][] h = new long[x + 1][y + 1];
    for (int i = 0; i <= x; i++) {
      for (int j = 0; j <= y; j++) {
        h[i][j] = UNKNOWN;
      }
    }
    return triangularManhMem(x, y, h);
  }

  private static long triangularManhMem(int x, int y, long[][] h) {
    if (y > x) {
      int temp = x;
      x = y;
      y = temp;
    }
    if (h[x][y] == UNKNOWN) {
      if (x == 1 || y == 1) {
        h[x][y] = 1; 
      } else {
        h[x][y] = triangularManhMem(x-1, y, h) + triangularManhMem(x, y-1, h);
      }
    }
    return h[x][y];
  }

  public static long manh(int x, int y) {
    if (x == 1 || y == 1) {
      return 1;
    } else {
      return manh(x-1, y) + manh(x, y-1);
    }
  }

  public static void main(String[] args) {
    System.out.println(triangularManh(18, 18));
    System.out.println(manh(18, 18)); // circa 5s
  }
}