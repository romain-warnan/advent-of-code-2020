package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


public class Day10Test  {
    
	private Day day = new Day10();
	
	@Test
	public void case1_0() {
		assertEquals("35", day.part1("src/test/resources/10-0.txt"));
	}
	
	@Test
	public void case1_1() {
		assertEquals("220", day.part1("src/test/resources/10-1.txt"));
	}
	
	@Test
	public void case2_0() {
		assertEquals("62", day.part2("src/test/resources/10-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/10.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("2210", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/10.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("51152360", answer);
	}
}
