/******************************  Shape.java  **********************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */
/**Shape Überklass */
public class Shape {
  
  /** Farbe der Shape*/
  public String colour;
  /** Kordinaten der Shape*/
  public double x, y, z;
  
  /** StandardKonstruktor */
  public Shape() { 
  
    colour = "schwarz";
  }
  
  /** Konstrukor mit Argumenten 
   * @param colour Farbe des Objekts
   * @param x X-Wert
   * @param y Y-Wert
   * @param z Z-Wert
   */
  public Shape(String colour, double x, double y, double z) {
  
    this.colour = colour;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  /** Verschiebt das Objekt um <tt>dX</tt>,<tt>dY</tt> und <tt>dZ</tt>.
   * @param dX X-Wert 
   * @param dY Y-Wert 
   * @param dZ Z-Wert
   */
  public void move(double dX, double dY, double dZ) {
  
    this.x += dX;
    this.y += dY;
    this.z += dZ;
  }
  
  /** Berechent das Volumen des Objekts.
   * @return Volumen des Objekts.
   */
  public double getVolume() {
  
    return 0.0; 
  }
  
  /** Berechent die Oberfläche des Objekts.
   * @return Oberfläche der Objekts
   */
  public double getArea() {
  
    return 0.0;
  }
  
  /** Berechnet den Abstand zu <tt>other</tt>.
   * @param other Shape zu dem der Abstand berechnet werden soll.
   * @return Abstand.
   */
  public double getDistanceTo(Shape other) {
  
    double distance = Math.sqrt((this.x - other.x)*(this.x - other.x) +
                                (this.y - other.y)*(this.y - other.y) +
                                (this.z - other.z)*(this.z - other.z));
    return distance;
  }
  
  /** ToString Funktion 
   * @return Farbe und Kordinaten der Shape als String.
   */
  public String toString(){
  
    return ("Farbe: " + colour + " X: "+ x +" Y: "+ y + " Z: " + z );
   
  } 


}

