import static org.junit.Assert.assertTrue;

import org.junit.*;

public class EntityTest {
    Entity eA, eB;
    
    private boolean roundedEquals(double x, double y) {
        double a = Math.round(x * 100.0) / 100.0;
        double b = Math.round(y * 100.0) / 100.0;
        return a == b;
    }

    @Before
    public void setup() {
	eA = new Entity(0, 0);
	eB = new Entity(100, 0);
    }

    @Test
    public void distanceShouldReturnDistance() {
	assertTrue(roundedEquals(eA.distance(eB), 100.0));
    }

    @Test
    public void entitiesShouldTravelPerExpectations() {
	eA = new Entity(0, 0, 1, 90);
	eB = new Entity(0, 0, 1, 180);
	assertTrue(roundedEquals(eA.getSpeedMph(), 1) && roundedEquals(eB.getSpeedMph(), 1));
	eA.tickSecond();
	eB.tickSecond();
	System.out.println(eA);
	assertTrue(roundedEquals(eA.getXPosition(), 0) && eA.getYPosition() > 0);
	assertTrue(eB.getXPosition() < 0 && roundedEquals(eB.getYPosition(), 0));
    }

    @Test
    public void coordinatesShouldBeBounded() {
        eA = new Entity(0, 0, 100, 45);
        for (int i = 0; i < 1000; i++) {
            eA.tickSecond();
        }
        assertTrue(eA.getXPosition() <= 100);
        assertTrue(eA.getXPosition() >= -100);
        assertTrue(eA.getYPosition() <= 100);
        assertTrue(eA.getYPosition() >= -100);
        eA = new Entity(0, 0, 100, 135);
        for (int i = 0; i < 1000; i++) {
            eA.tickSecond();
        }
        assertTrue(eA.getXPosition() <= 100);
        assertTrue(eA.getXPosition() >= -100);
        assertTrue(eA.getYPosition() <= 100);
        assertTrue(eA.getYPosition() >= -100);
        eA = new Entity(0, 0, 100, 225);
        for (int i = 0; i < 1000; i++) {
            eA.tickSecond();
        }
        assertTrue(eA.getXPosition() <= 100);
        assertTrue(eA.getXPosition() >= -100);
        assertTrue(eA.getYPosition() <= 100);
        assertTrue(eA.getYPosition() >= -100);
        eA = new Entity(0, 0, 100, 315);
        for (int i = 0; i < 1000; i++) {
            eA.tickSecond();
        }
        assertTrue(eA.getXPosition() <= 100);
        assertTrue(eA.getXPosition() >= -100);
        assertTrue(eA.getYPosition() <= 100);
        assertTrue(eA.getYPosition() >= -100);
    }
}
