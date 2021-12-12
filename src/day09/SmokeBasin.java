package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class SmokeBasin {

  static Map<Coordinates, Integer> coordinatesMap = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day09\\Day09_test.txt"));
    String temp;

    int[][] map = new int[5][10];
    int j = 0;
    while ((temp = reader.readLine()) != null) {
      char[] parsedString = temp.toCharArray();
      for (int i = 0; i < parsedString.length; i++) {
        map[j][i] = Character.getNumericValue(parsedString[i]);
      }
      j++;
    }

    List<Coordinates> lowPoints = new ArrayList<>();

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
          lowPoints.add((new Coordinates(k, i)));
        }
      }
    }

    List<Integer> basinSizes = new ArrayList<>();

    int firstPuzzleResult = 0;
    for (Coordinates i : lowPoints) {
      int var = coordinatesMap.get(i);
      var++;
      firstPuzzleResult += var;
      basinSizes.add(neighboursList(i).size());
    }

    basinSizes.sort(Collections.reverseOrder());
    int secondPuzzleResult = 1;
    for (int i = 0; i < 3; i++) {
      secondPuzzleResult *= basinSizes.get(i);
    }

    System.out.println(neighboursList(new Coordinates(0, 0)));

    System.out.println(firstPuzzleResult);
    System.out.println(secondPuzzleResult);
  }

  static Set<Coordinates> neighboursList(Coordinates lowPoint) {
    List<Coordinates> neighbours = new ArrayList<>();
    neighbours.add(lowPoint);

    // Optimize algorithm. For now program iterate through Coordinates, that already calculated.
    // Should extract intersection between tempSet and neighbours, and iterate through them
    while (true) {
      // Set to compare previous result;
      Set<Coordinates> tempSet = new HashSet<>();
      tempSet.addAll(new HashSet<>(neighbours));

      ListIterator<Coordinates> iterator = neighbours.listIterator();
      while (iterator.hasNext()) {
        Coordinates point = iterator.next();
        if (coordinatesMap.get(new Coordinates(point.x + 1, point.y)) != null
            && coordinatesMap.get(new Coordinates(point.x + 1, point.y)) != 9) {
          iterator.add(new Coordinates(point.x + 1, point.y));
        }
        if (coordinatesMap.get(new Coordinates(point.x - 1, point.y)) != null
            && coordinatesMap.get(new Coordinates(point.x - 1, point.y)) != 9) {
          iterator.add(new Coordinates(point.x - 1, point.y));
        }
        if (coordinatesMap.get(new Coordinates(point.x, point.y + 1)) != null
            && coordinatesMap.get(new Coordinates(point.x, point.y + 1)) != 9) {
          iterator.add(new Coordinates(point.x, point.y + 1));
        }
        if (coordinatesMap.get(new Coordinates(point.x, point.y - 1)) != null
            && coordinatesMap.get(new Coordinates(point.x, point.y - 1)) != 9) {
          iterator.add(new Coordinates(point.x, point.y - 1));
        }
      }
      if (tempSet.containsAll(new HashSet<>(neighbours))) {
        break;
      }
    }
    return new HashSet<>(neighbours);
  }
}

