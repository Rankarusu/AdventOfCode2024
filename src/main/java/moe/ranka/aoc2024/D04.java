package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;

public class D04 extends Day {

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
        var lines = readFile("04_test.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        

    for(

    int i = 0;i<map.length;i++)
    {
        for (int j = 0; j < map[0].length; j++) {

        }
    }
    }

    private boolean checkPoint(int[][]xy){
        for (int[][] i : offsets) {
            
        }
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
