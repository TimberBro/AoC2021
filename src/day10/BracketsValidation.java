package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class BracketsValidation {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day10\\Day10.txt"));
    String temp;
    List<String> incompleteLines = new ArrayList<>();
    List<BigInteger> autoCompleteScore = new ArrayList<>();

    int result = 0;
    while ((temp = reader.readLine()) != null) {
      result += invalidBracketsScore(temp);
      if (invalidBracketsScore(temp) == 0) {
        incompleteLines.add(temp);
      }
    }
    System.out.println("First part = " + result);

    for (String line : incompleteLines) {
      autoCompleteScore.add(completeBracketsScore(line));
    }
    Collections.sort(autoCompleteScore);
    System.out.println("Second part = " + autoCompleteScore.get((autoCompleteScore.size() / 2)));
  }


  static int invalidBracketsScore(String expr) {
    Deque<Character> stack
        = new ArrayDeque<>();

    int result = 0;
    for (int i = 0; i < expr.length(); i++) {
      char x = expr.charAt(i);

      if (x == '(' || x == '[' || x == '{' || x == '<') {
        stack.push(x);
        continue;
      }

      char check;
      switch (x) {
        case ')':
          check = stack.pop();
          if (check == '{' || check == '[' || check == '<') {
            return 3;
          }
          break;

        case '}':
          check = stack.pop();
          if (check == '(' || check == '[' || check == '<') {
            return 1197;
          }
          break;

        case ']':
          check = stack.pop();
          if (check == '(' || check == '{' || check == '<') {
            return 57;
          }
          break;
        case '>':
          check = stack.pop();
          if (check == '(' || check == '{' || check == '[') {
            return 25137;
          }
          break;
      }
    }

    return result;
  }

  static BigInteger completeBracketsScore(String expr) {
    Deque<Character> stack
        = new ArrayDeque<>();

    for (int i = 0; i < expr.length(); i++) {
      char x = expr.charAt(i);

      if (x == '(' || x == '[' || x == '{' || x == '<') {
        stack.push(x);
        continue;
      }

      switch (x) {
        case ')':

        case ']':
        case '>':

        case '}':
          stack.pop();
          break;
      }
    }
    BigInteger score = new BigInteger("0");
    for (Character c : stack) {
      score = score.multiply(new BigInteger("5"));
      switch (c) {
        case '(':
          score = score.add(new BigInteger("1"));
          break;
        case '[':
          score = score.add(new BigInteger("2"));
          break;
        case '{':
          score = score.add(new BigInteger("3"));
          break;
        case '<':
          score = score.add(new BigInteger("4"));
          break;
      }
    }
    return score;
  }
}


