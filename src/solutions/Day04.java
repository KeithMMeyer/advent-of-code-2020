package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day04 {

	public static void main(String[] args) throws IOException {

		String[] strings = reader("day04");
		String[] fields = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

		int count = 0;
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			mapPassport(strings[i], map);
			if (testValues(fields, map)) {
				count++;
			}
			map.clear();
		}
		System.out.println(count);
	}

	public static boolean testValues(String[] fields, HashMap<String, String> map) {
		String byr = "^(19[2-9][0-9]|200[0-2])$";
		String iyr = "^(201[0-9]|2020)$";
		String eyr = "^(202[0-9]|2030)$";
		String hgt = "^((1[5-8][0-9]cm|19[0-3]cm)|(59in|6[0-9]in|7[0-6]in))$";
		String hcl = "^(#[0-9a-f]{6})$";
		String ecl = "^(amb|blu|brn|gry|grn|hzl|oth)$";
		String pid = "^([0-9]{9})$";
		String[] tests = { byr, iyr, eyr, hgt, hcl, ecl, pid };
		for (int i = 0; i < fields.length; i++) {
			if (!(map.containsKey(fields[i]) && map.get(fields[i]).matches(tests[i]))) {
				return false;
			}
		}

		return true;
	}

	public static boolean hasFields(String[] fields, HashMap<String, String> map) {
		for (int i = 0; i < fields.length; i++) {
			if (!map.containsKey(fields[i])) {
				return false;
			}
		}
		return true;
	}

	public static HashMap<String, String> mapPassport(String line, HashMap<String, String> map) {
		String[] parts = line.split(" ");
		for (int i = 0; i < parts.length; i++) {
			map.put(parts[i].split(":")[0], parts[i].split(":")[1]);
		}
		return map;
	}

	public static String[] reader(String file) { // custom reader to handle multi-line entries
		String fullPath = "res/" + file + ".txt";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fullPath));
			ArrayList<String> lines = new ArrayList<String>();

			String line;
			String linePart;
			while ((line = reader.readLine()) != null) {
				while ((linePart = reader.readLine()) != null && !linePart.equals(""))
					line += " " + linePart;

				lines.add(line);
				System.out.println(line);
			}
			reader.close();

			return lines.toArray(new String[] {}); //for performance
		} catch (IOException e) {
			return null;
		}
	}

}
