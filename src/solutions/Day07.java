package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.AdventUtilities;

public class Day07 {

	static ArrayList<String> colors = new ArrayList<>();
	static ArrayList<String> set = new ArrayList<>();
	static HashMap<String, String[]> nums = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day07");
		HashMap<String, String[]> map = mapLoader(strings);
		set.add("shiny gold");
		System.out.println(outerLoop(map));
		System.out.println(bagCounter("shiny gold", map) - 1);

	}

	public static int bagCounter(String color, HashMap<String, String[]> map) {
		int count = 1;
		String[] colors = map.get(color);
		if (colors == null) {
			return count;
		}
		String[] numbers = nums.get(color);
		for (int i = 0; i < colors.length; i++) {
			//System.out.println(Arrays.toString(numbers));
			count += Integer.valueOf(numbers[i]) * bagCounter(colors[i], map);
		}
		return count;
	}

	public static int outerLoop(HashMap<String, String[]> map) {
		int count = 0;
		for (int i = 0; i < set.size(); i++) {
			String c = set.get(i);
			//System.out.println(c);
			count += colorCounter(c, map);
		}
		return count;
	}

	public static int colorCounter(String color, HashMap<String, String[]> map) {
		int count = 0;
		for (String c : colors) {
			//System.out.println(color + " " + c);
			String[] matches = map.get(c);
			for (int i = 0; i < matches.length; i++) {
				if (matches[i].equals(color)) {
					if (!set.contains(c)) {
						count++;
						set.add(c);
					}
				}
			}
		}
		return count;
	}

	public static HashMap<String, String[]> mapLoader(String[] strings) {
		HashMap<String, String[]> map = new HashMap<>();

		for (int i = 0; i < strings.length; i++) {
			String numbers = strings[i].replaceAll("\\D+", "x").substring(1);
			if (numbers.length() != 0) {
				numbers = numbers.substring(0, numbers.length() - 1);
			} else {
				continue;
			}
			//System.out.println(numbers);
			strings[i] = strings[i].replaceAll("bags*|\\d+|\\.", "");
			strings[i] = strings[i].replaceAll("\\s+", " ");
			String key = strings[i].substring(0, strings[i].indexOf("contain")).trim();
			String[] values = strings[i].substring(strings[i].indexOf("contain") + 7).split(",");
			for (int x = 0; x < values.length; x++) {
				values[x] = values[x].trim();
				//System.out.println(values[x]);
			}
			colors.add(key);
			map.put(key, values);
			nums.put(key, numbers.split("x"));
		}

		return map;
	}

}
