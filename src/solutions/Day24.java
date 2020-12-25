package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import main.AdventUtilities;

public class Day24 {

	static HashSet<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day24");
		for (String s : strings) {
			parse(s.toCharArray());
		}
		System.out.println(set.size());
		daily(100);
		System.out.println(set.size());

	}

	public static void daily(int times) {
		ArrayList<String> updates = new ArrayList<>();

		int size = 17;
		for (int i = 0; i < times; i++) {
			for (int ne = -size; ne < size; ne++) {
				for (int e = -size; e < size; e++) {
					int count = 0;
					count += set.contains((ne + 1) + ":" + (e + -1)) ? 1 : 0;
					count += set.contains((ne + 1) + ":" + (e + 0)) ? 1 : 0;
					count += set.contains((ne + 0) + ":" + (e + -1)) ? 1 : 0;
					count += set.contains((ne + 0) + ":" + (e + 1)) ? 1 : 0;
					count += set.contains((ne + -1) + ":" + (e + 1)) ? 1 : 0;
					count += set.contains((ne + -1) + ":" + (e + 0)) ? 1 : 0;

					if (set.contains(ne + ":" + e) && (count > 2 || count == 0)) {
						updates.add(ne + ":" + e);
					}
					if (!set.contains(ne + ":" + e) && count == 2) {
						updates.add(ne + ":" + e);
					}
				}
			}

			for (String s : updates) {
				if (set.contains(s)) {
					set.remove(s);
				} else {
					set.add(s);
				}
			}
			updates.clear();
			size++;
		}
	}

	public static void parse(char[] dir) {
		ArrayList<String> dirs = new ArrayList<>();
		String current = "";
		for (char c : dir) {
			current += c;
			if (c == 'e' || c == 'w') {
				dirs.add(current);
				current = "";
			}
		}
		int ne = 0;
		int e = 0;
		for (String s : dirs) {
			ne += s.equals("ne") ? 1 : s.equals("sw") ? -1 : 0;
			e += s.equals("e") ? 1 : s.equals("w") ? -1 : 0;

			ne += s.equals("nw") ? 1 : s.equals("se") ? -1 : 0;
			e += s.equals("nw") ? -1 : s.equals("se") ? 1 : 0;
		}

		current = ne + ":" + e;
		if (set.contains(current)) {
			set.remove(current);
		} else {
			set.add(current);
		}
	}

}
