package day13;

import day09.Coordinates;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TransparentOrigami {

  // For first part of the puzzle
  int countDots(List<Coordinates> dotList) {
    return new HashSet<>(dotList).size();
  }


  // For second part of the puzzle
  void drawDots(List<Coordinates> dotList) {
    Set<Coordinates> dotSet = new HashSet<>(dotList);

    int maxX = dotList.stream().mapToInt(Coordinates::getX).max().orElseThrow();
    int maxY = dotList.stream().mapToInt(Coordinates::getY).max().orElseThrow();
    int minX = dotList.stream().mapToInt(Coordinates::getX).min().orElseThrow();
    int minY = dotList.stream().mapToInt(Coordinates::getY).min().orElseThrow();
    char[][] array = new char[maxX - minX + 1][maxY - minY + 1];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        array[i][j] = '.';
      }
    }
    for (Coordinates coordinates : dotSet) {
      array[coordinates.getX() - minX][coordinates.getY() - minY] = '#';
    }

    // Why the fuck my output is inverted?
    System.out.println(Arrays.deepToString(array).replace("], ", "]\n"));
  }

  void foldHorizontally(List<Coordinates> dotList, int line) {
    for (Coordinates coordinates : dotList) {
      if (coordinates.getY() > line) {
        int i = coordinates.getY() - line;
        coordinates.setY(line - i);
      }
    }
  }

  void foldVertically(List<Coordinates> dotList, int line) {
    for (Coordinates coordinates : dotList) {
      if (coordinates.getX() > line) {
        int i = coordinates.getX() - line;
        coordinates.setX(line - i);
      }
    }
  }

  BufferedReader readFile(String path) {
    try {
      return new BufferedReader(new FileReader(path));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  List<Coordinates> getDotList(BufferedReader reader) throws IOException {
    List<Coordinates> dotList = new ArrayList<>();
    String temp;
    while (!Objects.equals(temp = reader.readLine(), "")) {
      String[] coordinates = temp.split(",");
      dotList.add(
          new Coordinates(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
    }
    return dotList;
  }

  List<Coordinates> getInstructionsList(BufferedReader reader) throws IOException {
    List<Coordinates> instructionsList = new ArrayList<>();
    String temp;
    while ((temp = reader.readLine()) != null) {
      String[] strings = temp.split(" ");
      String string = strings[strings.length - 1];
      String[] split = string.split("=");
      String direction = split[0];
      String index = split[1];
      if (direction.equals("y")) {
        instructionsList.add(new Coordinates(0, Integer.parseInt(index)));
      } else if (direction.equals("x")) {
        instructionsList.add(new Coordinates(Integer.parseInt(index),0));
      }
    }
    return instructionsList;
  }

  public static void main(String[] args) throws IOException {
    TransparentOrigami transparentOrigami = new TransparentOrigami();
    BufferedReader bufferedReader = transparentOrigami.readFile("src/day13/Day13.txt");
    List<Coordinates> dotList = transparentOrigami.getDotList(bufferedReader);
    List<Coordinates> instructionsList = transparentOrigami.getInstructionsList(bufferedReader);

    for (Coordinates instruction : instructionsList) {
      if (instruction.getX() == 0) {
        transparentOrigami.foldHorizontally(dotList, instruction.getY());
      } else if (instruction.getY() == 0) {
        transparentOrigami.foldVertically(dotList, instruction.getX());
      }
    }
    transparentOrigami.drawDots(dotList);
  }
}
