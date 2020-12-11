package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day11Test  {
    
	private Day day = new Day11();
	
	@Test
	public void case1_0() {
		assertEquals("37", day.part1("src/test/resources/11-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("-1", day.part2("src/test/resources/11-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/11.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("2261", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/11.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("-1", answer);
	}
}
