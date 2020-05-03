/**
 * Returns the LCS (Longest Common Sequence) between two texts given as list of lines
 * The algorythm is optimized with a bottom-up dynamic programming strategy.
 */

 public class AlmostDiff {
  private static int[][] llcsMatrix(SList<String> text1, SList<String> text2) {
    int rows = text1.length();
    int cols = text2.length();
    int[][] h = new int[rows + 1][cols + 1];

    // We will seed the matrix backwards
    // Filling up to length is important because we must include empty list in our matrix but we can't get it using length() or listRef()
    for (int i = rows; i >= 0; i--) {
      for (int j = cols; j >= 0; j--) {
        if (i == rows || j == cols) {
          // base case: text1 or text2 is empty -> 0
          h[i][j] = 0;
        } else if (text1.listRef(i).equals(text2.listRef(j))) {
          h[i][j] = 1 + h[i + 1][j + 1];
        } else {
          h[i][j] = Math.max(h[i + 1][j], h[i][j + 1]);
        }
      }
    }

    return h;
  }

  public static SList<String> almostDiff(SList<String> text1, SList<String> text2) {
    int[][] h = llcsMatrix(text1, text2);    
    int i = 0;
    int j = 0;
    SList<String> lcs = new SList<String>();

    while (i < text1.length() && j < text2.length()) {
      String line1 = text1.listRef(i);
      String line2 = text2.listRef(j);
      if (line1.equals(line2)) {
        lcs = lcs.cons(line1);
        i++;
        j++;
      } else if (h[i + 1][j] > h[i][j + 1]) {
        i++;
      } else if (h[i + 1][j] < h[i][j + 1]) {
        j++;
      } else if (Math.floor(Math.random() * 2) == 0) {
        j++;
      } else {
        i++;
      }
    }

    return lcs.reverse();
  }

  public static void main(String[] args) {
    // Every line begin with a different letter
    String[] arr1 = {
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      "In in ullamcorper neque, ac feugiat diam.",
      "Vestibulum id malesuada urna.",
      "Duis maximus, tortor sed rhoncus euismod, ante orci vestibulum magna, at dignissim purus augue mattis nunc.",
      "Etiam scelerisque, dui sit amet viverra pretium, augue ex semper lacus, at varius tortor justo quis magna.",
      "Maecenas sed facilisis eros, vitae consequat mauris.",
      "Nunc semper sem non euismod suscipit. ",
      "Cras sodales consequat orci at tincidunt.",
      "Proin porttitor nulla et lectus placerat egestas.",
      "Aenean nec porttitor nunc, commodo laoreet purus.",
      "Fusce mattis est nibh, sit amet imperdiet mauris posuere sit amet.",
      "Sed lectus libero, ultrices in cursus a, convallis ac quam."
    };

    SList<String> text1 = new SList<String>();
    for (int i = 0; i < arr1.length; i++) {
      text1 = text1.cons(arr1[i]);
    }
    text1 = text1.reverse();

    // Same text but sorted
    String[] arr2 = {
      "Aenean nec porttitor nunc, commodo laoreet purus.",
      "Cras sodales consequat orci at tincidunt.",
      "Duis maximus, tortor sed rhoncus euismod, ante orci vestibulum magna, at dignissim purus augue mattis nunc.",
      "Etiam scelerisque, dui sit amet viverra pretium, augue ex semper lacus, at varius tortor justo quis magna.",
      "Fusce mattis est nibh, sit amet imperdiet mauris posuere sit amet.",
      "In in ullamcorper neque, ac feugiat diam.",
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      "Maecenas sed facilisis eros, vitae consequat mauris.",
      "Nunc semper sem non euismod suscipit. ",
      "Proin porttitor nulla et lectus placerat egestas.",
      "Sed lectus libero, ultrices in cursus a, convallis ac quam.",
      "Vestibulum id malesuada urna."
    };

    SList<String> text2 = new SList<String>();
    for (int i = 0; i < arr2.length; i++) {
      text2 = text2.cons(arr2[i]);
    }
    text2 = text2.reverse();

    SList<String> diff = almostDiff(text1, text2);

    for (int i = 0; i < diff.length(); i++) {
      System.out.println(diff.listRef(i));
    }
  }
 }