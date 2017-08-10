import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class DebugExample {
    @Test
    public void tester() throws Exception {
	String[] case1 = { "3", "+", "8", "/", "4" };
	String[] result;
	String[] expected = { "3", "8", "4", "/", "+" };
	ArrayList<String> arrl = shuntingYard(case1);// returns 3,8,4,/,+
	result = arrl.toArray(new String[arrl.size()]);
	assertNotNull(result);
	assertArrayEquals(expected, result);
    }

    static ArrayList<String> shuntingYard(String[] tokenizedInput) throws Exception {
	ArrayList<String> result = new ArrayList<String>();
	Stack<String> stack = new Stack<String>();
	for (int i = 0; i < tokenizedInput.length; i++) {
	    String token = tokenizedInput[i];
	    if (isOperatorNotParen(token)) {
		while (!stack.isEmpty() && (leftAssociative(token) && prec(token) <= prec(stack.firstElement())
			|| (token.charAt(0) == '^' && prec(token) > prec(stack.firstElement())))) {
		    result.add(stack.peek());
		    stack.pop();
		    break;
		}
		stack.push(token);
	    } else if (token.charAt(0) == '(') {
		stack.push(token);
	    } else if (token.charAt(0) == ')') {
		while (!stack.isEmpty() && stack.firstElement() != ")") {
		    result.add(stack.peek());
		    stack.pop();
		}
	    } else {
		result.add(token);
	    }
	}
	while (!stack.isEmpty()) {
	    result.add(stack.peek());
	    stack.pop();
	}
	return result;
    }

    private static boolean isOperatorNotParen(String token) {
	token = token.replaceAll(" ","");
	if (token.equals("*"))
	    return true;
	if (token.equals("/"))
	    return true;
	if (token.equals("-"))
	    return true;
	if (token.equals("+"))
	    return true;
	if (token.equals("^"))
	    return true;
	return false;
    }

    private static int prec(String token) throws Exception {
	switch (token) {
	case "(":
	    return 4;
	case ")":
	    return 4;
	case "^":
	    return 3;
	case "*":
	    return 2;
	case "/":
	    return 2;
	case "+":
	    return 1;
	case "-":
	    return 1;
	default:
	    throw new Exception("Bad input token");
	}
    }

    private static boolean leftAssociative(String token) {
	if (token.equals("^"))
	    return false;
	return true;
    }
}
