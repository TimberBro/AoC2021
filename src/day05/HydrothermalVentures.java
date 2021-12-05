package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HydrothermalVentures {

  public static void main(String[] args) throws IOException {
    VenturesMap map = new VenturesMap();
    BufferedReader reader = new BufferedReader(new FileReader("src\\day05\\Day05.txt"));
    String temp;
    while ((temp = reader.readLine()) != null) {
      map.addVentures(temp);
    }
    // Uncomment to get answer for first puzzle.
    // map.deleteDiagonalVentures();
    map.drawVentures();
    System.out.println(map.countOverlappedPoints());
  }
}
