package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfLines;

import fr.insee.aoc.utils.BlocCollector;

public class Day06 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var sumOfCounts = streamOfLines(input)
			.collect(new BlocCollector())
			.stream()
			.mapToLong(Day06::differentChars)
			.sum();
		return String.valueOf(sumOfCounts);
	}
	
	private static long differentChars(String string) {
		return string.chars().distinct().count();
	}
}
