package pkg;


import java.util.*;

public class GuessingGame {
    // TODO make tests pass
    public static void main(String[] args) {
	Random rng = new Random();
	int correctAnswer = rng.nextInt(101);
	Scanner sc = new Scanner(System.in);
	int rounds = 0;
	int lastGuessed = -212421;
	while (notGuessed(lastGuessed, correctAnswer)) {
	    do {
		lastGuessed = sc.nextInt();
	    }
	    while (isInvalidInput(lastGuessed));
	    System.out.println(reportComparison(lastGuessed, correctAnswer));
	    rounds++;
	}
	System.out.println("Correct! You took " + rounds + " rounds.");
    }

    static String reportComparison(int lastGuessed, int correctAnswer) {
	if (lastGuessed > correctAnswer) {
	    return "your guess is too high";
	}
	if (lastGuessed < correctAnswer) {
	    return "too low";
	}
	return "correct";
    }

    static boolean isInvalidInput(double d) {
	if ((int) d != d)
	    return true;
	if (d > 100 || d < 0)
	    return true;
	return false;
    }

    static boolean notGuessed(int i, int j) {
	if (i == j)
	    return false;
	return true;
    }

}
