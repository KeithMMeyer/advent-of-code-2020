package solutions;

import java.io.IOException;
import java.util.HashMap;

import main.AdventUtilities;

public class Day12 {

	static HashMap<Integer, Integer> map = new HashMap<>();

	static int x = 0;
	static int y = 0;
	static int sx = 0;
	static int sy = 0;
	static int dir = 0;

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day12");
		run(strings, true);
		System.out.println(Math.abs(x) + Math.abs(y));
		x = 10;
		y = 1;
		run(strings, false);
		System.out.println(Math.abs(sx) + Math.abs(sy));
	}

	public static void run(String[] strings, boolean type) {
		for (String s : strings) {
			processLine(s, type);
			//System.out.println(s + " " + sx + " " + sy);
		}
	}

	public static void processLine(String command, boolean type) {
		char cmd = command.charAt(0);
		int value = Integer.valueOf(command.substring(1));
		switch (cmd) {
		case 'N':
			y += value;
			return;
		case 'S':
			y -= value;
			return;
		case 'E':
			x += value;
			return;
		case 'W':
			x -= value;
			return;
		case 'R':
			if (type) {
				dir = (dir + value) % 360;
				if (dir < 0)
					dir += 360;
			} else {
				for (int i = 0; i < value; i += 90) {
					int t = y;
					y = -x;
					x = t;
				}
			}
			return;
		case 'L':
			if (type) {
				dir = (dir - value) % 360;
				if (dir < 0)
					dir += 360;
			} else {
				for (int i = 0; i < value; i += 90) {
					int t = x;
					x = -y;
					y = t;
				}
			}
			return;
		case 'F':
			if (type) {
				x += dir == 0 ? value : dir == 180 ? -value : 0;
				y += dir == 90 ? -value : dir == 270 ? value : 0;
			} else {
				sx += x * value;
				sy += y * value;
			}
			return;
		default:
			return;
		}
	}

}
