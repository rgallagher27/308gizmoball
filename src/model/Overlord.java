package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Overlord implements IOverlord {
		HashMap<String, iGizmo> gizmos;
		
		private FileParser fileParse;
		private float gravity;
		private float mu, mu2;
	
		
		Overlord(){
			gizmos = new HashMap<String, iGizmo>();
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
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean connect(String producerGizmo, String consumerGizmo) {
			// TODO Auto-generated method stub
			return false;
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
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean addCircle(String gizmoName, int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean addTriangle(String gizmoName, int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean addFlipper(String gizmoName, int x, int y, boolean orient) {
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean addAbsorber(String gizmoName, int x1, int y1, int x2,
				int y2) {
			// TODO Auto-generated method stub
			return false;
		}





		@Override
		public boolean addBall(String gizmoName, float x, float y, float vx,
				float vy) {
			// TODO Auto-generated method stub
			return false;
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
