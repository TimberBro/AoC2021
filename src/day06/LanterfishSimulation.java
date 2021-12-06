package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class LanterfishSimulation {

  public static void main(String[] args) throws IOException {
    List<Lanternfish> shoal = new LinkedList<>();
    Lanternfish.setShoal(shoal);
    BufferedReader reader = new BufferedReader(new FileReader("src\\day06\\Day06_test.txt"));
    String temp;
    while ((temp = reader.readLine()) != null) {
      String[] values = temp.split(",");
      for (String value : values) {
        shoal.add(new Lanternfish(Integer.parseInt(value)));
      }
    }

    ListIterator<Lanternfish> iterator;
    for (int i = 0; i < 80; i++) {
      iterator = shoal.listIterator();
      while (iterator.hasNext()) {
        Lanternfish currentFish = iterator.next();
        if (currentFish.lifeCycle == 0) {
          iterator.add(new Lanternfish());
        }
        currentFish.spendDay();
      }
    }
    System.out.println(shoal.size());
  }

}
