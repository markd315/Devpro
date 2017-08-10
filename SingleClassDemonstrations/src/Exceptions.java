

import java.util.ArrayList;

public class Exceptions {
    public static void main(String[] args) {
	int x = 3;
	int[] y = new int[1];
	ArrayList<String> z = null;
	try {
	    x /= 0;

	} catch (ArithmeticException e) {
	    System.out.println("you tried to divide by zero, not allowed");
	}
	try{
	    y[2] = 3;
	}catch(ArrayIndexOutOfBoundsException e){
	    y = new int[3];
	    y[2] = 3;
	}
	finally{
	    System.out.println("this will always run");
	}
	System.out.println(y[2]);
	z.get(3);

    }
}
