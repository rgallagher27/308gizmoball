package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import exception.CannotRotateException;

import model.physics.Vect;

public class Overlord extends Observable implements iOverlord {

	private Dimension gridDimentions, canvasDimentions;

	private HashMap<String, iGizmo> gizmos;
	private HashMap<Integer, ArrayList<iGizmo>> keyTriggersDown;
	private HashMap<Integer, ArrayList<iGizmo>> keyTriggersUp;
	private ArrayList<String> connects;
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
		connects = new ArrayList<String>();
		board = new String[gridDimentions.height][gridDimentions.width]; // x
																			// along,
																			// y
																			// up

		for (int x = 0; x < gridDimentions.width; x++) {
			for (int y = 0; y < gridDimentions.height; y++) {
				board[y][x] = "";
			}
		}

		gizmos.put("Wall", new Wall(cellWidth, cellHeight));

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
		connects.remove(gizmoName);
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
		ArrayList<iBall> tmp = new ArrayList<iBall>(balls.values());
		ArrayList<iBall> returned = new ArrayList<iBall>();
		for (iBall b : tmp) {
			if (!b.isCaptured())
				returned.add(b);
		}
		return returned;
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
	public void saveGame(String fileName) {
		fileParse = new FileParser(this);
		fileParse.saveFile(fileName);
		for (iGizmo ig : getGizmos()) {
			if (!(ig instanceof Wall)) {
				fileParse.saveGizmo(ig.toString());
				if (ig instanceof TriangleBumper) {
					for (int i = (int) ig.getRotation(); i > 0; i = (i - 90)) {
						fileParse.saveGizmo("Rotate " + ig.getIdentifier());
					}
				}
			}
		}
		for (String s : connects) {
			for (iGizmo g : getGizmo(s).getTriggers()) {
				fileParse.saveGizmo("Connect " + s + " " + g.getIdentifier());
			}
		}
		// map iterator code from
		// http://www.devmanuals.com/tutorials/java/corejava/Collection/Map/HashMap/GetKeyAndValue.html
		Set mapSet = (Set) keyTriggersDown.entrySet();
		Iterator mapIterator = mapSet.iterator();
		while (mapIterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) mapIterator.next();
			for (iGizmo g : (ArrayList<iGizmo>) mapEntry.getValue()) {
				fileParse.saveGizmo("KeyConnect Key " + mapEntry.getKey()
						+ " down " + g.getIdentifier());
			}
		}
		mapSet = (Set) keyTriggersUp.entrySet();
		mapIterator = mapSet.iterator();
		while (mapIterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) mapIterator.next();
			for (iGizmo g : (ArrayList<iGizmo>) mapEntry.getValue()) {
				fileParse.saveGizmo("KeyConnect Key " + mapEntry.getKey()
						+ " up " + g.getIdentifier());
			}
		}
		for (iBall b : getBalls()) {
			fileParse.saveGizmo(b.toString());
		}
		fileParse.closeSaveFile();
		fileParse = null;
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

		int x = (int) Math.floor(startX);
		int y = (int) Math.floor(startY);
		System.out.println("x: " + x + " y: " + y);

		if (ex.length() > 0) {
			System.out.println("t : " + !board[y][x].equals(""));
			System.out.println("t2: " + !board[y][x].equals(ex));
			if (!board[y][x].equals("") && !board[y][x].equals(ex)) {
				System.out.println("returning false");
				return false;
			}
		} else {
			System.out.println("t : " + !board[y][x].equals(""));
			System.out.println("t2: " + !board[y][x].equals(ex));
			if (!board[y][x].equals(""))
				return false;
		}

		return true;
	}

	private void setPlace(String place, int startX, int startY, int endX,
			int endY) {

		if (startY == endY && endX == startX) {
			board[startY][startX] = place;
			System.out.println("placed " + place);
		} else {

			if (startY != endY) {
				for (int y = startY; y < endY; y++) {
					for (int x = startX; x < endX; x++) {
						board[y][x] = place;
					}
				}
			} else {
				for (int x = startX; x < endX; x++) {
					board[startY][x] = place;
				}
				System.out.println("placed " + place);
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
		System.out.println("width: " + width + " height : " + height);
		if (canPlace("", x, y, (x + height - 1), (y + width - 1))) {
			gizmos.put(id, new Absorber(id, new GizPoint(x, y), width, height,
					cellWidth, cellHeight));
			setPlace(id, x, y, (x + (width - 1)), (y + (height - 1)));

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
		System.out.println("vx : " + vx + " vy: " + vy);
		if ((int) vx == 0 && (int) vy == 0 && absorb != null) {
			if (canPlaceBall(absorberName, x, y, x, y)) {
				System.out.println("placing in absorber");
				iBall newBall = new Ball(ballName, new BallPoint(x, y), 1, 1,
						cellWidth, cellHeight, vx, vy, true);

				newBall.setCaptured(true);
				balls.put(ballName, newBall);
				((Absorber) absorb).captureBall(newBall);
				return true;
			}
		} else {
			if (canPlaceBall("", x, y, x, y)) {
				iBall newBall = new Ball(ballName, new BallPoint(x, y), 1, 1,
						cellWidth, cellHeight, vx, vy, false);

				newBall.setCaptured(false);
				balls.put(ballName, newBall);
				setPlace(ballName, (int) x, (int) y, (int) x, (int) y);
				if (!loadingFile) {
					setChanged();
					notifyObservers(ballName);
				}
				return true;
			}
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
			if (tmp == null) {
				tmp = new ArrayList<iGizmo>();
			}
			tmp.add(con);
			keyTriggersUp.put(keyNum, tmp);
			return true;
		} else {
			tmp = (ArrayList<iGizmo>) keyTriggersDown.get(keyNum);
			if (tmp == null) {
				tmp = new ArrayList<iGizmo>();
			}
			tmp.add(con);
			keyTriggersDown.put(keyNum, tmp);
			return true;
		}

	}

	@Override
	public boolean connect(String producerGizmo, String consumerGizmo) {
		String[] tmp = new String[2];
		iGizmo producer = getGizmo(producerGizmo);
		iGizmo consumer = getGizmo(consumerGizmo);
		tmp[0] = producerGizmo;
		tmp[1] = consumerGizmo;
		if (producer == null || consumer == null)
			return false;
		producer.addTrigger(consumer);
		connects.add(producerGizmo);
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
		setChanged();
		notifyObservers();

	}

	@Override
	public ArrayList<iGizmo> getGizmoDownKeytriggers(int keyCode) {
		ArrayList<iGizmo> tmp = new ArrayList<iGizmo>();
		if (keyTriggersDown.get(keyCode) != null) {
			return keyTriggersDown.get(keyCode);
		}
		return tmp;
	}

	@Override
	public ArrayList<iGizmo> getGizmoUpKeytriggers(int keyCode) {
		ArrayList<iGizmo> tmp = new ArrayList<iGizmo>();
		if (keyTriggersUp.get(keyCode) != null) {
			return keyTriggersUp.get(keyCode);
		}
		return tmp;
	}

	public void resetGame() {
		for (iBall ball : balls.values()) {
			ball.setLocation(ball.getOrigLocation());
			System.out.println(ball.getIdentifier() + " : "
					+ ball.getOrigLocation().getX() + " - "
					+ ball.getOrigLocation().getY());
			ball.setVelocity(ball.getOrigVelocity());
			ball.setCaptured(ball.getOrigCapture());
		}
		for (iGizmo giz : getGizmos()) {
			if (giz instanceof Flipper) {
				giz.setRotation(0);
			}
		}
		setChanged();
		notifyObservers();

		for (int i = 0; i < 20; i++) {
			for (int y = 0; y < 20; y++) {
				System.out.print(board[i][y]);
			}
			System.out.println();
		}
	}

	public String getGizName(int x, int y) {
		if (board[y][x].equals("") || board[y][x].contains("B"))
			return "";

		return board[y][x];
	}

	public String getBallName(int x, int y) {
		for (iBall ball : balls.values()) {
			if (ball.getLocation().getX() == (float) x
					&& ball.getLocation().getY() == (float) y) {
				return ball.getIdentifier();
			}
		}
		return "";
	}

	public String getNextName(String name) {
		int maxNo = 0;
		int no;
		if (name.contains("B")) {
			for (iBall ball : balls.values()) {
				no = Integer.parseInt(ball.getIdentifier().substring(1));
				if (no > maxNo)
					maxNo = no;
			}
			return "B" + (maxNo + 1);
		} else {

			for (iGizmo giz : getGizmos()) {
				if (giz.getIdentifier().contains(name)) {
					if (giz.getIdentifier().length() != 1) {
						no = Integer.parseInt(giz.getIdentifier().substring(1));
						if (no > maxNo)
							maxNo = no;
					}
				}
			}
		}
		return name + (maxNo + 1);
	}
}
