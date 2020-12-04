package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.arrayOfInt;

import fr.insee.aoc.utils.DayException;

public class Day01 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int[] expenses = arrayOfInt(input);
		for(int i = 0; i < expenses.length; i ++) {
			for(int j = 0; j < expenses.length; j ++) {
				if(i != j) {
					int a = expenses[i];
					int b = expenses[j];
					if(a + b == 2020) return String.valueOf(a * b);
				}
			}
		}
		throw new DayException();
	}

	@Override
	public String part2(String input, Object... params) {
		int[] expenses = arrayOfInt(input);
		for(int i = 0; i < expenses.length; i ++) {
			int a = expenses[i];
			for(int j = 0; j < expenses.length; j ++) {
				int b = expenses[j];
				if(i != j && a + b < 2020) {
					for(int k = 0; k < expenses.length; k ++) {
						if(j != k) {
							int c = expenses[k];
							if(a + b + c == 2020) return String.valueOf(a * b * c);
						}
					}
				}
			}
		}
		throw new DayException();
	}
}
