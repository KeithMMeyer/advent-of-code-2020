package solutions;

import java.io.IOException;
import java.util.HashMap;

import main.AdventUtilities;

public class Day20 {

	//static ArrayList<Integer> nums = new ArrayList<>();
	static HashMap<Integer, Tile> map = new HashMap<>();

	static class Tile {

		int id;
		String[] inner = new String[8];

		int adjacent = 0;
		String[] edges = new String[4];

		Tile[] connections = new Tile[4];

		public Tile(int id) {
			this.id = id;
		}

		public void connect(Tile tile) {
			//System.out.println(this + " " + tile);
			int i = 0;
			for (String edge : edges) {
				if (connections[i] == null) {
					int x = 0;
					for (String other : tile.edges) {
						if (tile.connections[x] == null) {
							if (matches(edge, other)) {
								connections[i] = tile;
								tile.connections[x] = this;
								adjacent++;
								tile.adjacent++;
								return;
							}
						}
						x++;
					}
				}
				i++;
			}
		}

		private boolean matches(String a, String b) {
			if (a.equals(b) || flipString(a).equals(b)) {
				return true;
			}
			return false;
		}

		private String flipString(String a) {
			byte[] strAsByteArray = a.getBytes();
			byte[] result = new byte[strAsByteArray.length];

			// Store result in reverse order into the
			// result byte[]
			for (int i = 0; i < strAsByteArray.length; i++)
				result[i] = strAsByteArray[strAsByteArray.length - i - 1];

			return new String(result);
		}

		public String toString() {
			String out = id + ": | ";
			for (String s : edges)
				out += s + " | ";
			return out;
		}
	}

	int total = 4512;

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day20");
		init(strings);

		String ids = "";
		long out = 1;
		for (Tile a : map.values()) {
			for (Tile b : map.values()) {
				if (a != b) {
					a.connect(b);
				}
			}
			if (a.adjacent < 3) {
				ids += a.id + ":" + a.adjacent + " ";
				out *= a.id;
			}
		}

		//System.out.println(map.get(2311) + "\n" + map.get(3079));
		System.out.println(ids);
		System.out.println(out);
	}

	public static void init(String[] tiles) {
		Tile current = null;
		String last = null;
		String left = "";
		String right = "";

		for (String s : tiles) {
			if (s.equals("")) {
				current.edges[1] = right;
				current.edges[2] = last;
				current.edges[3] = left;
				left = "";
				right = "";
				last = null;
				map.put(current.id, current);
			} else {
				if (s.contains("Tile")) {
					current = new Tile(Integer.valueOf(s.substring(5, s.length() - 1)));
				} else {
					if (last == null) {
						current.edges[0] = s;
					}
					last = s;
					left += s.charAt(0);
					right += s.charAt(s.length() - 1);
				}
			}
		}
	}

}
