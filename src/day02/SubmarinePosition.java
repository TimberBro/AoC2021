package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SubmarinePosition {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src\\day02\\Day02.txt"));
    String s;
    int x = 0;
    int y = 0;
    int aim = 0;
    while ((s = reader.readLine()) != null) {
      switch (s.split(" ")[0]) {
        case "forward":
          x += Integer.parseInt(s.split(" ")[1]);
          y += (aim * Integer.parseInt(s.split(" ")[1]));
          break;
        case "up":
          aim -= Integer.parseInt(s.split(" ")[1]);
          break;
        case "down":
          aim += Integer.parseInt(s.split(" ")[1]);
          break;
        default:
          System.out.println("Should be unreachable.");
          break;
      }
    }
    System.out.println(x * y);
  }
}
