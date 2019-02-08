/******************************  SphereTest.java  ******************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */

public class SphereTest {

  public static void main(String[] argv) {
     
    //Aufruf des Defaultkonstruktors
    Sphere sphere1 = new Sphere();        

    //ToString des DefaultShapes
    IO.println("Standart Sphere :");
    IO.println(sphere1); 
    IO.println();

    //Ausgabe des Standartvolumes.
    IO.println("Volume: " + sphere1.getVolume());
    IO.println();
    IO.println("Area: " + sphere1.getArea());
    IO.println();

    //Daten für neues Objekt einlesen.
    String colour = IO.readString("Farbe der neuen Kugel: ");
    IO.println();
    double[]kord = IO.readDoubles("Neues X, Y und Z : ",3);
    IO.println();
    double r; 
    do {
     r = IO.readDouble("Radius der neuen Kugel: ");
    } while(r < 0.0);
    IO.println();

    //Neues Objekt instanzieren
    Sphere sphere2 = new Sphere(colour,kord[0],kord[1],kord[2],r);

    //Neues Objekt ausgeben.
    IO.println("Neues Objekt:");
    IO.println(sphere2);
    IO.println("Volume: " + sphere2.getVolume());
    IO.println();
    IO.println("Area: " + sphere2.getArea());
    IO.println();   

    //Entfernung berechen.
    IO.print("Entfernung zwischen den beiden Kugeln: ");
    IO.println(sphere1.getDistanceTo(sphere2));
    IO.println();
    IO.println("Entfernung zwischen zweiter Kugel und dem Ursprung");
    Shape ursprung = new Shape();
    IO.println(sphere2.getDistanceTo(ursprung));
    IO.println();

    //Radius verändern und Ausgeben.
    do {
     r = IO.readDouble("Neuer Radius für zweite Kugel: ");
    } while(r < 0.0);
    IO.println();
    sphere2.setRadius(r);
    IO.println("Neuer Radius ist: " + sphere2.getRadius());

    //Verschiebe.
    kord = IO.readDoubles("Verschiebe erstes Objekt um X, Y und Z : ",3);
    sphere1.move(kord[0],kord[1],kord[2]);
    IO.println("Neue Kordinaten: ");
    IO.println(sphere1);

  }
}

