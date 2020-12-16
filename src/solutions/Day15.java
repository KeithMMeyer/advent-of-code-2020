package solutions;

import java.io.IOException;
import java.util.HashMap;

public class Day15 {

	//static ArrayList<Integer> nums = new ArrayList<>();
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		int[] numbers = { 2, 0, 6, 12, 1, 3 };
		int pos = 0;
		for (int i : numbers) {
			map.put(i, pos);
			pos++;
		}
		System.out.println(put(pos, 29999998));

	}

	public static int put(int start, int target) {
		int pos = start++;
		int last = 0;
		while (pos <= target) {
			if (map.containsKey(last)) {
				int temp = pos - map.get(last);
				map.put(last, pos);
				last = temp;
			} else {
				map.put(last, pos);
				last = 0;
			}
			pos++;
		}
		return last;

	}

}
