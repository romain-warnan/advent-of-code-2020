package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.height;
import static fr.insee.aoc.utils.Days.tableOfChars;
import static fr.insee.aoc.utils.Days.width;

public class Day11 implements Day {

	private static final int 
		EMPTY = (int) 'L',
		OCCUPIED = (int) '#',
		FLOOR = (int) '.';
	
	@Override
	public String part1(String input, Object... params) {
		var occupiedSeats = occupiedSeats(input, 4);
		return String.valueOf(occupiedSeats);
	}

	@Override
	public String part2(String input, Object... params) {
		var occupiedSeats = occupiedSeats(input, 5);
		return String.valueOf(occupiedSeats);
	}
	
	int occupiedSeats(String input, int tolerance) {
		var grid = tableOfChars(input);
		var height = height(grid);
		var width = width(grid);
		var hasChanged = true;
		while(hasChanged) {
			hasChanged = false;
			var nextGrid = new char[height][width];
			for(var i = 0; i < height; i ++) {
				for(var j = 0; j < width; j ++) {
					nextGrid[i][j] = nextState(grid, i, j, height, width, tolerance);
					if(nextGrid[i][j] != grid[i][j]) {
						hasChanged = true;
					}
				}
			}
			grid = nextGrid;
			nextGrid = null;
		}
		return numberOfOccupiedSeats(grid, height, width);
	}
	
	char nextState(char[][] grid, int i, int j, int height, int width, int tolerance) {
		var seat = grid[i][j];
		var seats = tolerance == 4 ? 
				adjacentsSeats(grid, i, j, height, width) : 
				firstVisibleSeats(grid, i, j, height, width);
		if(seat == EMPTY && seats.chars().allMatch(s -> s == EMPTY || s == FLOOR)) {
			return (char) OCCUPIED;
		}
		if(seat == OCCUPIED && seats.chars().filter(s -> s == OCCUPIED).count() >= tolerance) {
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
	
	static String firstVisibleSeats(char[][] grid, int i, int j, int height, int width) {
		var builder = new StringBuilder();
		if(0 < i) {
			if(0 < j) {
				var k = 1;
				while(0 <= i - k && 0 <= j - k && grid[i - k][j - k] == FLOOR) {
					k ++;
				}
				if(0 <= i - k && 0 <= j - k) {
					builder.append(grid[i - k][j - k]);
				}
			}
			if(j < width - 1) {
				var k = 1;
				while(0 <= i - k && j + k < width && grid[i - k][j + k] == FLOOR) {
					k ++;
				}
				if(0 <= i - k && j + k < width) {
					builder.append(grid[i - k][j + k]);
				}
			}
			var k = 1;
			while(0 <= i - k && grid[i - k][j] == FLOOR) {
				k ++;
			}
			if(0 <= i - k) {
				builder.append(grid[i - k][j]);
			}
		}
		if(i < height - 1) {
			if(0 < j) {
				var k = 1;
				while(i + k < height && 0 <= j - k && grid[i + k][j - k] == FLOOR) {
					k ++;
				}
				if(i + k < height && 0 <= j - k) {
					builder.append(grid[i + k][j - k]);
				}
			}
			if(j < width - 1) {
				var k = 1;
				while(i + k < height && j + k < width && grid[i + k][j + k] == FLOOR) {
					k ++;
				}
				if(i + k < height && j + k < width) {
					builder.append(grid[i + k][j + k]);
				}
			}
			var k = 1;
			while(i + k < height && grid[i + k][j] == FLOOR) {
				k ++;
			}
			if(i + k < height) {
				builder.append(grid[i + k][j]);
			}
		}
		if(0 < j) {
			var k = 1;
			while(0 <= j - k && grid[i][j - k] == FLOOR) {
				k ++;
			}
			if(0 <= j - k) {
				builder.append(grid[i][j - k]);
			}
		}
		if(j < width - 1) {
			var k = 1;
			while(j + k < width && grid[i][j + k] == FLOOR) {
				k ++;
			}
			if(j + k < width) {
				builder.append(grid[i][j + k]);
			}
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
