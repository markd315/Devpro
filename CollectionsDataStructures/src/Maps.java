import java.util.*;

public class Maps {
    public static void main(String[] args) {
	Map<Integer, Boolean> hashMap = new TreeMap<Integer, Boolean>();
	long before = System.nanoTime();
	for (int i = 0; i < 10001; i++) {
	    if (isPrime(i)) {
		hashMap.put(i, true);
	    } else {
		hashMap.put(i, false);
	    }
	}
	System.out.println(System.nanoTime() - before + " to initialize HashMap");
	int n = 0;
	Random rng = new Random();
	before = System.nanoTime();
	for (int i = 0; i < 10000000; i++) {
	    int r = rng.nextInt(10001);
	    if (hashMap.get(r) == true) {
		n++;
	    }
	}
	System.out.println((System.nanoTime() - before) + " to do hashmap lookups");
	before = System.nanoTime();
	n=0;
	for (int i = 0; i < 10000000; i++) {
	    int r = rng.nextInt(10001);
	    if (isPrime(r)) {
		n++;
	    }
	}
	System.out.println((System.nanoTime() - before) + " to do trial division");
	
    }

    private static boolean isPrime(int k) {
	if (k == 1)
	    return false;
	int stop = (int) Math.sqrt(k);
	int i = 2;
	while (i <= stop) {
	    if (k % i == 0) {
		return false;
	    }
	    i++;
	}
	return true;
    }
}
