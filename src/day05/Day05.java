package day05;

import java.io.IOException;

import main.AdventUtilities;

public class Day05 {

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day05");
		System.out.println(findMax(strings));
	}

	public static int findMax(String[] tickets) {
		int max = 0;
		int current;
		for (String t : tickets) {
			current = scoreTicket(t.toCharArray());
			if (max < current) {
				System.out.println(t);
			}
			max = max > current ? max : current;
		}
		return max;
	}

	public static int scoreTicket(char[] ticket) {
		int row = 64;
		int col = 4;
		int total = 0;
		boolean b = false;
		boolean r = false;
		boolean first = true;
		for (char c : ticket) {
			b = c == 'B';
			r = c == 'R';
			total += (b ? row * 8 : r ? col : 0);
			row /= 2;
			col /= c == 'B' || c == 'F' ? 1 : 2;
		}
		return total;
	}

}
