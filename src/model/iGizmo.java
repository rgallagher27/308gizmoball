package model;

import java.awt.Point;

public interface iGizmo {
	
	public String getIdentifier();
	
	public Point getLocation();
	
	public void setLocation(Point p);
	
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
	
	public double timeUntilCollision(iBall ball);
	
	public void collide(iBall ball);
	
	public void move();

}
