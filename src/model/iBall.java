package model;

import model.physics.Circle;
import model.physics.Vect;

public interface iBall {
	
	public String getIdentifier();
	
	public BallPoint getLocation();
	
	public BallPoint getOrigLocation();
	
	public Vect getOrigVelocity();
	
	public void setLocation(BallPoint p);
	
	public Circle returnBounds();
	
	public Vect getVelocity();
	
	public void setVelocity(Vect v);
	
	public double getRowWidth();
	
	public void setRowWidth(double w);
	
	public double getColumnHeight();
	
	public void setColumnHeight(double h);
	
	public double getCellWidth();
	
	public void setCellWidth(double w);
	
	public double getCellHeight();
	
	public void setCellHeight(double h);
	
	public double getRotation();
	
	public void setRotation(double r);
	
	public void setCaptured(boolean update);
	
	public boolean isCaptured();
	
	public void move(double deltaT);
	
	public double timeUntilCollision(iBall ball);
	
	public void collide(iBall ball);
	
	public double getRadius();

	public boolean getOrigCapture();
	
	public void setgravity(double g);

}
