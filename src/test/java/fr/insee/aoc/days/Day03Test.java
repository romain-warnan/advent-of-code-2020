package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class Day03Test  {
    
	private Day day = new Day03();
	
	@Test
	public void case1_0() {
		assertEquals("7", day.part1("src/test/resources/03-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("336", day.part2("src/test/resources/03-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/03.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("193", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/03.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("1355323200", answer);
	}
}
