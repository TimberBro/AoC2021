package day05;

import java.util.ArrayList;
import java.util.List;

class VenturesMap {

  List<Line> venturesMap = new ArrayList<>();

  void addVentures(String input) {
    String[] parsedLine = input.split("->");
    Point start = new Point(Integer.parseInt(parsedLine[0].trim().split(",")[0]),
        Integer.parseInt(parsedLine[0].trim().split(",")[1]));
    Point end = new Point(Integer.parseInt(parsedLine[1].trim().split(",")[0]),
        Integer.parseInt(parsedLine[1].trim().split(",")[1]));
    venturesMap.add(new Line(start, end));
  }

  void deleteDiagonalVentures() {
    List<Line> diagonalLines = new ArrayList<>();
    for (Line line : venturesMap) {
      if ((line.start.x != line.end.x) && (line.start.y != line.end.y)) {
        diagonalLines.add(line);
      }
    }
    for (Line line : diagonalLines) {
      venturesMap.remove(line);
    }
  }

  private List<Point> accessedPoints = new ArrayList<>();

  void drawVentures() {
    for (Line line : venturesMap) {
      if (line.start.x == line.end.x) {
        drawVerticalLine(line.start, line.end);
      } else if (line.start.y == line.end.y) {
        drawHorizontalLine(line.start, line.end);
      } else if (line.isDiagonal()) {
        drawDiagonalLine(line.start, line.end);
      }
    }
  }

  void drawVerticalLine(Point start, Point end) {
    int from = Math.min(start.y, end.y);
    int to = Math.max(start.y, end.y);
    for (int i = from; i <= to; i++) {
      if (!accessedPoints.contains(new Point(start.x, i))) {
        Point currentPoint = new Point(start.x, i);
        accessedPoints.add(currentPoint);
      } else {
        Point currentPoint = accessedPoints.get(accessedPoints.indexOf(new Point(start.x, i)));
        currentPoint.reference++;
      }
    }
  }

  void drawHorizontalLine(Point start, Point end) {
    int from = Math.min(start.x, end.x);
    int to = Math.max(start.x, end.x);
    for (int i = from; i <= to; i++) {
      if (!accessedPoints.contains(new Point(i, start.y))) {
        Point currentPoint = new Point(i, start.y);
        accessedPoints.add(currentPoint);
      } else {
        Point currentPoint = accessedPoints.get(accessedPoints.indexOf(new Point(i, start.y)));
        currentPoint.reference++;
      }
    }
  }

  void drawDiagonalLine(Point start, Point end) {
    int xDirection;
    int yDirection;
    if (start.x < end.x) {
      xDirection = 1;
    } else {
      xDirection = -1;
    }
    if (start.y < end.y) {
      yDirection = 1;
    } else {
      yDirection = -1;
    }

    Point currentPoint = start;
    while (currentPoint.x != end.x && currentPoint.y != end.y) {
      if (!accessedPoints.contains(currentPoint)) {
        accessedPoints.add(currentPoint);
      } else {
        currentPoint = accessedPoints.get(accessedPoints.indexOf(currentPoint));
        currentPoint.reference++;
      }
      currentPoint = new Point(currentPoint.x + xDirection, currentPoint.y + yDirection);
    }
    if (!accessedPoints.contains(end)) {
      accessedPoints.add(end);
    } else {
      currentPoint = accessedPoints.get(accessedPoints.indexOf(end));
      currentPoint.reference++;
    }
  }

  int countOverlappedPoints() {
    int result = 0;
    for (Point point : accessedPoints) {
      if (point.reference > 1) {
        result++;
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "VenturesMap{" + "venturesMap=" + venturesMap;
  }
}
