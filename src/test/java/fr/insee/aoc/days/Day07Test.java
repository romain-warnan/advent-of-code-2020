package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class Day07Test  {
    
	private Day day = new Day07();
	
	@Test
	public void case1_0() {
		assertEquals("4", day.part1("src/test/resources/07-0.txt"));
	}
	
	@Test
	@Disabled
	public void case2_0() {
		assertEquals("6", day.part2("src/test/resources/07-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/07.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("208", answer);
	}

	@Test
	@Disabled
	public void part2() {
		String answer = day.part2("src/main/resources/07.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("3351", answer);
	}
}
