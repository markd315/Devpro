import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
	Map<Integer, Boolean> hashMap = new HashMap<>();
	long before = System.nanoTime();
	for(int i=0; i<10001; i++){
	    if(isPrime(i)){
		hashMap.put(i, true); //Key, value
	    }else{
		hashMap.put(i, false);
	    }
	}
	//To create the hashmap
	System.out.println(System.nanoTime()-before + " to initialize hashMap");
	int n=0; //occurrences
	Random rng = new Random();
	before = System.nanoTime();
	for(int i=0; i<10000000; i++){ //10 mil lookups
	    int r = rng.nextInt(10001);
	    if(hashMap.get(r)){
		n++;
	    }
	}
	System.out.println(System.nanoTime()-before + " to do hashMap lookups");
	n=0; //occurrences
	before = System.nanoTime();
	for(int i=0; i<100000000; i++){ //10 mil lookups
	    int r = rng.nextInt(10001);
	    if(isPrime(r)){
		n++;
	    }
	}
	System.out.println(System.nanoTime()-before + " to run trial division instead of doing lookups");
	//Each key can only have one value
	//If you try to give it another, it will replace.
	//Lookups are done by key, never by value
	//Each value can have multiple keys mapping to it
	//Basically, we need it to be a "function" where
	//any input is either undefined or has one output
	
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
