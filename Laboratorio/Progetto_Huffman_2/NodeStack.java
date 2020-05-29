/**
 * This class implements part of core Stack's functionality, but is specialized to Huffman Nodes
 * 
 * NodeStack s = new NodeStack()  // Instantiate an empty stack
 * q.empty()   : boolean          // Return true if stack is empty
 * q.peek()    : Node             // Return the Node in top of stack
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

  public Node peek() {
    if (!empty()) {
      return stack[len - 1];
    } else {
      return null;
    }
  }

  public Node pop() {
    if (!empty()) {
      Node popped = peek();
      len--;
      Node[] newStack = new Node[len];
      for (int i = 0; i < len; i++) {
        newStack[i] = stack[i];
      }
      stack = newStack;
      return popped;
    } else {
      return null;
    }
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
}