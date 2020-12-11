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
		var grid = tableOfChars(input);
		var height = height(grid);
		var width = width(grid);
		var hasChanged = true;
		while(hasChanged) {
			hasChanged = false;
			var nextGrid = new char[height][width];
			for(var i = 0; i < height; i ++) {
				for(var j = 0; j < width; j ++) {
					nextGrid[i][j] = nextValue2(grid, i, j, height, width);
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
	
	char nextValue2(char[][] grid, int i, int j, int height, int width) {
		var seat = grid[i][j];
		var firstVisibleSeats = firstVisibleSeats(grid, i, j, height, width);
		if(seat == EMPTY && firstVisibleSeats.chars().allMatch(s -> s != OCCUPIED)) {
			return OCCUPIED;
		}
		if(seat == OCCUPIED && firstVisibleSeats.chars().filter(s -> s == OCCUPIED).count() >= 5) {
			return EMPTY;
		}
		return seat;
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
