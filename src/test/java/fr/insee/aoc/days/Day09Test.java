package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day09Test  {
    
	private Day day = new Day09();
	
	@Test
	public void case1_0() {
		assertEquals("127", day.part1("src/test/resources/09-0.txt", 5));
	}
	
	@Test
	@Disabled
	public void case2_0() {
		assertEquals("8", day.part2("src/test/resources/09-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/09.txt", 25);
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("1331", answer);
	}

	@Test
	@Disabled
	public void part2() {
		String answer = day.part2("src/main/resources/09.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("1121", answer);
	}
}
