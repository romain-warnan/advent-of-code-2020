package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import fr.insee.aoc.utils.DayException;
import org.apache.commons.lang3.StringUtils;

import java.util.function.ToLongFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day18 implements Day {

	private static final Pattern pattern = Pattern.compile("\\(([0-9+* ]+)\\)");

	@Override
	public String part1(String input, Object... params) {
		var sum = streamOfLines(input)
				.mapToLong(line -> evaluate(line, this::calculate1))
				.sum();
		return String.valueOf(sum);
	}

	@Override
	public String part2(String input, Object... params) {
		var sum = streamOfLines(input)
				.mapToLong(line -> evaluate(line, this::calculate2))
				.sum();
		return String.valueOf(sum);
	}

	long evaluate(String line, ToLongFunction<String> calculate) {
		String string = line;
		var matcher = pattern.matcher(string);
		while(matcher.find()) {
			string = matcher.replaceAll(m -> String.valueOf(calculate.applyAsLong(m.group())));
			matcher = pattern.matcher(string);
		}
		return calculate.applyAsLong(string);
	}

	long calculate1(String expression) {
		var numbers = numbers(expression);
		var operators = operators(expression);
		long result = numbers[0];
		for(var i = 0; i < operators.length; i ++) {
			var c = operators[i];
			if(c == '+') {
				result += numbers[i + 1];
			}
			else {
				result *= numbers[i + 1];
			}
		}
		return result;
	}

	long calculate2(String expression) {
		var numbers = numbers(expression);
		var operators = operators(expression);
		for(var i = 0; i < operators.length; i ++) {
			var c = operators[i];
			if(c == '+') {
				numbers[i + 1] = numbers[i] + numbers[i + 1];
				numbers[i] = 1;
			}
		}
		return LongStream.of(numbers).reduce(1, (a, b) -> a * b);
	}

	static int[] operators(String expression) {
		return Stream.of(expression.split("\\(|\\)| ?\\d ?"))
				.filter(StringUtils::isNotBlank)
				.mapToInt(s -> s.charAt(0))
				.toArray();
	}

	static long[] numbers(String expression) {
		return Stream.of(expression.split("\\(|\\)| \\+ | \\* "))
				.filter(StringUtils::isNotBlank)
				.mapToLong(Long::parseLong)
				.toArray();
	}
}
