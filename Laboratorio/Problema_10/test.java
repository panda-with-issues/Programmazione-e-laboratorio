public class test {
  public static void main(String[] args) {
    // describe toAdd()
    System.out.println(Utils.stringRange(".", 10));
    System.out.println(Utils.stringRange("+-", 5));

    // describe constructor StringSList()
    StringSList sl = new StringSList();
    System.out.println(sl);

    // describe constructor StringSList(str, lst)
    StringSList oneDigit = Utils.stringRange(".", 2);
    System.out.println(new StringSList("-", oneDigit));

    // describe isNull()
    System.out.println(sl.isNull());
    System.out.println(oneDigit.isNull());

    // describe car()
    StringSList twoDigits = Utils.stringRange("+-", 3);
    System.out.println(sl.car());
    System.out.println(oneDigit.car());
    System.out.println(twoDigits.car());

    // describe cdr()
    System.out.println(sl.cdr());
    System.out.println(oneDigit.cdr());
    System.out.println(twoDigits.cdr());

    // describe cons()
    StringSList consedLst = sl.cons("+").cons(".");
    System.out.println(consedLst);

    // describe length()
    System.out.println(sl.length());
    System.out.println(oneDigit.length());
    System.out.println(twoDigits.length());

    // describe listRef()
    System.out.println(sl.listRef(0));
    System.out.println(twoDigits.listRef(0));
    System.out.println(twoDigits.listRef(1));
    System.out.println(twoDigits.listRef(2));

    // describe equals()
    System.out.println(sl.equals(sl));
    System.out.println(twoDigits.equals(twoDigits));
    System.out.println(oneDigit.equals(twoDigits));
    System.out.println(twoDigits.equals(oneDigit));
    StringSList long3lst = Utils.stringRange(".", 3);
    System.out.println(twoDigits.equals(long3lst));
    System.out.println(consedLst.equals(oneDigit));

    // describe append()
    System.out.println(oneDigit.append(twoDigits));
    System.out.println(sl.append(oneDigit));
    System.out.println(oneDigit.append(sl));

    // describe reverse()
    System.out.println(twoDigits.reverse());
  }
}