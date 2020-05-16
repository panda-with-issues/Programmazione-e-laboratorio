/**
 * IntSList = Integer Scheme-like List
 * This class mocks the way Scheme implements lists. It is a LIFO data structure in which we can access the last integer pushed into the list (so called car) and the rest of the list
 * (known as cdr)
 * 
 * Protocol:
 * 
 * NULL_INTSLIST : IntSList  // empty list
 * 
 * IntSList il = new IntSList
 * il.isNull()     : boolean    // return whether the list is empty
 * il.car()        : int        // return last element pushed into the list
 * il.cdr()        : IntSList   // return the list without the car
 * il.cons(int)    : IntSList   // push int into il
 * il.length()     : int        // return string length
 * il.listRef(i)   : int        // return element at index i
 * il.isEqual(lst) : boolean    // return true if every element in il is in lst and if they have the same order
 * il.append(lst)  : IntSList   // join il and lst in a new list
 * il.reverse()    : IntSList   // reverse the order of il elements
 * 
 * new helper method added for Board:
 * il.includes(n)  : boolean    // return true if the list contains n
 */

public class IntSList {
  private final boolean empty;
  private final int first;
  private final IntSList rest;
  public static final IntSList NULL_INTSLIST = new IntSList();

  public IntSList () {
    empty = true;
    first = 0; // This line has no sense, but it's best-practice to initialize every instance variable inside the constructor
    rest = null;
  }

  public IntSList (int el, IntSList lst) {
    empty = false;
    first = el;
    rest = lst;
  }

  public boolean isNull() {
    return empty;
  }

  public int car() {
    return first;
  }

  public IntSList cdr() {
    return rest;
  }

  public IntSList cons(int el) {
    return new IntSList(el, this);
  }

  public String toString() {               
    if ( isNull() ) {
      return "()";
    } else if ( rest.isNull() ) {
      return "(" + first + ")";
    } else {
      String rep = "(" + first;
      IntSList r = rest;
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }

  /*
  public static IntSList range(int m, int n) {
    if (m > n) {
      return NULL_INTSLIST;
    } else {
      return range(m+1, n).cons(m);
    }
  }
  */
  
  // Exercise: refactor range to work without recursion
  public static IntSList range(int m, int n) {
    IntSList lst = NULL_INTSLIST;
    for (int i = n; i >= m; i--) {
      lst = lst.cons(i);
    }
    return lst;
  }
  
  public int length() {
    if (isNull()) {
      return 0;
    } else {
      return 1 + cdr().length();
    }
  }

  public int listRef(int i) {
    if (i == 0) {
      return car();
    } else {
      return cdr().listRef(i-1);
    }
  }

  public boolean isEqual(IntSList lst) {
    if (isNull()) {
      return lst.isNull();
    } else if (lst.isNull()) {
      return false;
    } else if (car() == lst.car()) {
      return cdr().isEqual(lst.cdr());
    } else {
      return false;
    }
  }

  public IntSList append(IntSList lst) {
    if (isNull()) {
      return lst;
    } else {
      return cdr().append(lst).cons(car());
    }
  }
  
  public IntSList reverse() {
    return reverseRec(NULL_INTSLIST);
  }

  private IntSList reverseRec(IntSList reversedLst) {
    if (isNull()) {
      return reversedLst;
    } else {
      return cdr().reverseRec(reversedLst.cons(car()));
    }
  }

  // New helper method added for Board
  public boolean includes(int n) {
    if (empty) {
      return false;
    } else if (car() == n) {
      return true;
    } else {
      return rest.includes(n);
    }
  }
}