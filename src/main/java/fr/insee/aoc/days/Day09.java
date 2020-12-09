package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import java.util.Arrays;

import fr.insee.aoc.utils.DayException;

public class Day09 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var preambleSize = (int) params[0];
		var numbers = streamOfLines(input).mapToLong(Long::parseLong).toArray();
		for(var n = preambleSize + 1; n < numbers.length; n ++) {
			var number = numbers[n];
			var valid = false;
			for(var i = n - preambleSize; i < n; i ++) {
				if(!valid) {
					for(var j = i + 1; j < n; j ++) {
						if(numbers[i] + numbers[j] == number) {
							valid = true;
						}
					}	
				}
			}
			if(!valid) {
				return String.valueOf(number);
			}
		}
		throw new DayException();
	}
	
	@Override
	public String part2(String input, Object... params) {
		var invalidNumber = (long) params[0];
		var numbers = streamOfLines(input).mapToLong(Long::parseLong).toArray();
		for(var n = 0; n < numbers.length; n ++) {
			var i = n;
			long sum = 0;
			while (sum < invalidNumber) {
				sum += numbers[i];
				if(sum == invalidNumber) {
					var range = Arrays.copyOfRange(numbers, n, i);
					var summary = Arrays.stream(range).summaryStatistics();
					return String.valueOf(summary.getMin() + summary.getMax());
				}
				i ++;
			}
		}
		throw new DayException();
	}
}
