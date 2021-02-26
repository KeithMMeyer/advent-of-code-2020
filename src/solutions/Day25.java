package solutions;

import java.util.HashSet;

public class Day25 {

	static HashSet<String> set = new HashSet<>();

	public static void main(String[] args) {
		long card = 12090988;
		long door = 240583;

		long v = 1;
		int i = 0;
		while (v != card) {
			v = (7 * v) % 20201227;
			i++;
		}

		v = 1;
		for (int x = 0; x < i; x++) {
			v = (door * v) % 20201227;
		}
		System.out.println(v);
	}

}
