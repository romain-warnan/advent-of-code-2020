package fr.insee.aoc.days;

import static com.codepoetics.protonpack.StreamUtils.takeUntil;
import static fr.insee.aoc.utils.Days.*;

import com.codepoetics.protonpack.StreamUtils;
import fr.insee.aoc.utils.DayException;

public class Day19 implements Day {

	@Override
	public String part1(String input, Object... params) {
		takeUntil(streamOfLines(input), String::isBlank)
				.map(line -> line.split(":"));
		throw new DayException();
	}

	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
}
