/**
 * Models a weighted binary tree data structure.
 * 
 * Node n = new Node(c, w)  // Instanciate a new node with value c and weight w
 * Node p = new Node(l, r)  // Join a left sub-tree with a right sub-tree with a new empty root
 * n.character() : char     // Return node's character value
 * n. weight()   : int      // Return node's weight
 * q.left()      : Node     // Return left sub-tree
 * q.right()     : Node     // Return right sub-tree
 * n.isLeaf()    : boolean  // Return true if n isn't root of any sub-tree
 */

public class Node implements Comparable<Node> {
  private final char character;
  private final int weight;
  private final Node left;
  private final Node right;
  
  public Node(char c, int w) {
    character = c;
    weight = w;
    left = null;
    right = null;
  }

  public Node(Node l, Node r) {
    character = (char) 0; // This assignment has no sense because there is no value associated with this Node, but we can't use null.
                          // We use instead the value of the first acceptable ASCII char, even if it will never be read
    weight = l.weight() + r.weight();
    left = l;
    right = r;
  }

  public char character() {
    return character;
  }

  public int weight() {
    return weight;
  }

  public Node left() {
    return left;
  }

  public Node right() {
    return right;
  }

  public boolean isLeaf() {
    // because they are binary tree, either there are both sub-tree or there is none
    return left == null;
  }

  public int compareTo(Node n) {
    if (weight < n.weight()) {
      return -1;
    } else if (weight == n.weight()) {
      return 0;
    } else {
      return 1;
    }
  }
}