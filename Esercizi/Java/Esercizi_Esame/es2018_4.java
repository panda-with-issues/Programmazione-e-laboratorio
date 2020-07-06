import java.util.Vector;

/**
 * Protocollo:
 * new ProximityStructure()  // costruisce una collezione vuota di misure
 * s.size()                  // restituisce il numero di misure contenute nella collezione
 * s.add( x )                // aggiunge la misura x alla collezione s
 * s.removeClosestTo( x )    // rimuove da s e restituisce la misura più prossima a x// (la cui distanza da x è più piccola) in s
 */

public class es2018_4 {
  private Vector<Double> s;

  public es2018_4() {
    s = new Vector<Double>();
  }

  public int size() {
    return s.size();
  }

  public void add(double x) {
    s.add(x);
  }

  public double removeClosestTo(double x) {
    double closest = s.get(0);
    double diff = Math.abs(x - closest);
    int closestIdx = 0;
    for (int i = 1; i < size(); i++) {
      double currentEl = s.get(i);
      double currentDiff = Math.abs(x - currentEl);
      if (currentDiff < diff) {
        closest = currentEl;
        diff = currentDiff;
        closestIdx = i;
      }
    }
    s.removeElementAt(closestIdx);
    return closest;
  }
}