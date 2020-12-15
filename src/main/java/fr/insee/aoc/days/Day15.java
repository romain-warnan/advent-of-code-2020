package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import fr.insee.aoc.utils.DayException;

import java.util.*;

public class Day15 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var threshold = (int) params[0];
		var startingNumbers = Arrays.stream(input.split(","))
				.mapToInt(Integer::parseInt)
				.toArray();
		var numberOfStarting = startingNumbers.length;
		var indexes = indexes(startingNumbers);
		var rank = numberOfStarting + 1;
		var lastNumber = -1;
		while (rank <= threshold) {
			lastNumber = nextNumber(lastNumber, rank, indexes);
			rank ++;
		}
		return String.valueOf(lastNumber);
	}

	@Override
	public String part2(String input, Object... params) {
		return part1(input, params);
	}

	static Map<Integer, Integer> indexes(int[] startingNumbers) {
		var indexes = new HashMap<Integer, Integer>();
		for(var i = 0; i < startingNumbers.length; i ++) {
			var number = startingNumbers[i];
			indexes.put(number, i + 1);
		}
		return indexes;
	}

	int nextNumber(Integer lastNumber, int rank, Map<Integer, Integer> indexes) {
		var index = indexes.get(lastNumber);
		indexes.put(lastNumber, rank - 1);
		if(index != null) {
			return rank - index - 1;
		}
		return 0;
	}
}
