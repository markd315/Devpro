import java.io.IOException;
import java.util.*;

public class Car extends Entity implements Drivable {
    protected static final double SPEED_LIMIT = 100;
    protected static final double CRASH_RADIUS = 3;
    protected static int numCars;
    protected double cruiseControl, odometer, price;
    protected int crashes, tickets;
    protected Colour colour;

    public enum Colour {
	BLACK, BLUE, GREEN;
    }

    public Car(double x, double y, Car.Colour c) {
	super(x, y);
	this.colour = c;
	numCars++;
    }

    public Car(Car.Colour c) {
	this(0.0, 0.0, c);
    }

    public Car(double x, double y) {
	this(x, y, Colour.BLACK);
    }

    public Car() {
	this(0.0, 0.0);
    }

    public static int getNumCars() {
	return numCars;
    }

    @Override
    public void gas() {
	this.gas(2);
    }

    @Override
    public void gas(double x) {
	if (x <= 0) {
	    throw new IllegalArgumentException("Arg must be positive.");
	}
	checkSpeed();
	super.speedMPH += x;
    }

    @Override	
    public Texture getTexture() throws IOException {
	if(colour == Car.Colour.BLUE){
	    if(this.getAngleDegrees() > 90 && this.angleDegrees <= 270)
		return new Texture("./tex/bluecarL.png");
	    return new Texture("./tex/bluecar.png");
	}
	else if(colour == Car.Colour.GREEN){
	    return new Texture("./tex/greencar.png");
	}
	return new Texture("./tex/car.png");
    }
    private void checkSpeed() {
	if (this.speedMPH > Car.SPEED_LIMIT) {
	    this.speedMPH = 0;
	    this.tickets++;
	    System.err.println("Car was stopped for exceeding speed limit.");
	}
	if(this.speedMPH < 0){
	    this.speedMPH =0;
	}
    }

    @Override
    public void brake() {
	this.brake(10);
    }

    @Override
    public void brake(double x) {
	if (x <= 0)
	    throw new IllegalArgumentException("Arg must be positive.");
	this.speedMPH -= x;
	checkSpeed();
	this.cruiseControl = 0;
    }

    @Override
    public double getOdometer() {
	return this.odometer;
    }

    public double tickSecond() {
	//freeze.
	double delta = super.tickSecond();
	this.speedMPH -= .8;
	if (this.speedMPH < this.cruiseControl) {
	    this.speedMPH = (this.cruiseControl + this.speedMPH) / 2.0;
	}
	this.checkSpeed();
	this.checkForCrash();
	this.odometer += delta;
	return delta;
    }

    private void checkAngle() {
	while (angleDegrees > 360) {
	    angleDegrees -= 360;
	}
	while (angleDegrees < 0) {
	    angleDegrees += 360;
	}
    }

    @Override
    public String toString() {
	return String.format("x:%.3f  y:%.3f colour:", xPosition, yPosition) + this.colour + String.format("\nMph:%.1f  Kph:%.1f angle:%.1f", this.getSpeedMph(), this.getSpeedKph(), this.getAngleDegrees());
    }

    public void setXPosition(double x) {
	this.xPosition = x;
    }

    public void setYPosition(double y) {
	this.yPosition = y;
    }

    @Override
    public boolean checkForCrash() {
	for (Entity e : Game.entities) {
	    if (this.distance(e) < Car.CRASH_RADIUS && e != this) {
		if (e instanceof Car) {
		    ((Car) e).handleCrash("into Car " + this.toString());
		}
		this.handleCrash("into Entity " + e.toString());
		double cx = (e.xPosition + this.xPosition) / 2.0;
		double cy = (e.yPosition + this.yPosition) / 2.0;
		this.setXPosition(cx + 3);
		e.setXPosition(cx - 3);
		this.setYPosition(cy + 3);
		e.setYPosition(cy - 3);
		this.speedMPH = 0;
		e.speedMPH = 0;
	    }
	}
	return false;
    }

    @Override
    public void handleCrash(String string) {
	System.err.println("Crashed " + string + "\nPrecrash state: " + this.toString());
	crashes++;
	this.speedMPH = 0;
	this.cruiseControl = 0;
    }

    public void turnLeft(int i) {
	if (i > 10 && this.speedMPH > 50) {
	    handleCrash("while turning left");
	} else if (i > 5 && this.speedMPH > 60) {
	    handleCrash("while turning left");
	} else if (i > 3 && this.speedMPH > 80) {
	    handleCrash("while turning left");
	}
	angleDegrees += i;
	checkAngle();
    }

    public double getCruiseControl() {
	return cruiseControl;
    }

    public void setCruiseControl(double cruiseControl) {
	this.cruiseControl = cruiseControl;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public Colour getColour() {
	return colour;
    }

    public void setColour(Colour colour) {
	this.colour = colour;
    }

    public int getCrashes() {
	return crashes;
    }

    public int getTickets() {
	return tickets;
    }

    public void turnRight(int i) {
	if (i > 10 && this.speedMPH > 50) {
	    handleCrash("while turning right");
	} else if (i > 5 && this.speedMPH > 60) {
	    handleCrash("while turning right");
	} else if (i > 3 && this.speedMPH > 80) {
	    handleCrash("while turning right");
	}
	angleDegrees -= i;
	checkAngle();
    }

    public void turnRight() {
	this.turnRight(15);
    }

    public void turnLeft() {
	this.turnLeft(15);
    }
}
