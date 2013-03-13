package model;

import java.awt.Point;
import java.util.ArrayList;

public interface iGizmo {
	
	public String getIdentifier();
	
	public GizPoint getLocation();
	
	public void setLocation(GizPoint p);
	
	public double getRowWidth();
	
	public void setRowWidth(double w);
	
	public double getColumnHeight();
	
	public void setColumnHeight(double h);
	
	public double getCellWidth();
	
	public void setCellWidth(double w);
	
	public void addTrigger(iGizmo giz);
	
	public ArrayList<iGizmo> getTriggers();
	
	public void removeTrigger(iGizmo giz);
	
	public double getCellHeight();
	
	public void setCellHeight(double h);
	
	public double getRotation();
	
	public void setRotation(double r);
	
	public double timeUntilCollision(iBall ball);
	
	public void collide(iBall ball);
	
	public void performAction(boolean a);
	
	public void move();

	public int getWidth();

	public int getHeight();
	
	public void rotate();

}
