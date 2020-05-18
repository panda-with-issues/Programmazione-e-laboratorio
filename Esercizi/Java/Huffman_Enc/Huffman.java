/**
 * Let compress a txt file using the Huffman encoding.
 * 
 * To compile run:
 * 
 * javac -classpath "huffman_toolkit.jar:." Huffman.java
 * 
 * To run:
 * 
 * java -classpath "huffman_toolkit.jar:." Huffman
 */

import java.util.PriorityQueue;
import huffman_toolkit.*;

public class Huffman {
  private static final int CHARS = 128; // [0, 127]

  public static int[] freq(String f) {
    int[] freq = new int[CHARS];
    for (int i = 0; i < CHARS; i++) {
      freq[i] = 0;
    }
    InputTextFile in = new InputTextFile(f);
    while (in.textAvailable()) {
      final char c = in.readChar();
      freq[c]++;
    }
    in.close();
    return freq;
  }

  public static Node HuffamnTree(int[] freq) {
    PriorityQueue<Node> q = new PriorityQueue<Node>();
    for (int i = 0; i < CHARS; i++) {
      if (freq[i] > 0) {
        final Node n = new Node((char) i, freq[i]);
        q.add(n);
      }
    }
    while (q.size() > 1) {
      final Node l = q.poll();
      final Node r = q.poll();
      final Node n = new Node(l, r);
      q.add(n);
    }
    return q.poll();
  }

  public static String[] codeTable(Node root) {
    String[] codes = new String[CHARS];
    fillTable(root, "", codes);
    return codes;
  }

  private static void fillTable(Node n, String pre, String[] codes) {
    if (n.isLeaf()) {
      codes[n.character()] = pre;
    } else {
      fillTable(n.left(), pre + "0", codes);
      fillTable(n.right(), pre + "1", codes);
    }
  }

  public static String flattenTree(Node n) {
    if (n.isLeaf()) {
      if (n.character() == '@') {
        return "\\@"; 
      } else if (n.character() == '\\') {
        return "\\";
      } else {
        return "" + n.character();
      }
    } else {
      return "@" + flattenTree(n.left()) + flattenTree(n.right());
    }
  }

  public static void compress(String f, String path) {
    final int[] freq = freq(f);
    final Node root = HuffamnTree(freq);
    final String[] codes = codeTable(root);
    final int size = root.weight();
    final String treeEnc = flattenTree(root);
    InputTextFile in = new InputTextFile(f);
    OutputTextFile out = new OutputTextFile(path);
    out.writeTextLine("" + size);
    out.writeTextLine(treeEnc);
    for (int i = 0; i < size; i++) {
      final char c = in.readChar();
      out.writeCode(codes[c]);
    }
    in.close();
    out.close();
  }

  public static void main(String[] args) {
    compress("Huffman.java", "src_compressed.txt");
  }
}