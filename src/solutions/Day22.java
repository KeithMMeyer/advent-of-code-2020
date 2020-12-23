package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import main.AdventUtilities;
import main.Pair;

public class Day22 {

	static HashSet<String> set = new HashSet<>();
	static List<Integer> player1 = new ArrayList<>();
	static List<Integer> player2 = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day22");
		init(strings);

		Pair<List<Integer>, List<Integer>> temp;
		//		while (player1.size() > 0 && player2.size() > 0) {
		//			temp = processHand(player1, player2);
		//			player1 = temp.k;
		//			player2 = temp.v;
		//		}
		//		if (player1.size() == 0)
		//			player1 = player2;
		//
		//		long total = 0;
		//		for (int i = 0; i < player1.size(); i++) {
		//			total += player1.get(i) * (player1.size() - i);
		//		}
		//		System.out.println(total);
		//
		//		player1.clear();
		//		player2.clear();
		//init(strings);

		temp = newRules(player1, player2, set);
		player1 = player1.size() > player2.size() ? temp.k : temp.v;
		System.out.println(player1 + "\n" + player2 + "\n");
		long total = 0;
		for (int i = 0; i < player1.size(); i++) {
			total += player1.get(i) * (player1.size() - i);
		}
		System.out.println(total);
	}

	public static Pair<List<Integer>, List<Integer>> newRules(List<Integer> temp1, List<Integer> temp2,
			HashSet<String> set) {
		List<Integer> player1 = new ArrayList<>();
		List<Integer> player2 = new ArrayList<>();
		for (Integer i : temp1) {
			player1.add(i);
		}
		for (Integer i : temp2) {
			player2.add(i);
		}
		while (player1.size() > 0 && player2.size() > 0) {
			//System.out.println(player1 + "\n" + player2 + "\n");
			String hash = "";
			for (Integer i : player1) {
				hash += i.hashCode() + ".";
			}
			hash += ":";
			for (Integer i : player2) {
				hash += i.hashCode() + ".";
			}
			if (set.contains(hash))
				return new Pair<List<Integer>, List<Integer>>(player1, new ArrayList<Integer>());
			set.add(hash);

			int a = player1.get(0);
			int b = player2.get(0);
			if (a <= player1.size() - 1 && b <= player2.size() - 1) {
				int compare = newRules(player1.subList(1, player1.get(0) + 1), player2.subList(1, player2.get(0) + 1),
						new HashSet<String>()).k.size();
				boolean winner = compare > 0;
				//System.out.println("=>" + player1 + "\n=>" + player2);
				//System.out.println(winner + " " + compare);
				if (winner) {
					player1.add(player1.get(0));
					player1.add(player2.get(0));
				} else {
					player2.add(player2.get(0));
					player2.add(player1.get(0));
				}
				player1.remove(0);
				player2.remove(0);
			} else {
				processHand(player1, player2);
			}
		}

		return new Pair<List<Integer>, List<Integer>>(player1, player2);
	}

	public static Pair<List<Integer>, List<Integer>> processHand(List<Integer> player1, List<Integer> player2) {
		if (player1.get(0) > player2.get(0)) {
			player1.add(player1.get(0));
			player1.add(player2.get(0));
		}

		if (player2.get(0) > player1.get(0)) {
			player2.add(player2.get(0));
			player2.add(player1.get(0));
		}
		if (player1.get(0) == player2.get(0)) {
			System.out.println("Help: " + player1 + " " + player2);
		}
		player1.remove(0);
		player2.remove(0);
		return new Pair<List<Integer>, List<Integer>>(player1, player2);
	}

	public static void init(String[] strings) {
		for (int i = 1; i < 26; i++) {
			player1.add(Integer.valueOf(strings[i]));
		}
		for (int i = 28; i < strings.length; i++) {
			player2.add(Integer.valueOf(strings[i]));
		}
	}

}
