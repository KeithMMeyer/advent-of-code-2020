package main;

import java.util.HashMap;

public class PracticeProblems {

	public static void main(String[] args) {
		System.out.print(isPermutation("hello", "olehh"));
	}

	public static boolean uniqueChars(String str) {
		if (str.length() > 256)
			return false;
		boolean[] chars = new boolean[256]; //alternatively use hashmap for unknown encoding
		for (int i = 0; i < str.length(); i++) {
			if (chars[str.charAt(i) + 0])
				return false;
			chars[str.charAt(i) + 0] = true;
		}

		return true;

	}

	public static boolean isPermutation(String a, String b) {
		if (a.length() != b.length())
			return false;
		HashMap<Character, Integer> set = new HashMap<>();
		for (int i = 0; i < a.length(); i++) {
			int value = set.containsKey(a.charAt(i)) ? set.get(a.charAt(i)) + 1 : 1;
			set.put(a.charAt(i), value);
		}
		for (int i = 0; i < b.length(); i++) {
			if (!set.containsKey(b.charAt(i)) || set.get(b.charAt(i)) > 0) {
				return false;
			}
			set.put(b.charAt(i), set.get(b.charAt(i)) - 1);
		}

		return true;

	}

}
