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
	
	public void setVelocity(Double vx, Double vy);
	
	public Double getVelocityX();
	public Double getVelocityY();
	
	public List<iGizmo> getTriggers();
	
	public void rotate();

	public int getWidth();

	public int getHeight();

	public void setLocation(float x, float y); //required to set the ball point.

}
