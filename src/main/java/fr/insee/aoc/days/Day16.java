package fr.insee.aoc.days;

import static com.codepoetics.protonpack.StreamUtils.*;
import static fr.insee.aoc.utils.Days.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import fr.insee.aoc.utils.Collectors;
import fr.insee.aoc.utils.DayException;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day16 implements Day {

	private static final Pattern pattern = Pattern.compile("(.+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

	@Override
	public String part1(String input, Object... params) {
		var fields = fields(input);
		var errorRate = skipUntilInclusive(streamOfLines(input), l -> l.equals("nearby tickets:"))
			.flatMap(l -> Arrays.stream(l.split(",")))
			.mapToInt(Integer::parseInt)
			.filter(v -> !isValid(fields, v))
			.sum();
		return String.valueOf(errorRate);
	}

	@Override
	public String part2(String input, Object... params) {
		var fields = fields(input);
		var tickets = validTickets(input, fields);
		var myTicket = myTicket(input);
		tickets.add(myTicket);
		var matches = new HashMap<Field, Integer>(fields.size());
		while (matches.size() < fields.size()) {
			candidates(fields, tickets, matches).entrySet()
					.stream()
					.filter(e -> e.getValue().size() == 1)
					.forEach(e -> matches.put(e.getValue().get(0), e.getKey()));
		}
		var indexes = departureIndexes(matches);
		long value = 1;
		for(var i : indexes) {
			value *= myTicket.values[i];
		}
		return String.valueOf(value);
	}

	static int[] departureIndexes(HashMap<Field, Integer> matches) {
		return matches.entrySet()
				.stream()
				.filter(e -> e.getKey().name.startsWith("departure"))
				.mapToInt(Entry::getValue)
				.toArray();
	}

	static boolean isCandidate(int index, Field field, List<Ticket> tickets) {
		return tickets.stream()
				.mapToInt(t -> t.values[index])
				.allMatch(field::accept);
	}

	static List<Field> candidates(int index, List<Field> fields, List<Ticket> tickets, Map<Field, Integer> matches) {
		return fields.stream()
				.filter(f -> !matches.containsKey(f))
				.filter(f -> isCandidate(index, f, tickets))
				.collect(toList());
	}

	static Map<Integer, List<Field>> candidates(List<Field> fields, List<Ticket> tickets, Map<Field, Integer> matches) {
		var candidates = new HashMap<Integer, List<Field>>(fields.size());
		for(var n = 0; n < fields.size(); n ++) {
			if(!matches.containsValue(n)) {
				candidates.put(n, candidates(n, fields, tickets, matches));
			}
		}
		return candidates;
	}

	static Ticket myTicket(String input) {
		return skipUntilInclusive(streamOfLines(input), l -> l.equals("your ticket:"))
				.map(Ticket::from)
				.findFirst()
				.orElseThrow();
	}

	static List<Field> fields(String input) {
		return takeUntil(streamOfLines(input), StringUtils::isBlank)
			.map(Field::from)
			.collect(toList());
	}

	static List<Ticket> validTickets(String input, List<Field> fields) {
		return skipUntilInclusive(streamOfLines(input), l -> l.equals("nearby tickets:"))
			.map(Ticket::from)
			.filter(t-> t.isValid(fields))
			.collect(toList());
	}

	static boolean isValid(Collection<Field> fields, int value) {
		return fields.stream().anyMatch(f -> f.accept(value));
	}

	static class Ticket {
		int[] values;

		static Ticket from(String line) {
			var ticket = new Ticket();
			ticket.values = Arrays.stream(line.split(","))
				.mapToInt(Integer::parseInt)
				.toArray();
			return ticket;
		}

		boolean isValid(Collection<Field> fields) {
			return Arrays.stream(values).allMatch(v -> Day16.isValid(fields, v));
		}
	}

	static class Field {
		String name;
		Range<Integer> firstRange, secondRange;

		static Field from(String line) {
			var field = new Field();
			var matcher = pattern.matcher(line);
			if(matcher.matches()) {
				field.name = readString(1, matcher);
				field.firstRange = Range.between(readInt(2, matcher), readInt(3, matcher));
				field.secondRange = Range.between(readInt(4, matcher), readInt(5, matcher));
			}
			return field;
		}

		boolean accept(int value) {
			return firstRange.contains(value) || secondRange.contains(value);
		}
	}
}
