import queens.*;

class test {

  private static void RenderBoardList(SList<Board> lst) {
    int count = 1;
    while (!lst.isNull()) {
      System.out.println(count + ") " + lst.car().arrangement());
      lst = lst.cdr();
      count++;
    }
  }

  public static void viewBoards(SList<Board> boards) {
    int size = boards.car().size();
    ChessboardView gui = new ChessboardView(size);
    while (!boards.isNull()) {
      gui.setQueens(boards.car().san());
      boards = boards.cdr();
    }
  }
  public static void main(String[] args) {
    /*
     * Part 1
     */
    
    // Completions number
    for (int i = 1; i <= 10; i++) {
      System.out.print("Size " + i + ": "); // this is not formally ok but it works thanks to compiler's quirks. It's a test so I think it's ok to speed things up
      int sols = Queens.complsNum(i);
      System.out.println(sols + (sols == 1 ? " solution" : " solutions"));
    }

    // Completions list (as SList of Board instances)
    for (int i = 4; i <= 6; i++) {
      SList<Board> sols = Queens.complsLst(i);
      System.out.println("Requested output: " + sols);
      RenderBoardList(sols);
    }

    /*
    * Part 2
    * 
    * Run `java -classpath "queens.jar:." test` to compile
    */

    for (int i = 4; i <= 6; i++) {
     SList<Integer> compls = Queens.complsLst(i)
     viewBoards(compls);
    }
  }
}