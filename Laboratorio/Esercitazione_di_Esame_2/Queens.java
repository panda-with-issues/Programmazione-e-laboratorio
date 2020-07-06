
/*
 * Rompicapo delle "n regine"
 *
 * Ultimo aggiornamento: 23/05/2018
 *
 *
 * Realizzazione del dato astratto "scacchiera"
 * la cui configurazione eveolve aggiungendoe rimuovendo regine
 *
 * Protocollo della classe Board:
 *
 *   new Board( int n )           :  costruttore (scacchiera vuota)
 *
 *   size()                       :  int
 *
 *   queensOn()                   :  int
 *
 *   underAttack( int i, int j )  :  boolean
 *
 *   addQueen( int i, int j )     :  void
 *
 *   arrangement()                :  String
 *
 *
 * Board b;
 *
 *   new Board(n)           costruttore della scacchiera n x n vuota;
 *   b.size()               dimensione n della scacchiera b;
 *   b.queensOn()           numero di regine collocate nella scacchiera b;
 *   b.underAttack(i,j)     la posizione <i,j> e' minacciata?
 *   b.addQueen(i,j)        modifica della configurazione di b
 *                          aggiungendo una nuova regina in posizione <i,j>
 *   b.removeQueen(i,j)     modifica della configurazione di b
 *                          rimuovendo la regina in posizione <i,j>
 *                          (si assume che ci sia una regina in <i,j>)
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */


public class Queens {


  /*
   * I. Numero di soluzioni:
   *
   *
   * Il numero di modi diversi in cui si possono disporre n regine
   *
   *   numberOfSolutions( n )
   *
   * in una scacchiera n x n e' dato dal numero di modi diversi in
   * cui si puo' completare la disposizione delle regine a partire
   * da una scacchiera n x n inizialmente vuota
   *
   *   numberOfCompletions( new Board(n) )
   */
  
  public static int numberOfSolutions( int n ) {
    
    return numberOfCompletions( new Board(n) );
  }
  
  
  /*
   * Il numero di modi in cui si puo' completare la disposizione
   * a partire da una scacchiera b parzialmente configurata
   *
   *   numberOfCompletions( b )   : int
   *
   * dove k regine (0 <= k < n) sono collocate nelle prime k righe
   * di b, si puo' determinare visitando le configurazioni
   * che si ottengono aggiungendo una regina nella riga k+1 in tutti
   * i modi possibili (nelle posizioni che non sono gia' minacciate)
   *
   *   for ( int j=1; j<=n; j=j+1 ) {
   *     if ( !b.underAttack(i,j) ) { b.addQueen(i,j) ... ... }
   *   }
   *
   * procedendo ricorsivamente per ciascuna di queste il numero
   * di modi in cui si puo' completare la disposizione
   *
   *   numberOfCompletions( b )
   *
   * e sommando i valori che ne risultano
   *
   *   count = count + numberOfCompletions( ... )
   *
   * Se invece la scacchiera rappresenta una soluzione (q == n)
   * c'e' un solo modo (banale) di completare la disposizione:
   * lasciare le cose come stanno!
   *
   * Invariante:
   * Al termine di una chiamata ricorsiva la configurazione
   * della scacchiera b risulta ripristinata nello stato in
   * cui si trovava all'inizio della chiamata stessa
   */
  
  private static int numberOfCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return 1;
    
    } else {
    
      int i = q + 1;
      int count = 0;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          b.addQueen( i, j );
          count = count + numberOfCompletions( b );
          b.removeQueen( i, j );  // !
      }}
      return count;
    }
  }
  
  
  /*
   * II. Lista delle soluzioni:
   *
   * Confronta il programma precedente!
   */
  
  public static SList<String> listOfAllSolutions( int n ) {
    
    return listOfAllCompletions( new Board(n) );
  }
  
  
  private static SList<String> listOfAllCompletions( Board b ) {
    
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
      
      return ( NULL_SRTINGLIST.cons(b.arrangement()) );
      
    } else {
      
      int i = q + 1;
      SList<String> solutions = NULL_SRTINGLIST;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
          
          b.addQueen( i, j );
          solutions = solutions.append( listOfAllCompletions(b) );
          b.removeQueen( i, j );
        }}
      return solutions;
    }
  }
  
  
  // Lista Schem-like vuota di stringhe
  
  private static final SList<String> NULL_SRTINGLIST = new SList<String>();

  
  // Eventuale programma principale
  
  public static void main( String args[] ) {
    
    int n = Integer.parseInt( args[0] );
    
    System.out.println( numberOfSolutions(n) );
    System.out.println( listOfAllSolutions(n) );
  }


}  // class Queens

