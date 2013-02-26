package model;


import java.util.List;

import physics.LineSegment;

import exception.CannotRotateException;

public interface iGizmo {
	
	public void doAction();
	
	public String getName();
	
	public void setLocation(GizPoint p);
	
	public GizPoint getLocation();
	
	public GizPoint getBounds();
	
	public void addTrigger(iGizmo t);
	
	public void removeTrigger(iGizmo t);
	
	public List<iGizmo> getTriggers();
	
	public void rotate() throws CannotRotateException;

	public int getWidth();

	public int getHeight();
	
	public List<LineSegment> getBoundSegments();
	
	public double timeTillCol(iBall ball);
	
	public iBall collide(iBall ball);


}
