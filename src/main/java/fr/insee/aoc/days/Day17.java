package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import fr.insee.aoc.utils.DayException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Day17 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var slice = tableOfChars(input);
		var height = height(slice);
		var width = width(slice);
		var activeCubes = new ArrayList<Cube>(5_000);
		for(var i = 0; i < height; i ++) {
			for(var j = 0; j < width; j ++) {
				if(slice[i][j] == '#') {
					activeCubes.add(Cube.newActive(i, j));
				}
			}
		}
		var n = 0;
		while (n < 6) {
			n ++;
			var statX = activeCubes.stream().mapToInt(cube -> cube.x).summaryStatistics();
			var statY = activeCubes.stream().mapToInt(cube -> cube.y).summaryStatistics();
			var statZ = activeCubes.stream().mapToInt(cube -> cube.z).summaryStatistics();
			var changes = new HashSet<Cube>();
			for(int i = statX.getMin() - 1; i <= statX.getMax() + 1; i ++) {
				for(int j = statY.getMin() - 1; j <= statY.getMax() + 1; j ++) {
					for(int k = statZ.getMin() - 1; k <= statZ.getMax() + 1; k ++) {
						var inactive = Cube.newInactive(i, j, k);
						var cube = activeCubes.stream()
								.filter(c -> c.equals(inactive))
								.findFirst()
								.orElse(inactive);
						var neighbors = cube.neighbors();
						var activeNeighbors = neighbors.stream().filter(activeCubes::contains).count();
						if(cube.active) {
							if(activeNeighbors != 2 && activeNeighbors != 3) {
								changes.add(cube);
							}
						}
						else {
							if(activeNeighbors == 3) {
								changes.add(cube);
							}
						}
					}
				}
			}
			for(var cube : changes) {
				if(cube.active) {
					cube.active = false;
					activeCubes.remove(cube);
				}
				else {
					cube.active = true;
					activeCubes.add(cube);
				}
			}
		}

		return String.valueOf(activeCubes.size());
	}

	static class Cube {
		int x, y, z;
		boolean active;

		static Cube newActive(int i, int j) {
			var cube = new Cube();
			cube.active = true;
			cube.x = j;
			cube.y = i;
			cube.z = 0;
			return cube;
		}

		static Cube newInactive(int x, int y, int z) {
			var cube = new Cube();
			cube.active = false;
			cube.x = x;
			cube.y = y;
			cube.z = z;
			return cube;
		}

		List<Cube> neighbors() {
			var neighbors = new ArrayList<Cube>(26);
			for(var i = -1; i <= 1; i ++) {
				for(var j = -1; j <= 1; j ++) {
					for(var k = -1; k <= 1; k ++) {
						if(i != 0 || j != 0 || k != 0) {
							neighbors.add(Cube.newInactive(x + i, y + j, z + k));
						}
					}
				}
			}
			return neighbors;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Cube cube = (Cube) o;
			return x == cube.x && y == cube.y && z == cube.z;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y, z);
		}
	}

	@Override
	public String part2(String input, Object... params) {
		throw new DayException();
	}
}
