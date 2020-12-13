package solutions;

import java.io.IOException;
import java.util.ArrayList;

import main.AdventUtilities;

public class Day13 {

	static int[] numbers;

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day13");
		System.out.println(Integer.valueOf(minWait(Integer.valueOf(strings[0]), strings[1].split(","))));
		System.out.println(AdventUtilities.crt(numbers, remainders(strings[1].split(",")), numbers.length));

	}

	public static int minWait(int goal, String[] strings) {
		ArrayList<Integer> nums = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		int bus = 0;
		for (String s : strings) {
			if (s.charAt(0) != 'x') {
				int value = Integer.valueOf(s);
				int time = (int) ((value * (Math.ceil(goal / (value + 0.0)))) - goal);
				nums.add(value);
				if (time < min) {
					min = time;
					bus = value;
				}
			}
		}
		numbers = new int[nums.size()];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = nums.get(i);
		}
		return min * bus;
	}

	public static int[] remainders(String[] strings) {
		int diff = 0;
		int counter = 0;
		int[] diffs = new int[numbers.length];
		for (String s : strings) {
			if (s.charAt(0) != 'x') {
				diffs[counter] = (Integer.valueOf(s) - (diff % Integer.valueOf(s))) % Integer.valueOf(s);
				counter++;
			}
			diff++;
		}
		return diffs;

	}

}
