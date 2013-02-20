package model;

import java.util.List;

public interface IOverlord {
	
	
	
	public boolean addSquare(String gizmoName, int x, int y);
	public boolean addCircle(String gizmoName, int x, int y);
	public boolean addTriangle(String gizmoName, int x, int y);
	public boolean addFlipper(String gizmoName, int x, int y, boolean orient); //true = right, false = left
	public boolean addAbsorber(String gizmoName, int x1, int y1, int x2, int y2);
	public boolean addBall(String gizmoName, float x, float y, double vx, double vy);
	public void removeGizmo(String gizmoName);
	public List<iGizmo> getGizmos();
	public iGizmo getGizmo(String gizmoName);
	public boolean moveGizmo(String gizmoName, int x, int y);
	public boolean moveGizmo(String gizmoName, float x, float y);
	public boolean rotateGizmo(String gizmoName);
	public void setGravity(float newGrav);
	public void setFriction(float mu, float mu2);
	public boolean keyConnect(int keyNum, boolean direction, String consumer); //up = true, false = down.
	public boolean connect(String producerGizmo, String consumerGizmo);
	public void loadGame(String mapName);
	public void saveGame(String mapName);
	public List<String> getMapNames();

}
