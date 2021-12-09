package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SmokeBasin {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day09\\Day09.txt"));
    String temp;

    int[][] map = new int[100][100];
    int j = 0;
    while ((temp = reader.readLine()) != null) {
      char[] parsedString = temp.toCharArray();
      for (int i = 0; i < parsedString.length; i++) {
        map[j][i] = Character.getNumericValue(parsedString[i]);
      }
      j++;
    }

    List<Integer> lowPoints = new ArrayList<>();
    Map<Coordinates, Integer> coordinatesMap = new HashMap<>();

    for (int i = 0; i < map.length; i++) {
      for (int k = 0; k < map[0].length; k++) {
        coordinatesMap.put(new Coordinates(k, i), map[i][k]);
      }
    }

    for (int i = 0; i < map.length; i++) {
      for (int k = 0; k < map[0].length; k++) {
        List<Integer> neighbours = new ArrayList<>();

        if (coordinatesMap.get(new Coordinates(k + 1, i)) != null) {
          neighbours.add(coordinatesMap.get(new Coordinates(k + 1, i)));
        }
        if (coordinatesMap.get(new Coordinates(k - 1, i)) != null) {
          neighbours.add(coordinatesMap.get(new Coordinates(k - 1, i)));
        }
        if (coordinatesMap.get(new Coordinates(k, i + 1)) != null) {
          neighbours.add(coordinatesMap.get(new Coordinates(k, i + 1)));
        }
        if (coordinatesMap.get(new Coordinates(k, i - 1)) != null) {
          neighbours.add(coordinatesMap.get(new Coordinates(k, i - 1)));
        }
        Collections.sort(neighbours);
        if (coordinatesMap.get(new Coordinates(k, i)) < neighbours.get(0)) {
          lowPoints.add(coordinatesMap.get(new Coordinates(k, i)));
        }
      }
    }

    int result = 0;
    for (Integer i : lowPoints) {
      int var = i;
      var++;
      result += var;
    }
    System.out.println(result);
  }
}

class Coordinates {

  int x;
  int y;

  Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "x: " + x + "; y: " + y + "; ";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinates that = (Coordinates) o;
    return x == that.x &&
        y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
