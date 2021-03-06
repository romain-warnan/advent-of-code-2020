package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day19Test  {
    
	private Day day = new Day19();
	
	@Test
	public void case2_0() {
		assertEquals("-1", day.part2("src/test/resources/19-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/19.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("184", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/19.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("-1", answer);
	}
}
