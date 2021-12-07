package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrabAligning {

  public static void main(String[] args) throws IOException {
    List<Integer> crabPositions = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src\\day07\\Day07.txt"));
    String temp;
    while ((temp = reader.readLine()) != null) {
      String[] parsedTemp = temp.split(",");
      for (String position : parsedTemp) {
        crabPositions.add(Integer.parseInt(position));
      }
    }

    System.out.println(countMinimalConsumption(crabPositions));
    System.out.println(countMinimalExpensiveConsumption(crabPositions));
  }

  private static int countMinimalConsumption(List<Integer> crabPositions) {
    List<Integer> resultList = new ArrayList<>();
    for (int i = 0; i < Collections.max(crabPositions); i++) {
      int result = 0;
      for (int j = 0; j < crabPositions.size(); j++) {
        result += fuelConsumption(crabPositions.get(j), i);
      }
      resultList.add(result);
    }
    return Collections.min(resultList);
  }

  private static int countMinimalExpensiveConsumption(List<Integer> crabPositions) {
    List<Integer> resultList = new ArrayList<>();
    for (int i = 0; i < Collections.max(crabPositions); i++) {
      int result = 0;
      for (int j = 0; j < crabPositions.size(); j++) {
        result += expensiveFuelConsumption(crabPositions.get(j), i);
      }
      resultList.add(result);
    }
    return Collections.min(resultList);
  }

  static int fuelConsumption(int crabPosition, int desiredPosition) {
    return Math.abs(crabPosition - desiredPosition);
  }

  static int expensiveFuelConsumption(int crabPosition, int desiredPosition) {
    int sum = 0;
    for (int i = 0; i <= fuelConsumption(crabPosition, desiredPosition); i++) {
      sum += i;
    }
    return sum;
  }
}
