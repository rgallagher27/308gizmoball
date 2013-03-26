package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.physics.Circle;
import model.physics.LineSegment;

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
	
	public void removeTrigger(iGizmo giz);

	public ArrayList<iGizmo> getTriggers();
	
	public double getCellHeight();
	
	public void setCellHeight(double h);
	
	public double getRotation();
	
	public void setRotation(double r);
	
	public double timeUntilCollision(iBall ball);
	
	public void collide(iBall ball);
	
	public void performAction(boolean a);
	
	public void move(double Delta_T);

	public int getWidth();

	public int getHeight();
	
	public void rotate();
	
	public Color getColour();
	
	/* Segment test code */
	public List<LineSegment> getSegments();
	
	public List<Circle> getCircles();
	
	public int getTriggerCount();
	

}
