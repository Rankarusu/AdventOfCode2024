package moe.ranka.aoc2024;

import java.util.Arrays;
import java.util.List;

public class D04 extends Day {

  private String word = "XMAS";
  private int[][][] offsets = new int[][][] {
      new int[][] {
          new int[] { -1, 0 },
          new int[] { -2, 0 },
          new int[] { -3, 0 },
      },
      new int[][] {
          new int[] { -1, -1 },
          new int[] { -2, -2 },
          new int[] { -3, -3 },
      },
      new int[][] {
          new int[] { 0, -1 },
          new int[] { 0, -2 },
          new int[] { 0, -3 },
      },
      new int[][] {
          new int[] { 1, -1 },
          new int[] { 2, -2 },
          new int[] { 3, -3 },
      },
      new int[][] {
          new int[] { 1, 0 },
          new int[] { 2, 0 },
          new int[] { 3, 0 },
      },
      new int[][] {
          new int[] { 1, 1 },
          new int[] { 2, 2 },
          new int[] { 3, 3 },
      },
      new int[][] {
          new int[] { 0, 1 },
          new int[] { 0, 2 },
          new int[] { 0, 3 },
      },
      new int[][] {
          new int[] { -1, 1 },
          new int[] { -2, 2 },
          new int[] { -3, 3 },
      }, };

  @Override
  public void part1() {
    var lines = readFile("04.txt").split("\n");
    char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
    int counter = 0;

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        counter += checkPoint(i, j, map);
      }
    }
    System.out.println(counter);
  }

  private int checkPoint(int x, int y, char[][] grid) {

    int counter = 0;
    if (grid[x][y] != this.word.charAt(0)) {
      // we only need to start on points with starting letters
      return 0;
    }

    for (int i = 0; i < offsets.length; i++) {
      if (checkForWord(grid, x, y, offsets[i])) {
        counter++;
      }
    }
    return counter;
  }

  private boolean checkForWord(char[][] grid, int x, int y, int[][] points) {
    for (int j = 0; j < points.length; j++) {
      var newX = x + points[j][0];
      var newY = y + points[j][1];
      if (isOutOfBounds(newX, newY, grid)) {
        return false;
      }
      if (grid[newX][newY] != word.charAt(j + 1)) {
        return false;
      }
    }
    return true;
  }

  private boolean isOutOfBounds(int x, int y, char[][] grid) {
    return (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length);
  }

  @Override
  public void part2() {
    var lines = readFile("04.txt").split("\n");
    char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
    int counter = 0;

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (checkPoint2(i, j, map)) {
          counter++;
        }
      }
    }
    System.out.println(counter);
  }

  private List<String> winningString = Arrays.asList(
      "SMSM", "MSMS", "SSMM", "MMSS");

  private boolean checkPoint2(int x, int y, char[][] grid) {
    if (grid[x][y] != 'A') {
      // we only need to start on points with starting letters
      return false;
    }
    var sb = new StringBuilder();

    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        var newX = x + j;
        var newY = y + i;
        if (isOutOfBounds(newX, newY, grid)) {
          return false;
        }
        sb.append((grid[newX][newY]));
      }
    }
    return winningString.contains(sb.toString());
  }

}
