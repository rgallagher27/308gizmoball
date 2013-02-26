package model;

public interface iBall {
	
	
	
	public void setVelocity(double vx, double vy);
	public void setLocation(float x, float y); //required to set the ball point.
	public BallPoint getLocation();
	public void setReleased(boolean rel);
	public boolean getReleased();
	public double getVelocityX();
	public double getVelocityY();
	public double getRadius();
	public String getName();
	public void setImmunity(int b);
	public int getImmunity();
}
