package moe.ranka.aoc2024;

import java.util.Arrays;

public class D06 extends Day {

    enum Direction {
        UP, RIGHT, DOWN, LEFT;

        private static final Direction[] vals = values();

        public Direction next() {
            return vals[(this.ordinal() + 1) % vals.length];
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
        // TODO Auto-generated method stub

    }

}
