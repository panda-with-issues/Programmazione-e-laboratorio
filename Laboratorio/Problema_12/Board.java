/**
 * This class represents a chessboard and it will be used to solve the puzzle of putting n queens on a n*n chessboard without them threatening each others
 * 
 * Protocol:
 * 
 * Board b = new Board(n)         // Create an empty n*n board
 * b.size()            : int      // return n for a n*n board
 * b.queensOn()        : int      // return the number of already placed queens
 * b.underAttack(r, f) : boolean  // return true if the square <r, f> is threatened by a queen already placed on board
 * b.addQueen(r, f)    : Board    // return a new Board with a queen placed in square <r, f>
 * b.arrangement()     : String   // return a string representation of the state of the board
 *                                // format: <size, queensOn, (ranks threatened), (files threatened), (upper-right diagonals threatened), (upper-left diagonals threatened), queens positions in SAN>
 * Added for part 2:
 * b.san()             : String   // return onboard queens position in standard algebric notation
 */

public class Board {
  private final int size;
  private final int queens;
  private final SList<Integer> threatenedRanks;
  private final SList<Integer> threatenedFiles;
  private final SList<Integer> threatenedRight; // encoded as the difference between squares coordinates
  private final SList<Integer> threatenedLeft;  // encoded as the sum of squares coordinates
  private String san;                           // Stands for Standard Algebric Notation
  
  private static final String FILES = "_abcdefghij";

  public Board(int n) {
    size = n;
    queens = 0;
    threatenedRanks = new SList<Integer>();
    threatenedFiles = new SList<Integer>();
    threatenedRight = new SList<Integer>();
    threatenedLeft = new SList<Integer>();
    san = "";
  }

  private Board(int n, int q, SList<Integer> r, SList<Integer> f, SList<Integer> rd, SList<Integer> ld) {
    size = n;
    queens = q;
    threatenedRanks = r;
    threatenedFiles = f;
    threatenedRight = rd;
    threatenedLeft = ld;
    san = getSan();
  }

   private static boolean includes(SList<Integer> lst, int n) {
    while (!lst.isNull()) {
      if (lst.car() == n) {
        return true;
      }
      lst = lst.cdr();
    }
    return false;
  }

  private String getSan() {
    String san = "";
    SList<Integer> ranks = threatenedRanks;
    SList<Integer> files = threatenedFiles;
    while (!ranks.isNull()) {
      String f = FILES.substring(files.car(), files.car()+1);
      String r = String.valueOf(ranks.car());
      san += " " + f + r + " ";
      ranks = ranks.cdr();
      files = files.cdr(); 
    }
    return san.trim();
  }

  public int size() {
    return size;
  }

  public int queensOn() {
    return queens;
  }

  public boolean underAttack(int r, int f) {
    if (
      includes(threatenedRanks, r) ||
      includes(threatenedFiles, f) ||
      includes(threatenedRight, r - f) ||
      includes(threatenedLeft, r + f) 
    ) {
      return true;
    } else {
      return false;
    }
  }

  public Board addQueen(int r, int f) {
    return new Board(
      size,
      queens+1,
      threatenedRanks.cons(r),
      threatenedFiles.cons(f),
      threatenedRight.cons(r - f),
      threatenedLeft.cons(r + f)
    );
  }

  public String arrangement() {
    String[] infos = {
      String.valueOf(size),
      String.valueOf(queens),
      threatenedRanks.toString(),
      threatenedFiles.toString(),
      threatenedRight.toString(),
      threatenedLeft.toString(),
      "\"" + san + "\""
    };
    return "<" + String.join(", ", infos) + ">";
  }

  public String san() {
    return san;
  }
}