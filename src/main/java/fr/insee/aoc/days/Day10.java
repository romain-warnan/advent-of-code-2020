package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfInt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var adapters = streamOfInt(input).sorted().toArray();
		var delta1 = adapters[0] == 1 ? 1 : 0;
		var delta3 = adapters[0] == 3 ? 2 : 1;
		for(int i = 1; i < adapters.length; i ++) {
			if(adapters[i] - adapters[i - 1] == 1) {
				delta1 ++;
			}
			else if(adapters[i] - adapters[i - 1] == 3) {
				delta3 ++;
			}
		}
		return String.valueOf(delta1 * delta3);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var adapters = IntStream.concat(IntStream.of(0), streamOfInt(input)).sorted().toArray();
		var values = new ArrayList<Integer>();
		var strike = 1;
		for(int i = 1; i < adapters.length; i ++) {
			if(adapters[i] - adapters[i - 1] == 1) {
				strike ++;
			}
			if(adapters[i] - adapters[i - 1] == 3 || i + 1 == adapters.length) {
				switch(strike) {
					case 3:
						values.add(2);
						break;
					case 4:
						values.add(4);
						break;
					case 5:
						values.add(7);
						break;
				}
				strike = 1;
			}
		}
		var arrangements = values.stream()
				.map(BigInteger::valueOf)
				.reduce(BigInteger.ONE, BigInteger::multiply);
		return String.valueOf(arrangements);
	}
}
