package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LanterfishSimulation {

  // Initialize list for each possible cycle number.
  // Forced to use BigInteger, because of integer overflow.
  static List<BigInteger> fishCycleList = new ArrayList<>(Arrays
      .asList(
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0"),
          new BigInteger("0")));

  static BigInteger fishSum() {
    BigInteger result = new BigInteger("0");
    for (BigInteger i : fishCycleList) {
      result = result.add(i);
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day06\\Day06.txt"));
    String temp;
    while ((temp = reader.readLine()) != null) {
      String[] values = temp.split(",");
      for (String value : values) {
        BigInteger currentValue = fishCycleList.get(Integer.parseInt(value));
        currentValue = currentValue.add(new BigInteger("1"));
        fishCycleList.set(Integer.parseInt(value), currentValue);
      }
    }

    // Change value to get answer to first puzzle.
    for (int i = 0; i < 256; i++) {
      // Mark how much fished should be added.
      BigInteger newFishes = fishCycleList.get(0);
      for (int j = 1; j < fishCycleList.size(); j++) {
        fishCycleList.set(j - 1, fishCycleList.get(j));
      }
      // Add old fishes back to 6th day of cycle.
      fishCycleList.set(6, new BigInteger(String.valueOf(newFishes.add(fishCycleList.get(6)))));
      // Add new fishes to 8th day of cycle.
      fishCycleList.set(8, newFishes);
    }

    System.out.println(fishSum());
  }
}
