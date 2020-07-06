/*
 * PART 1
 * 
 * Define a Java class in order to represent list of strings, that works in the same fashion Scheme lists do.
 */

/**
 * StringSList = String Scheme-like List
 * This class mocks the way Scheme implements lists. 
 * It is a LIFO data structure in which we can access the last String element pushed into the list (so called car) and the rest of the list
 * (known as cdr)
 * 
 * Protocol:
 * 
 * StringSList sl = new StringSList()         // instanciate an empty list
 * StringSList nl = new StringSList(str, sl)  // create a new list where str is pushed on top of the given String list
 * sl.isNull()     : boolean                  // return whether the list is empty
 * sl.car()        : String                   // return last string pushed into the list
 * sl.cdr()        : StringSList              // return a new list without car
 * sl.length()     : int                      // return list length
 * sl.cons(str)    : StringSList              // return a new list where str is pushed on top of sl
 * sl.listRef(i)   : String                   // return the string at position i
 * sl.equals(lst)  : boolean                  // return true if every string in sl is in lst and if they have the same order
 * sl.append(lst)  : StringSList              // join sl and lst in a new list
 * sl.reverse()    : StringSList              // reverse the order of sl elements 
 */

public class StringSList {
  private final boolean empty;
  private final String first;
  private final StringSList rest;

  public StringSList() {
    empty = true;
    first = null;
    rest = null;
  }

  public StringSList(String str, StringSList lst) {
    empty = false;
    first = str;
    rest = lst;
  }

  public boolean isNull() {
    return empty;
  }

  public String car() {
    return first;
  }

  public StringSList cdr() {
    return rest;
  }

  public StringSList cons(String str) {
    return new StringSList(str, this);
  }
  
  public int length() {
    if (isNull()) {
      return 0;
    } else {
      return 1 + cdr().length();
    }
  }

  public String listRef(int i) {
    if (i == 0) {
      return car();
    } else {
      return cdr().listRef(i-1);
    }
  }

  public boolean equals(StringSList lst) {
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

  public StringSList append(StringSList lst) {
    if (isNull()) {
      return lst;
    } else {
      return cdr().append(lst).cons(car());
    }
  }
  
  public StringSList reverse() {
    return reverseRec(new StringSList());
  }

  private StringSList reverseRec(StringSList reversedLst) {
    if (isNull()) {
      return reversedLst;
    } else {
      return cdr().reverseRec(reversedLst.cons(car()));
    }
  }

  public String toString() {               
    if ( isNull() ) {
      return "()";
    } else if ( rest.isNull() ) {
      return "(" + first + ")";
    } else {
      String rep = "(" + first;
      StringSList r = rest;
      while ( !r.isNull() ) {
        rep = rep + ", " + r.car();
        r = r.cdr();
      }
      return ( rep + ")" );
    }
  }
} 