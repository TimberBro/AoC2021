package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SquidGame {

  public static void main(String[] args) throws IOException {
    List<Integer> numbers = new LinkedList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src\\day04\\Day04.txt"));
    String temp;
    temp = reader.readLine();
    List<BingoTable> tableList = new ArrayList<>();
    for (String s : temp.split(",")) {
      numbers.add(Integer.parseInt(s));
    }

    while (reader.readLine() != null) {
      tableList.add(new BingoTable());
      BingoTable currentTable = tableList.get(tableList.size() - 1);
      for (int i = 0; i < 5; i++) {
        temp = reader.readLine();
        temp = temp.trim().replaceAll(" +", " ");
        currentTable.addRows(temp);
      }
    }

    initializeResultTables(tableList);

    int finalScore = 0;
    for (Integer i : numbers) {
      for (BingoTable gameTable : tableList) {
        gameTable.searchValue(i);
        if (gameTable.validateResultTable()) {
          finalScore = gameTable.sumUnmarkedValues();
          finalScore *= i;
          System.out.println(finalScore);
          return;
        }
      }
    }
  }

  private static void initializeResultTables(List<BingoTable> tableList) {
    for (BingoTable table : tableList) {
      table.initializeResultTable();
    }
  }
}
