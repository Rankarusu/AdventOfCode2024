package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D07 extends Day {

    char[] operators = { '+', '*', '|' };

    @Override
    public void part1() {
        var file = this.readFile("07.txt").split("\n");
        long res = 0;
        for (String line : file) {
            var split = line.split(":");
            long testValue = Long.parseLong(split[0]);
            long[] operands = Arrays.stream(split[1].strip().split(" ")).mapToLong(Long::parseLong).toArray();
            if (isValid(operands, testValue)) {
                res += testValue;
            }
        }
        System.out.println(res);

    }

    private boolean isValid(long[] operands, long testValue) {

        List<List<Character>> premutations = new ArrayList<>();
        generatePermutations(operators, new ArrayList<>(), operands.length - 1, premutations);
        for (List<Character> perm : premutations) {
            long res = operands[0];
            int i = 1;
            for (Character op : perm) {
                if (op == '*') {
                    res *= operands[i];
                } else {
                    res += operands[i];
                }
                i++;
            }
            if (testValue == res) {
                return true;
            }
        }
        return false;

    }

    public void generatePermutations(char[] chars, List<Character> current, int maxLength,
            List<List<Character>> result) {
        if (current.size() == maxLength) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (char c : chars) {
            current.add(c);
            generatePermutations(chars, current, maxLength, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    @Override
    public void part2() {
        var file = this.readFile("07.txt").split("\n");
        long res = 0;
        for (String line : file) {
            var split = line.split(":");
            long testValue = Long.parseLong(split[0]);
            long[] operands = Arrays.stream(split[1].strip().split(" ")).mapToLong(Long::parseLong).toArray();
            if (isValid2(operands, testValue)) {
                res += testValue;
            }
        }
        System.out.println(res);

    }

    private boolean isValid2(long[] operands, long testValue) {

        List<List<Character>> permutations = new ArrayList<>();
        generatePermutations(operators, new ArrayList<>(), operands.length - 1, permutations);

        for (List<Character> perm : permutations) {
            long res = operands[0];
            int i = 1;
            for (Character op : perm) {
                if (op == '*') {
                    res *= operands[i];
                } else if (op == '+') {
                    res += operands[i];
                } else {
                    res = Long.parseLong(new StringBuilder().append(res).append(operands[i]).toString());
                }
                i++;
            }
            if (testValue == res) {
                return true;
            }
        }
        return false;

    }
}
