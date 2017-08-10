public class Argstest {
	public static void main(String[] args){
		String argOne = args[0]; //set to "printThis"
		String append = new String(" and i will be happy"); //alternate object construction of String.
		argOne+= append; //Can be concatenated together.
		//talk about the dot operator for objects.
		System.out.println("argone: " + argOne + ". of length " + argOne.length());
		System.out.println(argOne.charAt(5)); //char at index 5: sixth element.
		//What if the last line is out of bounds? 
		System.out.println(args.toString());
		//What the heck does this mean?!?
		int toDelete = argOne.indexOf('n');//becomes 3
		String deletedN = argOne.substring(0,toDelete) + argOne.substring(toDelete+1); 
		//substring can take multiple parameter combinations: 
		//int startIndex
		//or int startIndex, int endIndex
		//this is called overloading where the same method name does multiple things and we will see it more later.
		//substring DOES include the "startIndex" but does NOT include the "stopIndex."
		//A useful property of this is that the length will be equal to the subtraction.
		System.out.println(deletedN);
		//show variants of this line with toLowerCase, toUpperCase.
		deletedN = ("   " + deletedN + "   ").trim();
		char[] chararr = deletedN.toCharArray();
		for(char c : chararr){
			System.out.print(c);
		}
		if(deletedN.contains("n")){
			System.out.println("still lowercase n's in the string!");
		}
		toDelete = argOne.indexOf('T');//becomes 3
		String deletedT = argOne.substring(0,toDelete) + argOne.substring(toDelete+1); 
		//talk about .equals()
		if(deletedN.toLowerCase().equals(deletedN)){ //try with deletedT
			System.out.println("the string is all lowercase!");
		}
		else{
			System.out.println("still uppercase elements in the String!");
		}
		String toSplit = "sudo rm -rf";
		String[] split = toSplit.split(" ");//Splits on every instance of a space.
		argOne = argOne.replace('i', 'I');
		System.out.println(argOne); //char at index 5: sixth element.
	}
}
