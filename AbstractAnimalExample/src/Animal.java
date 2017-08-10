import java.util.*;

public abstract class Animal {
    int numberOfLegs;
    int numberOfCells;
    abstract void eat();
    abstract void die();
    public static void main(String[] args){
	List<Animal> animals = new ArrayList<Animal>();
	animals.add(new Dog());
	animals.add(new Cat());
	for(Animal a : animals){
	    a.eat();
	}
    }
}
