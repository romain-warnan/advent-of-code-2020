package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day18Test  {
    
	private Day day = new Day18();
	
	@Test
	public void case1_0() {
		assertEquals("26335", day.part1("src/test/resources/18-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("693942", day.part2("src/test/resources/18-1.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/18.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("16332191652452", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/18.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("351175492232654", answer);
	}
}
