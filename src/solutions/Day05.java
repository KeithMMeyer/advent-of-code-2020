package solutions;

import java.io.IOException;
import java.util.Arrays;

import main.AdventUtilities;

public class Day05 {

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day05");
		System.out.println(findMax(strings));
		System.out.println(findMissing(sort(strings)));
	}

	public static int findMissing(int[] tickets) {
		int prev = tickets[0] - 1;
		for (int t : tickets) {
			if (t - 1 == prev) {
				prev++;
			} else {
				return t - 1;
			}
		}
		return -1;
	}

	public static int[] sort(String[] tickets) {
		int[] IDs = new int[tickets.length];
		for (int i = 0; i < tickets.length; i++) {
			IDs[i] = scoreTicket(tickets[i].toCharArray());
		}
		Arrays.sort(IDs);
		return IDs;
	}

	public static int findMax(String[] tickets) {
		int max = 0;
		int current;
		for (String t : tickets) {
			current = scoreTicket(t.toCharArray());
			if (max < current) {
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
