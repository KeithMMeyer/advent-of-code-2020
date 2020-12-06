package day06;

import java.io.IOException;

import main.AdventUtilities;

public class Day06 {

	static String[] questionList = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day06");
		//System.out.println(strings[2]);
		//System.out.println(testField("aaabsfe", "a"));
		System.out.println(scoreGroup(strings, true));
		System.out.println(scoreGroup(strings, false));
	}

	public static int scoreGroup(String[] groups, boolean type) {
		int count = 0;
		boolean[] score = new boolean[questionList.length];
		for (int i = 0; i < groups.length; i++) {
			score = new boolean[score.length];
			while (i < groups.length && !groups[i].equals("")) {
				scoreCard(groups[i], score, type);
				i++;
			}
			for (int x = 0; x < score.length; x++) {
				if (score[x] && type)
					count += 1;
				if (!score[x] && !type)
					count += 1;
			}
		}

		return count;
	}

	public static boolean[] scoreCard(String answer, boolean[] group, boolean type) {
		for (int i = 0; i < questionList.length; i++) {
			if (type && testField(answer, questionList[i]))
				group[i] = true;
			if (!type && !testField(answer, questionList[i]))
				group[i] = true;
		}
		return group;
	}

	public static boolean testField(String answer, String field) {
		return answer.contains(field);
	}

}
