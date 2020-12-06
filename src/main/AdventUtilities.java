package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdventUtilities {

	public static String[] reader(String file) {
		String fullPath = "res/" + file + ".txt";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fullPath));
			ArrayList<String> lines = new ArrayList<String>();

			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();

			return lines.toArray(new String[] {}); //for performance
		} catch (IOException e) {
			return null;
		}
	}
}
