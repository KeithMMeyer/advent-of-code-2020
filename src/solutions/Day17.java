package solutions;

import java.io.IOException;
import java.util.ArrayList;

import main.AdventUtilities;

public class Day17 {

	static HyperCube cube;
	static ArrayList<HyperCube> wCube = new ArrayList<>();

	static class HyperCube {
		public int x;
		public int y;
		public int z;

		ArrayList<ArrayList<String>> cubeList = new ArrayList<>();

		public HyperCube() {

		}

		public HyperCube(String[] start) {
			ArrayList<String> first = new ArrayList<>();
			for (String s : start) {
				first.add(s);
			}
			cubeList.add(first);
			x = start[0].length();
			y = start.length;
			z = 1;
		}

		public static HyperCube createBlank(ArrayList<String> blanks, int count) {
			HyperCube blank = new HyperCube();
			for (int i = 0; i < count; i++) {
				blank.cubeList.add((ArrayList<String>) blanks.clone());
			}
			blank.y = blanks.size();
			blank.z = count;
			blank.x = blanks.get(0).length();
			return blank;
		}

		public void expand() {
			this.x += 2;
			this.y += 2;
			this.z += 2;
			int len = cubeList.get(0).get(0).length();
			String blank = "";
			for (int i = 0; i < len; i++) {
				blank += ".";
			}
			ArrayList<String> blanks = new ArrayList<>();
			for (int i = 0; i < cubeList.get(0).size(); i++) {
				blanks.add(blank);
			}
			cubeList.add(0, blanks);
			cubeList.add((ArrayList<String>) blanks.clone());

			for (int z = 0; z < cubeList.size(); z++) {
				cubeList.get(z).add(0, blank);
				cubeList.get(z).add(blank);
				for (int y = 0; y < cubeList.get(z).size(); y++) {
					cubeList.get(z).set(y, "." + cubeList.get(z).get(y) + ".");
				}

			}
		}

		public void set(int x, int y, int z, char val) {
			String curr = cubeList.get(z).get(y);
			cubeList.get(z).set(y, curr.substring(0, x) + val + curr.substring(x + 1));
		}

		public char get(int x, int y, int z) {
			if (z < 0 || z >= cubeList.size() || y < 0 || y >= cubeList.get(z).size() || x < 0
					|| x >= cubeList.get(z).get(y).length())
				return '.';
			return cubeList.get(z).get(y).charAt(x);
		}

		public String toString() {
			StringBuilder out = new StringBuilder();
			for (ArrayList<String> a : cubeList) {
				out.append("\n");
				for (String s : a) {
					out.append(s + "\n");
				}
			}
			return out.toString();
		}
	}

	public static void main(String[] args) throws IOException {

		String[] strings = AdventUtilities.reader("day17");
		cube = new HyperCube(strings);
		System.out.println(run3D(6));

		cube = new HyperCube(strings);
		wCube.add(cube);
		System.out.println(run4D(6));
	}

	public static long run3D(int reps) {
		ArrayList<String> updates = new ArrayList<>();
		for (int i = 0; i < reps; i++) {
			cube.expand();
			for (int z = 0; z < cube.z; z++) {
				for (int y = 0; y < cube.y; y++) {
					for (int x = 0; x < cube.x; x++) {
						int actives = countActive(x, y, z);
						if ((actives == 2 && cube.get(x, y, z) == '#') || actives == 3) {
							updates.add(x + " " + y + " " + z + " #");
						} else {
							updates.add(x + " " + y + " " + z + " .");
						}
					}
				}
			}
			for (String s : updates) {
				String[] p = s.split(" ");
				cube.set(Integer.valueOf(p[0]), Integer.valueOf(p[1]), Integer.valueOf(p[2]), p[3].charAt(0));
			}
			updates.clear();
		}
		int count = 0;
		for (int z = 0; z < cube.z; z++) {
			for (int y = 0; y < cube.y; y++) {
				for (int x = 0; x < cube.x; x++) {
					if (cube.get(x, y, z) == '#')
						count++;
				}
			}
		}
		return count;
	}

	public static long run4D(int reps) {
		ArrayList<String> updates = new ArrayList<>();
		for (int i = 0; i < reps; i++) {
			expand();
			for (int w = 0; w < wCube.size(); w++) {
				for (int z = 0; z < wCube.get(w).z; z++) {
					for (int y = 0; y < wCube.get(w).y; y++) {
						for (int x = 0; x < wCube.get(w).x; x++) {
							int actives = countActive(x, y, z, w);
							if ((actives == 2 && getPos(x, y, z, w) == '#') || actives == 3) {
								updates.add(x + " " + y + " " + z + " " + w + " #");
							} else {
								updates.add(x + " " + y + " " + z + " " + w + " .");
							}
						}
					}
				}
			}
			for (String s : updates) {
				String[] p = s.split(" ");
				wCube.get(Integer.valueOf(p[3])).set(Integer.valueOf(p[0]), Integer.valueOf(p[1]),
						Integer.valueOf(p[2]), p[4].charAt(0));
			}
			updates.clear();
		}

		int count = 0;
		for (int w = 0; w < wCube.size(); w++) {
			for (int z = 0; z < wCube.get(w).z; z++) {
				for (int y = 0; y < wCube.get(w).y; y++) {
					for (int x = 0; x < wCube.get(w).x; x++) {
						if (getPos(x, y, z, w) == '#')
							count++;
					}
				}
			}
		}
		return count;
	}

	public static void expand() {
		for (HyperCube c : wCube) {
			c.expand();
		}
		int len = wCube.get(0).x;
		String blank = "";
		for (int i = 0; i < len; i++) {
			blank += ".";
		}
		ArrayList<String> blanks = new ArrayList<>();
		for (int i = 0; i < wCube.get(0).y; i++) {
			blanks.add(blank);
		}

		wCube.add(0, HyperCube.createBlank(blanks, wCube.get(0).z));
		wCube.add(HyperCube.createBlank(blanks, wCube.get(0).z));

	}

	public static int countActive(int x, int y, int z, int w) {
		int count = 0;
		if (getPos(x, y, z, w) == '#') {
			count--;
		}
		for (int i = 0; i < 81; i++) {
			if (getPos(x + (i % 3) - 1, y + ((i / 3) % 3) - 1, z + (((i / 3) / 3) % 3 - 1),
					w + (((i / 3) / 3) / 3 - 1)) == '#') {
				count++;
			}
		}
		return count;
	}

	public static int countActive(int x, int y, int z) {
		int count = 0;
		if (cube.get(x, y, z) == '#') {
			count--;
		}
		for (int i = 0; i < 27; i++) {
			if (cube.get(x + (i % 3) - 1, y + ((i / 3) % 3) - 1, z + ((i / 3) / 3 - 1)) == '#') {
				count++;
			}
		}
		return count;
	}

	public static char getPos(int x, int y, int z, int w) {
		if (w < 0 || w >= wCube.size())
			return '.';
		return wCube.get(w).get(x, y, z);
	}

}
