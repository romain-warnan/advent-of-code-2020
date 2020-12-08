package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.arrayOfLines;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import fr.insee.aoc.utils.DayException;

public class Day08 implements Day {

	@Override
	public String part1(String input, Object... params) {
		var operations = operations(input);
		var position = 0;
		var accumulator = new AtomicInteger();
		var executed = new HashSet<Integer>();
		while(position < operations.length) {
			try {
				var nextPosition = operations[position].execute(executed, accumulator);
				executed.add(position);
				position = nextPosition;
			}
			catch(DayException e) {
				return String.valueOf(accumulator);
			}
		}
		throw new DayException();
	}
	
	@Override
	public String part2(String input, Object... params) {
		var operations = operations(input);
		for(int i = 0; i < operations.length; i ++) {
			var operation = operations[i];
			if(operation instanceof Nop || operation instanceof Jmp) {
				operations[i] = operation.change();
				var position = 0;
				var accumulator = new AtomicInteger();
				var executed = new HashSet<Integer>();
				var fail = false;
				while(position < operations.length) {
					try {
						var nextPosition = operations[position].execute(executed, accumulator);
						executed.add(position);
						position = nextPosition;
					}
					catch(DayException e) {
						operations[i] = operation;
						fail = true;
						break;
					}
				}
				if(!fail) {
					return String.valueOf(accumulator);
				}
			}
			
		}
		throw new DayException();
	}
	
	static Operation[] operations(String input) {
		var lines = arrayOfLines(input);
		var operations = new Operation[lines.length];
		for(int i = 0; i < lines.length; i ++) {
			var operation = Operation.from(lines[i]);
			operation.index = i;
			operations[i] = operation;
		}
		return operations;
	}
	
	static abstract class Operation {
		int index, value;
		
		Operation(int value) {
			this.value = value;
		}
	
		static Operation from(String line) {
			var tokens = line.split(" ");
			var type = tokens[0];
			var value = Integer.parseInt(tokens[1]);
			switch (type) {
				case "acc":
					return new Acc(value);
				case "jmp":
					return new Jmp(value);
				default:
					return new Nop(value);
			}
		}

		int execute(Set<Integer> executed, AtomicInteger accumulator) {
			if(executed.contains(index)) {
				throw new DayException("infinite loop !");
			}
			return -1;
		}
		
		Operation change() {
			return this;
		}
	}
	
	static class Acc extends Operation {

		Acc(int value) {
			super(value);
		}

		@Override
		public int execute(Set<Integer> executed, AtomicInteger accumulator) {
			super.execute(executed, accumulator);
			accumulator.addAndGet(value);
			return index + 1;
		}
	}
	
	static class Jmp extends Operation {
		
		Jmp(int value) {
			super(value);
		}

		@Override
		public int execute(Set<Integer> executed, AtomicInteger accumulator) {
			super.execute(executed, accumulator);
			return index + value;
		}

		@Override
		Operation change() {
			var operation = new Nop(value);
			operation.index = this.index;
			return operation;
		}
	}
	
	static class Nop extends Operation {
		
		Nop(int value) {
			super(value);
		}

		@Override
		public int execute(Set<Integer> executed, AtomicInteger accumulator) {
			super.execute(executed, accumulator);
			return index + 1;
		}
		
		@Override
		Operation change() {
			var operation = new Jmp(value);
			operation.index = this.index;
			return operation;
		}
	}
}
