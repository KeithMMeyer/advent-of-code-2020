package solutions;

import java.io.IOException;
import java.util.HashMap;

import main.AdventUtilities;

public class Day19 {

	static HashMap<String, String> map = new HashMap<>();
	static HashMap<Integer, String> regMap = new HashMap<>();
	static int a;
	static int b;

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day19");
		init(strings, 132);
		//init(strings, 31);
		System.out.println(map);
		regMap.put(a, "a");
		regMap.put(b, "b");
		regMap.put(8, getRegex(42) + "+");
		regMap.put(11, getRegex(42) + "(" + getRegex(42) + "(" + getRegex(42) + "(" + getRegex(42) + getRegex(31) + ")?"
				+ getRegex(31) + ")?" + getRegex(31) + ")?" + getRegex(31));
		System.out.println(getRegex(0));
		regMap.put(0, "^" + regMap.get(0) + "$");
		//System.out.println("bbabbbbaabaabba".matches(getRegex(0)));
		System.out.println(match(strings, 134));

	}

	public static void init(String[] rules, int end) {
		for (int i = 0; i < end; i++) {
			String[] rule = rules[i].split(": ");
			if (rule[1].equals("\"a\"")) {
				a = Integer.valueOf(rule[0]);
			} else {
				if (rule[1].equals("\"b\"")) {
					b = Integer.valueOf(rule[0]);
				} else {
					map.put(rule[0], rule[1]);
				}
			}
		}
	}

	public static void generateRegex() {
		for (HashMap.Entry<String, String> entry : map.entrySet()) {

		}
	}

	public static String getRegex(int key) {
		if (regMap.containsKey(key))
			return regMap.get(key);
		return toRegex(key, map.get("" + key));
	}

	public static String toRegex(int key, String rule) {
		String out = "(";
		String[] or = rule.split(" \\| ");
		for (int i = 0; i < or.length; i++) {
			String[] parts = or[i].split(" ");
			if (or.length > 1) {
				out += "(";
			}
			for (String p : parts) {
				out += getRegex(Integer.valueOf(p));
			}
			if (or.length > 1 && i == 0) {
				out += ")|(";
			}
			if (or.length > 1) {
				out += ")";
			}
		}
		out += ")";
		regMap.put(key, out);
		return out;
	}

	public static int match(String[] messages, int start) {
		int count = 0;
		for (int i = start; i < messages.length; i++) {
			if (messages[i].matches(getRegex(0))) {
				count++;
			}
		}
		return count;
	}

}
