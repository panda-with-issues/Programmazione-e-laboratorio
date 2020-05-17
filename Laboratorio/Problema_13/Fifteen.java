/**
 * This class models a 15-puzzle frame generalized to the case n x n, that is with n^2 -1 tiles.
 * This class lets its state be modified by the user moving the tiles.
 * Tiles are mapped to n in [1, n^2 - 1], while gap maps to n^2.
 * 
 * Protocol:
 * 
 * Fifteen frame = new Fifteen(n)      // Instantiate a new puzzle frame with dimension n x n
 * frame.size()             : int      // Return the dimension of a frame's edge (n)
 * frame.gap()              : int      // Return the value mapped to gap
 * frame.tiles()            : int[]    // Return a list with every tile in frame in the order in which they actually are
 * frame.isOrdered()        : boolean  // Return true if every tile in frame is in ascending order (that is, if the puzzle is solved)
 * frame.canMove(n)         : boolean  // Return true if the tile n can be moved (that is, if it's adjacent to the gap)
 * frame.indexOf(n)         : int      // Return a tile's index in tiles
 * frame.getAdjacentsIdx(i) : int[]    // Return an array containing the indices of adjacent tiles to a given tile, known its index.
 *                                     // Adjacent indices are stored in clockwise order starting from the upper.
 * frame.move(n)                       // Modify frame state moving a tile into the gap
 * (frame.toString())
 */

public class Fifteen {
  private static final int UNKNOWN = 0;
  private final int size;
  private final int gap;
  private int[] tiles;

  public Fifteen(int n) {
    size = n;
    gap = n * n;
    int tileToInsert = n * n;
    tiles = new int[n * n];
    for (int i = 0; i < tiles.length; i++) {
      tiles[i] = UNKNOWN;
    }
    // warning: this can generate unsolvable configurations!
    while(tileToInsert > 0) {
      final int i =  (int) Math.floor(Math.random() * n * n);
      if (tiles[i] == UNKNOWN) {
        tiles[i] = tileToInsert;
        tileToInsert--;
      }
    }
  }

  public Fifteen(int n, int[] arr){
    // this constructor is meant for debug only and it's not meant to be used
    size = n;
    gap = n * n;
    tiles = arr;
  }

  public int size() {
    return size;
  }

  public int gap() {
    return gap;
  }

  public int[] tiles() {
    return tiles;
  }
  
  public boolean isOrdered() {
    for (int i = 0; i < tiles.length; i++) {
      if (tiles[i] != i + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean canMove(int tile) {
    int[] adjacentsIdx = getAdjacentsIdx(tile);
    return isIdxInArr(indexOf(gap), adjacentsIdx);
  }

  private boolean isIdxInArr(int idx, int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == idx) {
        return true;
      }
    }
    return false;
  }

  public int indexOf(int tile) {
    for (int i = 0; i < tiles.length; i++) {
      if (tiles[i] == tile) {
        return i;
      }
    }
    // this return is non-sense (because we'll never pass a tile > 16 or < 0) but to be thorough, we conventionally return -1 if tile isn't found in tiles
    return -1;
  }

  public int[] getAdjacentsIdx(int tile) {
    final int tileIdx = indexOf(tile);
    /*
     * To explain my logic, i'll consider the case in which n = 4, where n is one dimension of the frame.
     * The upper element index is given by idx-n and must be >= 0 (tile 5 has got an upper adjacent with idx 0).
     * The lower one is given by idx+n and must be < n^2 (16 is 12's lower tile and its index is 15 < n^2).
     * Left adjacent index is given by idx-1 if idx % n != 0, else we are on the frame's left edge and there is no left adjacent (tiles 1, 5, 9, 13 have no left adjacent).
     * Right adjacent index is given by idx + 1 if idx % n != n - 1, else we are on the frame's right edge and there is no right adjacent (tiles 4, 8, 12, 16 have no right adjacent).
     * 
     * When an adjacent doesn't exist, we return idx in its place because it surely won't evaluate to true when checked against the gap.
     */
    final int upperIdx = tileIdx - size >= 0 ? tileIdx - size : tileIdx;
    final int lowerIdx = tileIdx + size < size * size ? tileIdx + size : tileIdx;
    final int leftIdx = tileIdx % size != 0 ? tileIdx - 1 : tileIdx;
    final int rightIdx = tileIdx % size != size -1 ? tileIdx + 1 : tileIdx;
    int[] adjacents = new int[] {upperIdx, rightIdx, lowerIdx, leftIdx};
    return adjacents;
  }

  public void move(int tile) {
    int tileIdx = indexOf(tile); 
    tiles[indexOf(gap)] = tile;
    tiles[tileIdx] = gap;
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < tiles.length; i++) {
      if (tiles[i] < 10) {
        // add additional space if tile has 1 digit for better align
        str += " ";
      }
      if (tiles[i] == gap) {
        str += "// ";
      } else {
        str += tiles[i] + " ";
      }
      if (i % size == size - 1) {
        // if we are at the end of a line
        str += "\n";
      }
    }
    return str;
  }

  /*
   * Test
   */
  public static void main(String[] args) {
    // describe constructor (and toString())
    Fifteen three = new Fifteen(3);
    Fifteen four = new Fifteen(4);
    Fifteen five = new Fifteen(5);
    System.out.println(three);
    System.out.println(four);
    System.out.println(five);

    // describe isOrdered()
    System.out.println(four.isOrdered()); // odds are that this will be false
    Fifteen orderedThree = new Fifteen(3, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    System.out.println(orderedThree.isOrdered());
    System.out.println(); // spacer

    // describe canMove()
    Fifteen example = new Fifteen(4, new int[] {1, 6, 2, 3, 13, 16, 7, 4, 8, 5, 11, 15, 14, 9, 10, 12}); // this is the configuration included in problem's text
    for (int i = 1; i <= 16; i++) {
      System.out.println(i + " can move? " + example.canMove(i)); // only 5, 6, 7, 13 should be true
    }
    System.out.println(); //spacer

    // describe move()
    System.out.println("move 6");
    example.move(6);
    System.out.println(example);
    example.move(6); // undo
    System.out.println("move 7");
    example.move(7);
    System.out.println(example);
    example.move(7); // undo
    System.out.println("move 5");
    example.move(5);
    System.out.println(example);
    example.move(5); // undo
    System.out.println("move 13");
    example.move(13);
    System.out.println(example);
    example.move(13); // undo
    System.out.println("move 10");
    example.move(10);
    System.out.println(example);
  } 
}