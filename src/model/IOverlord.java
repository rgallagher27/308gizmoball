package model;

import java.util.List;

import exception.CannotRotateException;

public interface IOverlord {
	
	
	
	public boolean addSquare(String gizmoName, int x, int y);
	public void setBallLocation(String ballName, float x, float y);
	public boolean addCircle(String gizmoName, int x, int y);
	public boolean addTriangle(String gizmoName, int x, int y);
	public boolean addFlipper(String gizmoName, int x, int y, boolean orient); //true = right, false = left
	public boolean addAbsorber(String gizmoName, int x1, int y1, int x2, int y2);
	public boolean addBall(String ballName, String absorberName, float x, float y, double vx, double vy);
	public void removeGizmo(String gizmoName);
	public void removeBall(String ballName);
	public List<iGizmo> getGizmos();
	public List<iBall> getBalls();
	public iGizmo getGizmo(String gizmoName);
	public iBall getBall(String ballName);
	public boolean moveGizmo(String gizmoName, int x, int y);
	public boolean moveBall(String ballName, String absorberName, float x, float y);
	public boolean rotateGizmo(String gizmoName) throws CannotRotateException;
	public void setGravity(float newGrav);
	public void setFriction(float mu, float mu2);
	public float getGravity();
	public float getFrictionMu();
	public float getFrictionMu2();
	public boolean keyConnect(int keyNum, boolean direction, String consumer); //up = true, false = down.
	public boolean connect(String producerGizmo, String consumerGizmo);
	public void loadGame(String mapName);
	public void saveGame(String mapName);
	

}
