package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.Arrays;
import java.util.List;

import fr.insee.aoc.utils.DayException;
import fr.insee.aoc.utils.Point;

public class Day12 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var boat = new Boat();
		streamOfLines(input)
			.map(Instruction::from)
			.forEach(boat::move);
		return String.valueOf(boat.position.manhattan());
	}

	static enum Way {
		N, E, S, W;
		
		static final List<Way> ways = Arrays.asList(values());
		
		static Way turnRight(Way way, int degrees) {
			int n = degrees / 90;
			var index = ways.indexOf(way);
			return ways.get((index + n) % 4);
		}
		
		static Way turnLeft(Way way, int degrees) {
			return turnRight(way, 360 - degrees);
		}
	}
	
	static class Instruction {
		char action;
		int value;
		
		static Instruction from(String line) {
			var instruction = new Instruction();
			instruction.action = line.charAt(0);
			instruction.value = Integer.parseInt(line.substring(1));
			return instruction;
		}
	}
	
	static class Boat {
		Point position = Point.of(0, 0);
		Way way = Way.E;
		
		void move(Instruction instruction) {
			switch(instruction.action) {
				case 'N':
					position.y += instruction.value;
					break;
				case 'E':
					position.x += instruction.value;
					break;
				case 'S':
					position.y -= instruction.value;
					break;
				case 'W':
					position.x -= instruction.value;
					break;
				case 'L':
					way = Way.turnLeft(way, instruction.value);
					break;
				case 'R':
					way = Way.turnRight(way, instruction.value);
				break;
				case 'F':
					switch (way) {
						case N:
							position.y += instruction.value;
							break;
						case E:
							position.x += instruction.value;
							break;
						case S:
							position.y -= instruction.value;
							break;
						case W:
							position.x -= instruction.value;
							break;
						}
					break;
				}
		}
	}
	
	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
}
