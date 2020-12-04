package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import fr.insee.aoc.utils.DayException;

public class Day04 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var numberOfValidPassport = streamOfLines(input)
				.collect(new BlocCollector())
				.stream()
				.map(Passport::from)
				.filter(Passport::isValid)
				.count();
		return String.valueOf(numberOfValidPassport);
	}

	private static class Passport {
		private String byr, iyr, eyr, hgt, hcl, ecl, pid, cid;
		
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
				passport.cid = value;
				break;
			default:
				throw new DayException("Code inconnu : " + key);
			}
		}
	
		boolean isValid() {
			return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null;
		}
	}
	
	private static class BlocCollector implements Collector<String, List<String>, List<String>> {
		
		@Override
		public Supplier<List<String>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<String>, String> accumulator() {
			return (lines, line) -> {
				if(lines.isEmpty()) {
					lines.add("");
				}
				if(line.isBlank()) {
					lines.add("");
				}
				else {
					int lastIndex = lines.size() - 1;
					lines.set(lastIndex, lines.get(lastIndex) + " " + line);
				}
			};
		}

		@Override
		public BinaryOperator<List<String>> combiner() {
			return null;
		}

		@Override
		public Function<List<String>, List<String>> finisher() {
			return Collections::unmodifiableList;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return EnumSet.of(Characteristics.IDENTITY_FINISH);
		}
		
	}
}
