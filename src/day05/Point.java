package day05;

import java.util.Objects;

class Point {

  int x;
  int y;
  int reference = 1;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return x == point.x && y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, reference);
  }

  @Override
  public String toString() {
    return "Point{" + "x=" + x + ", y=" + y + ", reference=" + reference + '}';
  }
}
