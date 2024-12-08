package moe.ranka.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D06 extends Day {
    // this is so scuffed. 
    // my first idea was to track each point visited including the current direction and tryying to run from there until we encounter the start point again
    // but i couldnt get it to work. thats why i just brute force here.

    enum Direction {
        UP, RIGHT, DOWN, LEFT;

        private static final Direction[] vals = values();

        public Direction next() {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

    class Point {
        int x;
        int y;
        Direction dir;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
            Point other = (Point) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        private D06 getEnclosingInstance() {
            return D06.this;
        }

    }

    @Override
    public void part1() {
        var lines = readFile("06.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        int x = 0;
        int y = 0;
        int visited = 1;
        Direction dir = Direction.UP;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] == '^') {
                    x = i;
                    y = j;
                }
            }
        }

        try {
            while (true) {
                if (map[y][x] == '.') {
                    visited++;
                }
                map[y][x] = 'X';
                switch (dir) {
                    case UP:
                        if (map[y - 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y--;
                        }
                        break;
                    case LEFT:
                        if (map[y][x - 1] == '#') {
                            dir = dir.next();
                        } else {
                            x--;
                        }
                        break;
                    case DOWN:
                        if (map[y + 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y++;

                        }
                        break;
                    case RIGHT:
                        if (map[y][x + 1] == '#') {
                            dir = dir.next();
                        } else {
                            x++;
                        }
                        break;
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(visited);
        }

    }

    @Override
    public void part2() {
        var lines = readFile("06.txt").split("\n");
        char[][] map = Arrays.stream(lines).map(String::toCharArray).toArray(char[][]::new);
        int x = 0;
        int y = 0;
        int startX = 0;
        int startY = 0;
        Set<Point> visited = new HashSet<>();

        Direction dir = Direction.UP;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] == '^') {
                    x = i;
                    startX = i;
                    y = j;
                    startY = j;
                }
            }
        }
        try {
            while (true) {
                visited.add(new Point(x, y));
                switch (dir) {
                    case UP:
                        if (map[y - 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y--;
                        }
                        break;
                    case LEFT:
                        if (map[y][x - 1] == '#') {
                            dir = dir.next();
                        } else {
                            x--;
                        }
                        break;
                    case DOWN:
                        if (map[y + 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y++;
                        }
                        break;
                    case RIGHT:
                        if (map[y][x + 1] == '#') {
                            dir = dir.next();
                        } else {
                            x++;
                        }
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        int res = 0;
        List<Point> a = new ArrayList<>();

        for (Point point : visited) {
            char[][] mapCopy = new char[map.length][];
            for (int k = 0; k < map.length; k++) {
                mapCopy[k] = map[k].clone();
            }
            mapCopy[point.y][point.x] = '#';
            if (testLoop(point, startX, startY, mapCopy)) {
                res++;
                a.add(point);
            }
        }
        System.out.println(res);

    }

    private boolean testLoop(Point point, int startX, int startY, char[][] map) {

        int x = startX;
        int y = startY;
        int steps = 0;
        Direction dir = Direction.UP;
        try {
            while (true) {
                steps++;
                switch (dir) {
                    case UP:
                        if (map[y - 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y--;
                        }
                        break;
                    case LEFT:
                        if (map[y][x - 1] == '#') {
                            dir = dir.next();
                        } else {
                            x--;
                        }
                        break;
                    case DOWN:
                        if (map[y + 1][x] == '#') {
                            dir = dir.next();
                        } else {
                            y++;
                        }
                        break;
                    case RIGHT:
                        if (map[y][x + 1] == '#') {
                            dir = dir.next();
                        } else {
                            x++;
                        }
                        break;
                }

                if (steps > 30000) {
                    return true;
                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

}
