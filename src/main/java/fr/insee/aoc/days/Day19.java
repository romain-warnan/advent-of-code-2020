package fr.insee.aoc.days;

import static com.codepoetics.protonpack.StreamUtils.skipUntilInclusive;
import static com.codepoetics.protonpack.StreamUtils.takeUntil;
import static fr.insee.aoc.utils.Days.*;

import fr.insee.aoc.utils.DayException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day19 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var rules = takeUntil(streamOfLines(input), String::isBlank)
				.map(line -> line.split(":"))
				.collect(Collectors.toMap(
						tokens -> Integer.parseInt(tokens[0]),
						tokens -> new Rule(tokens[1])));
		var regex = rules.get(0).apply(rules);
		var pattern = Pattern.compile(regex);
		var count = skipUntilInclusive(streamOfLines(input), String::isBlank)
				.map(pattern::matcher)
				.filter(Matcher::matches)
				.count();
		return String.valueOf(count);
	}


	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}

	static class Rule {
		SimpleRule first, second;

		Rule(String string) {
			var tokens = string.trim().split("\\|");
			first = new SimpleRule(tokens[0]);
			if(tokens.length > 1) {
				second = new SimpleRule(tokens[1]);
			}
		}

		String apply(Map<Integer, Rule> rules) {
			if(this.isComplex()) {
				return "(" + first.apply(rules) + "|" + second.apply(rules) + ")";
			}
			return first.apply(rules);
		}

		boolean isComplex() {
			return second != null;
		}
	}

	static class SimpleRule {
		int first = -1, second = -1;
		char c;

		String apply(Map<Integer, Rule> rules) {
			if(this.isTerminal()) {
				return "" + c;
			}
			return rules.get(first).apply(rules) + (second < 0 ? "" : rules.get(second).apply(rules));
		}

		SimpleRule(String string) {
			if(string.contains("\"")) {
				c = string.charAt(1);
			}
			else {
				var tokens = string.trim().split(" ");
				first = Integer.parseInt(tokens[0]);
				if(tokens.length > 1) {
					second = Integer.parseInt(tokens[1]);
				}
			}
		}

		boolean isTerminal() {
			return c == 'a' || c == 'b';
		}
	}
}
