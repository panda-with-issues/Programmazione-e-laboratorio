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

import java.util.*;
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
      char c = n.character();
      if (c == '@' || c == '\\') {
        return "\\" + c;
      } else {
        return "" + c;
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
  
  // public static Node restoreTree(InputTextFile in) {
  //   char c = in.readChar();
  //   if (c == '@') {
  //     Node left = restoreTree(in);
  //     Node right = restoreTree(in);
  //     return new Node(left, right);
  //   } else {
  //     if (c == '\\') {
  //       c = in.readChar();
  //     }
  //     return new Node(c, 0);
  //   }
  // }

  /*
   * Exercise: write an iterative, imperative version of restoreTree
   */
  public static Node restoreTree(InputTextFile in) {
    /*
     * To restore the Huffman tree we will read flatten string tree backward. 
     * If the element is a char, we create a Node and put it into a stack.
     * When we find a @, we create a node popping the first two element in stack, then we push again the new created node into the stack.
     * At the end of the string, into the stack there will be the root of Huffman's tree.
     */
    // We are assuming that \n appears in src (and odds are that it will), but this method won't work if src doesn't contains any \n
    String tree = in.readTextLine() + "\n" + in.readTextLine();
    Stack<Node> s = new Stack<Node>();
    for (int i = tree.length() - 1; i >= 0; i--) {
      // we read flatten tree backward
      char c = tree.charAt(i);
      if ((c == '@' || c == '\\') &&
          i != 0 &&
          tree.charAt(i-1) == '\\' &&
          tree.charAt(i-2) != '\\') { // situation like "\\@" will mess up the algorythm
      // check if @ or \ are characters
      s.push(new Node(c, 0));
      } else if (c == '\\') {
        // skip escape character
        continue;
      } else if (c == '@') {
        // if c is not a char, it marks a node
        // we create this node popping the first two nodes in stack and then push this new node
        Node l = s.pop();
        Node r = s.pop();
        s.push(new Node(l, r));
      } else {
        // c is a char
        // we create a leaf node and put it into stack
        s.push(new Node(c, 0));
      }
    }
    // at the end of the cicle, we have only our root node in stack
    return s.pop();
   }

  public static void decompress(String src, String dst) {
    InputTextFile in = new InputTextFile(src);
    OutputTextFile out = new OutputTextFile(dst);
    int size = Integer.parseInt(in.readTextLine());
    Node root = restoreTree(in);
    // String dummy = in.readTextLine(); // will be empty, needed only to go to new line
                                         // this is only needed if using recursive version of restoreTree() 
    for (int i = 0; i < size; i++) {
      char c = restoreChar(root, in);
      out.writeChar(c);
    }
    in.close();
    out.close();
  }

  private static char restoreChar(Node n, InputTextFile in) {
    while(!n.isLeaf()) {
      int bit = in.readBit();
        if (bit == 0) {
          n = n.left();
        } else {
          n = n.right();
        }
      }
    return n.character();
    }

  public static void main(String[] args) {
    compress("Huffman.java", "src_compressed.txt");
    decompress("src_compressed.txt", "huffman_decompressed.txt");
  }
}
