package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

public class Day03 implements Day {

	@Override
	public String part1(String input, Object... params) {
		char[][] map = tableOfChars(input);
		int height = height(map);
		int width = width(map);
		int i = 0, j = 0;
		int numberOfTrees = 0;
		while(i < height) {
			if(map[i][j] == '#') numberOfTrees++;
			i++;
			j = (j + 3) % width;
		}
		return String.valueOf(numberOfTrees);
	}
	
}
