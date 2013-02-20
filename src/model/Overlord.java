package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Overlord implements IOverlord {
		HashMap<String, iGizmo> gizmos;
		HashMap<Integer, List<iGizmo>> keyTriggersDown;
		HashMap<Integer, List<iGizmo>> keyTriggersUp;
		private FileParser fileParse;
		private float gravity;
		private float mu, mu2;
		String[][] board;
	
		
		Overlord(int boardx, int boardy){
			gizmos = new HashMap<String, iGizmo>();
			keyTriggersDown = new HashMap<Integer, List<iGizmo>>();
			keyTriggersUp = new HashMap<Integer, List<iGizmo>>();
			board = new String[boardy][boardx]; // x along, y up
		}


		@Override
		public void removeGizmo(String gizmoName) {
			gizmos.remove(gizmoName);
			
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
		public boolean rotateGizmo(String gizmoName) {
			iGizmo tmp = getGizmo(gizmoName);
			if(tmp != null){
				tmp.rotate();
				return true;
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
			fileParse = new FileParser();
			fileParse.loadFile(mapName);
			
			
			
		}


		@Override
		public void saveGame(String mapName) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public List<String> getMapNames() {
			// TODO Auto-generated method stub
			return null;
		}





		@Override
		public boolean addSquare(String gizmoName, int x, int y) {
			if(canPlace(x, y, x, y)){
				gizmos.put(gizmoName, new Square(gizmoName, x, y));
				setPlace("S", x, y, x, y);
				return true;
			}
			return false;
		}





		@Override
		public boolean addCircle(String gizmoName, int x, int y) {
			if(canPlace(x, y, x, y)){
			gizmos.put(gizmoName, new Circle(gizmoName, x, y));
			setPlace("C", x, y, x, y);
			return true;
			}
			return false;
		}





		@Override
		public boolean addTriangle(String gizmoName, int x, int y) {
			if(canPlace(x, y, x, y)){
			gizmos.put(gizmoName, new Triangle(gizmoName, x, y));
			setPlace("T", x, y, x, y);
			return true;
			}
			return false;
		}





		@Override
		public boolean addFlipper(String gizmoName, int x, int y, boolean orient) {
			if(canPlace(x, y, x+1, y+1)){
			if(orient){
				gizmos.put(gizmoName, new RightFlipper(gizmoName, x, y));
				setPlace("RF", x, y, x+1, y+1);
			}else{
				gizmos.put(gizmoName, new LeftFlipper(gizmoName, x, y));
				setPlace("LF", x, y, x+1, y+1);
			}
			return true;
			}
			return false;
		}



		private boolean canPlace(int startX, int startY, int endX, int endY){
			for(int y = startY; y < endY; y++){
				for(int x = startX; x < endX; x++){
					if(!board[y][x].equals("")) return false;
				}
			}
			return true;
		}
		
		private void setPlace(String gizmo, int startX, int startY, int endX, int endY){
			for(int y = startY; y < endY; y++){
				for(int x = startX; x < endX; x++){
					board[y][x] = gizmo;
				}
			}
		}

		@Override
		public boolean addAbsorber(String gizmoName, int x1, int y1, int x2,
				int y2) {
			if(canPlace(x1, y1, x2, y2)){
			gizmos.put(gizmoName, new Absorber(gizmoName, x1, y1, x2, y2));
			setPlace("A", x1, y1, x2, y2);
			return true;
			}
			return false;
		}





		@Override
		public boolean addBall(String gizmoName, float x, float y, float vx,
				float vy) {
			
			gizmos.put(gizmoName, new Ball(gizmoName, x, y, vx, vy));
			return true;
		}





		@Override
		public boolean moveGizmo(String gizmoName, int x, int y) {
			
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean moveGizmo(String gizmoName, float x, float y) {
			// TODO Auto-generated method stub
			return false;
		}
		
	

}
