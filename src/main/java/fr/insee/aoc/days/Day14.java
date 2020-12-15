package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.arrayOfLines;
import static fr.insee.aoc.utils.Days.readLong;
import static java.lang.Long.parseLong;
import static java.lang.Long.toBinaryString;
import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.util.*;
import java.util.regex.Pattern;

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
					mem.put(key, applyValueMask(mask, value));
				}
			}
		}
		var sum =  mem.values().stream().mapToLong(Long::longValue).sum();
		return String.valueOf(sum);
	}
	
	@Override
	public String part2(String input, Object... params) {
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
					applyMemMask(mask, key).forEach(n -> mem.put(n, value));
				}
			}
		}
		var sum =  mem.values().stream().mapToLong(Long::longValue).sum();
		return String.valueOf(sum);
	}

	static long applyValueMask(String mask, long value) {
		var binary = leftPad(toBinaryString(value), 36, '0').toCharArray();
		for(int n = 0; n < 36; n ++) {
			char m = mask.charAt(n);
			if(m != 'X') {
				binary[n] = m;
			}
		}
		return parseLong(new String(binary), 2);
	}
	
	static List<Long> applyMemMask(String mask, long mem) {
		var binary = leftPad(toBinaryString(mem), 36, '0').toCharArray();
		for(int n = 0; n < 36; n ++) {
			char m = mask.charAt(n);
			if(m == 'X') {
				binary[n] = 'X';
			}
			else if(m == '1') {
				binary[n] = '1';
			}
		}
		return duplicate(singletonList(new String(binary)))
			.stream()
			.map(s -> parseLong(s, 2))
			.collect(toList());
	}
	
	static List<String> duplicate(List<String> strings) {
		if(strings.stream().noneMatch(s -> s.contains("X"))) {
			return strings;
		}
		var duplicates = new ArrayList<String>();
		for(var string : strings) {
			if(string.contains("X")) {
				duplicates.add(string.replaceFirst("X", "0"));
				duplicates.add(string.replaceFirst("X", "1"));
			}
			else {
				duplicates.add(string);
			}
		}
		return duplicate(duplicates);
	}
	
}
