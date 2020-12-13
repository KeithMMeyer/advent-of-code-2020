package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdventUtilities {

	public static String[] reader(String file) {
		String fullPath = "res/" + file + ".txt";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fullPath));
			ArrayList<String> lines = new ArrayList<String>();

			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();

			return lines.toArray(new String[] {}); //for performance
		} catch (IOException e) {
			return null;
		}
	}

	private static long inv(long a, long m) {
		long m0 = m, t, q;
		long x0 = 0, x1 = 1;

		if (m == 1)
			return 0;

		// Apply extended Euclid Algorithm 
		while (a > 1) {
			// q is quotient 
			q = a / m;

			t = m;

			// m is remainder now, process 
			// same as euclid's algo 
			m = a % m;
			a = t;

			t = x0;

			x0 = x1 - q * x0;

			x1 = t;
		}

		// Make x1 positive 
		if (x1 < 0)
			x1 += m0;

		return x1;
	}

	/*
	 * Chinese Remainder theorem method provided by Nikita Tiwari.
	 */
	public static long crt(int num[], int rem[], int k) {
		// Compute product of all numbers 
		long prod = 1;
		for (int i = 0; i < k; i++)
			prod *= num[i];

		// Initialize result 
		long result = 0;

		// Apply above formula 
		for (int i = 0; i < k; i++) {
			long pp = prod / num[i];
			result += rem[i] * inv(pp, num[i]) * pp;
		}

		return result % prod;
	}
}
