package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class D01 extends Day {

    @Override
    public void part1() {
        var file = this.readFile("01.txt");
        var lines = file.split("\n");
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        var result = 0;

        for (String line : lines) {
            var numbers = line.split("\s+");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        }

        left.sort(null);
        right.sort(null);

        for (int i = 0; i < left.size(); i++) {
            var l = left.get(i);
            var r = right.get(i);
            result += (Math.abs(r - l));
        }

        System.out.println(result);

    }

    @Override
    public void part2() {
        var file = this.readFile("01.txt");
        var lines = file.split("\n");
        var left = new ArrayList<Integer>();
        Map<Integer, Integer> right = new HashMap<>();

        var result = 0;

        for (String line : lines) {
            var numbers = line.split("\s+");
            left.add(Integer.parseInt(numbers[0]));
            right.computeIfPresent(Integer.parseInt(numbers[1]), (k, v) -> v + 1);
            right.putIfAbsent(Integer.parseInt(numbers[1]), 1);
        }

        for (Integer item : left) {
            result += item * right.getOrDefault(item, 0);
        }

        System.out.println(result);

    }

}
