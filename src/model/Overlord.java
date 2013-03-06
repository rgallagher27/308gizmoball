package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import exception.CannotRotateException;

import model.physics.Vect;

public class Overlord extends Observable implements iOverlord {

	private Dimension gridDimentions, canvasDimentions;

	private HashMap<String, iGizmo> gizmos;
	private HashMap<Integer, ArrayList<iGizmo>> keyTriggersDown;
	private HashMap<Integer, ArrayList<iGizmo>> keyTriggersUp;
	private HashMap<String, iBall> balls;
	private FileParser fileParse;
	private String[][] board;
	private double cellWidth, cellHeight;
	private float gravity;
	private float mu, mu2;
	private boolean loadingFile;

	public Overlord(Dimension gridDimentions, Dimension canvasDimentions) {

		this.gridDimentions = gridDimentions;
		this.canvasDimentions = canvasDimentions;
		cellWidth = canvasDimentions.getWidth() / gridDimentions.getWidth();
		cellHeight = canvasDimentions.getHeight() / gridDimentions.getHeight();
		gizmos = new HashMap<String, iGizmo>();
		balls = new HashMap<String, iBall>();
		keyTriggersDown = new HashMap<Integer, ArrayList<iGizmo>>();
		keyTriggersUp = new HashMap<Integer, ArrayList<iGizmo>>();
		board = new String[gridDimentions.width][gridDimentions.height]; // x
																			// along,
																			// y
																			// up

		for (int x = 0; x < gridDimentions.width; x++) {
			for (int y = 0; y < gridDimentions.height; y++) {
				board[y][x] = "";
			}
		}

		gizmos.put("GizmoWall", new Wall(cellWidth, cellHeight));

	}

	@Override
	public void removeGizmo(String gizmoName) {
		iGizmo gizRem = getGizmo(gizmoName);
		for (iGizmo giz : getGizmos()) {
			giz.removeTrigger(gizRem);
		}
		for (Integer keyVal : keyTriggersDown.keySet()) {
			keyTriggersDown.get(keyVal).remove(gizRem);

		}
		for (Integer keyVal : keyTriggersUp.keySet()) {
			keyTriggersUp.get(keyVal).remove(gizRem);
		}
		gizmos.remove(gizmoName);
		removeFromBoard(gizmoName);
		setChanged();
		notifyObservers(gizmoName);
	}

	@Override
	public List<iGizmo> getGizmos() {
		return new ArrayList<iGizmo>(gizmos.values());
	}

	@Override
	public iGizmo getGizmo(String gizmoName) {
		return gizmos.get(gizmoName);
	}

	@Override
	public List<iBall> getBalls() {
		return new ArrayList<iBall>(balls.values());
	}

	@Override
	public iBall getBall(String identifier) {
		for (iBall b : getBalls())
			if (b.getIdentifier().equals(identifier))
				return b;
		return null;
	}

	@Override
	public void setGravity(float newGrav) {
		gravity = newGrav;

	}

	@Override
	public void saveGame(String mapName) {

	}

	@Override
	public void setFriction(float mu, float mu2) {
		this.mu = mu;
		this.mu2 = mu2;

	}

	private boolean canPlace(String ex, int startX, int startY, int endX,
			int endY) {
		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				if (ex.length() > 0) {
					if (!board[y][x].equals("") && !board[y][x].equals(ex))
						return false;
				} else {
					if (!board[y][x].equals(""))
						return false;
				}
			}
		}
		return true;
	}

	private boolean canPlaceBall(String ex, float startX, float startY,
			float endX, float endY) {
		for (int y = (int) Math.floor(startY); y < (int) Math.ceil(endY); y++) {
			for (int x = (int) Math.floor(startX); x < (int) Math.ceil(endX); x++) {
				if (ex.length() > 0) {
					if (!board[y][x].equals("")
							&& !board[y][x].substring(0, 1).equals(ex))
						return false;
				} else {
					if (!board[y][x].equals(""))
						return false;
				}
			}
		}
		return true;
	}

	private void setPlace(String place, int startX, int startY, int endX,
			int endY) {
		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				board[y][x] = place;
			}
		}
	}

	private void removeFromBoard(String toRemove) {
		for (int x = 0; x < board[0].length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[y][x].equals(toRemove)) {
					board[y][x] = "";
				}
			}
		}
	}

	@Override
	public boolean addAbsorber(String id, int x, int y, int width, int height) {

		if (canPlace("", x, y, (x + height), (y + width))) {
			gizmos.put(id, new Absorber(id, new GizPoint(x, y), width, height,
					cellWidth, cellHeight));
			setPlace(id, x, y, (x + height), (y + width));
			if (!loadingFile) {
				setChanged();
				notifyObservers(id);
			}
			return true;
		}
		return false;

	}

	@Override
	public boolean addBall(String ballName, String absorberName, float x,
			float y, double vx, double vy) {
		iGizmo absorb = null;
		if (absorberName.length() > 0) {
			absorb = getGizmo(absorberName);
		}
		if (vx == 0.0 && vy == 0.0 && absorb != null) {
			if (canPlaceBall("A", x, y, x, y)) {
				iBall newBall = new Ball(ballName, new BallPoint(x, y), 1, 1,
						cellWidth, cellHeight);
				newBall.setVelocity(new Vect(vx, vy));
				newBall.setCaptured(true);
				balls.put(ballName, newBall);
				return true;
			}
		}
		if (canPlaceBall("", x, y, x, y)) {
			iBall newBall = new Ball(ballName, new BallPoint(x, y), 1, 1,
					cellWidth, cellHeight);
			newBall.setVelocity(new Vect(vx, vy));
			newBall.setCaptured(false);
			if (!loadingFile) {
				setChanged();
				notifyObservers(ballName);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean moveGizmo(String gizmoName, int x, int y) {
		iGizmo temp = getGizmo(gizmoName);

		if (canPlace(gizmoName, x, y, x + (temp.getWidth() - 1),
				y + (temp.getHeight() - 1))) {
			temp.setLocation(new GizPoint(x, y));
			removeFromBoard(gizmoName);
			setPlace(gizmoName, x, y, x + (temp.getWidth() - 1),
					y + (temp.getHeight() - 1));
			setChanged();
			notifyObservers(gizmoName);
			return true;
		}
		return false;

	}

	@Override
	public boolean addCircle(String id, int x, int y) {
		if (canPlace("", x, y, x, y)) {
			gizmos.put(id, new CircleBumper(id, new GizPoint(x, y), 1, 1,
					cellWidth, cellHeight));
			setPlace(id, x, y, x, y);
			if (!loadingFile) {
				setChanged();
				notifyObservers(id);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addFlipper(String id, int x, int y, boolean orient) {
		if (canPlace("", x, y, x + 1, y + 1)) {
			if (!orient) {
				gizmos.put(id, new LeftFlipper(id, new GizPoint(x, y), 1, 2,
						cellWidth, cellHeight));
				setPlace(id, x, y, x + 1, y + 1);
			} else {
				gizmos.put(id, new RightFlipper(id, new GizPoint(x, y), 1, 2,
						cellWidth, cellHeight));
				setPlace(id, x, y, x + 1, y + 1);
			}
			if (!loadingFile) {
				setChanged();
				notifyObservers(id);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addSquare(String id, int x, int y) {
		if (canPlace("", x, y, x, y)) {
			gizmos.put(id, new SquareBumper(id, new GizPoint(x, y), 1, 1,
					cellWidth, cellHeight));
			setPlace(id, x, y, x, y);
			if (!loadingFile) {
				setChanged();
				notifyObservers(id);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addTriangle(String id, int x, int y) {
		if (canPlace("", x, y, x, y)) {
			gizmos.put(id, new TriangleBumper(id, new GizPoint(x, y), 1, 1,
					cellWidth, cellHeight));
			setPlace(id, x, y, x, y);
			if (!loadingFile) {
				setChanged();
				notifyObservers(id);
			}
			return true;
		}
		return false;
	}

	public boolean rotateGizmo(String gizmoName) throws CannotRotateException {
		iGizmo tmp = getGizmo(gizmoName);
		if (tmp != null) {
			if ((tmp.getWidth() == 1 && tmp.getHeight() == 1)
					|| (tmp.getWidth() == 2 && tmp.getHeight() == 2)) {
				tmp.rotate(); // 90
				setChanged();
				notifyObservers(gizmoName);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean keyConnect(int keyNum, boolean direction, String consumer) {
		ArrayList<iGizmo> tmp;
		iGizmo con = getGizmo(consumer);
		if (con == null)
			return false;
		if (direction) {
			tmp = (ArrayList<iGizmo>) keyTriggersUp.get(keyNum);
			tmp.add(con);
			keyTriggersUp.put(keyNum, tmp);
			return true;
		} else {
			tmp = (ArrayList<iGizmo>) keyTriggersDown.get(keyNum);
			tmp.add(con);
			keyTriggersDown.put(keyNum, tmp);
			return true;
		}

	}

	@Override
	public boolean connect(String producerGizmo, String consumerGizmo) {
		iGizmo producer = getGizmo(producerGizmo);
		iGizmo consumer = getGizmo(consumerGizmo);
		if (producer == null || consumer == null)
			return false;
		producer.addTrigger(consumer);
		return true;
	}

	@Override
	public void loadGame(String mapName) {
		loadingFile = true;
		fileParse = new FileParser(this);
		fileParse.loadFile(mapName);
		fileParse = null;
		loadingFile = false;
	}

	@Override
	public void setBallLocation(String ballName, float x, float y) {
		balls.get(ballName).setLocation(new BallPoint(x, y));
		setChanged();
		notifyObservers(ballName);

	}

	@Override
	public void removeBall(String ballName) {
		iBall ball = balls.remove(ballName);
		if (ball != null) {
			for (iGizmo a : getGizmos()) {
				if (a instanceof Absorber) {
					((Absorber) a).removeStoredBall(ballName);
				}
			}
		}

	}

	@Override
	public boolean moveBall(String ballName, String absorberName, float x,
			float y) {
		iBall temp = getBall(ballName);
		iGizmo absorb = null;
		if (absorberName.length() > 0) {
			absorb = getGizmo(absorberName);
		}
		Vect v = temp.getVelocity();
		if (v.x() == 0.0 && v.y() == 0.0 && absorb != null) {
			if (canPlaceBall("A", x, y, x, y)) {
				temp.setLocation(new BallPoint(19, 19));
				removeFromBoard(ballName);
				((Absorber) absorb).captureBall(temp);
				// setPlace(ballName, (int)x, (int)y, (int)x, (int)y); //if the
				// ball is inside the absorber, dont place on map
				setChanged();
				notifyObservers(ballName);
				return true;
			}
		}
		if (canPlaceBall("", x, y, x, y)) {
			temp.setLocation(new BallPoint(x, y));
			removeFromBoard(ballName);
			setPlace(ballName, (int) x, (int) y, (int) x, (int) y); // we might
																	// not want
																	// the
																	// "ball" in
																	// the board
			setChanged();
			notifyObservers(ballName);
			return true;
		}
		return false;
	}

	@Override
	public float getGravity() {
		return gravity;
	}

	@Override
	public float getFrictionMu() {
		return mu;
	}

	@Override
	public float getFrictionMu2() {
		return mu2;
	}

	@Override
	public void moveAllGizmos() {
		for (iGizmo giz : getGizmos()) {
			giz.move();
		}

	}
	
	@Override
	public ArrayList<iGizmo> getGizmoDownKeytriggers(int keyCode) {
		
		return keyTriggersDown.get(keyCode);
	}

	@Override
	public ArrayList<iGizmo> getGizmoUpKeytriggers(int keyCode)  {
		return keyTriggersUp.get(keyCode);
	}

}
