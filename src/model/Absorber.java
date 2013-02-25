package model;

import java.util.ArrayList;
import java.util.List;

import exception.CannotRotateException;

import physics.LineSegment;

public class Absorber implements iGizmo {
	
	private List<iBall> storedBalls = null;
	private List<iGizmo> triggers = null;
	private String id;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	private List<LineSegment> boundSegments;


	public Absorber(String gizmoName, int x1, int y1, int x2, int y2) {
		id = gizmoName;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		height = Math.abs(y1 - y2);
		width = Math.abs(x1 - x2);
		calcBoundSegments();
	}

	private void calcBoundSegments(){
		boundSegments = new ArrayList<LineSegment>();
		boundSegments.add(new LineSegment(x1,y1,x2, y1));
		boundSegments.add(new LineSegment(x1, y1, x1, y2));
		boundSegments.add(new LineSegment(x2, y1, x2, y2));
		boundSegments.add(new LineSegment(x1, y2, x2, y2));
	}
	
	
	@Override
	public void doAction() {
		if(storedBalls.size() > 0){
		storedBalls.remove(0).setReleased(true);
		}
	}

	@Override
	public void setLocation(GizPoint p) {
		x1 = p.getX();
		y1 = p.getY();
	}

	@Override
	public GizPoint getLocation() {
		return new GizPoint(x1, y1);
	}

	public List<LineSegment> getBoundSegments() {
		// TODO Auto-generated method stub
		return boundSegments;
	}

	@Override
	public void addTrigger(iGizmo t) {
		if(triggers == null) triggers = new ArrayList<iGizmo>();
		triggers.add(t);
		
	}

	@Override
	public void removeTrigger(iGizmo t) {
		if(triggers.size() > 0){
			triggers.remove(t);
		}
		
	}

	@Override
	public List<iGizmo> getTriggers() {
		
		return triggers;
	}

	@Override
	public void rotate() throws CannotRotateException {
		throw new CannotRotateException("Absorbers cannot be rotated");
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void addBall(iBall ball){
		if(storedBalls == null) storedBalls = new ArrayList<iBall>();
		storedBalls.add(ball);
		ball.setReleased(false);
	}

	public void removeStoredBall(String ballName) {
		if(storedBalls.size() > 0){
			for(iBall ball : storedBalls){
				if(ball.getName().equals(ballName)){
					storedBalls.remove(ball);
					break;
				}
			}
		}
		
	}

	@Override
	public GizPoint getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
