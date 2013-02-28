package model;

import java.util.List;
import java.util.Observer;

public interface iOverlord {
	
	public List<iGizmo> getAllGizmos();
	
	public List<iBall> getAllballs();
	
	public iGizmo getGizmo(String identifier);
	
	public iBall getBall(String identifier);
	
	public void addGizmoObserver(Observer o);
	
	public void addBallObserver(Observer o);

}
