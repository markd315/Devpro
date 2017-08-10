import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOStreams {
	public static void main(String[] args) throws IOException {
		// Goal: find the smallest prime divisor of a target integer.
		BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
		PrintWriter out = new PrintWriter(bw);
		int i = 1;
		while (i < 100000) {
			if (isPrime(i))
				out.println(i);// Can write an int, String, char, etc.
			i++;
		}
		out.close();// Make sure to close or flush before you think about using
					// this file for anything else!
		// So our file is now filled with a ton of primes allowing us to save
		// work on repetitions of numbers up to 10,000^2.
		BufferedReader br = new BufferedReader(new FileReader("out.txt"));
		int target = 542255;
		int remaining = target;
		int nextPrime = 1;
		String factorization = "";
		while (remaining > 1) {
			do {
				nextPrime = Integer.parseInt(br.readLine());
			} while (remaining % nextPrime != 0);
			while (remaining % nextPrime == 0) {
				remaining /= nextPrime;
				factorization += nextPrime + ", ";
			}
		}
		System.out.println("prime factorization of " + target + " is " + factorization);
	}

	private static boolean isPrime(int k) {
		// implemented by trial division up to sqrt(k);
		if(k==1)
			return false;
		int stop = (int) Math.sqrt(k);
		int i = 2;
		while (i <= stop) {
			if (k % i == 0)
				return false;
			i++;
		}
		return true;
	}
}
