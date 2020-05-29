/**
 * This class implements part of core PriorityQueue's functionality, but is specialized to Huffman Nodes
 * 
 * q = new NodeQueue()  // instantiate a new queue
 * q.size() : int       // Return how many elements are enqueued
 * q.peek() : Node      // Return queue's first element
 * q.poll() : Node      // Dequeue first element and return it
 * q.add(n)             // Enqueue a node
 */

public class NodeQueue {
  private Node[] q;
  private int size;

  public NodeQueue() {
    q = new Node[0];
    size = 0;
  }

  public int size() {
    return size;
  }

  public Node peek() {
    if (size > 0) {
      int minIdx = getMinIdx();
      return q[minIdx];
    } else {
      return null;
    }
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

  public Node poll() {
    if (size > 0) {
      Node min = peek();
      deleteNode();
      size--;
      return min;
    } else {
      return null;
    }
  }

  private void deleteNode() {
    /*
     * overwrite element to be deleted with element in last position, then copy first n - 1 element in a new queue
     * deleteNode(2) -->
     * actual q = [3, 5, 1, 6]
     * tempQ = [3, 5, 6, 6]
     * newQ = [3, 5, 6]
     */
    int minIdx = getMinIdx();
    q[minIdx] = q[size - 1];
    Node[] newQ =  new Node[size - 1];
    for (int i = 0; i < size - 1; i++) {
      newQ[i] = q[i];
    }
    q = newQ;
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
}