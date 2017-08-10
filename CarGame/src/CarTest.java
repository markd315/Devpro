import static org.junit.Assert.*;

import org.junit.*;

public class CarTest {
    private Car carA, carB;

    @After
    public void after(){
	Game.entities.clear();
    }
    
    
    @Before
    public void setup() {
	carA = new Car(0, 0, Car.Colour.BLACK);// Will head in positive x
	carB = new Car(100, 0, Car.Colour.BLUE);// Will turn 180 and head in negative x
	Game.entities.add(carA);
	Game.entities.add(carB);
    }

    private boolean roundedEquals(double x, double y) {
	double a = Math.round(x * 10000.0) / 10000.0;
	double b = Math.round(y * 10000.0) / 10000.0;
	return a == b;
    }

    @Test
    public void speedShouldApproachCruiseSpeedWhenLeftAlone() {
	// TODO mention tweaking direction.
	carA.brake(Double.MAX_VALUE);
	assertTrue(roundedEquals(carA.getSpeedMph(), 0));
	carA.setCruiseControl(10);
	carA.turnLeft(10);
	for (int i = 0; i < 10; i++)
	    carA.tickSecond();
	assertTrue(carA.getSpeedMph() > 7.0);
	assertTrue(carA.getSpeedMph() < 10.0);
    }

    @Test
    public void headingVerticallyOnlyYChanges() {
	carA = new Car(0,0);
	carA.turnRight(90);
	assertTrue(carA.getAngleDegrees() == 270);
	double ox = carA.getXPosition();
	double oy = carA.getYPosition();
	carA.gas(10);
	for (int i = 0; i < 100; i++)
	    carA.tickSecond();
	// assertTrue(roundedEquals(carA.getXPosition(), ox));
	assertTrue(carA.getYPosition() < oy);
    }

    @Test
    public void headingHorizontallyOnlyXChanges() {
	assertTrue(carA.getAngleDegrees() == 0);
	double ox = carA.getXPosition();
	double oy = carA.getYPosition();
	carA.gas(10);
	carA.tickSecond();
	assertTrue(carA.getXPosition() > ox);
	assertTrue(roundedEquals(carA.getYPosition(), oy));
    }

    @Test
    public void speedShouldBeLimited() {
	carA.gas(105);
	carA.gas(12);
	carA.tickSecond();
	assertTrue(carA.getSpeedMph() <= 100);
    }

    @Test
    public void shouldCrashWhenTurnedAtSpeed() {
	assertEquals(carA.getCrashes(), 0);
	carA.gas(90);
	carA.turnLeft(10);
	assertEquals(carA.getCrashes(), 1);
    }

    @Test
    public void carsShouldCrashOnCollision() {
	carB.turnLeft(180);
	carA.setCruiseControl(2);
	carB.setCruiseControl(2);
	assertTrue(carA.getCrashes() == 0);
	assertTrue(carB.getCrashes() == 0);
	for (int i = 0; i < 2000; i++) {
	    carA.tickSecond();
	    carB.tickSecond();
	}
	assertTrue(carA.getCrashes() > 0);
	assertTrue(carB.getCrashes() > 0);
    }

    @Test
    public void carsShouldNotRepeatedlyCrash() {
	carB.turnLeft(180);
	carA.setCruiseControl(2);
	carB.setCruiseControl(2);
	assertTrue(carA.getCrashes() == 0);
	assertTrue(carB.getCrashes() == 0);
	for (int i = 0; i < 1000; i++) {
	    carA.tickSecond();
	    carB.tickSecond();
	}
	assertTrue(carA.getCrashes() <= 1);
	assertTrue(carB.getCrashes() <= 1);
    }
}
