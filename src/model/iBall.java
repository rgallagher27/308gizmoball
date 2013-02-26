package model;

import java.awt.geom.Point2D;

public interface iBall {
	
	public String getIdentifier();
	
	public Point2D.Double getLocation();
	
	public void setLocation(Point2D.Double p);
	
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
	
	public void move();
}
