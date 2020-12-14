package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.math3.util.Pair;

import fr.insee.aoc.utils.DayException;

public class Day13 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var notes = arrayOfLines(input);
		var timestamp = Integer.parseInt(notes[0]);
		var pair = Arrays.stream(notes[1].split(","))
				.filter(s -> !s.equals("x"))
				.mapToInt(Integer::parseInt)
				.mapToObj(i -> new Pair<Integer, Integer>(i, delta(i, timestamp)))
				.min(comparingInt(p -> p.getSecond()))
				.orElseThrow();
		return String.valueOf(pair.getFirst() * pair.getSecond());
	}

	int delta(int minute, int timestamp) {
		var n = 0;
		while(minute * n < timestamp) {
			n ++;
		}
		return (minute * n)  - timestamp;
	}
	
	
	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
}
