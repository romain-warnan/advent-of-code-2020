package fr.insee.aoc.days;

import static com.codepoetics.protonpack.StreamUtils.takeUntil;
import static fr.insee.aoc.utils.Days.*;

import fr.insee.aoc.utils.DayException;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var lines = arrayOfLines(input);
		var ingredients = new HashMap<String, Integer>();
		var allergens = new HashSet<Allergen>();
		for (var line : lines) {
			var allergenNames = StringUtils.substringBetween(line, "(contains ", ")").split(", ");
			var ingredientNames = StringUtils.substringBefore(line, "(").split(" ");
			for(var name : allergenNames) {
				var allergen = findAllergen(name, allergens);
				if(allergen != null) {
					 allergen.mergeIngredients(ingredientNames);
				 }
				else {
					allergens.add(Allergen.from(name, ingredientNames));
				}
			}

			for(var name : ingredientNames) {
				Integer count = ingredients.get(name);
				if(count == null) {
					count = 1;
				}
				else {
					count ++;
				}
				ingredients.put(name, count);
			}
		}
		var dangerousIngredient = allergens.stream()
				.flatMap(a -> a.possibleIngredients.stream())
				.collect(Collectors.toSet());
		var sum = ingredients.entrySet().stream()
				.filter(e -> !dangerousIngredient.contains(e.getKey()))
				.mapToInt(Map.Entry::getValue)
				.sum();
		return String.valueOf(sum);
	}

	@Override
	public String part2(String input, Object... params) {
		var lines = arrayOfLines(input);
		var allergens = new HashSet<Allergen>();
		var knownAllergens = new ArrayList<Allergen>();
		for (var line : lines) {
			var allergenNames = StringUtils.substringBetween(line, "(contains ", ")").split(", ");
			var ingredientNames = StringUtils.substringBefore(line, "(").split(" ");
			for (var name : allergenNames) {
				var allergen = findAllergen(name, allergens);
				if (allergen != null) {
					allergen.mergeIngredients(ingredientNames);
				} else {
					allergens.add(Allergen.from(name, ingredientNames));
				}
			}
		}
		while (allergens.size() > 0) {
			var set = allergens.stream()
					.filter(a -> a.possibleIngredients.size() == 1)
					.collect(Collectors.toSet());
			allergens.removeAll(set);
			var array = set.stream()
					.map(Allergen::uniqueIngredient)
					.toArray(String[]::new);
			allergens.forEach(a -> a.removeIngredient(array));
			knownAllergens.addAll(set);
		}
		return knownAllergens.stream()
				.sorted()
				.map(Allergen::uniqueIngredient)
				.collect(Collectors.joining(","));
	}

	static Allergen findAllergen(String name, Set<Allergen> allergens) {
		return allergens.stream()
				.filter(i -> i.name.equals(name))
				.findFirst()
				.orElse(null);
	}

	static class Allergen implements Comparable<Allergen> {
		String name;
		Set<String> possibleIngredients;

		static Allergen from(String name, String[] ingredients) {
			var allergen = new Allergen();
			allergen.name = name;
			allergen.possibleIngredients = new HashSet<>(Arrays.asList(ingredients));
			return allergen;
		}

		String uniqueIngredient() {
			return possibleIngredients.stream().findFirst().orElseThrow();
		}

		void mergeIngredients(String[] ingredients) {
			possibleIngredients.retainAll(Arrays.asList(ingredients));
		}

		void removeIngredient(String[] ingredients) {
			possibleIngredients.removeAll(Arrays.asList(ingredients));
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Allergen that = (Allergen) o;
			return name.equals(that.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name);
		}

		@Override
		public int compareTo(Allergen other) {
			return this.name.compareTo(other.name);
		}
	}
}
