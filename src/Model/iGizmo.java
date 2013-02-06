package Model;

import java.awt.Point;
import java.util.List;

public interface iGizmo {
	
	public void doAction();
	
	public void setLocation(Point p);
	
	public Point getLocation();
	
	public void getBounds();
	
	public void addTrigger(iGizmo t);
	
	public void addTrigger(int k);
	
	public void removeTrigger(iGizmo t);
	
	public void removeTrigger(int k);
	
	public List<iGizmo> getTriggers();
	
	public void rotate();

}
