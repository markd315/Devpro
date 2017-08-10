package pkg;

import static org.junit.Assert.*;

import org.junit.*;
import static pkg.GuessingGame.*;

public class GuessingGameTest {
    
    @Test
    public void comparisonShouldBeAccurate(){
	assertEquals(reportComparison(33, 33), "correct");
	assertEquals(reportComparison(33, 67), "too low");
	assertEquals(reportComparison(33, 11), "your guess is too high");
    }
    
    @Test
    public void inputShouldBeGreaterThan0AndLessThan100() {
	assertFalse(isInvalidInput(1));
	assertFalse(isInvalidInput(100));
	assertFalse(isInvalidInput(0));
	assertFalse(GuessingGame.isInvalidInput(37));
	assertTrue(GuessingGame.isInvalidInput(-1));
	assertTrue(GuessingGame.isInvalidInput(-.5));
	assertTrue(GuessingGame.isInvalidInput(101));
	assertTrue(GuessingGame.isInvalidInput(.75));
	
    }
    @Test
    public void gameShouldContinueUntilGuessed(){
	assertTrue(GuessingGame.notGuessed(23, 26));
	assertTrue(GuessingGame.notGuessed(99, 100));
	assertFalse(GuessingGame.notGuessed(23, 23));
    }
    @After
    public void doAfter(){
	System.out.println("Runs after every test");
    }
    @Before
    public void doBefore(){
	System.out.println("Runs before every test");
    }
}
