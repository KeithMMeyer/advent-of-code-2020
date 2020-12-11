package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.AdventUtilities;

public class Day10 {

	static ArrayList<Integer> avail = new ArrayList<>();
	static HashMap<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day10");
		fillArr(strings);
		int max = 0;
		for (String s : strings) {
			if (Integer.valueOf(s) > max) {
				max = Integer.valueOf(s);
			}
		}
		avail.add(max + 3);
		System.out.println(countDifferences(1) * countDifferences(3));
		System.out.println(avail);
		//System.out.println(findOp(avail, avail.size() - 2));
		System.out.println(blocks(avail));

	}

	public static long blocks(ArrayList<Integer> test) {
		avail.add(0, 0);
		ArrayList<Integer> blocks = new ArrayList<>();
		for (int i = 1; i < test.size(); i++) {
			if (test.get(i) - test.get(i - 1) == 1) {
				int start = i;
				while (test.get(i) - test.get(i - 1) == 1) {
					i++;
				}
				blocks.add(i - start + 1);
			}
		}
		System.out.println(blocks);
		for (int i = 0; i < blocks.size(); i++) {
			int count = 1;
			for (int x = 0; x < blocks.get(i) - 1; x++) {
				count += x;
			}
			blocks.set(i, count);
		}
		System.out.println(blocks);
		long count = 1;
		for (int i : blocks) {
			count *= i;
		}

		return count;
	}

	public static int findOp(ArrayList<Integer> test, int i) {
		if (i == 0) {
			return 1;
		}
		if (i == 1) {
			if (test.get(i + 1) < 4)
				return 2;
			return 1;
		}
		int count = 1;
		int count2 = 0;
		int prev2 = i == 2 ? -5 : test.get(i - 2);
		int prev = test.get(i - 1);
		int next = test.get(i + 1);
		if ((next - prev) < 4) {
			count++;
			//if ((next - prev2) < 4)
			//count2++;
		}
		if (!map.containsKey(i - 1)) {
			map.put(i - 1, findOp(test, i - 1));
		}
		if (count2 == 0) {
			map.put(i, map.get(i - 1) * count);
		} else {
			map.put(i, map.get(i - 1) / 2 * (map.get(i - 1) - map.get(i - 2) + 2));
		}
		System.out.println(prev + " " + next);
		System.out.println(map);
		return map.get(i);
	}

	public static int findOptions(ArrayList<Integer> test) {
		int count = 1;
		int prev = 0;
		int curr;
		int next;
		for (int i = 0; i < test.size() - 1; i++) {
			curr = test.get(i);
			next = test.get(i + 1);
			if (((next - prev)) < 4) {
				test.remove(i);
				count += findOptions(test);
				test.add(i, curr);
				//System.out.println(count);
			}
			prev = curr;
		}
		return count;
	}

	public static int countDifferences(int diff) {
		int count = 0;
		int prev = 0;
		for (Integer i : avail) {
			//System.out.println((i - prev));
			if ((i - prev) == diff) {
				count++;
			}
			prev = i;
		}
		return count;
	}

	public static void fillArr(String[] strings) {
		//avail.add(0);
		int[] temp = new int[strings.length];
		int counter = 0;
		for (String s : strings) {
			temp[counter] = Integer.valueOf(s);
			counter++;
		}
		Arrays.sort(temp);
		for (int i : temp) {
			avail.add(i);
		}

	}

}
