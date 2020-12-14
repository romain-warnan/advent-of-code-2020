package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import java.util.Arrays;

import fr.insee.aoc.utils.DayException;

public class Day13 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var notes = arrayOfLines(input);
		var timestamp = Integer.parseInt(notes[0]);
		var minutes = Arrays.stream(notes[1].split(","))
				.filter(s -> !s.equals("x"))
				.mapToInt(Integer::parseInt)
				.toArray();
		var minId = 0;
		var minDelta = Integer.MAX_VALUE;
		for(int i = 0; i < minutes.length; i ++) {
			var minute = minutes[i];
			var delta = delta(minute, timestamp);
			if(delta < minDelta) {
				minDelta = delta;
				minId = minute;
			}
		}
		return String.valueOf(minId * minDelta);
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
