package day04;

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
			if (hasFields(fields, map)) {
				count++;
			}
			map.clear();
		}
		System.out.println(count);
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
