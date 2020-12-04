package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.height;
import static fr.insee.aoc.utils.Days.tableOfChars;
import static fr.insee.aoc.utils.Days.width;

import java.util.stream.Stream;

public class Day03 implements Day {

	@Override
	public String part1(String input, Object... params) {
		char[][] map = tableOfChars(input);
		int numberOfTrees = Slope.of(3, 1).slideOn(map);
		return String.valueOf(numberOfTrees);
	}

	@Override
	public String part2(String input, Object... params) {
		char[][] map = tableOfChars(input);
		int multiply = Stream
			.of(Slope.of(1, 1), Slope.of(3, 1), Slope.of(5, 1), Slope.of(7, 1), Slope.of(1, 2))
			.mapToInt(s -> s.slideOn(map))
			.reduce(1, (a, b) -> a * b);
		return String.valueOf(multiply);
	}

	private static class Slope {
		private int right, down;
		
		public static Slope of(int right, int down) {
			var slope = new Slope();
			slope.right = right;
			slope.down = down;
			return slope;
		}
		
		public int slideOn(char[][] map) {
			int height = height(map);
			int width = width(map);
			int i = 0, j = 0;
			int numberOfTrees = 0;
			while(i < height) {
				if(map[i][j] == '#') numberOfTrees++;
				i += down;
				j = (j + right) % width;
			}
			return numberOfTrees;
		}
	}
}
