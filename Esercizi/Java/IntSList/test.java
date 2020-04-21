
public class test {
  public static void main (String[] args) {
    IntSList lst1 = IntSList.range(1, 5);
    IntSList lst2 = IntSList.range(6, 10);
    IntSList mergedLst = lst1.append(lst2);
    System.out.println(mergedLst);
  }
}