package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryDiagnostic {

  // Solution for second part.
  static int mostCommonBit(List<String> list, boolean bit) {
    for (int i = 0; list.size() > 1; i++) {
      int zeros = 0;
      int ones = 0;
      for (String temp :
          list) {
        if (temp.charAt(i) == '1') {
          ++ones;
        } else {
          ++zeros;
        }
      }

      int target = bit ? (zeros > ones ? '0' : '1') : (ones < zeros ? '1' : '0');
      List<String> result = new ArrayList<>();
      for (String in : list) {
        if (in.charAt(i) == target) {
          result.add(in);
        }
      }
      list = result;
    }
    return Integer.parseInt(list.get(0), 2);
  }

  // Solution for first part.
  private static int multiplyRates(List<String> list) {
    StringBuilder gammaRate = new StringBuilder();
    StringBuilder epsilonRate = new StringBuilder();
    for (int i = 0; i < list.get(0).length(); i++) {
      int zeros = 0;
      int ones = 0;
      for (String temp : list) {
        if (temp.charAt(i) == '1') {
          ones++;
        } else {
          zeros++;
        }
      }
      if (zeros > ones) {
        gammaRate.append("0");
        epsilonRate.append("1");
      } else {
        gammaRate.append("1");
        epsilonRate.append("0");
      }
    }
    return Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2);
  }


  public static void main(String[] args) throws IOException {
    List<String> parsedFile = new LinkedList<>();
    String temp;
    BufferedReader reader = new BufferedReader(new FileReader("src\\day03\\Day03.txt"));
    while ((temp = reader.readLine()) != null) {
      parsedFile.add(temp);
    }
    System.out.println(multiplyRates(parsedFile));
    System.out.println(mostCommonBit(parsedFile, true) * mostCommonBit(parsedFile, false));
  }

}
