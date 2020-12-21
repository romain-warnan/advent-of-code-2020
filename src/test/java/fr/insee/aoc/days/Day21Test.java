package fr.insee.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Day21Test  {
    
	private Day day = new Day21();
	
	@Test
	public void case1_0() {
		assertEquals("5", day.part1("src/test/resources/21-0.txt"));
	}

	@Test
	public void case2_0() {
		assertEquals("mxmxvkd,sqjhc,fvjkl", day.part2("src/test/resources/21-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/21.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("2265", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/21.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		assertEquals("dtb,zgk,pxr,cqnl,xkclg,xtzh,jpnv,lsvlx", answer);
	}
}
