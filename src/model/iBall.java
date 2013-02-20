package model;

public interface iBall {
	
	
	
	public void setVelocity(double vx, double vy);
	public void setLocation(float x, float y); //required to set the ball point.
	public Double getVelocityX();
	public Double getVelocityY();

}
