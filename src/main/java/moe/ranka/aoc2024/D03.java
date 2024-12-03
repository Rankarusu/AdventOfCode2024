package moe.ranka.aoc2024;

import java.util.regex.Pattern;

public class D03 extends Day {

  @Override
  public void part1() {
    var file = readFile("03.txt");
    var p = Pattern.compile("mul\\((?<num1>\\d{1,3}),(?<num2>\\d{1,3})\\)");
    var m = p.matcher(file);

    int sum = 0;

    while (m.find()) {
      var num1 = m.group("num1");
      var num2 = m.group("num2");
      sum += (Integer.parseInt(num1) * Integer.parseInt(num2));

    }

    System.out.println(sum);
  }

  @Override
  public void part2() {
    var file = readFile("03.txt");
    var p = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((?<num1>\\d{1,3}),(?<num2>\\d{1,3})\\)");
    var m = p.matcher(file);

    int sum = 0;
    boolean enabled = true;

    while (m.find()) {
      var match = m.group(0);

      var num1 = m.group("num1");
      var num2 = m.group("num2");
      if (match.equals("do()")) {
        enabled = true;
      }
      if (match.equals("don't()")) {
        enabled = false;
      }

      if (enabled && num1 != null && num2 != null) {
        sum += (Integer.parseInt(num1) * Integer.parseInt(num2));
      }
    }
    System.out.println(sum);
  }

}
