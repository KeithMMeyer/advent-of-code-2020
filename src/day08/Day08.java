package day08;

import java.io.IOException;
import java.util.HashSet;

import main.AdventUtilities;

public class Day08 {

	static int acc = 0;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day08");
		repair(strings);
		System.out.println(acc);

	}

	public static void repair(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			acc = 0;
			set.clear();
			String cmd = strings[i].split(" ")[0];
			switch (cmd) {
			case "nop":
				strings[i] = "jmp" + " " + strings[i].split(" ")[1];
				if (runner(strings) == 0) {
					return;
				}
				strings[i] = "nop" + " " + strings[i].split(" ")[1];
				break;
			case "jmp":
				strings[i] = "nop" + " " + strings[i].split(" ")[1];
				if (runner(strings) == 0) {
					return;
				}
				strings[i] = "jmp" + " " + strings[i].split(" ")[1];
			default:
				continue;
			}
		}
	}

	public static int runner(String[] strings) {
		int line = 0;
		while (line < strings.length) {
			System.out.println(acc);
			if (set.contains(line))
				return -1;
			set.add(line);
			line = processLine(strings[line], line);
		}
		return 0;
	}

	public static int processLine(String command, int line) {
		String cmd = command.split(" ")[0];
		System.out.println(command);
		switch (cmd) {
		case "acc":
			acc += Integer.valueOf(command.split(" ")[1]);
			return ++line;
		case "jmp":
			return line + Integer.valueOf(command.split(" ")[1]);
		default:
			return ++line;
		}
	}

}
