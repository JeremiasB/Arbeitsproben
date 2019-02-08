/******************************  Cube.java  **********************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */
/** Cube Klasse */
public class Cube extends Shape {

  /** Kantenlänge des Cubes */
  private double width;
  /** Standart Konstruktor */
  public Cube() {

  }
  
  /** Konstrukor mit Argumenten 
  * @param colour Farbe des Objekts
  * @param x X-Wert
  * @param y Y-Wert
  * @param z Z-Wert
  * @param width Kantenlänge
  * @throws RuntimeException Wenn Kantenlänge negativ ist.
  */ 
  public Cube(String colour, double x, double y, double z, double width) {
    super(colour, x, y, z);
    checkWidth(width);
    this.width = width;
  }

  /** Prüfmethode für die Kantenlänge
  * @param width Kantenlänge die geprüft werden soll.
  * @throw RuntimeException Wenn Kantenlänge negativ ist.
  */   
  private void checkWidth(double width) {
    if(width < 0) { 
      throw new RuntimeException("Kantenlänge negativ");
    }
  }

  /**Setzt neue Kantenlänge
   * @param width Neue Kantenlänge
   * @throws RuntimeException Wenn Kantenlänge negativ ist.
   */
  public void setWidth(double width) {
    checkWidth(width);
    this.width = width;
  }
  
  /**Gibt die Kantenlänge des Cubes zurück.
   * @return Kantenlänge
   */
  public double getWidth() {
    return width;
  }
  /** Berechnet das Volumen des Objekts.
   * @return Volumen
   */
  public double getVolume() {
    return width * width * width; 
  }

  /**Berechnet die Oberfläche des Objekts.
   * @return Oberfläche
   */
  public double getArea() {
    return 6*width*width;
  }

  /** Berechnet den Abstand zu <tt>other</tt>.
   * @param other Shape zu dem der Abstand berechnet werden soll.
   * @return Abstand.
   */
  public double getDistanceTo(Shape other) {
    //Speichere Abstand der Punkte.
    double p_distance = super.getDistanceTo(other);
    double distance;
    //Wenn other kein Cube ist gebe den Abstand der Punke zurück.
    if(!(other instanceof Cube)) { 
      return p_distance;
    }
    //Wenn other ein Würfel ist berechne den Abstand der Mittelpunkte.
    //Caste other als Cube.
    Cube cube = (Cube) other;
    //Speichere halbe Kantenlängen der Würfel.
    double other_width = cube.getWidth();
    double w1 = width/2;
    double w2 = other_width/2;
    //Berechen Abstand der um halbe Kantenlängen verschibenen Punkte.
    distance = Math.sqrt((this.x-w1 - other.x-w2)*(this.x-w1 - other.x-w2) +
                         (this.y-w1 - other.y-w2)*(this.y-w1 - other.y-w2) +
                         (this.z-w1 - other.z-w2)*(this.z-w1 - other.z-w2));
    return distance;

  }
  
  /** ToString Funktion 
   * @return Farbe, Kantenlänge und Kordinaten der Shape als String.
   */
  public String toString(){
    return ("Farbe: " + colour + " X: "+ x +" Y: "+ y + " Z: " + z + " Width: " 
            + width);
  } 


}

