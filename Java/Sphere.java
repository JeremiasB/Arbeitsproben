/******************************  Sphere.java  **********************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */

/** Sphere Klasse */ 
public class Sphere extends Shape {
  
  /** Radius der Kugel */
  private double radius;
 
  /** Standart Konstruktor */ 
  public Sphere() { 

  }
  
  /** Konstrukor mit Argumenten 
   * @param colour Farbe des Objekts
   * @param x X-Wert
   * @param y Y-Wert
   * @param z Z-Wert
   * @param radius Radius
   * @throws RuntimeException Wenn Radius negativ ist.
   */ 
  public Sphere(String colour, double x, double y, double z, double radius) {
    super(colour, x, y, z);
    checkRadius(radius);
    this.radius = radius;
  }

  /** Prüfmethode für den Radius
   * @param width Radius der geprüft werden soll.
   * @throw RuntimeException Wenn Radius negativ ist.
   */   
  private void checkRadius(double radius) { 
    if(radius < 0) {
      throw new RuntimeException("Radius negativ");
    }
  }
  
  /** Gibt Radius der Kugel zurück
   * @return Radius
   */
  public double getRadius() {
    return radius;
  }

  /** Setzt neuen Radius 
   * @param Radius Neuer Radius
   * @throws RuntimeException Wenn Radius negativ
   */
  public void setRadius(double radius) {
    checkRadius(radius);
    this.radius = radius;
  }

  /** Berechnet das Volumen des Objekts.
   * @return Volumen
   */
  public double getVolume() {
    return ((4/3*Math.PI)*radius*radius*radius);
  }

  /**Berechnet die Oberfläche des Objekts.
   * @return Oberfläche
   */
  public double getArea() {
    return ((4*Math.PI)*radius*radius);
  }
  /** Berechnet den Abstand zu <tt>other</tt>.
   * Wenn other kein Sphere ist gibt Abstand der Punkte (x,y,z) zurück.
   * Sonst berechne Abstand der beiden Kugeln.
   * @param other Shape zu dem der Abstand berechnet werden soll.
   * @returns Abstand.
   */
  public double getDistanceTo(Shape other) {
    //Speichere Abstand der Punkte.
    double mp_distance = super.getDistanceTo(other);
    //Wenn other kein Cube ist, gib Abstand der Punkte zurück.
    if(!(other instanceof Sphere)) {
      return mp_distance;
    } 
    else {
    //Sonst berechene den Abstand der beiden Kugeln.
    //Caste other als Sphere.
      Sphere sphere = (Sphere) other;
    //Prüfe ob Kreise sich schneiden  
      if(mp_distance > radius + sphere.getRadius()) {
        //Gebe Punktabstand minus beide Radien aus.
        return mp_distance - radius - sphere.getRadius();
      }
      else {
        //Kreise schneiden sich, Abstand ist null.      
        return 0.0;
      }
    }
  }
  
   /** ToString Funktion 
   * @return Farbe, Radius und Kordinaten der Shape als String.
   */
  public String toString(){
    return ("Farbe: " + colour + " Radius: " + radius +" X: "+ x +" Y: "+ y + 
             " Z: " + z );
  } 


}

