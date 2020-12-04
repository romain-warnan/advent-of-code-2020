package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day01Test  {
    
	private Day day = new Day01();
	
	@Test
	public void case1_0() {
		assertEquals("514579", day.part1("src/test/resources/01-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("241861950", day.part2("src/test/resources/01-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/01.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("355875", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/01.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("140379120", answer);
	}
}
