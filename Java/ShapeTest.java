/******************************  ShapeTest.java  ******************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */

public class ShapeTest {

  public static void main(String[] argv) {
     
    //Aufruf des Defaultkonstruktors
    Shape dot = new Shape();        

    //ToString des DefaultShapes
    IO.println("Standart Shape :");
    IO.println(dot); 
    IO.println();

    //Ausgabe des Standartvolumes.
    IO.println("Volume: " + dot.getVolume());
    IO.println();
    IO.println("Area: " + dot.getArea());
    IO.println();

    //Daten für neues Objekt einlesen.
    String colour = IO.readString("Farbe eines neuen Objekts: ");
    double[]kord = IO.readDoubles("Neues X, Y und Z : ",3);

    //Neues Objekt instanzieren
    Shape dot1 = new Shape(colour,kord[0],kord[1],kord[2]);

    //Neues Objekt ausgeben.
    IO.println("Neues Objekt:");
    IO.println(dot1);
    IO.println("Volume: " + dot.getVolume());
    IO.println();
    IO.println("Area: " + dot.getArea());
    IO.println();   

    //Entfernung berechen.
    IO.print("Entfernung zwischen den Objekten: ");
    IO.println(dot.getDistanceTo(dot1));
    IO.println();

    //Verschiebe.
    kord = IO.readDoubles("Verschiebe erstes Objekt um X, Y und Z : ",3);
    dot.move(kord[0],kord[1],kord[2]);
    IO.println("Neue Kordinaten: ");
    IO.println(dot);

  }
}

