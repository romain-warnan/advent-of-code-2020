package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.Arrays;

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
	
	@Override
	public String part2(String input, Object... params) {
		var sumOfCounts = streamOfLines(input)
				.collect(new BlocCollector(" "))
				.stream()
				.map(s -> s.split(" "))
				.mapToInt(Day06::commonsChars)
				.sum();
		return String.valueOf(sumOfCounts);
	}
	
	private static long differentChars(String string) {
		return string.chars().distinct().count();
	}
	
	private static int commonsChars(String[] tokens) {
		int n = 0;
		for(int i = 0; i < tokens[0].length(); i ++) {
			char c = tokens[0].charAt(i);
			if(Arrays.stream(tokens).allMatch(t -> t.contains(String.valueOf(c)))) {
				n ++;
			}
		}
		return n;
	}
}
