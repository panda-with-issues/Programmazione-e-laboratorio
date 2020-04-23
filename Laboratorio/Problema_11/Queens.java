public class Queens {
  public static int solsNum(int n) {
    return complsNum(new Board(n));
  }

  private static int complsNum(Board b) {
    int n = b.size();
    int q = b.queensOn();
    if (n == q) {
      return 1;
    } else {
      int i = q + 1; // successive traverse where there is no queen
      int count = 0;
      for (int j = 1; j <= n; j++) {
        if (!b.underAttack(i, j)) {
          count += complsNum(b.addQueen(i, j));
        }
      }
      return count;
    }
  }
}