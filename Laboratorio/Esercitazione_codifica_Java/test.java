public class test {
  public static void main (String[] args) {
    // exercise 1
    System.out.println(Exercises.btrSucc("."));
    System.out.println(Exercises.btrSucc("+"));
    System.out.println(Exercises.btrSucc("+-"));
    System.out.println(Exercises.btrSucc("+."));
    System.out.println(Exercises.btrSucc("+.+"));

    // exercise 2
    System.out.println(Exercises.onesComplement("00000"));
    System.out.println(Exercises.onesComplement("11111"));
    System.out.println(Exercises.onesComplement("10101"));

    System.out.println(Exercises.onesComplementFor("00000"));
    System.out.println(Exercises.onesComplementFor("11111"));
    System.out.println(Exercises.onesComplementFor("10101"));
  }
}