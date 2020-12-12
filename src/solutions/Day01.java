package solutions;

import java.io.IOException;
import java.util.Arrays;

import main.AdventUtilities;

public class Day01 {

	static class Pair<S, T> { // very lightweight pair class to avoid javafx
		public S k;
		public T v;

		public Pair(S key, T value) {
			k = key;
			v = value;
		}
	}

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day01");
		int[] numbers = new int[strings.length];
		for (int i = 0; i < strings.length; i++) { // n time
			numbers[i] = Integer.parseInt(strings[i]);
		}
		Arrays.sort(numbers); // n log n time
		Pair<Integer, Integer> duo = perfectPair(numbers, 2020);
		int[] trio = threeAmigos(numbers, 2020);
		System.out.println(duo.k * duo.v);
		System.out.println(trio[0] * trio[1] * trio[2]);
	}

	public static int[] threeAmigos(int[] numbers, int total) { // n^2 time
		int[] result = new int[3];
		for (int i = 0; i < numbers.length; i++) { // n^2 time
			int goal = total - numbers[i];
			Pair<Integer, Integer> pair = perfectPair(numbers, goal);
			if (pair != null) {
				result[0] = numbers[i];
				result[1] = pair.k;
				result[2] = pair.v;
				return result;
			}
		}
		return null;
	}

	private static Pair<Integer, Integer> perfectPair(int[] numbers, int total) { // n time
		int l = 0;
		int r = numbers.length - 1;
		while (l != r) { // n time
			int attempt = numbers[l] + numbers[r];
			if (attempt == total)
				return new Pair<Integer, Integer>(numbers[l], numbers[r]);
			if (attempt < total) {
				l++;
			} else {
				r--;
			}
		}
		return null;
	}

}
