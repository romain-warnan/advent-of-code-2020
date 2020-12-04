package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readChar;
import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.readString;
import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.regex.Pattern;

public class Day02 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var numberOfValidPasswords = streamOfLines(input)
				.map(CorporatePolicy::from)
				.filter(CorporatePolicy::isValidOld)
				.count();
		return String.valueOf(numberOfValidPasswords);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var numberOfValidPasswords = streamOfLines(input)
				.map(CorporatePolicy::from)
				.filter(CorporatePolicy::isValidNew)
				.count();
		return String.valueOf(numberOfValidPasswords);
	}
	
	private static class CorporatePolicy {
		private char letter;
		private int min, max;
		private String password;
		
		private static final Pattern regex = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");
		
		public static CorporatePolicy from(String line) {
			var policy = new CorporatePolicy();
			var matcher = regex.matcher(line);
			if(matcher.matches()) {
				policy.min = readInt(1, matcher);
				policy.max = readInt(2, matcher);
				policy.letter = readChar(3, matcher);
				policy.password = readString(4, matcher);
			}
			return policy;
		}
		
		public boolean isValidOld() {
			var n = password.chars().filter(c -> c == letter).count();
			return min <= n && n <= max;
		}
		
		public boolean isValidNew() {
			return password.charAt(min - 1) == letter ^ password.charAt(max - 1) == letter;
		}
	}
}
