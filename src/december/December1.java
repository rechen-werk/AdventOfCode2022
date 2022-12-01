package december;

import model.Elf;
import util.Input;

import java.util.*;

public class December1 extends December {
	private final Input input;
	public December1(Input input) {
		this.input = input;
	}
	@Override
	public long star1() {
		return input()
			.stream()
			.mapToLong(Elf::getCalories)
			.max().orElse(0);
	}

	@Override
	public long star2() {
		return input()
			.stream()
			.map(Elf::getCalories)
			.sorted(Comparator.reverseOrder())
			.limit(3)
			.mapToLong(i -> i)
			.sum();
	}

	private List<Elf> input() {
		return Arrays
			.stream(input.get(1).split("\n\n"))
			.map(b -> {
				Elf e= new Elf();
				e.fillBag(b.split("\n"));
				return e;
			})
			.toList();
	}
}
