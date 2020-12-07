package day03;

import java.io.IOException;

import main.AdventUtilities;

public class Day03 {

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day03");
		System.out.println(countTrees(strings, 3, 1));
		long result = 1;
		int[] rights = { 1, 3, 5, 7 };
		for (int i = 0; i < rights.length; i++) {
			result *= countTrees(strings, rights[i], 1);
		}
		result *= countTrees(strings, 1, 2);
		System.out.println(result);
	}

	public static int countTrees(String[] map, int right, int down) {
		int count = 0;
		int width = map[0].length();
		int position = 0;
		for (int i = 0; i < map.length; i += down) {
			if (map[i].charAt(position) == '#')
				count++;
			position = (position + right) % width;
		}
		return count;
	}

}
