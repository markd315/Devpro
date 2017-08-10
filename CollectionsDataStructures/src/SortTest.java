import java.util.*;

public class SortTest {
    public static void main(String[] args) {
	Random rng = new Random();
	List<Integer> arrayList = new ArrayList<Integer>();	
	long before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    arrayList.add(rng.nextInt(50001));
	}
	System.out.println(System.nanoTime()-before + " to initialize ArrayList");
	before = System.nanoTime();
	arrayList.sort(null);//Null comparator is fine since Integer implements Comparable
	System.out.println(System.nanoTime()-before +" to run ArrayList test");
	/*for(Integer x:arrayList){
	    System.out.println(x);
	}*/ //Verifies sorted order
	arrayList = null; //Just so we free up the memory.
	System.gc();
	List<Integer> linkedList = new LinkedList<Integer>();	
	before = System.nanoTime();
	for(int i=0; i<50001; i++){
	    linkedList.add(rng.nextInt(50001));
	}
	System.out.println(System.nanoTime()-before + " to initialize LinkedList");
	before = System.nanoTime();
	linkedList.sort(null);
	System.out.println(System.nanoTime()-before +" to run LinkedList test");
	/*for(Integer x:linkedList){
	    System.out.println(x);
	}*/ //Verifies sorted order 
	linkedList = null; //Just so we free up the memory.
	System.gc();
    }
}
