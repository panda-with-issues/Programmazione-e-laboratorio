public class es2017_4 {
  public static IntSList lis( IntSList s ) {
    return lisRec( s, 0 );
  } 
  
  public static IntSList lisRec( IntSList s, int t ) {
    if ( s.isNull() ) {
      return new IntSList();
    } else if ( s.car() <= t ) {
      return lisRec( s.cdr(), t );
    } else {
      return longer( new IntSList( s.car(), lisRec( s.cdr(), s.car() ) ),
                         lisRec( s.cdr(), t )
                         );
    }
  }
  
  public static IntSList longer( IntSList u, IntSList v ) {
    if ( u.length() < v.length() ) {
      return v;
    } else {
      return u;
    }
  }

  private static final IntSList UNKNOWN = null;
  public static IntSList lisMem(IntSList s) {
    int n = s.length();
    IntSList[] h = new IntSList[n+1];
    for (int i = 0; i <= n; i += 1) {
      h[i] = UNKNOWN;
    }
    return lisMain(s, 0, h);
  }

  private static IntSList lisMain(IntSList s, int t, IntSList[] h) {
    int i = t;
    if (h[i] == UNKNOWN) {
      if ( s.isNull() ) {
        h[i] = new IntSList();
      } else if ( s.car() <= t ) {
        h[i] = lisMain( s.cdr(), t, h );
      } else {
        h[i] = longer( new IntSList( s.car(), lisMain( s.cdr(), s.car(), h ) ),
                           lisMain( s.cdr(), t, h )
                           );
      }
    }
    return h[i];
  }

  public static void main(String[] args) {
    int[] s = new int[] {9, 3, 7, 5, 4, 2, 6, 1, 8, 10};
    IntSList lst = new IntSList();
    for (int i = 0; i < s.length; i++) {
      lst = lst.cons(s[i]);
    }

    System.out.println(lst);

    System.out.println(lis(lst));
    System.out.println(lisMem(lst));
  }
}