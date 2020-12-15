package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day15Test  {
    
	private Day day = new Day15();
	
	@Test
	public void case1_0() {
		assertEquals("436", day.part1("0,3,6", 2020));
	}

	@Test
	public void part1() {
		String answer = day.part1("6,13,1,15,2,0", 2020);
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("1194", answer);
	}

	@Test
	public void case2_0() {
		assertEquals("175594", day.part2("0,3,6", 30_000_000));
	}

	@Test
	public void part2() {
		String answer = day.part2("6,13,1,15,2,0", 30_000_000);
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("48710", answer);
	}
}
