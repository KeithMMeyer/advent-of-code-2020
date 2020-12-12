package day11;

import java.io.IOException;
import java.util.HashMap;

import main.AdventUtilities;

public class Day11 {

	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day11");
		System.out.println(repeat(strings, true) + "\n" + repeat(strings, false));
	}

	public static int repeat(String[] strings, boolean type) {
		for (int i = 0; i < strings.length; i++) {
			strings = emptySeats(fillSeats(strings, type), type);
		}
		int count = 0;
		for (int y = 0; y < strings.length; y++) {
			for (int x = 0; x < strings[y].length(); x++) {
				if (strings[y].charAt(x) == '#')
					count++;
			}
		}
		return count;
	}

	public static String[] emptySeats(String[] seats, boolean type) {
		String[] filled = seats.clone();
		for (int y = 0; y < seats.length; y++) {
			for (int x = 0; x < seats[y].length(); x++) {
				if (isFilled(seats, x, y)) {
					int blocks = type ? -1 : -2;
					for (int z = 0; z < 9; z++) {
						if ((type && isFilled(seats, (x - 1) + z % 3, (y - 1) + (z / 3)))
								|| (!type && extend(seats, (x - 1) + z % 3, (y - 1) + (z / 3), z % 3 - 1, z / 3 - 1))) {
							blocks += 1;
						}
					}
					if (blocks > 3)
						filled[y] = filled[y].substring(0, x) + "L" + filled[y].substring(x + 1);
				}
			}
		}
		return filled;

	}

	public static String[] fillSeats(String[] seats, boolean type) {
		String[] filled = seats.clone();
		for (int y = 0; y < seats.length; y++) {
			for (int x = 0; x < seats[y].length(); x++) {
				if (filled[y].charAt(x) == 'L') {
					boolean avail = true;
					for (int z = 0; z < 9; z++) {
						if ((type && isFilled(seats, (x - 1) + z % 3, (y - 1) + (z / 3)))
								|| (!type && extend(seats, (x - 1) + z % 3, (y - 1) + (z / 3), z % 3 - 1, z / 3 - 1))) {
							avail = false;
						}
					}
					if (avail)
						filled[y] = filled[y].substring(0, x) + "#" + filled[y].substring(x + 1);
				}
			}
		}
		return filled;

	}

	public static boolean extend(String[] seats, int x, int y, int dirX, int dirY) {
		if (dirX == dirY && dirX == 0) {
			return seats[y].charAt(x) == '#';
		}
		while (y >= 0 && y < seats.length && x >= 0 && x < seats[y].length() && seats[y].charAt(x) != 'L') {
			if (seats[y].charAt(x) == '#')
				return true;
			x += dirX;
			y += dirY;
		}
		return false;
	}

	public static boolean isFilled(String[] seats, int x, int y) {
		if (y < 0 || y >= seats.length || x < 0 || x >= seats[y].length() || seats[y].charAt(x) != '#') {
			return false;
		}
		return true;
	}

}
