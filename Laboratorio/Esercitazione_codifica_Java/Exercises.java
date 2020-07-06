public class Exercises {
  /*
   * 1: Write a procedure that returns a number literal in balanced ternary system.
   * This literal must be the successor of the given literal and  the procedure must  directly on digits.
   */
  public static String btrSucc(String btr) {
    int n = btr.length();
    char lsb = btr.charAt(n-1);
    if (n == 1) {
      if (lsb == '+') {
        return "+-";
      } else {
        return "+";
      }
    } else {
      String pre = btr.substring(0, n-1);
      if (lsb == '+') {
        return btrSucc(pre) + "-";
      } else {
        return pre + (lsb == '-' ? "." : "+");
      }
    }
  }

  /*
   * 2: Write a procedure that, given a string of bits, returns a string with the 1's complement
   */
  public static String onesComplement(String bin) {
    if (bin.equals("")) {
      return "";
    } else {
      return onesComplement(bin.substring(0, bin.length()-1)) + (bin.charAt(bin.length()-1) == '0' ? "1" : "0");
    }
  }

  // Now write the same procedure without recursion
  public static String onesComplementFor(String bin) {
    String complement = "";
    for (int i = 0; i < bin.length(); i++) {
      String charToAdd = bin.charAt(i) == '0' ? "1" : "0";
      complement += charToAdd;
    }
    return complement;
  }
}