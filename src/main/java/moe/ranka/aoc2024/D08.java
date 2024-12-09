package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D08 extends Day {

    class Node {
        public int x;
        public int y;
        public char signal;

        public Node(int x, int y, char signal) {
            this.x = x;
            this.y = y;
            this.signal = signal;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;

            return true;
        }

        public List<Node> getAntiNodeLocations(Node node) {
            if (node.signal != signal) {
                throw new RuntimeException("signals nor equal");
            }

            List<Node> res = new ArrayList<>();

            int diffX = x - node.x;
            int diffY = y - node.y;
            res.add(new Node(node.x - diffX, node.y - diffY, signal));
            res.add(new Node(x + diffX, y + diffY, signal));
            return res;
        }

        public List<Node> getAntiNodeLocations2(Node node, char[][] map) {
            if (node.signal != signal) {
                throw new RuntimeException("signals nor equal");
            }

            List<Node> res = new ArrayList<>();
            res.add(this);
            res.add(node);

            int i = 1;
            while (true) {

                int diffY = (y - node.y) * i;
                int diffX = (x - node.x) * i;

                if (isOutOfBounds(node.x - diffX, node.y - diffY, map)
                        && isOutOfBounds(x + diffX, y + diffY, map)) {
                    break;
                }
                if (!isOutOfBounds(node.x - diffX, node.y - diffY, map)) {

                    res.add(new Node(node.x - diffX, node.y - diffY, signal));
                }

                if (!isOutOfBounds(x + diffX, y + diffY, map)) {
                    res.add(new Node(x + diffX, y + diffY, signal));

                }
                i++;
            }
            return res;
        }

        private D08 getEnclosingInstance() {
            return D08.this;
        }

    }

    @Override
    public void part1() {
        var lines = this.readFile("08.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        Map<Character, List<Node>> nodes = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char signal = map[j][i];
                if (signal != '.') {
                    nodes.putIfAbsent(signal, new ArrayList<>());
                    nodes.get(signal).add(new Node(i, j, signal));
                }
            }
        }
        Set<Node> antiNodes = new HashSet<>();
        for (Character key : nodes.keySet()) {
            var values = nodes.get(key);
            for (int i = 0; i < values.size(); i++) {
                for (int j = i + 1; j < values.size(); j++) {
                    Node a = values.get(i);
                    Node b = values.get(j);
                    antiNodes.addAll(a.getAntiNodeLocations(b));
                }
            }
        }
        var res = antiNodes.stream().filter(x -> !isOutOfBounds(x.x, x.y, map)).toList();
        // for (Node node : res) {
        //     map[node.y][node.x] = '#';
        // }
        // for (int i = 0; i < map.length; i++) {
        //     System.out.println(String.valueOf(map[i]));
        // }
        System.out.println(res.size());
    }

    private boolean isOutOfBounds(int x, int y, char[][] grid) {
        return (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length);
    }

    @Override
    public void part2() {
        var lines = this.readFile("08.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        Map<Character, List<Node>> nodes = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char signal = map[j][i];
                if (signal != '.') {
                    nodes.putIfAbsent(signal, new ArrayList<>());
                    nodes.get(signal).add(new Node(i, j, signal));
                }
            }
        }
        Set<Node> antiNodes = new HashSet<>();
        for (Character key : nodes.keySet()) {
            var values = nodes.get(key);
            for (int i = 0; i < values.size(); i++) {
                for (int j = i + 1; j < values.size(); j++) {
                    Node a = values.get(i);
                    Node b = values.get(j);
                    antiNodes.addAll(a.getAntiNodeLocations2(b, map));
                }
            }
        }
        var res = antiNodes.stream().filter(x -> !isOutOfBounds(x.x, x.y, map)).toList();
        // for (Node node : res) {
        //     map[node.y][node.x] = '#';
        // }
        // for (int i = 0; i < map.length; i++) {
        //     System.out.println(String.valueOf(map[i]));
        // }
        System.out.println(res.size());

    }

}
