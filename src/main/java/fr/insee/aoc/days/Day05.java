package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.lang.Integer.parseInt;

import fr.insee.aoc.utils.DayException;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int highestSeatID = streamOfLines(input)
			.map(BoardingPass::from)
			.mapToInt(BoardingPass::seatID)
			.max()
			.orElseThrow(DayException::new);
		return String.valueOf(highestSeatID);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var seats = streamOfLines(input)
				.map(BoardingPass::from)
				.mapToInt(BoardingPass::seatID)
				.sorted()
				.toArray();
		for (int i = 1; i < seats.length; i ++) {
			if(seats[i] - seats[i - 1] > 1) {
				return String.valueOf(seats[i] - 1);
			}
		}
		throw new DayException();
	}
	
	private static class BoardingPass {
		
		int row, column;
		
		static BoardingPass from(String line) {
			var pass = new BoardingPass();
			pass.row = parseInt(line.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
			pass.column = parseInt(line.substring(7, 10).replace('L', '0').replace('R', '1'), 2);
			return pass;
		}
		
		int seatID() {
			return row * 8 + column;
		}
	}
}
