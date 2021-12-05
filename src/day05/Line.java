package day05;

class Line {
  Point start;
  Point end;

  boolean isDiagonal() {
    return (Math.abs(end.x - start.x) == Math.abs(end.y - start.y));
  }

  Line(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "Line{" + "start=" + start + ", end=" + end + '}';
  }
}
