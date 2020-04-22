/*
 * PART 2
 * 
 * Define a procedure that given a string literal btr reprensenting a non-negative integer in ternary balanced system, and another non-negative integer n, returns the list of 
 * n consecutives integer literals, starting from btr.
 * Use binSucc() defined in previous exercise
 */

public class Utils {
  public static StringSList stringRange(String btr, int n) {
    StringSList range = new StringSList();
    String toAdd = btr;
    for (int i = 0; i < n; i++) {
      range = range.cons(toAdd);
      toAdd = Exercises.btrSucc(toAdd);
    }
    return range.reverse();
  }
}