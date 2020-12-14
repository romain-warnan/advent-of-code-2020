package fr.insee.aoc.days;

import static com.codepoetics.protonpack.Indexed.index;
import static com.codepoetics.protonpack.StreamUtils.zipWithIndex;
import static fr.insee.aoc.utils.Days.arrayOfLines;
import static java.lang.Long.parseLong;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.Pair;

import com.codepoetics.protonpack.Indexed;

public class Day13 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var notes = arrayOfLines(input);
		var timestamp = Integer.parseInt(notes[0]);
		var pair = Arrays.stream(notes[1].split(","))
				.filter(s -> !s.equals("x"))
				.mapToInt(Integer::parseInt)
				.mapToObj(i -> new Pair<Integer, Integer>(i, delta(i, timestamp)))
				.min(comparingInt(Pair::getSecond))
				.orElseThrow();
		return String.valueOf(pair.getFirst() * pair.getSecond());
	}
	
	@Override
	public String part2(String input, Object... params) {
		var notes = arrayOfLines(input);
		var buses = zipWithIndex(Arrays.stream(notes[1].split(",")))
			.filter(s -> !s.getValue().equals("x"))
			.map(i -> index(i.getIndex(), parseLong(i.getValue())))
			.sorted(comparingLong(Indexed::getValue))
			.collect(toList());
		long n = 1;
		long time = 0;
		for (var bus : buses) {
			while ((time + bus.getIndex()) % bus.getValue() != 0) {
                time += n;
            }
            n = ArithmeticUtils.lcm(n, bus.getValue());
		}
		return String.valueOf(time);
	}

	int delta(int minute, int timestamp) {
		var n = 0;
		while(minute * n < timestamp) {
			n ++;
		}
		return (minute * n)  - timestamp;
	}
}
