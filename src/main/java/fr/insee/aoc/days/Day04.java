package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.readString;
import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.regex.Pattern;

import fr.insee.aoc.utils.BlocCollector;
import fr.insee.aoc.utils.DayException;

public class Day04 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var numberOfValidPassport = streamOfLines(input)
				.collect(new BlocCollector(" "))
				.stream()
				.map(Passport::from)
				.filter(Passport::hasAllFields)
				.count();
		return String.valueOf(numberOfValidPassport);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var numberOfValidPassport = streamOfLines(input)
				.collect(new BlocCollector(" "))
				.stream()
				.map(Passport::from)
				.filter(Passport::hasAllFields)
				.filter(Passport::hasValidFields)
				.count();
		return String.valueOf(numberOfValidPassport);
	}

	private static class Passport {
		private String byr, iyr, eyr, hgt, hcl, ecl, pid;
		private static final Pattern
			byrPattern = Pattern.compile("\\d{4}"),
			iyrPattern = Pattern.compile("\\d{4}"),
			eyrPattern = Pattern.compile("\\d{4}"),
			hgtPattern = Pattern.compile("(\\d+)(cm|in)"),
			hclPattern = Pattern.compile("#[0-9a-f]{6}"),
			eclPattern = Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)"),
			pidPattern = Pattern.compile("\\d{9}");
		
		static Passport from(String line) {
			var passport = new Passport();
			var tokens = line.split(" ");
			for(String token : tokens) {
				if(token.contains(":")) {
					readToken(passport, token);
				}
				
			}
			return passport;
		}

		private boolean byrCheck() {
			var matcher = byrPattern.matcher(byr);
			if(matcher.matches()) {
				int n = readInt(0, matcher);
				return 1920 <= n && n <= 2002;
			}
			return false;
		}
		
		private boolean iyrCheck() {
			var matcher = iyrPattern.matcher(iyr);
			if(matcher.matches()) {
				int n = readInt(0, matcher);
				return 2010 <= n && n <= 2020;
			}
			return false;
		}
		
		private boolean eyrCheck() {
			var matcher = eyrPattern.matcher(eyr);
			if(matcher.matches()) {
				int n = readInt(0, matcher);
				return 2020 <= n && n <= 2030;
			}
			return false;
		}
		
		private boolean hgtCheck() {
			var matcher = hgtPattern.matcher(hgt);
			if(matcher.matches()) {
				int n = readInt(1, matcher);
				var unit = readString(2, matcher);
				if(unit.equals("cm")) {
					return 150 <= n && n <= 193;
				}
				else {
					return 59 <= n && n <= 76;
				}
			}
			return false;
		
		}
		
		private boolean hclCheck() {
			var matcher = hclPattern.matcher(hcl);
			return matcher.matches();
		}
		
		private boolean eclCheck() {
			var matcher = eclPattern.matcher(ecl);
			return matcher.matches();
		}
		
		private boolean pidCheck() {
			var matcher = pidPattern.matcher(pid);
			return matcher.matches();
		}
		
		private static void readToken(Passport passport, String token) {
			var item = token.split(":");
			var key = item[0];
			var value = item[1];
			switch (key) {
			case "byr":
				passport.byr = value;
				break;
			case "iyr":
				passport.iyr = value;
				break;
			case "eyr":
				passport.eyr = value;
				break;
			case "hgt":
				passport.hgt = value;
				break;
			case "hcl":
				passport.hcl = value;
				break;
			case "ecl":
				passport.ecl = value;
				break;
			case "pid":
				passport.pid = value;
				break;
			case "cid":
				break;
			default:
				throw new DayException("Code inconnu : " + key);
			}
		}
	
		boolean hasAllFields() {
			return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null;
		}
		
		
		boolean hasValidFields() {
			return byrCheck() && iyrCheck() && eyrCheck() && hgtCheck() && hclCheck() && eclCheck() && pidCheck();
		}
	}
}
