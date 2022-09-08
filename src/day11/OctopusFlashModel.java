package day11;

import day09.Coordinates;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OctopusFlashModel {

  int flashCounter = 0;
  Queue<Coordinates> updateQueue = new ConcurrentLinkedDeque<>();

  public static void main(String[] args) throws Exception {
    OctopusFlashModel model = new OctopusFlashModel();
    Octopus[][] octopusLevels = model.readOctopusLevels("src/day11/Day11.txt");

    System.out.println("Before any steps:");
    System.out.println(Arrays.deepToString(octopusLevels).replace("], ", "]\n"));

    for (int i = 0; i < 100; i++) {
      model.simulateStep(octopusLevels);
      System.out.println("After step " + i + " flashes = " + model.flashCounter);
      System.out.println(Arrays.deepToString(octopusLevels).replace("], ", "]\n"));
    }
  }

  private void simulateStep(Octopus[][] octopusLevels) {
    increaseEnergyLevel(octopusLevels);
    simulateFlash(octopusLevels);
  }

  Octopus[][] readOctopusLevels(String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String temp;
    Octopus[][] map = new Octopus[10][10];
    int mapLine = 0;

    while ((temp = reader.readLine()) != null) {
      char[] charArray = temp.toCharArray();
      for (int i = 0; i < temp.length(); i++) {
        map[mapLine][i] = new Octopus(Character.getNumericValue(charArray[i]), false);
      }
      mapLine++;
    }
    return map;
  }

  void increaseEnergyLevel(Octopus[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        Octopus octopus = map[i][j];
        octopus.energyLevel++;
        if (octopus.energyLevel > 9) {
          updateQueue.add(new Coordinates(i, j));
        }
      }
    }
  }

  void simulateFlash(Octopus[][] map) {
    while (!updateQueue.isEmpty()) {
      Coordinates coordinate = updateQueue.poll();
      Octopus octopus = map[coordinate.getX()][coordinate.getY()];
      if (octopus.energyLevel == 0) {
        continue;
      }
      octopus.energyLevel = 0;
      flashCounter++;

      List<Coordinates> neighbours = getNeighbours(map, coordinate.getX(), coordinate.getY());
      for (Coordinates neighbourCoord : neighbours) {
        Octopus octopus1 = map[neighbourCoord.getX()][neighbourCoord.getY()];
        if (octopus1.energyLevel != 0) {
          octopus1.energyLevel++;
          if (octopus1.energyLevel > 9) {
            updateQueue.add(neighbourCoord);
          }
        }
      }
    }
  }

  // Might need a better solution to find neighbours.
  private List<Coordinates> getNeighbours(Octopus[][] map, int i, int j) {
    List<Coordinates> neighbours = new ArrayList<>();
    // Check corners
    if (i == 0 && j == 0) {
      neighbours.add(new Coordinates(i, j + 1));
      neighbours.add(new Coordinates(i + 1, j));
      neighbours.add(new Coordinates(i + 1, j + 1));
    } else if (i == 0 && j == map[i].length - 1) {
      neighbours.add(new Coordinates(i, j - 1));
      neighbours.add(new Coordinates(i + 1, j - 1));
      neighbours.add(new Coordinates(i + 1, j));
    } else if (i == map[i].length - 1 && j == 0) {
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i - 1, j + 1));
      neighbours.add(new Coordinates(i, j + 1));
    } else if (i == map[i].length - 1 && j == map[i].length - 1) {
      neighbours.add(new Coordinates(i - 1, j - 1));
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i, j - 1));
    } else

    // Check edge cases
    if (i == 0) {
      neighbours.add(new Coordinates(i, j - 1));
      neighbours.add(new Coordinates(i, j + 1));
      neighbours.add(new Coordinates(i + 1, j - 1));
      neighbours.add(new Coordinates(i + 1, j));
      neighbours.add(new Coordinates(i + 1, j + 1));
    } else if (j == 0) {
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i - 1, j + 1));
      neighbours.add(new Coordinates(i, j + 1));
      neighbours.add(new Coordinates(i + 1, j));
      neighbours.add(new Coordinates(i + 1, j + 1));
    } else if (i == map[i].length - 1) {
      neighbours.add(new Coordinates(i - 1, j - 1));
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i - 1, j + 1));
      neighbours.add(new Coordinates(i, j - 1));
      neighbours.add(new Coordinates(i, j + 1));
    } else if (j == map[i].length - 1) {
      neighbours.add(new Coordinates(i - 1, j - 1));
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i, j - 1));
      neighbours.add(new Coordinates(i + 1, j - 1));
      neighbours.add(new Coordinates(i + 1, j));
    } else {
      // General case
      neighbours.add(new Coordinates(i - 1, j - 1));
      neighbours.add(new Coordinates(i - 1, j));
      neighbours.add(new Coordinates(i - 1, j + 1));
      neighbours.add(new Coordinates(i, j - 1));
      neighbours.add(new Coordinates(i, j + 1));
      neighbours.add(new Coordinates(i + 1, j - 1));
      neighbours.add(new Coordinates(i + 1, j));
      neighbours.add(new Coordinates(i + 1, j + 1));
    }
    return neighbours;
  }
}

class Octopus {
  int energyLevel;
  boolean flashed;

  public Octopus(int energyLevel, boolean flashed) {
    this.energyLevel = energyLevel;
    this.flashed = flashed;
  }

  @Override
  public String toString() {
    return "" + energyLevel;
  }
}
