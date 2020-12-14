package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.arrayOfLines;
import static fr.insee.aoc.utils.Days.readLong;
import static java.lang.Long.parseLong;
import static java.lang.Long.toBinaryString;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.HashMap;
import java.util.regex.Pattern;

import fr.insee.aoc.utils.DayException;

public class Day14 implements Day {

	static final Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

	@Override
	public String part1(String input, Object... params) {
		var lines = arrayOfLines(input);
		var mem = new HashMap<Long, Long>();
		var mask = "";
		for(var line : lines) {
			if(line.startsWith("mask")) {
				mask = line.substring(7);
			}
			else {
				var matcher = pattern.matcher(line);
				if(matcher.matches()) {
					var key = readLong(1, matcher);
					var value = readLong(2, matcher);
					mem.put(key, applyMask(mask, value));
				}
			}
		}
		var sum =  mem.values().stream().mapToLong(Long::longValue).sum();
		return String.valueOf(sum);
	}

	static long applyMask(String mask, long value) {
		var binary = leftPad(toBinaryString(value), 36, '0').toCharArray();
		for(int n = 0; n < 36; n ++) {
			char m = mask.charAt(n);
			if(m != 'X') {
				binary[n] = m;
			}
		}
		return parseLong(new String(binary), 2);
	}
	
	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
	
}
