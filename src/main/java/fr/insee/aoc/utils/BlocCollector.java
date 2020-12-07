package fr.insee.aoc.utils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BlocCollector implements Collector<String, Deque<String>, List<String>> {
	
	private String separator;
	
	public BlocCollector() {
		this.separator = "";
	}
	
	public BlocCollector(String separator) {
		this.separator = separator;
	}

	@Override
	public Supplier<Deque<String>> supplier() {
		return LinkedList::new;
	}

	@Override
	public BiConsumer<Deque<String>, String> accumulator() {
		return (lines, line) -> {
			if(lines.isEmpty()) {
				lines.add("");
			}
			if(line.isBlank()) {
				lines.add("");
			}
			else {
				var current = lines.pollLast();
				lines.offerLast(current + (current.isEmpty() ? "" : separator) + line);
			}
		};
	}

	@Override
	public BinaryOperator<Deque<String>> combiner() {
		return null;
	}

	@Override
	public Function<Deque<String>, List<String>> finisher() {
		return ArrayList::new;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.noneOf(Characteristics.class);
	}
}
