/**
 * This class implements part of core PriorityQueue's functionality, but is specialized to Huffman Nodes
 * 
 * NodePriorityQ q = new NodePriorityQ()  // Instantiate an empty queue
 * q.size()     : int                     // Return queue's length
 * q.poll()     : Node                    // Dequeue the lighter Node in queue (the one with minor weight) and return it
 * q.add(node)                            // Enqueue a node
 */

public class NodePriorityQ {
  private Node[] q;
  private int size;

  public NodePriorityQ() {
    q = new Node[0];
    size = 0;
  }

  public int size() {
    return size;
  }

  public Node poll() {
    int minIdx = getMinIdx();
    Node min = q[minIdx]; 
    deleteNode(minIdx);
    size--;
    return min;
  }

  private int getMinIdx() {
    // acceptable and possible values are [0, 127]. 0 is a dummy placeholder, it'll never be read.
    Node min = new Node('0', 128);
    int idx = 0;
    for (int i = 0; i < size; i++) {
      if (q[i].weight() < min.weight()) {
        min = q[i];
        idx = i;
      }
    }
    return idx;
  }

  private void deleteNode(int minIdx) {
    /*
     * overwrite element to be deleted with element in last position, then copy first n - 1 element in a new queue
     * actual q = [3, 5, 1, 6]
     * tempQ = [3, 5, 6, 6]
     * newQ = [3, 5, 6]
     */
    q[minIdx] = q[size - 1];
    Node[] newQ =  new Node[size - 1];
    for (int i = 0; i < size - 1; i++) {
      newQ[i] = q[i];
    }
  }

  public void add(Node n) {
    Node[] newQ = new Node[size + 1];
    for (int i = 0; i < size; i++) {
      newQ[i] = q[i];
    }
    newQ[size] = n;
    q = newQ;
    size++;
  }

  public String toString() {
    String rep = "[";
    for (int i = 0; i < size; i++) {
      if (i == size - 1) {
        rep += q[i].character();
      } else {
        rep += q[i].character() + ", ";
      }
    }
    rep += "]";
    return rep;
  } 

  /*
   * test
   */
  public static void main(String[] args) {
    NodePriorityQ q = new NodePriorityQ();
    Node a = new Node('x', 10);
    Node b = new Node('1', 1);
    Node c = new Node('8', 8);
    Node d = new Node('7', 7);
    Node e = new Node('2', 2);
    q.add(a);
    System.out.println(q);
    q.add(b);
    System.out.println(q);
    q.add(c);
    System.out.println(q);
    q.add(d);
    System.out.println(q);
    q.add(e);
    System.out.println(q);
    while (q.size() > 0) {
      System.out.println(q.poll().character());
      System.out.println(q);
    }
  }
}