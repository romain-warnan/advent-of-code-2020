package fr.insee.aoc.utils;

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

public class BlocCollector implements Collector<String, List<String>, List<String>> {
	
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
