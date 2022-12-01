package model;

import java.util.Arrays;

public class Elf {
	private int[] itemCalories;

	public void fillBag(String... calories) {
		itemCalories = Arrays.stream(calories).mapToInt(Integer::parseInt).toArray();
	}

	public int getCalories() {
		return Arrays.stream(itemCalories).sum();
	}
}
