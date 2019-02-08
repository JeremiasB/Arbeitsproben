/******************************  CubeTest.java  ******************************/

import AlgoTools.IO;

/**
 * @version 13.12.14
 *  
 * @author  jbrass
 */

public class CubeTest {

  public static void main(String[] argv) {
     
    //Aufruf des Defaultkonstruktors
    Cube cube1 = new Cube();        

    //ToString des DefaultShapes
    IO.println("Standart Cube :");
    IO.println(cube1); 
    IO.println();

    //Ausgabe des Standartvolumes.
    IO.println("Volume: " + cube1.getVolume());
    IO.println();
    IO.println("Area: " + cube1.getArea());
    IO.println();

    //Daten für neues Objekt einlesen.
    String colour = IO.readString("Farbe des neuen Würfels: ");
    IO.println();
    double[]kord = IO.readDoubles("Neues X, Y und Z : ",3);
    IO.println();
    double w; 
    do {
     w = IO.readDouble("Kantenlänge des neuen Würfels: ");
    } while(w < 0.0);
    IO.println();

    //Neues Objekt instanzieren
    Cube cube2 = new Cube(colour,kord[0],kord[1],kord[2],w);

    //Neues Objekt ausgeben.
    IO.println("Neues Objekt:");
    IO.println(cube2);
    IO.println("Volume: " + cube2.getVolume());
    IO.println();
    IO.println("Area: " + cube2.getArea());
    IO.println();   

    //Entfernung berechen.
    IO.print("Entfernung zwischen den beiden Kugeln: ");
    IO.println(cube1.getDistanceTo(cube2));
    IO.println();
    IO.println("Entfernung zwischen zweiter Kugel und dem Ursprung");
    Shape ursprung = new Shape();
    IO.println(cube2.getDistanceTo(ursprung));
    IO.println();

    //Radius verändern und Ausgeben.
    do {
     w = IO.readDouble("Neuer Kantenlänge für zweiten Würfel: ");
    } while(w < 0.0);
    IO.println();
    cube2.setWidth(w);
    IO.println("Neuer Kantenlänge ist: " + cube2.getWidth());

    //Verschiebe.
    kord = IO.readDoubles("Verschiebe erstes Objekt um X, Y und Z : ",3);
    cube1.move(kord[0],kord[1],kord[2]);
    IO.println("Neue Kordinaten: ");
    IO.println(cube1);

  }
}

