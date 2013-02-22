package model;


import java.util.List;

import exception.CannotRotateException;

public interface iGizmo {
	
	public void doAction();
	
	public void setLocation(GizPoint p);
	
	public GizPoint getLocation();
	
	public GizPoint getBounds();
	
	public void addTrigger(iGizmo t);
	
	public void removeTrigger(iGizmo t);
	
	public List<iGizmo> getTriggers();
	
	public void rotate() throws CannotRotateException;

	public int getWidth();

	public int getHeight();


}
