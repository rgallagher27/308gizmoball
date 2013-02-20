package model;

import java.awt.Point;
import java.util.List;

public interface iGizmo {
	
	public void doAction();
	
	public void setLocation(Point p);
	
	public Point getLocation();
	
	public Point getBounds();
	
	public void addTrigger(iGizmo t);
	
	public void removeTrigger(iGizmo t);
	
	public List<iGizmo> getTriggers();
	
	public void rotate();

	public int getWidth();

	public int getHeight();


}
