package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class D05 extends Day {

    @Override
    public void part1() {
        var file = this.readFile("05.txt").split("\n\n");
        List<int[]> rules = List.of(file[0].split("\n"))
                .stream()
                .map(x -> Arrays.stream(x.split("\\|"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .collect(Collectors.toList());
        List<List<Integer>> updates = List.of(file[1].split("\n"))
                .stream()
                .map(x -> Arrays.stream(x.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();

        for (List<Integer> update : updates) {
            if (checkRules(update, rules)) {
                res.add(update.get((update.size() - 1) / 2));
            }
        }

        System.out.println(res.stream().mapToInt(Integer::valueOf).sum());
    }

    private boolean checkRules(List<Integer> update, List<int[]> rules) {
        for (int[] rule : rules) {
            var a = update.indexOf(rule[0]);
            var b = update.indexOf(rule[1]);

            if (a != -1 && b != -1 && b < a) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void part2() {
        var file = this.readFile("05.txt").split("\n\n");
        List<int[]> rules = List.of(file[0].split("\n"))
                .stream()
                .map(x -> Arrays.stream(x.split("\\|"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .collect(Collectors.toList());
        List<List<Integer>> updates = List.of(file[1].split("\n"))
                .stream()
                .map(x -> Arrays.stream(x.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();

        for (List<Integer> update : updates) {
            if (!checkRules(update, rules)) {
                update.sort(
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                for (int[] rule : rules) {
                                    if (rule[0] == b && rule[1] == a) {
                                        return 1;
                                    }
                                }
                                return -1;
                            }
                        });
                res.add(update.get((update.size() - 1) / 2));
            }
        }
        System.out.println(res.stream().mapToInt(Integer::valueOf).sum());
    }

}
