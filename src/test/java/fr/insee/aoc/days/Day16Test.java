package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day16Test  {
    
	private Day day = new Day16();
	
	@Test
	public void case1_0() {
		assertEquals("71", day.part1("src/test/resources/16-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/16.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("25984", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/16.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("1265347500049", answer);
	}
}
