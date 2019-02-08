/******************************  ShapeTest.java  ******************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */

public class ShapeTest {

  public static void main(String[] argv) {
     
     Shape dot = new Shape();

     IO.println(dot);
     IO.println("Volume: " + dot.getVolume());
     IO.println("Area: " + dot.getArea());

     Shape dot1 = new Shape("Pink", 10.0, 5.0, 0.0);
     IO.println(dot1);
     IO.println(dot.getDistanceTo(dot1) + " < Entfernung");
     dot.move(10, -5, 20);
     IO.println(dot);
  }
}

