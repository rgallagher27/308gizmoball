package model;

import java.awt.Point;

public interface iGizmo {
	
	public String getIdentifier();
	
	public Point getLocation();
	
	public void setLocation(Point p);
	
	public double getWidth();
	
	public void setWidth(double w);
	
	public double getHeight();
	
	public void setHeight(double h);
	
	public double getRotation();
	
	public void setRotation(double r);
	
	public void move();

}
