import java.io.*;

public class Entity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected static final double KM_PER_MI = 1.609344;
    protected double xPosition, yPosition, speedMPH, angleDegrees;
    protected static int numEntities = 0;
    
    public Entity(double xPosition, double yPosition, double speed, double angle){
	this.xPosition = xPosition;
	this.yPosition = yPosition;
	this.speedMPH = speed;
	this.angleDegrees = angle;
	numEntities++;
    }
    public Entity(double xPosition, double yPosition){
	this(xPosition, yPosition, 0.0, 0.0);
    }
    public Entity(){
	this(0.0, 0.0, 0.0, 0.0);
    }
    public double distance(Entity eB) {
	return Math.sqrt(Math.pow(this.xPosition - eB.xPosition, 2) + Math.pow(this.yPosition - eB.getYPosition(), 2));
    }
    public double getSpeedMph() {
	return this.speedMPH;
    }
    public double getSpeedKph() {
	return this.speedMPH * KM_PER_MI;
    }
    public double tickSecond() {
	double metersPerHour = this.getSpeedKph()*1000;
	double metersPerSecond = metersPerHour/3600;
	double xDelta = Math.cos(Math.toRadians(angleDegrees)) * metersPerSecond;
	double yDelta = Math.sin(Math.toRadians(angleDegrees)) * metersPerSecond;
	this.xPosition+=xDelta;
	this.yPosition+=yDelta;
	checkWalls();
	checkAngle();
	return xDelta+yDelta;
    }
    	
    private void checkAngle() {
	while(angleDegrees > 360){
	    angleDegrees-=360;
	}
	while(angleDegrees < 0){
	    angleDegrees+=360;
	}
    }
    private void checkWalls() {
	if(this.xPosition > 100){
	    this.xPosition = 100;
	    this.speedMPH/=2;
	}
	if(this.yPosition > 100){
	    this.yPosition = 100;
	    this.speedMPH/=2;
	}
	if(this.xPosition < -100){
	    this.xPosition = -100;
	    this.speedMPH/=2;
	}
	if(this.yPosition < -100){
	    this.yPosition = -100;
	    this.speedMPH/=2;
	}
    }
    @Override
    public String toString(){
	return String.format("x:%.3f  y:%.3f" , xPosition, yPosition)+"\n"+String.format("mph:%.3f  angle:%.3f" , speedMPH, angleDegrees);
    }
    
    
    public double getXPosition() {
        return xPosition;
    }
    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }
    public double getYPosition() {
        return this.yPosition;
    }
    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    public double getAngleDegrees() {
        return angleDegrees;
    }
    public void setAngleDegrees(double angleDegrees) {
        this.angleDegrees = angleDegrees;
    }
    public static int getNumEntities() {
        return numEntities;
    }
    public Texture getTexture() throws IOException {
	return new Texture("./tex/enemy.png");
    }
    
}
