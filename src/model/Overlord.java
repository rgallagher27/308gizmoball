package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Overlord implements IOverlord {
	
		private static float BALL_RADIUS = 0.25F;
		HashMap<String, iGizmo> gizmos;
		HashMap<Integer, List<iGizmo>> keyTriggersDown;
		HashMap<Integer, List<iGizmo>> keyTriggersUp;
		private FileParser fileParse;
		private float gravity;
		private float mu, mu2;
		String[][] board;
		private HashMap<String, iBall> balls;
	
		
		Overlord(int boardx, int boardy){
			gizmos = new HashMap<String, iGizmo>();
			balls = new HashMap<String, iBall>();
			keyTriggersDown = new HashMap<Integer, List<iGizmo>>();
			keyTriggersUp = new HashMap<Integer, List<iGizmo>>();
			board = new String[boardy][boardx]; // x along, y up
		}


		@Override
		public void removeGizmo(String gizmoName) {
			iGizmo gizRem = getGizmo(gizmoName);
			for(iGizmo giz : getGizmos()){
				giz.removeTrigger(gizRem);
			}
			for(Integer keyVal : keyTriggersDown.keySet()){
				keyTriggersDown.get(keyVal).remove(gizRem);
				
			}
			for(Integer keyVal : keyTriggersUp.keySet()){
				keyTriggersUp.get(keyVal).remove(gizRem);
			}
			gizmos.remove(gizmoName);
			removeFromBoard(gizmoName);
		}


		@Override
		public List<iGizmo> getGizmos() {
			return new ArrayList<iGizmo>(gizmos.values());
		}


		@Override
		public iGizmo getGizmo(String gizmoName) {
			return gizmos.get(gizmoName);
		}
		
		


		
		public boolean rotateGizmo(String gizmoName) {
			iGizmo tmp = getGizmo(gizmoName);
			if(tmp != null){
				if((tmp.getWidth() == 1 && tmp.getHeight() == 1) || (tmp.getWidth() == 2 && tmp.getHeight() == 2)){
					tmp.rotate();
					return true;
				}
			}
			return false;
		}


		@Override
		public void setGravity(float newGrav) {
			gravity = newGrav;
			
		}


		@Override
		public void setFriction(float mu, float mu2) {
			this.mu = mu;
			this.mu2 = mu2;
			
		}


		@Override
		public boolean keyConnect(int keyNum, boolean direction, String consumer) {
			ArrayList<iGizmo> tmp;
			iGizmo con = getGizmo(consumer);
			if(con == null) return false;
			if(direction) {
				tmp = (ArrayList<iGizmo>) keyTriggersUp.get(keyNum);
				tmp.add(con);
				keyTriggersUp.put(keyNum, tmp);
				return true;
			}else{
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
			if(producer == null || consumer == null) return false;
			producer.addTrigger(consumer);
			return true;
		}


		@Override
		public void loadGame(String mapName) {
			fileParse = new FileParser(this);
			fileParse.loadFile(mapName);
			fileParse = null;
		}


		@Override
		public void saveGame(String mapName) {
			
			
		}

		@Override
		public boolean addSquare(String gizmoName, int x, int y) {
			if(canPlace("", x, y, x, y)){
				gizmos.put(gizmoName, new Square(gizmoName, x, y));
				setPlace(gizmoName, x, y, x, y);
				return true;
			}
			return false;
		}





		@Override
		public boolean addCircle(String gizmoName, int x, int y) {
			if(canPlace("", x, y, x, y)){
			gizmos.put(gizmoName, new Circle(gizmoName, x, y));
			setPlace(gizmoName, x, y, x, y);
			return true;
			}
			return false;
		}





		@Override
		public boolean addTriangle(String gizmoName, int x, int y) {
			if(canPlace("", x, y, x, y)){
			gizmos.put(gizmoName, new Triangle(gizmoName, x, y));
			setPlace(gizmoName, x, y, x, y);
			return true;
			}
			return false;
		}





		@Override
		public boolean addFlipper(String gizmoName, int x, int y, boolean orient) {
			if(canPlace("", x, y, x+1, y+1)){
			if(orient){
				gizmos.put(gizmoName, new RightFlipper(gizmoName, x, y));
				setPlace(gizmoName, x, y, x+1, y+1);
			}else{
				gizmos.put(gizmoName, new LeftFlipper(gizmoName, x, y));
				setPlace(gizmoName, x, y, x+1, y+1);
			}
			return true;
			}
			return false;
		}



		private boolean canPlace(String ex, int startX, int startY, int endX, int endY){
			for(int y = startY; y < endY; y++){
				for(int x = startX; x < endX; x++){
					if(ex.length() > 0){
						if(!board[y][x].equals("") && !board[y][x].equals(ex)) return false;
					}else{
						if(!board[y][x].equals("")) return false;
					}
				}
			}
			return true;
		}
		private boolean canPlaceBall(String ex, float startX, float startY, float endX, float endY){
			for(int y = (int) Math.floor(startY); y < (int) Math.ceil(endY); y++){
				for(int x = (int) Math.floor(startX); x < (int) Math.ceil(endX); x++){
					if(ex.length() > 0){
						if(!board[y][x].equals("") && !board[y][x].substring(0, 1).equals(ex)) return false;
					}else{
						if(!board[y][x].equals("")) return false;
					}
				}
			}
			return true;
		}
		
		private void setPlace(String place, int startX, int startY, int endX, int endY){
			for(int y = startY; y < endY; y++){
				for(int x = startX; x < endX; x++){
					board[y][x] = place;
				}
			}
		}
		
		private void removeFromBoard(String toRemove){
			for(int x = 0; x < board[0].length; x++){
				for(int y = 0; y < board.length; y++){
					if(board[y][x].equals(toRemove)){
						board[y][x] = "";
					}
				}
			}
		}

		@Override
		public boolean addAbsorber(String gizmoName, int x1, int y1, int x2,
				int y2) {
			if(canPlace("", x1, y1, x2, y2)){
			gizmos.put(gizmoName, new Absorber(gizmoName, x1, y1, x2, y2));
			setPlace(gizmoName, x1, y1, x2, y2);
			return true;
			}
			return false;
		}





		@Override
		public boolean addBall(String ballName, String absorberName, float x, float y, double vx,
				double vy) {
			iGizmo absorb = null;
			if(absorberName.length() > 0){
				absorb = getGizmo(absorberName);
			}
			if(vx == 0.0 && vy == 0.0 && absorb != null){
				if(canPlaceBall("A", x, y, x, y)){
					balls.put(ballName, new Ball(ballName, ((float)(absorb.getLocation().getX() + absorb.getWidth()) - 0.25F), ((float)(absorb.getLocation().getY() + absorb.getHeight()) - 0.25F), vx, vy));
					setPlace(ballName, (int)x, (int)y, (int)x, (int)y); //we might not want the "ball" in the board
					((Absorber) absorb).addBall(getBall(ballName));
					/* DO NOT UPDATE OBSERVER AT THIS POINT, DONT DRAW UNDER ABSORBER */
					return true;
				}
			}
			if(canPlaceBall("", x, y, x, y)){
				balls.put(ballName, new Ball(ballName, x, y, vx, vy));
				setPlace(ballName, (int)x, (int)y, (int)x, (int)y);//we might not want the "ball" in the board
				/* update here */
				return true;
			}
			return false;
		}





		@Override
		public boolean moveGizmo(String gizmoName, int x, int y) {
				iGizmo temp = getGizmo(gizmoName);

				if(canPlace(gizmoName, x, y, x + (temp.getWidth()-1), y + (temp.getHeight()-1))){
					temp.setLocation(new Point(x, y));
					removeFromBoard(gizmoName);
					setPlace(gizmoName, x, y, x + (temp.getWidth()-1), y + (temp.getHeight()-1));
					return true;
				}
				return false;
			
		}


		@Override
		public boolean moveBall(String ballName, String absorberName, float x, float y) {
			iBall temp = getBall(ballName);
			iGizmo absorb = null;
			if(absorberName.length() > 0){
				absorb = getGizmo(absorberName);
			}
			if(temp.getVelocityX() == 0.0 && temp.getVelocityY() == 0.0 && absorb != null){
				if(canPlaceBall("A", x, y, x, y)){
					temp.setLocation(((float)(absorb.getLocation().getX() + absorb.getWidth()) - 0.25F), ((float)(absorb.getLocation().getY() + absorb.getHeight()) - 0.25F));
					removeFromBoard(ballName);
					((Absorber) absorb).addBall(temp);
					//setPlace(ballName, (int)x, (int)y, (int)x, (int)y); //if the ball is inside the absorber, dont place on map
					
					return true;
				}
			}
			if(canPlaceBall("", x, y, x, y)){
				temp.setLocation(x, y);
				removeFromBoard(ballName);
				setPlace(ballName, (int)x, (int)y, (int)x, (int)y); //we might not want the "ball" in the board
				return true;
			}
			return false;
		}


		@Override
		public void removeBall(String ballName) {
			iBall ball = balls.remove(ballName);
			if(ball != null){
				for(iGizmo a : getGizmos()){
					if(a instanceof Absorber){
						((Absorber) a).removeStoredBall(ballName);
					}
				}
			}
			
		}


		@Override
		public List<iBall> getBalls() {
			return new ArrayList<iBall>(balls.values());
		}


		@Override
		public iBall getBall(String ballName) {
			return balls.get(ballName);
		}


		@Override
		public List<String> getPossibleGizmoCollisions(float ballX, float ballY, boolean horizDirection,
				boolean vertDirection) {
			int x = (int) ballX;
			int y = (int) ballY;
			
			/* to be completed */
			
			return null;
		}
		
	

}
