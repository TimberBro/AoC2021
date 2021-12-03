package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SonarSweep {

  static void fileToArray(ArrayList<Integer> list, String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String s;
    while ((s = reader.readLine()) != null) {
      list.add(Integer.valueOf(s));
    }
  }

  static ArrayList<Integer> slidingWindow(ArrayList<Integer> list) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < list.size() - 2; i++) {
      result.add(list.get(i) + list.get(i + 1) + list.get(i + 2));
    }
    return result;
  }

  static void countIncreases(ArrayList<Integer> list) {
    int increasesCounter = 0;
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i + 1) > list.get(i)) {
        increasesCounter++;
      }
    }
    System.out.println(increasesCounter);
  }

  public static void main(String[] args) {
    ArrayList<Integer> values = new ArrayList<>();
    try {
      fileToArray(values, "src\\Day01\\Day01.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    countIncreases(values);
    countIncreases(slidingWindow(values));
  }
}
