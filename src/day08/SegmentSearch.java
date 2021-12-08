package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class SegmentSearch {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day08\\Day08.txt"));
    String temp;

    int firstPartResult = 0;
    int secondPartResult = 0;
    while ((temp = reader.readLine()) != null) {
      List<String> beforePipeline = new ArrayList<>();
      List<String> afterPipeline = new ArrayList<>();
      String[] parsedLine = temp.split("\\|");
      beforePipeline.addAll(Arrays.asList(parsedLine[0].trim().split(" ")));
      afterPipeline.addAll(Arrays.asList(parsedLine[1].trim().split(" ")));
      firstPartResult += countCombinations(beforePipeline, afterPipeline);
      secondPartResult += stringToNum(beforePipeline, afterPipeline);
    }

    System.out.println(firstPartResult);
    System.out.println(secondPartResult);

  }

  static int countCombinations(List<String> whereToFind, List<String> whatToFind) {
    int result = 0;
    for (String word : whatToFind) {
      for (String anagram : whereToFind) {
        if (findAnagram(word, anagram) && correctInput(anagram)) {
          result++;
        }
      }
    }
    return result;
  }

  static boolean findAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    } else {
      char[] arrayS1 = s1.toCharArray();
      char[] arrayS2 = s2.toCharArray();
      Arrays.sort(arrayS1);
      Arrays.sort(arrayS2);
      return Arrays.equals(arrayS1, arrayS2);
    }
  }

  static boolean correctInput(String input) {
    return input.length() == 2 || input.length() == 3 || input.length() == 4 || input.length() == 7;
  }

  static int stringToNum(List<String> input, List<String> time) {
    Map<Integer, String> map = new HashMap<>();
    List<String> length6 = new ArrayList<>();
    List<String> length5 = new ArrayList<>();
    for (String value : input) {
      switch (value.length()) {
        case 2:
          map.put(1, sortString(value));
          break;
        case 3:
          map.put(7, sortString(value));
          break;
        case 4:
          map.put(4, sortString(value));
          break;
        case 7:
          map.put(8, sortString(value));
          break;
        case 6:
          length6.add(sortString(value));
          break;
        case 5:
          length5.add(sortString(value));
          break;
      }
    }
    for (String value6 : length6) {
      Set<Character> set = value6.chars()
          .mapToObj(e -> (char) e).collect(Collectors.toSet());
      Set<Character> set6 = map.get(1).chars().mapToObj(e -> (char) e).collect(
          Collectors.toSet());
      set.retainAll(set6);
      if (set.size() == 1) {
        map.put(6, value6);
      }
    }
    length6.remove(map.get(6));
    for (String value9 : length6) {
      Set<Character> set = value9.chars()
          .mapToObj(e -> (char) e).collect(Collectors.toSet());
      Set<Character> set4 = map.get(4).chars().mapToObj(e -> (char) e).collect(
          Collectors.toSet());
      set.retainAll(set4);
      if (set.size() == 4) {
        map.put(9, value9);
      }
    }
    length6.remove(map.get(9));
    map.put(0, length6.get(0));

    for (String value5 : length5) {
      Set<Character> set = value5.chars()
          .mapToObj(e -> (char) e).collect(Collectors.toSet());
      Set<Character> set6 = map.get(6).chars().mapToObj(e -> (char) e).collect(
          Collectors.toSet());
      set.retainAll(set6);
      if (set.size() == 5) {
        map.put(5, value5);
      }
    }
    length5.remove(map.get(5));

    for (String value3 : length5) {
      Set<Character> set = value3.chars()
          .mapToObj(e -> (char) e).collect(Collectors.toSet());
      Set<Character> set1 = map.get(1).chars().mapToObj(e -> (char) e).collect(
          Collectors.toSet());
      set.retainAll(set1);
      if (set.size() == 2) {
        map.put(3, value3);
      }
    }
    length5.remove(map.get(3));
    map.put(2, length5.get(0));

    StringBuilder sb = new StringBuilder();
    for (String number : time) {
      sb.append(getKey(map, sortString(number)));
    }

    return Integer.parseInt(sb.toString());
  }

  public static String sortString(String inputString) {
    char[] tempArray = inputString.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }

  static <K, V> K getKey(Map<K, V> map, V value) {
    for (Entry<K, V> entry : map.entrySet()) {
      if (entry.getValue().equals(value)) {
        return entry.getKey();
      }
    }
    return null;
  }
}