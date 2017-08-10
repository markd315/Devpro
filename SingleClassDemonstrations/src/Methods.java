
public class Methods {
	public static void main(String[] args){
		System.out.println(power(3,2));
		System.out.println(power(2,4));
		System.out.println(factorial(5));
		printError("just testing, false alarm");
	}
	
	//(access modifier) (static) (final) [return or void] [name](parameters...)
	//There are a few others like synchronized, volatile and native that we don't really need to worry about for now
	private static double power(double base, int power){
		double ret = 1;
		while(power>0){
			ret*=base;
			power--;
		}
		return ret;
	}
	private static int factorial(int i) {
		if(i==1)
			return 1;
		return factorial(i-1)*i;
	}
	private static void printError(String description){
		System.out.println("An error has occurred: "+ description);
	}
}
