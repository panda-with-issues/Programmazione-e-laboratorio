/**
 * This class implements part of core Stack's functionality, but is specialized to Huffman Nodes
 * 
 * NodeStack s = new NodeStack()  // Instantiate an empty stack
 * q.empty()   : boolean          // Return true if stack is empty
 * q.pop()     : Node             // Remove the last inserted Node in stack and return it
 * q.push(n)                      // Put a node into stack
 */

public class NodeStack {
  private Node[] stack;
  private int len;


  public NodeStack() {
    stack = new Node[0];
    len = 0;
  }

  public boolean empty() {
    return len == 0;
  }

  public Node pop() {
    len--;
    Node popped = stack[len];
    Node[] newStack = new Node[len];
    for (int i = 0; i < len; i++) {
      newStack[i] = stack[i];
    }
    stack = newStack;
    return popped;
  }

  public void push(Node n) {
    Node[] newStack = new Node[len + 1];
    for (int i = 0; i < len; i++) {
      newStack[i] = stack[i];
    }
    newStack[len] = n;
    stack = newStack;
    len++;
  }

  public String toString() {
    String rep = "[";
    for (int i = 0; i < len; i++) {
      if (i == len - 1) {
        rep += stack[i].character();
      } else {
        rep += stack[i].character() + ", ";
      }
    }
    rep += "]";
    return rep;
  } 

  /*
   * test
   */
  public static void main(String[] args) {
    NodeStack s = new NodeStack();
    Node a = new Node('x', 10);
    Node b = new Node('1', 1);
    Node c = new Node('8', 8);
    Node d = new Node('7', 7);
    Node e = new Node('2', 2);
    s.push(a);
    System.out.println(s);
    s.push(b);
    System.out.println(s);
    s.push(c);
    System.out.println(s);
    s.push(d);
    System.out.println(s);
    s.push(e);
    System.out.println(s);
    while (!s.empty()) {
      System.out.println(s.pop().character());
      System.out.println(s);
    }
  }
}