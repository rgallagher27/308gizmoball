package model;

import java.awt.Point;
import java.util.List;

public interface iGizmo {
	
	public void doAction();
	
	public void setLocation(Point p);
	
	public Point getLocation();
	
	public Point getBounds();
	
	public void addTrigger(iGizmo t);
	
	public void addTrigger(int k);
	
	public void removeTrigger(iGizmo t);
	
	public void removeTrigger(int k);
	
	public void setVelocity(Double v);
	
	public Double getVelocity();
	
	public List<iGizmo> getTriggers();
	
	public void rotate();

}
