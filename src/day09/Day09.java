package day09;

import java.io.IOException;
import java.util.ArrayList;

import main.AdventUtilities;

public class Day09 {

	static ArrayList<Integer> avail = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day09");
		for (int i = 0; i < 25; i++) {
			avail.add(Integer.valueOf(strings[i]));
		}
		findSet(strings, findMissing(strings));
		System.out.println(avail);
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (Integer i : avail) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		System.out.println(min + max);

	}

	public static int findMissing(String[] strings) {
		for (int s = 25; s < strings.length; s++) {
			int target = Integer.valueOf(strings[s]);
			boolean found = false;
			for (Integer i : avail) {
				if (avail.contains(target - i)) {
					found = true;
				}
			}
			avail.remove(0);
			avail.add(target);
			if (!found) {
				return target;
			}
		}
		return -1;
	}

	public static void findSet(String[] strings, int target) {
		avail.clear();
		int s = 0;
		while (s < strings.length) {
			if (sum() == target) {
				return;
			} else {
				if (sum() > target) {
					avail.remove(0);
				} else {
					if (sum() < target) {
						avail.add(Integer.valueOf(strings[s]));
						s++;
					}
				}
			}
		}

	}

	public static int sum() {
		int total = 0;
		for (Integer i : avail) {
			total += i;
		}
		return total;
	}

}
