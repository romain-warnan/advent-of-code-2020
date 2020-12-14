package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day14Test  {
    
	private Day day = new Day14();
	
	@Test
	public void case1_0() {
		assertEquals("165", day.part1("src/test/resources/14-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("208", day.part2("src/test/resources/14-1.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/14.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("11179633149677", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/14.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("4822600194774", answer);
	}
}
