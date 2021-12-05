package day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BingoTable {

  Map<Integer, List<Integer>> gameTable = new HashMap<>();
  Map<Integer, List<Boolean>> resultTable = new HashMap<>();

  void addRows(String row) {
    int currentSize = gameTable.keySet().size();
    List<Integer> rowValues = new ArrayList<>();
    for (String s : row.split(" ")) {
      rowValues.add(Integer.parseInt(s));
    }
    gameTable.put(currentSize, rowValues);
  }

  void initializeResultTable() {
    int columnSize = gameTable.keySet().size();
    int rowSize = gameTable.get(0).size();
    for (int i = 0; i < columnSize; i++) {
      List<Boolean> currentRow = new ArrayList<>();
      for (int j = 0; j < rowSize; j++) {
        currentRow.add(false);
      }
      resultTable.put(i, currentRow);
    }
  }

  void searchValue(int value) {
    int columnSize = gameTable.keySet().size();
    for (int i = 0; i < columnSize; i++) {
      List<Integer> currentRow = gameTable.get(i);
      if (currentRow.contains(value)) {
        markValue(i, currentRow.indexOf(value));
      }
    }
  }

  private void markValue(int columnIndex, int rowIndex) {
    List<Boolean> rowToMark = resultTable.get(columnIndex);
    rowToMark.set(rowIndex, true);
  }

  boolean validateResultTable() {
    return validateColumns() || validateRows();
  }

  private boolean validateRows() {
    int columnSize = gameTable.keySet().size();
    for (int i = 0; i < columnSize; i++) {
      List<Boolean> currentRow = resultTable.get(i);
      if (!currentRow.contains(false)) {
        return true;
      }
    }
    return false;
  }

  private boolean validateColumns() {
    int columnSize = gameTable.keySet().size();
    int rowSize = gameTable.get(0).size();
    List<Boolean> currentColumn = new ArrayList<>();
    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < columnSize; j++) {
        currentColumn.add(resultTable.get(j).get(i));
      }
      if (!currentColumn.contains(false)) {
        return true;
      }
    }
    return false;
  }

  int sumUnmarkedValues() {
    int result = 0;
    int columnSize = gameTable.keySet().size();
    int rowSize = gameTable.get(0).size();

    for (int i = 0; i < columnSize; i++) {
      for (int j = 0; j < rowSize; j++) {
        if (!resultTable.get(i).get(j)) {
          result += gameTable.get(i).get(j);
        }
      }
    }
    return result;
  }
}
