package moe.ranka.aoc2024;

import java.util.Arrays;

public class D02 extends Day {

    @Override
    public void part1() {
        var file = this.readFile("02.txt");
        var readings = file.split("\n");
        int safeReports = 0;

        for (String reading : readings) {
            var levels = Arrays.stream(reading.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (analyzeLevels(levels)){
                safeReports++;
            }
        }
        System.out.println(safeReports);
    }

    private boolean analyzeLevels(int[] levels) {
        int lastDiff = 0;
        for (int i = 0; i < levels.length - 1; i++) {
            int current = levels[i];
            int next = levels[i + 1];
            int diff = next - current;
            int tempLastDiff = lastDiff;
            int absDiff = Math.abs(diff);

            lastDiff = diff;

            if (absDiff > 3 || absDiff < 1){
                return false;
            }
            if ((diff > 0 && tempLastDiff < 0) || (diff < 0 && tempLastDiff > 0)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void part2() {
        // TODO Auto-generated method stub

    }

}
