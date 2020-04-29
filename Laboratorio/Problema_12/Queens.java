public class Queens {
  public static int complsNum(int n) {
    return complsNumRec(new Board(n));
  }

  private static int complsNumRec(Board b) {
    int n = b.size();
    int q = b.queensOn();

    if (n == q) {
      return 1;
    } else {
      int i = q + 1; // successive rank where there is no queen
      int count = 0;
      for (int j = 1; j <= n; j++) {
        if (!b.underAttack(i, j)) {
          count += complsNumRec(b.addQueen(i, j));
        }
      }
      return count;
    }
  }

  public static SList<Board> complsLst(int n) {
    return complsLstRec(new Board(n));
  }

  private static SList<Board> complsLstRec(Board b) {
    int n = b.size();
    int q = b.queensOn();
    
    if (n == q) {
      return new SList<Board>().cons(b);
    } else {
      int i = q + 1;
      SList<Board> sols = new SList<Board>();
      for (int j = 1; j <= n; j++) {
        if (!b.underAttack(i, j)) {
          sols = sols.append(complsLstRec(b.addQueen(i, j)));
        }
      }
      return sols;
    }
  }
}