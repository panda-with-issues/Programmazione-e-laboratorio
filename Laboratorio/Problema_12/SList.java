/**
 * SList = generic Scheme-like List
 * This class mocks the way Scheme implements lists. It is a LIFO data structure in which we can access the last element pushed into the list (so called car) and the rest of the list
 * (known as cdr)
 * 
 * Protocol:
 * 
 * SList<T> sl = new SList<T>() // instanciate a new empty list
 * sl.isNull()     : boolean    // return whether the list is empty
 * sl.car()        : T          // return last element pushed into the list
 * sl.cdr()        : SList<T>   // return the list without the car
 * sl.cons(e)      : SList<T>   // push e into sl
 * sl.length()     : int        // return the list's elements number
 * sl.listRef(i)   : T          // return element at index i
 * sl.equals(lst)  : boolean    // return true if every element in sl is in lst and if they have the same order
 * sl.append(lst)  : SList<T>   // join sl and lst in a new list
 * sl.reverse()    : SList<T>   // reverse the order of il elements
 */

public class SList<T> {
  private final boolean empty;
  private final T first;
  private final SList<T> rest;

  public SList () {
    empty = true;
    first = null;
    rest = null;
  }

  private SList (T el, SList<T> lst) {
    empty = false;
    first = el;
    rest = lst;
  }

  public boolean isNull() {
    return empty;
  }

  public T car() {
    return first;
  }

  public SList<T> cdr() {
    return rest;
  }

  public SList<T> cons(T el) {
    return new SList<T>(el, this);
  }

  public String toString() {               
    if ( isNull() ) {
      return "()";
    } else if ( rest.isNull() ) {
      return "(" + first + ")";
    } else {
      String rep = "(" + first;
      SList<T> r = rest;
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }
  
  public int length() {
    if (isNull()) {
      return 0;
    } else {
      return 1 + cdr().length();
    }
  }

  public T listRef(int i) {
    if (i == 0) {
      return car();
    } else {
      return cdr().listRef(i-1);
    }
  }

  public boolean equals(SList<T> lst) {
    if (isNull()) {
      return lst.isNull();
    } else if (lst.isNull()) {
      return false;
    } else if (car() == lst.car()) {
      return cdr().equals(lst.cdr());
    } else {
      return false;
    }
  }

  public SList<T> append(SList<T> lst) {
    if (isNull()) {
      return lst;
    } else {
      return cdr().append(lst).cons(car());
    }
  }
  
  public SList<T> reverse() {
    return reverseRec(new SList<T>());
  }

  private SList<T> reverseRec(SList<T> reversedLst) {
    if (isNull()) {
      return reversedLst;
    } else {
      return cdr().reverseRec(reversedLst.cons(car()));
    }
  }
}