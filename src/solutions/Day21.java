package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import main.AdventUtilities;
import main.Pair;

public class Day21 {

	static HashMap<String, ArrayList<String>> known = new HashMap<>();
	static ArrayList<Pair<ArrayList<String>, ArrayList<String>>> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day21");
		init(strings);
		figureOut();
		int count = 0;
		for (Pair<ArrayList<String>, ArrayList<String>> food : list) {
			count += food.k.size() - food.v.size();
		}
		System.out.println(count);

		String[] arr = Arrays.copyOf(known.keySet().toArray(), known.size(), String[].class);
		Arrays.sort(arr);
		String danger = known.get(arr[0]).get(0);
		for (int i = 1; i < arr.length; i++) {
			danger += "," + known.get(arr[i]).get(0);
		}
		System.out.println(danger);

	}

	public static void figureOut() {
		for (String allergen : known.keySet()) {
			for (Pair<ArrayList<String>, ArrayList<String>> food : list) {
				if (food.v.contains(allergen)) {
					Iterator<String> itr = known.get(allergen).iterator();
					while (itr.hasNext()) {
						String ing = itr.next();
						if (!food.k.contains(ing)) {
							itr.remove();
						}
					}
				}
			}
		}
		boolean tooBig = true;
		while (tooBig) {
			tooBig = false;
			for (String allergen : known.keySet()) {
				tooBig = known.get(allergen).size() > 1 ? true : tooBig;
				if (known.get(allergen).size() > 1) {
					Iterator<String> itr = known.get(allergen).iterator();
					while (itr.hasNext()) {
						String ing = itr.next();
						for (ArrayList<String> ings : known.values()) {
							if (ings.size() == 1 && known.get(allergen) != ings && ings.contains(ing))
								itr.remove();
						}
					}
				}
			}
		}

		for (String allergen : known.keySet()) {
			for (Pair<ArrayList<String>, ArrayList<String>> food : list) {
				if (food.k.contains(known.get(allergen).get(0)) && !food.v.contains(allergen)) {
					food.v.add(allergen);
				}
			}
		}
	}

	public static void init(String[] foods) {
		for (String f : foods) {
			String[] parts = f.split(" \\(contains ");
			String[] ingrediants = parts[0].split(" ");
			parts[1] = parts[1].substring(0, parts[1].length() - 1);
			String[] allergens = parts[1].split(", ");
			ArrayList<String> ing = new ArrayList<>();
			ArrayList<String> alle = new ArrayList<>();
			for (String s : ingrediants) {
				ing.add(s);
			}
			for (String s : allergens) {
				alle.add(s);
				if (!known.containsKey(s))
					known.put(s, (ArrayList<String>) ing.clone());
			}
			Pair<ArrayList<String>, ArrayList<String>> pair = new Pair<>(ing, alle);
			list.add(pair);
		}
	}

}
