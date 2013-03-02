package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface iOverlord {
	
	public List<iGizmo> getAllGizmos();
	
	public List<iBall> getAllballs();
	
	public iGizmo getGizmo(String identifier);
	
	public iBall getBall(String identifier);
	
	public Map<Integer, ArrayList<iGizmo>> getGizmoDownKeytriggers();
	
	public Map<Integer, ArrayList<iGizmo>> getGizmoUpKeytriggers();
	
	public void moveAllGizmos();
	
	/*
	 * Collision methods
	 */
	 
	public double collideGizmos(iBall b, double Current_Delta_T);
	
	public double collideBalls(iBall b, double Current_Delta_T);
	
	/*
	 * Gizmo and Ball addition methods
	 */
	public void addAbsorber(String id, int x, int y, int width, int height);
	
	public void addBall(String id, double x, double y, double xV, double yV);
	
	public void addCircle(String id, int x, int y);
	
	public void addFlipper(String id, String type, int x, int y);
	
	public void addSquare(String id, int x, int y);
	
	public void addTriangel(String id, int x, int y);
	
	/*
	 * Gizmo and ball modification methods
	 */
	public void delete(String name);
	
	public void move(String name, double x, double y);
	
	public void rotate(String name);
	
	public void keyConnect(int keyCode, String type, String consumer );
}
