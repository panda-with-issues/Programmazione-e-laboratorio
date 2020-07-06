class test {
  public static void main(String[] args) {
    // describe Board
    Board b = new Board(5);
    System.out.println(b.arrangement());
    Board b2 = b.addQueen(3, 3);
    System.out.println(b2.arrangement());
    Board b3 = b2.addQueen(1, 4);
    System.out.println(b3.arrangement());

    // Solve problem
    for (int i = 1; i <= 10; i++) {
      System.out.print("Size " + i + ": "); // this is not formally ok but it works thanks to compiler's quirks. It's a test so I think it's ok to speed things up
      int sols = Queens.numberOfSolutions(i);
      System.out.println(sols + (sols == 1 ? " solution" : " solutions"));
    }
  }
}