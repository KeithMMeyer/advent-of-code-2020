package day02;

import java.io.IOException;

import main.AdventUtilities;

public class Day02 {

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day02");
		System.out.println(countMistakes(strings, true));
		System.out.println(countMistakes(strings, false));

	}

	public static int countMistakes(String[] passwords, boolean type) { // n time
		int count = 0;
		for (int i = 0; i < passwords.length; i++) { // n time
			String[] parts = passwords[i].split(" ");
			if (validate(parts[0].split("-")[0], parts[0].split("-")[1], parts[1].substring(0, 1), parts[2], type))
				count++;
		}
		return count;
	}

	public static boolean validate(String min, String max, String c, String password, boolean type) {
		if (type) {
			String match = (("^[^" + c + "]*(" + c + "[^" + c + "]*){" + min + "," + max + "}[^" + c + "]*$"));
			return password.matches(match);
		} else {
			//System.out.print(password.substring(Integer.valueOf(min) + 1, Integer.valueOf(min) + 2) + " ");
			//System.out.print(password.substring(Integer.valueOf(max) + 1, Integer.valueOf(max) + 2) + " ");
			boolean low = password.substring(Integer.valueOf(min) - 1, Integer.valueOf(min)).equals(c);
			boolean high = password.substring(Integer.valueOf(max) - 1, Integer.valueOf(max)).equals(c);
			//System.out.println(low + " " + high + " " + ((low || high) && !(low && high)));
			return (low || high) && !(low && high);
		}

	}

}
