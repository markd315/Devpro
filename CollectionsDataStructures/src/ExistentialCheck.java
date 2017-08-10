import java.util.*;

public class ExistentialCheck {
    
    public static void main(String[] args) {
	Random rng = new Random();
	List<Integer> arrayList = new ArrayList<Integer>();	
	long before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    arrayList.add(rng.nextInt(50001));
	}
	before = System.nanoTime();
	int hits = runFiftyKChecks(arrayList);
	System.out.println(System.nanoTime()-before +" to run ArrayList test with " +hits + " hits");
	arrayList = null; //Just so we free up the memory.
	System.gc();
	List<Integer> linkedList = new LinkedList<Integer>();	
	before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    linkedList.add(rng.nextInt(50001));
	}
	before = System.nanoTime();
	hits = runFiftyKChecks(linkedList);
	System.out.println(System.nanoTime()-before +" to run LinkedList test " +hits + " hits");
	linkedList = null; //Just so we free up the memory.
	System.gc();
	//Try set implementations.
	Set<Integer> hashSet = new HashSet<Integer>();	
	before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    hashSet.add(rng.nextInt(50001));
	}
	before = System.nanoTime();
	hits = runFiftyKChecks(hashSet);
	System.out.println(System.nanoTime()-before +" to run HashSet test " +hits + " hits");
	hashSet = null; //Just so we free up the memory.
	System.gc();
	Set<Integer> treeSet = new TreeSet<Integer>();	
	before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    treeSet.add(rng.nextInt(50001));
	}
	before = System.nanoTime();
	hits = runFiftyKChecks(treeSet);
	System.out.println(System.nanoTime()-before +" to run TreeSet test " +hits + " hits");
	treeSet = null; //Just so we free up the memory.
	System.gc();
    }
    public static int runFiftyKChecks(List<Integer> arr){
	int k=0;
	for(int i=0; i<50001; i++){
	    if(arr.contains(i)){
		k++;
	    }
	}
	return k;
    }
    public static int runFiftyKChecks(Set<Integer> set){
	int k=0;
	for(int i=0; i<50001; i++){
	    if(set.contains(i)){
		k++;
	    }
	}
	return k;
    }
}
