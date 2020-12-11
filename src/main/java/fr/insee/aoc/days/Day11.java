package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import fr.insee.aoc.utils.DayException;

public class Day11 implements Day {

	private static final int 
		EMPTY = (int) 'L',
		OCCUPIED = (int) '#',
		FLOOR = (int) '.';
	
	@Override
	public String part1(String input, Object... params) {
		var grid = tableOfChars(input);
		var height = height(grid);
		var width = width(grid);
		var hasChanged = true;
		while(hasChanged) {
			hasChanged = false;
			var nextGrid = new char[height][width];
			for(var i = 0; i < height; i ++) {
				for(var j = 0; j < width; j ++) {
					nextGrid[i][j] = nextValue(grid, i, j, height, width);
					if(nextGrid[i][j] != grid[i][j]) {
						hasChanged = true;
					}
				}
			}
			grid = nextGrid;
			nextGrid = null;
		}
		var occupiedSeats = numberOfOccupiedSeats(grid, height, width);
		return String.valueOf(occupiedSeats);
	}

	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
	
	char nextValue(char[][] grid, int i, int j, int height, int width) {
		var seat = grid[i][j];
		var adjacentsSeats = adjacentsSeats(grid, i, j, height, width);
		if(seat == EMPTY && adjacentsSeats.chars().allMatch(s -> s == EMPTY || s == FLOOR)) {
			return (char) OCCUPIED;
		}
		if(seat == OCCUPIED && adjacentsSeats.chars().filter(s -> s == OCCUPIED).count() >= 4) {
			return (char) EMPTY;
		}
		return seat;
	}

	static String adjacentsSeats(char[][] grid, int i, int j, int height, int width) {
		var builder = new StringBuilder();
		if(0 < i) {
			if(0 < j) {
				builder.append(grid[i - 1][j - 1]);
			}
			if(j < width - 1) {
				builder.append(grid[i - 1][j + 1]);
			}
			builder.append(grid[i - 1][j]);
		}
		if(i < height - 1) {
			if(0 < j) {
				builder.append(grid[i + 1][j - 1]);
			}
			if(j < width - 1) {
				builder.append(grid[i + 1][j + 1]);
			}
			builder.append(grid[i + 1][j]);
		}
		if(0 < j) {
			builder.append(grid[i][j - 1]);
		}
		if(j < width - 1) {
			builder.append(grid[i][j + 1]);
		}
		return builder.toString();
	}
	
	int numberOfOccupiedSeats(char[][] grid, int height, int width) {
		int n = 0;
		for(var i = 0; i < height; i ++) {
			for(var j = 0; j < width; j ++) {
				if(grid[i][j] == OCCUPIED) n ++;
			}
		}
		return n;
	}
}
