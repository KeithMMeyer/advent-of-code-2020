package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.AdventUtilities;

public class Day14 {

	static HashMap<Long, Long> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day14");
		System.out.println(memory(strings, true));
		map.clear();
		System.out.println(memory(strings, false));
	}

	public static long memory(String[] strings, boolean type) {
		long count = 0;

		char[] mask = {};
		for (String s : strings) {
			if (s.charAt(1) == 'a') {
				mask = s.split(" = ")[1].toCharArray();
			} else {
				if (type) {
					long value = masker(Long.valueOf(s.split(" = ")[1]), mask);
					if (value > 0) {
						long key = Long.valueOf(s.split(" = ")[0].substring(4, s.split(" = ")[0].length() - 1));
						map.put(key, value);
					}
				} else {
					masker2(Long.valueOf(s.split(" = ")[0].substring(4, s.split(" = ")[0].length() - 1)), mask,
							Long.valueOf(s.split(" = ")[1]));
				}
			}
		}

		for (

		Long value : map.values())
			count += value;
		return count;
	}

	public static void masker2(long x, char[] mask, long value) {
		if (value < 0)
			return;
		String val = expander(Long.toBinaryString(x));
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		for (int i = 0; i < mask.length; i++) {
			if (mask[i] == '1') {
				for (int z = 0; z < list.size(); z++) {
					list.set(z, list.get(z) + mask[i]);
				}
			} else {
				if (mask[i] == 'X') {
					int max = list.size();
					for (int z = 0; z < max; z++) {
						list.add(list.get(z) + 1);
						list.set(z, list.get(z) + 0);
					}
				} else {
					for (int z = 0; z < list.size(); z++) {
						list.set(z, list.get(z) + val.charAt(i));
					}
				}
			}
		}
		for (String s : list) {
			map.put(Long.valueOf(s, 2), value);
		}
	}

	public static long masker(long x, char[] mask) {
		String val = expander(Long.toBinaryString(x));
		String output = "";
		for (int i = 0; i < mask.length; i++) {
			if (mask[i] != 'X') {
				output += mask[i];
			} else {
				output += val.charAt(i);
			}
		}
		return Long.valueOf(output, 2);
	}

	public static String expander(String num) {
		String output = "";
		if (num.length() < 36) {
			for (int i = 0; i < 36 - num.length(); i++) {
				output += "0";
			}
		}
		output += num;
		return output;
	}

}
