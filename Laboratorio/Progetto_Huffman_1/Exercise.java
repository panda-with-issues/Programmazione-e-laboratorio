/*
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
  private static final int RANDOM_CHARS_NUM = 3414; // wc -m Huffman.java
  private static final int CHARS = 128;

  public static void generateTable(String f, String path) {
    final int[] freq = Huffman.freq(f);
    final Node root = Huffman.HuffamnTree(freq);
    final String[] huffmanCodes = Huffman.codeTable(root);
    OutputTextFile out = new OutputTextFile(path);
    for (int i = 0; i < CHARS; i++) {
      // line must contain ASCII code, glyph, number of occurences, Huffman code, and its length
      if (freq[i] > 0) {
        String line = "";
        line += i + "\t";
        line += (char) i + "\t";
        line += freq[i] + "\t";
        line += huffmanCodes[i] + "\t";
        line += huffmanCodes[i].length();
        out.writeTextLine(line);
      }
    }
    out.close();
  }

  public static void generateRandomTxt() {
    OutputTextFile f = new OutputTextFile("random.txt");
    for (int i = 0; i < RANDOM_CHARS_NUM; i++) {
      int c = (int) (Math.random() * CHARS);
      f.writeChar((char) c);
    }
    f.close();
  }

  public static void main(String[] args) {
    generateTable("Huffman.java", "Huffman_freq.txt");
    generateRandomTxt();
    generateTable("random.txt", "random_freq.txt");
    /*
     * Random text has every possible character in range and every character has got a Huffman code whose length is around 7 (the normal ASCII code's length).
     * This is due to Math.random() having a uniform distribution of probability, thus every character's occurrence numbers are almost the same
     */
    Huffman.compress("Huffman.java", "Huffman_compressed.txt");
    Huffman.compress("random.txt", "random_compressed.txt");
  }
}