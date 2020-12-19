package solutions;

import java.io.IOException;
import java.util.HashMap;

import main.AdventUtilities;
import main.Pair;

public class Day18 {

	//static ArrayList<Integer> nums = new ArrayList<>();9
	static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day18");
		long output = 0;
		int line = 0;
		for (String s : strings) {
			line++;
			output += firstPass(s.split(""), 0).k;
			//System.out.println(line + " " + firstPass(s.split(""), 0).k);
		}
		System.out.println(output);

	}

	public static int count(String[] tickets) {
		int count = 0;

		return count;
	}

	public static Pair<Long, Integer> firstPass(String[] problem, int start) {
		int store = -1;
		long last = 0;
		char op = '!';
		for (int i = start; i < problem.length; i++) {
			String s = problem[i];
			//System.out.println(last + " " + op + " " + s);
			try {
				long v = Long.parseLong(s);
				if (op == '+') {
					last += v;
					problem[store] = "" + last;
					problem[i] = " ";
				} else {
					if (op == '*') {
						problem[store] = "" + last;
						last = v;
						store = i;
					} else {
						last = v;
						store = i;
					}

				}
			} catch (NumberFormatException ex) {
				if (s.charAt(0) == '+' || s.charAt(0) == '*') {
					op = s.charAt(0);
				} else {
					if (s.charAt(0) == '(') {
						firstPass(problem, i + 1);
						Pair<Long, Integer> res = math(problem, i + 1);
						long v = res.k;
						if (op == '+') {
							last += v;
							problem[store] = "" + last;
							problem[i] = " ";
						} else {
							if (op == '*') {
								problem[store] = "" + last;
								problem[i] = "" + v;
								last = v;
								store = i;
							} else {
								problem[i] = "" + v;
								last = v;
								store = i;
							}
						}
						i++;
						while (i <= res.v) {
							problem[i] = " ";
							i++;
						}
						i--;
					} else {
						if (s.charAt(0) == ')') {
							//System.out.println("Pass Done!");
							return new Pair<>(last, i);
						}
					}
				}
			}
		}
		return math(problem, 0);
	}

	public static Pair<Long, Integer> math(String[] problem, int start) {
		long out = 0;
		char op = '!';
		for (int i = start; i < problem.length; i++) {
			String s = problem[i];
			//System.out.println(out + " " + op + " " + s);
			try {
				long v = Long.parseLong(s);
				if (op == '+') {
					out += v;
				} else {
					if (op == '*') {
						out *= v;
					} else {
						out = v;
					}
				}
			} catch (NumberFormatException ex) {
				if (s.charAt(0) == '+' || s.charAt(0) == '*') {
					op = s.charAt(0);
				} else {
					if (s.charAt(0) == '(') {
						Pair<Long, Integer> res = math(problem, i + 1);
						long v = res.k;
						if (op == '+') {
							out += v;
						} else {
							if (op == '*') {
								out *= v;
							} else {
								out = v;
							}
						}
						i = res.v;
					} else {
						if (s.charAt(0) == ')') {
							//System.out.println("Math Done!");
							return new Pair<>(out, i);
						}
					}
				}
			}
		}
		return new Pair<>(out, -1000000);
	}

}
