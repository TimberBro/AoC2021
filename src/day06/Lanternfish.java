package day06;

import java.util.List;

public class Lanternfish {

  int lifeCycle;
  static List<Lanternfish> shoal;

  Lanternfish() {
    lifeCycle = 8;
  }

  Lanternfish(int init) {
    lifeCycle = init;
  }

  void spendDay() {
    if (lifeCycle == 0) {
      lifeCycle = 7;
    }
    lifeCycle -= 1;
  }

  public static void setShoal(List<Lanternfish> shoal) {
    Lanternfish.shoal = shoal;
  }

  @Override
  public String toString() {
    return String.valueOf(lifeCycle);
  }
}
