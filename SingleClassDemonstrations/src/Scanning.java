import java.io.*;
import java.util.Scanner;

public class Scanning {
    public static void main(String[] args) throws Exception{
	File config = new File("config.cfg");
	double bias;
	boolean decimalAdditionMode = false;
	Scanner sc = new Scanner(System.in);
	Scanner file = new Scanner(config);
	String line = file.nextLine();
	line = line.replaceAll(" ", "");
	if(!line.substring(0, 20).equalsIgnoreCase("decimalAdditionMode=")){
	   file.close();
	   throw new Exception("config doesn't match");
	}
	if(line.replaceAll(" ", "").substring(20).equalsIgnoreCase("true"))
	    decimalAdditionMode = true;
	file.skip("bias=");
	bias = file.nextDouble();
	if(!decimalAdditionMode){
	    int a = sc.nextInt();
	    int b = sc.nextInt();
	    System.out.println(a+b+(int)bias);
	}
	else{//is decimal mode.
	    double a = sc.nextDouble();
	    double b = sc.nextDouble();
	    System.out.println(a+b+bias);
	}
	sc.close();
	file.close();
    }
}
