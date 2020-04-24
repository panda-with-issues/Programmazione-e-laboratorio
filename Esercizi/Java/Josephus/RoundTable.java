/**
 * Implements Josephus's (Titus Flavius) version of eenie meenie minee moe, where every 3rd soldier one is removed (actual euphemism for "sent to be killed by the next chosen")
 * 
 * Protocol:
 * 
 * RoundTable rt = new RoundTable(n)  // initialize the object with n soldiers
 * rt.alives()       : int            // return the number of soldier not removed
 * rt.getSoldiers()  : IntSList       // return the list of soldiers still to be counted
 * rt.remove3rd()    : RoundTable     // return a new RoundTable without the 3rd soldier
 */

public class RoundTable {
  private final IntSList soldiers;
  private final IntSList survived;
  private final int alives;

  public RoundTable(int soldiersNum) {
    soldiers = IntSList.range(1, soldiersNum);
    survived = new IntSList();
    alives = soldiersNum;
  }

  private RoundTable(IntSList toCheck, IntSList checked, int newLength) {
    soldiers = toCheck;
    survived = checked;
    alives = newLength;
  }

  public int alives() {
    return alives;
  }

  public IntSList getSoldiers() {
    return soldiers;
  }
  
  public RoundTable remove3rd() {
    // System.out.println("-> [" + soldiers + " " + survived + " " + alives + "]");
    IntSList toCheck = soldiers.cdr().cdr();
    IntSList checked = survived.cons(soldiers.car()).cons(soldiers.cdr().car());
    // System.out.println("[" + toCheck + " " + checked + " " + alives + "]");
    if (toCheck.isNull()) {
      return new RoundTable(
        checked.reverse().cdr(),
        new IntSList(), 
        alives - 1
      );
    } else if (toCheck.cdr().isNull()) {
      // toCheck length == 1
      return new RoundTable(
        checked.reverse(),
        new IntSList(),
        alives - 1
      );
    } else if (toCheck.cdr().cdr().isNull()) {
      // toChceck length == 2
      /*
       * This condition is needed because toCheck has step 2.
       * Without that, a situation like
       * [soldiers: (7 8 9 10), survived: (5 4 2 1), alives: 8]
       * will lead to an exception:
       * -> [toCheck: (9 10), (8 7 5 4 2 1) 8]
       * -> [soldiers: (10), survived: (8 7 5 4 2 1), alives: 7]
       * -> [tochecK: (10).cdr().cdr() -> ().cdr() throw exception!]
       * So soldiers length must be always > 1
       */
      return new RoundTable(
        toCheck.cdr().append(checked.reverse()),
        new IntSList(),
        alives - 1
      );
    } else {
      return new RoundTable(
        toCheck.cdr(),
        checked,
        alives - 1
      );
    }
  }
}