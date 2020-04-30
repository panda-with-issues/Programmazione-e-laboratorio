public class FibonacciBU {
  public static long fibonacciBU(int k) {// k > 0
    long[] h = new long[k + 1];
    h[0] = 1;
    h[1] = 1;
    for (int i = 2; i <= k; i++) {
      h[i] = h[i-1] + h[i-2];
    }
    return h[k];
  }

  public static long fibonacci(int k) {
    if (k < 2) {
      return 1;
    } else {
      return fibonacci(k-1) + fibonacci(k-2);
    }
  }

  public static void main(String[] args) {
    System.out.println(fibonacciBU(1));
    System.out.println(fibonacci(1));
    System.out.println(fibonacciBU(2));
    System.out.println(fibonacci(2));
    System.out.println(fibonacciBU(3));
    System.out.println(fibonacci(3));
    System.out.println(fibonacciBU(10));
    System.out.println(fibonacci(10));
    System.out.println(fibonacciBU(45));
    System.out.println(fibonacci(45)); // circa 5s
  }
}