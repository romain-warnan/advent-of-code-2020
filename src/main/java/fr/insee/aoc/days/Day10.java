package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import java.util.Arrays;

import fr.insee.aoc.utils.DayException;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var adapters = streamOfInt(input).sorted().toArray();
		var delta1 = adapters[0] == 1 ? 1 : 0;
		var delta3 = adapters[0] == 3 ? 2 : 1;
		for(int i = 1; i < adapters.length; i ++) {
			if(adapters[i] - adapters[i - 1] == 1) {
				delta1 ++;
			}
			else if(adapters[i] - adapters[i - 1] == 3) {
				delta3 ++;
			}
		}
		return String.valueOf(delta1 * delta3);
	}
	
	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
}
