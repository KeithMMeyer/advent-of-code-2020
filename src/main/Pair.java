package main;

public class Pair<S, T> { // very lightweight pair class to avoid using javafx
	public S k;
	public T v;

	public Pair(S key, T value) {
		k = key;
		v = value;
	}

	public String toString() {
		return "( " + k + ", " + v + " )";
	}
}
