
public class Dog extends Animal {
    @Override
    public void eat() {
	System.out.println("dog eat");
    }
    public int getLegs(){
	return this.numberOfLegs; 
    }
    @Override
    public void die() {
	//method stub
    }
}
