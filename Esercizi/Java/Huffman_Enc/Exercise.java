/**
 * Generate a random text file of 5000 characters (included in first set of ASCII characters, from 0 to 127) and compress it using Huffman encoding
 * 
 * To compile run:
 * 
 * javac -classpath "huffman_toolkit.jar:." Exercise.java
 * 
 * To run:
 * 
 * java -classpath "huffman_toolkit.jar:." Exercise
 */

import huffman_toolkit.*;

public class Exercise {
  private static final int CHARS_NUM = 5000;
  private static final int CHARS = 128;

  public static void generateRandomTxt() {
    OutputTextFile f = new OutputTextFile("random.txt");
    for (int i = 0; i < CHARS_NUM; i++) {
      int c = (int) (Math.random() * CHARS);
      f.writeChar((char) c);
    }
    f.close();
  }

  public static void main(String[] args) {
    generateRandomTxt();
    Huffman.compress("random.txt", "random_compressed.txt");
    // compressed is bigger than source because we have an uniform distribution of chars + we wrote our header with size and tree encoding!
  }
}