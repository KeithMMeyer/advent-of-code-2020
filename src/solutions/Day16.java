package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.AdventUtilities;

public class Day16 {

	//static ArrayList<Integer> nums = new ArrayList<>();
	static HashMap<String, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> map = new HashMap<>();

	static class Pair<S, T> { // very lightweight pair class to avoid javafx
		public S k;
		public T v;

		public Pair(S key, T value) {
			k = key;
			v = value;
		}

		public String toString() {
			return "( " + k + ", " + v + " )";
		}
	}

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day16");
		put(strings);
		System.out.println(map);
		System.out.println(count(strings));
		long mult = 1;
		for (int i = 0; i < 20; i++) {
			ArrayList<Integer> val = findField(strings, strings[i].split(": ")[0]);
		}
		int[] values = { 18, 17, 10, 19, 6, 7 };
		for (int i = 0; i < values.length; i++) {
			System.out.println(strings[22].split(",")[values[i]]);
			mult *= Integer.valueOf(strings[22].split(",")[values[i]]);
		}
		System.out.println(mult);

	}

	public static ArrayList<Integer> findField(String[] tickets, String field) {
		boolean[] notPossible = new boolean[20];
		for (int i = 25; i < tickets.length; i++) {
			String[] fields = tickets[i].split(",");
			boolean toDo = true;
			for (int x = 0; x < fields.length; x++) {
				if (!eval(Integer.valueOf(fields[x]), "all")) {
					toDo = false;
				}
			}
			if (toDo) {
				for (int x = 0; x < fields.length; x++) {
					if (!notPossible[x]) {
						if (!eval(Integer.valueOf(fields[x]), field)) {
							notPossible[x] = true;
						}
					}
				}
			}
		}
		ArrayList<Integer> possible = new ArrayList<>();
		for (int i = 0; i < notPossible.length; i++) {
			if (!notPossible[i]) {
				possible.add(i);
			}
		}
		return possible;
	}

	public static int count(String[] tickets) {
		int count = 0;
		for (int i = 25; i < tickets.length; i++) {
			String[] fields = tickets[i].split(",");
			for (int x = 0; x < fields.length; x++) {
				if (!eval(Integer.valueOf(fields[x]), "all")) {
					count += Integer.valueOf(fields[x]);
					//break;
				}
			}
		}
		return count;
	}

	public static boolean eval(int value, String field) {
		if (field.equals("all")) {
			for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair : map.values()) {
				if ((pair.k.k <= value && pair.k.v >= value) || (pair.v.k <= value && pair.v.v >= value))
					return true;
			}
		} else {
			Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair = map.get(field);
			if ((pair.k.k <= value && pair.k.v >= value) || (pair.v.k <= value && pair.v.v >= value))
				return true;
		}
		return false;
	}

	public static void put(String[] fields) {
		for (int i = 0; i < 20; i++) {
			String ranges = fields[i].split(": ")[1];
			int first = Integer.valueOf(ranges.split(" or ")[0].split("-")[0]);
			int second = Integer.valueOf(ranges.split(" or ")[0].split("-")[1]);
			Pair<Integer, Integer> one = new Pair<>(first, second);
			first = Integer.valueOf(ranges.split(" or ")[1].split("-")[0]);
			second = Integer.valueOf(ranges.split(" or ")[1].split("-")[1]);
			Pair<Integer, Integer> two = new Pair<>(first, second);
			map.put(fields[i].split(": ")[0], new Pair<>(one, two));
		}
	}

}
