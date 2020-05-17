/* 
 * To compile this class run:
 * javac -classpath "puzzleboard.jar:." PlayMe.java
 * 
 * To run the code:
 * java -classpath "puzzleboard.jar:." PlayMe
 */
import puzzleboard.PuzzleBoard;

public class PlayMe {
  private static int[] getCoord(int idx, Fifteen frame) {
    /*
     * To transform our vector in a matrix, we can consider our array index to be in the form ni + j,
     * where n is the dimension of the board and (i, j) are the element's coordinate in the matrix.
     */
    final int n = frame.size();
    final int j = idx % n;
    final int i = (idx - j) / n;
    final int[] coord = new int[] {i, j};
    return coord;
  }

  public static void main(String[] args) {
    Fifteen four = new Fifteen(4);
    PuzzleBoard gui = new PuzzleBoard(4);

    // set frame's initial state
    final int[] tiles = four.tiles();
    for (int i = 0; i < tiles.length; i++) {
      final int[] coord = getCoord(i, four);
      if (tiles[i] != four.gap()) {
        // +1 is needed because setNumber() is 1-indexed (just why?)
        gui.setNumber(coord[0] + 1, coord[1] + 1, tiles[i]);
      }
    }
    gui.display();

    // play the game
    while (!four.isOrdered()) {
      final int tile = gui.get();
      if (four.canMove(tile)) {
        four.move(tile);
        // update gui
        // place new tile
        final int idx = four.indexOf(tile);
        final int[] tileCoord = getCoord(idx, four);
        gui.setNumber(tileCoord[0] + 1, tileCoord[1] + 1, tile);
        // place new gap
        final int gapIdx = four.indexOf(four.gap());
        final int[] gapCoord = getCoord(gapIdx, four);
        gui.clear(gapCoord[0] + 1, gapCoord[1] + 1);
        gui.display();
      }
    }
    System.out.println("GG You clear the game! See you soon!");
  }
}