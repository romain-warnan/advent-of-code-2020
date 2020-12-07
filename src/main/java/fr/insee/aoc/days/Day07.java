package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.readString;
import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Day07 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var bags = bags(input);
		var shinyGold = Bag.find("shiny gold", bags);
		var count = bags.stream().filter(b -> b.contains(shinyGold)).count();
		return String.valueOf(count);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var bags = bags(input);
		var shinyGold = Bag.find("shiny gold", bags);
		return String.valueOf(shinyGold.countBags());
	}
	
	static Set<Bag> bags(String input) {
		var bags = new HashSet<Bag>();
		streamOfLines(input).forEach(line -> Bag.from(line, bags));
		return bags;
	}
	
	static class Bag {
		
		static final Pattern pattern = Pattern.compile(" ?(\\d+) (.+) bags?\\.?");
		
		String color;
		Map<Bag, Integer> content = new HashMap<>();
		
		Bag(String color) {
			this.color = color;
		}
		
		static Bag from(String line, Set<Bag> bags) {
			var tokens = line.split(" bags contain");
			var color = tokens[0];
			var content = tokens[1];
			var bag = find(color, bags);
			if(!content.equals(" no other bags.")) {
				var strings = content.split(",");
				for(var string : strings) {
					var matcher = pattern.matcher(string);
					if(matcher.matches()) {
						var n = readInt(1, matcher);
						var c = readString(2, matcher);
						bag.content.put(find(c, bags), n);
					}
				}
				
			}
			return bag;
		}
		
		
		static Bag find(String color, Set<Bag> bags) {
			return bags.stream().filter(b -> b.color.equals(color)).findFirst().orElseGet(() -> {
				var bag = new Bag(color);
				bags.add(bag);
				return bag;
			});
		}
		
		boolean contains(Bag bag) {
			return content.containsKey(bag) || content.keySet().stream().anyMatch(b -> b.contains(bag));
		}
		
		int countBags() {
			if(content.isEmpty()) {
				return 0;
			}
			int n = 0;
			for(var entry : content.entrySet()) {
				n += entry.getValue() * (1 + entry.getKey().countBags());
			}
			return n;
		}
	}
}
