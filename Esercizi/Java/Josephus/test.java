
public class test {
  public static void main(String[] args) {
    for (int i = 3; i <= 20; i++) {
      System.out.print("With " + i + " soldiers: ");
      System.out.println(Josephus.lastTwo(i));
    }
  }
}