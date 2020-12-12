package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.Arrays;
import java.util.List;

import fr.insee.aoc.utils.Point;

public class Day12 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var boat = new Boat();
		streamOfLines(input).map(Instruction::from).forEach(boat::move);
		return String.valueOf(boat.position.manhattan());
	}
	
	@Override
	public String part2(String input, Object... params) {
		var boat = new Boat();
		streamOfLines(input).map(Instruction::from).forEach(boat::moveTowardWaypoint);
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
		Point position = Point.of(0, 0), waypoint = Point.of(10, 1);
		Way way = Way.E;

		void turnRight(int value) {
			switch(value) {
			case 90:
				waypoint = Point.of(waypoint.y, -waypoint.x);
				break;
			case 180:
				waypoint = Point.of(-waypoint.x, -waypoint.y);
				break;
			case 270:
				waypoint = Point.of(-waypoint.y, waypoint.x);
				break;
			}
		}
		
		void turnLeft(int value) {
			turnRight(360 - value);
		}
		
		void move(Instruction instruction) {
			switch (instruction.action) {
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

		void moveTowardWaypoint(Instruction instruction) {
			switch (instruction.action) {
			case 'N':
				waypoint.y += instruction.value;
				break;
			case 'E':
				waypoint.x += instruction.value;
				break;
			case 'S':
				waypoint.y -= instruction.value;
				break;
			case 'W':
				waypoint.x -= instruction.value;
				break;
			case 'L':
				turnLeft(instruction.value);
				break;
			case 'R':
				turnRight(instruction.value);
				break;
			case 'F':
				position.x += (waypoint.x * instruction.value);
				position.y += (waypoint.y * instruction.value);
				break;
			}
			
		}
	}
}
