import java.io.*;

public class Primes {
    public static void main(String[] args) throws IOException {
	FileWriter fw = new FileWriter("out.txt");
	BufferedWriter bw = new BufferedWriter(fw);
	PrintWriter out = new PrintWriter(bw);
	int i = 1;
	while(i<10000){
	    if(isPrime(i)){
		out.println(i);
	    }
	    i++;
	}
	out.close();
	BufferedReader br = new BufferedReader(new FileReader("out.txt"));
	int target = 32215;
	int remaining = target;
	int nextPrime = 1;
	String factorization = "";
	while(remaining > 1){
	    do{
		nextPrime = Integer.parseInt(br.readLine());
	    }
	    while(remaining % nextPrime != 0);
	    while(remaining % nextPrime == 0){
		remaining /= nextPrime;
		factorization += nextPrime + " ";
	    }
	}
	System.out.println("prime factorization of " + target + " is "+ factorization);
    }
    private static boolean isPrime(int k){
	if(k==1)
	    return false;
	int stop = (int) Math.sqrt(k);
	int i=2;
	while(i<=stop){
	    if(k%i == 0){
		return false;
	    }
	    i++;
	}
	return true;
	
    }
}
