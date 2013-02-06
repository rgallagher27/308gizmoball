package Model;

import java.awt.Point;
import java.util.List;

public interface iGizmo {
	
	public void doAction();
	
	public void setLocation(Point p);
	
	public Point getLocation();
	
	public void addTrigger(iGizmo t);
	
	public void addTrigger(int k);
	
	public void removeTrigger(iGizmo t);
	
	public void rotate();
	
	public List<iGizmo> getTriggers();

}
